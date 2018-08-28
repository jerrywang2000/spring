package com.tools.objectManagement.aspect;

import java.util.Arrays;
import java.util.LinkedHashMap;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import com.tools.objectManagement.service.impl.ObjectServiceImpl;
import org.apache.ibatis.session.SqlSession;


@Aspect
@Component
public class EventAspect {

	@Pointcut("execution(public * com.tools.objectManagement.service.*.updateByPrimaryKey(..))")
	public void updateAspect() {
	}

	@Before("updateAspect()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		SqlSession sqlSession = ((ObjectServiceImpl)joinPoint.getThis()).getSqlSession();
		try {
		    Object result = sqlSession.selectOne("com.tools.objectManagement.entity.mapper.LocationMapper.selectByPrimaryKey", new Integer(100));
		    System.out.println("after select");
		    if (result!=null) {
		    	System.out.println(result);
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "."
				+ joinPoint.getSignature().getName());
		System.out.println("ARGS : " + Arrays.toString(joinPoint.getArgs()));
		System.out.println(joinPoint.getArgs()[0].toString().toLowerCase()+"Id");
		System.out.println(((LinkedHashMap)joinPoint.getArgs()[1]).get(joinPoint.getArgs()[0].toString().toLowerCase()+"Id"));
		if (((LinkedHashMap)joinPoint.getArgs()[1]).get(joinPoint.getArgs()[0].toString().toLowerCase()+"Id").toString().equals("100")) {
			throw new Exception ("cannot update this row.");
		}

	}

	@AfterReturning(returning = "ret", pointcut = "updateAspect()")
	public void doAfterReturning(Object ret) throws Throwable {
		System.out.println("after processed return: " + ret);
	}

	@AfterThrowing("updateAspect()")
	public void throwss(JoinPoint jp) {
		System.out.println("exception processed.....");
	}

	@After("updateAspect()")
	public void after(JoinPoint jp) {
		System.out.println("after processed.....");
	}

	@Around("updateAspect()")
	public Object arround(ProceedingJoinPoint pjp) {
		System.out.println("arround start.....");
		try {
			Object o = pjp.proceed();
			System.out.println("arround processed，returned :" + o);
			return o;
		} catch (Throwable e) {
			e.printStackTrace();
			return null;
		}
	}

}
