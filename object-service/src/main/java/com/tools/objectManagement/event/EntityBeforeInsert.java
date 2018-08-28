package com.tools.objectManagement.event;

import org.apache.ibatis.session.SqlSession;

public interface EntityBeforeInsert {

	public boolean validate (SqlSession sqlSession, Integer recordId);
	
	public boolean beforeInsert (SqlSession sqlSession, Integer recordId);
	
	public boolean afterInsert (SqlSession sqlSession, Integer recordId);
}
