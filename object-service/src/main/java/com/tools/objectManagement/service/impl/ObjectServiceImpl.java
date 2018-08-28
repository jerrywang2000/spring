package com.tools.objectManagement.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tools.objectManagement.entity.*;
import com.tools.objectManagement.entity.mapper.*;
import com.tools.objectManagement.service.ObjectService;

import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import groovy.util.GroovyScriptEngine;

@Service
public class ObjectServiceImpl implements ObjectService{
//	@Autowired

//	private AbstractDao[] daos;
//	@Autowired
	private SqlSession sqlSession;
//	private SqlSessionFactory sqlSessionFactory; 
	
	private final static String MAPPER_CLASS_PATH = "com.tools.objectManagement.entity.mapper";
	private final static String EVENT_CLASS_PATH = "com.tools.objectManagement.event.impl";

	public ObjectServiceImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
 
	} 
	
	public SqlSession getSqlSession() {
		return this.sqlSession;
	}
	
    private ObjectMapper findMapper(String entityName) throws Exception {  

        return (ObjectMapper)sqlSession.getMapper(Class.forName(MAPPER_CLASS_PATH + "." + entityName + "Mapper"));
    } 
//	private Optional<AbstractDao> findDao(String entityName) {
//		return Arrays.stream(daos).filter(dao -> dao.isMatching(entityName)).findFirst();
//
//	}

    public int deleteByPrimaryKey(String entityName, Integer recordId) {
	   int returnRows = 0;
		
			if (!this.validateBeforeDelete(entityName, recordId)) {
				throw new RuntimeException("Validate failed before delete.");
			}
			
			ObjectMapper mapper = null;
			try {
				mapper = this.findMapper(entityName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        
			returnRows = mapper.deleteByPrimaryKey(recordId);
		
		return returnRows; 
    }

    private boolean validateBeforeDelete (String entityName, Integer recordId) {
    	boolean returnValue = true;
    	Class<?> eventClass;
    	Object eventObj;
    	Method validateMethod = null;
    	Boolean validationReturns = null;
		try {
			eventClass = Class.forName(EVENT_CLASS_PATH + "." + entityName + "BeforeDelete");		
			eventObj = eventClass.newInstance();
			validateMethod = eventClass.getDeclaredMethod("validate", SqlSession.class, Integer.class); 
			validationReturns = (Boolean)validateMethod.invoke(eventObj, sqlSession, recordId);
			returnValue = validationReturns.booleanValue();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	return returnValue;
    }
    
    private boolean validateBeforeInsert(String entityName) {
    	boolean returnValue = true; 
    	String filepath = null;
    	String filename = null;
    	String funname = null;
    	Object isValid = null;
        try {
        	filepath = "src/main/groovy/com/tools/objectManagement/event/impl/"+entityName+"Validation.groovy";
        	filename = entityName+"Validation.groovy";
        	funname = "validate";
        	System.out.println("Call before Groovy");
//            GroovyScriptEngine engine = new GroovyScriptEngine(this.getClass().getResource(filepath).getPath());
//        	Script script = engine.createScript(filename, new Binding());
        	Binding binding = new Binding();

//        	GroovyShell shell = new GroovyShell();
//        	Script script = shell.parse(new File(this.getClass().getResource(filepath).getPath()));
//        	Script script = shell.parse(new File(filepath));
//        	binding.setVariable("newConcat", script);
        	GroovyClassLoader classLoader = new GroovyClassLoader();
            Class groovy = classLoader.parseClass(new File(filepath));
            GroovyObject groovyObj = (GroovyObject) groovy.newInstance();
            isValid = groovyObj.invokeMethod("validate", new Object[] { "" });
            returnValue = ((Boolean)isValid).booleanValue();
            Object[] args = {};
//            script.invokeMethod("test", new Object[] { "test" });
//            isValid = script.invokeMethod(funname, new Object());
//            isValid = script.evaluate("newConcat."+funname+"(a)");
            System.out.println("Call after Groovy");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return returnValue;
    }

    public int insert(String entityName, Object entity) {
    	int count = 0;
    	
//		try {
			if (!this.validateBeforeInsert(entityName)) {
				throw new RuntimeException("Validate failed before insert.");
			}		
			
			ObjectMapper mapper = null;
			try {
				mapper = this.findMapper(entityName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			count = mapper.insertSelective(entity);
//			this.deleteByPrimaryKey("Customer", new Integer(300));
//		} catch (RuntimeException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			throw e;
//		}
    	
    	return count;
    }
    
//    public Record selectByPrimaryKey(String entityName, String recordId) {
//    	Record record;
//    	record = dao.selectByPrimaryKey(recordId);
//    	return record;
//    }
    
	   public List<?> selectAll(String entityName) {
		   List<?> list = null;
			try {
				ObjectMapper mapper = this.findMapper(entityName);
	        
				list = mapper.selectByExample(null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		   return list;
	   }
	   public Object selectByPrimaryKey(String entityName, Integer recordId) {
		   Object object = null;
			try {
				ObjectMapper mapper = this.findMapper(entityName);
	        
				object = mapper.selectByPrimaryKey(recordId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return object;
	   }
//    public List<?> selectAll(String entityName) {
//    	Class<?> daoClass;
//    	Object daoObj;
//    	Method selectAllMethod = null;
//    	List<?> list = null;
//		try {
//			daoClass = Class.forName(MAPPER_CLASS_PATH + "." + entityName);		
//	    	daoObj = daoClass.newInstance();
//	    	selectAllMethod = daoClass.getDeclaredMethod("selectAll");
//	    	list = (List<?>) selectAllMethod.invoke(daoObj);
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InstantiationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (NoSuchMethodException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SecurityException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalArgumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InvocationTargetException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    	return list;
//    	//return findDao(entityName).get().someMethod().selectAll();
//    }
    
    public int updateByPrimaryKey(String entityName, Object record) {
		   int returnRows = 0;
			try {
				ObjectMapper mapper = this.findMapper(entityName);
				System.out.println("-----------------"+entityName);
				returnRows = mapper.updateByPrimaryKeySelective(record);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return returnRows;   	
    }
//
//    @Bean
//    public DataSourceTransactionManager transactionManager() {
//        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
//        transactionManager.setDataSource(dataSource);
//        return transactionManager;
//    }

}

