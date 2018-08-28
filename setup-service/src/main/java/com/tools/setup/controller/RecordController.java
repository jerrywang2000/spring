package com.tools.setup.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.tools.setup.entity.Record;
import com.tools.setup.entity.User;
import com.tools.setup.service.RecordService;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import javax.validation.Valid;

@RestController
public class RecordController {

	@Autowired
	private RecordService recordService;	
	

//	@RequestMapping(value="/records",method=RequestMethod.GET)
	@GetMapping(value = "/records", produces = MediaTypes.HAL_JSON_VALUE)
	public HttpEntity<Resources<Record>> getRecords(){
//		return recordService.selectAll();
		Resources<Record> resources = new Resources<>(recordService.selectAll());
		resources.add(linkTo(methodOn(RecordController.class).getRecords()).withSelfRel());
		return new ResponseEntity<>(resources, HttpStatus.OK);
	}
	
//	@RequestMapping(value="/records/{id}",method=RequestMethod.GET)
	@GetMapping(value = "/records/{id}", produces = MediaTypes.HAL_JSON_VALUE)
	public Resource<Record> getRecordByPrimaryKey(@PathVariable String id){
		Record rec = recordService.selectByPrimaryKey(id);
		rec.add(linkTo(methodOn(RecordController.class).getRecordByPrimaryKey(id)).withSelfRel());

		return new Resource<Record>(rec);
	}
	
	@RequestMapping(value="/records", method=RequestMethod.POST)
	public Record addRecord(@RequestBody Record input) {
		int count = this.recordService.insert(input);
		if (count == 1) {
			return input;
		} else {
			return null;
		}
		
	}
	
	@RequestMapping(value="/records/{id}",method=RequestMethod.PATCH)
	public Record updateRecordByPrimaryKey(@PathVariable String id, @RequestBody Record input){
		int count;
		Record rec = recordService.selectByPrimaryKey(id);
		if (rec != null) {
			input.setRecordId(id);
			count = recordService.updateByPrimaryKey(input);			
			if (count == 1) {
				rec = recordService.selectByPrimaryKey(id);
			}
		}
		return rec;
	}
	
	@RequestMapping(value="/records/{id}",method=RequestMethod.DELETE)
	public int deleteRecordByPrimaryKey(@PathVariable String id){
		int count = recordService.deleteByPrimaryKey(id);
		
		return count;
	}
	
	@RequestMapping(path = "/users", method = RequestMethod.POST)
	public String createNewAccount(@Valid @RequestBody User user) {
		return recordService.create(user);
	}
}
