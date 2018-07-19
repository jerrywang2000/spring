package com.tools.objectManagement.service;

import java.util.List;

import com.tools.objectManagement.entity.*;

public interface ObjectService {

	Object selectByPrimaryKey(String entityName, Integer recordId);
	
	List<?> selectAll(String entityName);
	
    int deleteByPrimaryKey(String entityName, Integer recordId);

    int insert(String entityName, Object record);
         
    int updateByPrimaryKey(String entityName, Object record);
}
