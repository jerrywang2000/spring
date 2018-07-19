package com.tools.setup.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tools.setup.dao.RecordDao;
import com.tools.setup.entity.Record;
import com.tools.setup.service.RecordService;

@Service
public class RecordServiceImpl implements RecordService{
	@Autowired
	private RecordDao dao;
    public int deleteByPrimaryKey(String recordId) {
    	int count;
    	count = dao.deleteByPrimaryKey(recordId);
    	return count;
    }

    public int insert(Record record) {
    	int count;
    	count = dao.insert(record);
    	return count;
    }
    
    public Record selectByPrimaryKey(String recordId) {
    	Record record;
    	record = dao.selectByPrimaryKey(recordId);
    	return record;
    }
    
    public List<Record> selectAll() {
    	return dao.selectAll();
    }
    
    public int updateByPrimaryKey(Record record) {
    	int count = dao.updateByPrimaryKeySelective(record);
    	return count;    	
    }
    

}

