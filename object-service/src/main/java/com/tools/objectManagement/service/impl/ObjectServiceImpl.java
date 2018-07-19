package com.tools.objectManagement.service.impl;

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
import org.springframework.stereotype.Service;

import com.tools.objectManagement.entity.*;
import com.tools.objectManagement.entity.mapper.*;
import com.tools.objectManagement.service.ObjectService;

@Service
public class ObjectServiceImpl implements ObjectService{
//	@Autowired

//	private AbstractDao[] daos;
//	@Autowired
	private SqlSession sqlSession;
//	private SqlSessionFactory sqlSessionFactory; 
	
	private final static String MAPPER_CLASS_PATH = "com.tools.objectManagement.entity.mapper";

	public ObjectServiceImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
//        String resource = "myBatisConfig.xml"; 
//        InputStream inputStream = null;
//		try {
//			inputStream = Resources.getResourceAsStream(resource);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}  
//        //使用SqlSessionFactoryBuilder创建sessionFactory  
//        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream); 
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
		try {
			ObjectMapper mapper = this.findMapper(entityName);
        
			returnRows = mapper.deleteByPrimaryKey(recordId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnRows; 
    }

    public int insert(String entityName, Object entity) {
    	int count = 0;
    	
		try {
			ObjectMapper mapper = this.findMapper(entityName);
			count = mapper.insertSelective(entity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
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



}

