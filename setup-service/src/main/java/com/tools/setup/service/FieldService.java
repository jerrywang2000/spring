package com.tools.setup.service;

import java.util.List;

import com.tools.setup.entity.Field;

public interface FieldService {

	Field selectByPrimaryKey(String fieldId);
	
	List<Field> selectAll();
	
    int deleteByPrimaryKey(String fieldId);

    int insert(Field field);
           
    int updateByPrimaryKey(Field field);
}
