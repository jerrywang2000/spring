package com.tools.objectManagement.event.impl;


import org.apache.ibatis.session.SqlSession;

import com.tools.objectManagement.event.EntityBeforeDelete;

public class CustomerBeforeDelete implements EntityBeforeDelete {

	
	public CustomerBeforeDelete() {
		
	}
	
	public boolean validate (SqlSession sqlSession, Integer recordId) {
		boolean returnValue = false;
		System.out.println("In validate of CustomerBeforeDelete");
		try {
		    Object result = sqlSession.selectOne("com.tools.objectManagement.entity.mapper.LocationMapper.selectByPrimaryKey", new Integer(100));
		    System.out.println("after select");
		    if (result!=null) {
		    	returnValue = true;
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
	    System.out.println(returnValue);
		return returnValue;
	}
	
	public boolean beforeDelete (SqlSession sqlSession, Integer recordId) {
		return true;
	}
	
	public boolean afterDelete (SqlSession sqlSession, Integer recordId) {
		return true;
	}
}
