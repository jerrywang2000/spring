package com.tools.objectManagement.event;

import org.apache.ibatis.session.SqlSession;

public interface EntityBeforeDelete {

	public boolean validate (SqlSession sqlSession, Integer recordId);
	
	public boolean beforeDelete (SqlSession sqlSession, Integer recordId);
	
	public boolean afterDelete (SqlSession sqlSession, Integer recordId);
}
