package com.tools.setup.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tools.setup.dao.FieldDao;
import com.tools.setup.entity.Field;
import com.tools.setup.service.FieldService;

@Service
public class FieldServiceImpl implements FieldService{
	@Autowired
	private FieldDao dao;
    public int deleteByPrimaryKey(String fieldId) {
    	int count;
    	count = dao.deleteByPrimaryKey(fieldId);
    	return count;
    }

    public int insert(Field field) {
    	int count;
    	count = dao.insert(field);
    	return count;
    }
    
    public Field selectByPrimaryKey(String fieldId) {
    	Field field;
    	field = dao.selectByPrimaryKey(fieldId);
    	return field;
    }
    
    public List<Field> selectAll() {
    	return dao.selectAll();
    }
    
    public int updateByPrimaryKey(Field field) {
    	int count = dao.updateByPrimaryKeySelective(field);
    	return count;    	
    }
}

