package com.tools.setup.service;

import java.util.List;

import com.tools.setup.entity.RecordField;

public interface RecordFieldService {

	RecordField selectByPrimaryKey(Integer id);
	
	List<RecordField> selectAll();
	
	List<RecordField> selectByRecordId(String recordId);
	
    int deleteByPrimaryKey(Integer id);

    int insert(RecordField record);
           
    int updateByPrimaryKey(RecordField record);
    
    String deploy(String recordId);
}
