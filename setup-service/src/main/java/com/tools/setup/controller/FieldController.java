package com.tools.setup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tools.setup.entity.Field;
import com.tools.setup.service.FieldService;

@RestController
public class FieldController {

	@Autowired
	private FieldService fieldService;
	
	@RequestMapping(value="/fields",method=RequestMethod.GET)
	public List<Field> getRecords(){
		return fieldService.selectAll();
	}
	
	@RequestMapping(value="/fields/{id}",method=RequestMethod.GET)
	public Field getRecordByPrimaryKey(@PathVariable String id){
		return fieldService.selectByPrimaryKey(id);
	}
	
	@RequestMapping(value="/fields", method=RequestMethod.POST)
	public Field addRecord(@RequestBody Field input) {
		int count = this.fieldService.insert(input);
		if (count == 1) {
			return input;
		} else {
			return null;
		}
		
	}
	
	@RequestMapping(value="/fields/{id}",method=RequestMethod.PATCH)
	public Field updateRecordByPrimaryKey(@PathVariable String id, @RequestBody Field input){
		int count;
		Field rec = fieldService.selectByPrimaryKey(id);
		if (rec != null) {
			input.setFieldId(id);
			count = fieldService.updateByPrimaryKey(input);			
			if (count == 1) {
				rec = fieldService.selectByPrimaryKey(id);
			}
		}
		return rec;
	}
	
	@RequestMapping(value="/fields/{id}",method=RequestMethod.DELETE)
	public int deleteRecordByPrimaryKey(@PathVariable String id){
		return fieldService.deleteByPrimaryKey(id);
	}
}
