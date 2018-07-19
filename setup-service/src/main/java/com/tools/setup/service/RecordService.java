package com.tools.setup.service;

import java.util.List;

import com.tools.setup.entity.Record;

public interface RecordService {

	Record selectByPrimaryKey(String recordId);
	
	List<Record> selectAll();
	
    int deleteByPrimaryKey(String recordId);
    
    int insert(Record record);
           
    int updateByPrimaryKey(Record record);
    
    
}
