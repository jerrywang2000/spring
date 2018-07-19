package com.tools.setup.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tools.setup.client.ObjectServiceClient;
import com.tools.setup.dao.RecordFieldDao;
import com.tools.setup.entity.RecordField;
import com.tools.setup.service.RecordFieldService;

@Service
public class RecordFieldServiceImpl implements RecordFieldService{
	@Autowired
	private RecordFieldDao dao; 
	
	@Autowired
	private ObjectServiceClient objectService;
	
    public int deleteByPrimaryKey(Integer id) {
    	int count;
    	count = dao.deleteByPrimaryKey(id);
    	return count;
    }

    public int insert(RecordField record) {
    	int count;
    	count = dao.insert(record);
    	return count;
    }
    
    public RecordField selectByPrimaryKey(Integer id) {
    	RecordField record;
    	record = dao.selectByPrimaryKey(id);
    	return record;
    }
    
    public List<RecordField> selectAll() {
    	return dao.selectAll();
    }
    
    public List<RecordField> selectByRecordId(String recordId) {
    	return dao.selectByRecordId(recordId);
    }
    
    public int updateByPrimaryKey(RecordField record) {
    	int count = dao.updateByPrimaryKeySelective(record);
    	return count;    	
    }
    
    public String deploy(String recordId) {
    	String sql = "";
    	String tableName = "";
    	String pkFields = "";
    	List<RecordField> fields = dao.selectByRecordId(recordId);
    	for (RecordField field : fields) {
    		if ("".equals(tableName)) {
    			tableName = field.getRecordId();
    			sql = "CREATE TABLE "+tableName + "(\n";
    		}
    		sql += field.getFieldId()+ " " + field.getFieldType(); 
    		if ("VARCHAR".equalsIgnoreCase(field.getFieldType())) {
    			sql += "("+field.getLength()+")"; 
    		}
    		if ("Y".equalsIgnoreCase(field.getIsRequired())) {
    			sql += " NOT NULL,\n"; 
    		} else {
    			sql += " NULL,\n"; 
    		}
    		if ("Y".equalsIgnoreCase(field.getIsPk())) {
    			if ("".equals(pkFields)) {
    				pkFields = field.getFieldId();
    			} else {
    				pkFields += ","+ field.getFieldId();
    			}
    		}
    	}
    	sql += "CREATED_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),\n";
    	sql += "CREATED_BY VARCHAR(40) NOT NULL DEFAULT 'ADMIN',\n";
    	sql += "LAST_UPDATED_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),\n";
    	sql += "LAST_UPDATED_BY VARCHAR(40) NOT NULL DEFAULT 'ADMIN',\n";    	      	   
    	   
    	sql += "PRIMARY KEY ("+pkFields+")\n";
    	sql += ")ENGINE=InnoDB DEFAULT CHARSET=utf8;\n";
    	
    	LinkedHashMap<String, String> payload = new LinkedHashMap<String, String>();
		payload.put("sql", sql);
    	
    	return objectService.deployRecord(payload);
    }
    

}

