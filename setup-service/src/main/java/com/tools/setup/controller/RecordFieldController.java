package com.tools.setup.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tools.setup.client.ObjectServiceClient;
import com.tools.setup.entity.RecordField;
import com.tools.setup.service.RecordFieldService;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@RestController
public class RecordFieldController {

	@Autowired
	private RecordFieldService recordFieldService;	


	
//	@RequestMapping(value="/records",method=RequestMethod.GET)
	@GetMapping(value = "/recordFields", produces = MediaTypes.HAL_JSON_VALUE)
	public List<RecordField> getRecordFields(){
		return recordFieldService.selectAll();		
	}
	
	@GetMapping(value = "/recordFields/{recordId}", produces = MediaTypes.HAL_JSON_VALUE)
	public List<RecordField> getRecordFieldsByRecordId(@PathVariable String recordId){
		
		return recordFieldService.selectByRecordId(recordId);
	}
	
//	@RequestMapping(value="/records/{id}",method=RequestMethod.GET)
	@GetMapping(value = "/recordFields/{recordId}/{id}", produces = MediaTypes.HAL_JSON_VALUE)
	public Resource<RecordField> getRecordByPrimaryKey(@PathVariable String recordId, @PathVariable Integer id){
		RecordField rec = recordFieldService.selectByPrimaryKey(id);
		rec.add(linkTo(methodOn(RecordFieldController.class).getRecordByPrimaryKey(recordId, id)).withSelfRel());

		return new Resource<RecordField>(rec);
	}
	
	@RequestMapping(value="/recordFields", method=RequestMethod.POST)
	public RecordField addRecordField(@RequestBody RecordField input) {
		int count = this.recordFieldService.insert(input);
		if (count == 1) {
			return input;
		} else {
			return null;
		}
		
	}
	
	@RequestMapping(value="/recordFields/{id}",method=RequestMethod.PATCH)
	public RecordField updateRecordByPrimaryKey(@PathVariable Integer id, @RequestBody RecordField input){
		int count;
		RecordField rec = recordFieldService.selectByPrimaryKey(id);
		if (rec != null) {
			input.setRecordFieldId(id);
			count = recordFieldService.updateByPrimaryKey(input);			
			if (count == 1) {
				rec = recordFieldService.selectByPrimaryKey(id);
			}
		}
		return rec;
	}
	
	@RequestMapping(value="/recordFields/{id}",method=RequestMethod.DELETE)
	public int deleteRecordByPrimaryKey(@PathVariable Integer id){
		int count = recordFieldService.deleteByPrimaryKey(id);
		
		return count;
	}
	
	@RequestMapping(value="/recordFields/{recordId}", method=RequestMethod.POST)
	public Object deployRecord(@PathVariable String recordId, @RequestBody Object input) {
				
		return recordFieldService.deploy(recordId);
		
	}
}
