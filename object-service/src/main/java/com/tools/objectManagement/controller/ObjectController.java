package com.tools.objectManagement.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tools.objectManagement.entity.*;
import com.tools.objectManagement.service.ObjectService;

@RestController
public class ObjectController {

	@Autowired
	private ObjectService objectService;
	
	private final static String ENTITY_CLASS_PATH = "com.tools.objectManagement.entity";
	
	@RequestMapping(value="/{apiName}",method=RequestMethod.GET)
	public List<?> getRecords(@PathVariable String apiName){
		return objectService.selectAll(apiName);
	}
	
//	@RequestMapping(value="/{apiName}/{id}",method=RequestMethod.GET)
	@GetMapping(value = "/{apiName}/{id}", produces = MediaTypes.HAL_JSON_VALUE)
	public Resource<BaseEntity> getRecordByPrimaryKey(@PathVariable String apiName, @PathVariable Integer id){
		BaseEntity rec = (BaseEntity)objectService.selectByPrimaryKey(apiName, id);
		rec.add(linkTo(methodOn(ObjectController.class).getRecordByPrimaryKey(apiName, id)).withSelfRel());

		return new Resource<BaseEntity>(rec);
	}
	
	@RequestMapping(value="/{apiName}", method=RequestMethod.POST)
	public Object addRecord(@PathVariable String apiName, @RequestBody Object input) {
		int count = this.objectService.insert(apiName, input);
		if (count == 1) {
			return input;
		} else {
			return null;
		}
		
	}
	
	@RequestMapping(value="/{apiName}/{id}",method=RequestMethod.PATCH)
	public Object updateRecordByPrimaryKey(@PathVariable String apiName, @PathVariable String id, @RequestBody Object input){
		int count;
		Object rec = objectService.selectByPrimaryKey(apiName, Integer.valueOf(id));
		
		if (rec != null) {
			
	    	Class<?> entityClass;
	    	Object entity;
	    	Method setId = null;
	    	Set keys = ((LinkedHashMap)input).keySet();
	    	keys.forEach(System.out::println); 

	    	((LinkedHashMap)input).put(apiName.toLowerCase()+"Id", Integer.valueOf(id));

	    	/*
			try {
				entityClass = Class.forName(ENTITY_CLASS_PATH + "." + apiName);		
				entity = entityClass.newInstance();
				Class[] cArg = new Class[1];
				cArg[0] = Integer.class;
//				Object[] oPara = {Integer.valueOf(id)};
				setId = entityClass.getDeclaredMethod("set"+apiName+"Id", cArg);
		    	setId.invoke(entity, Integer.valueOf(id));

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			*/
//			input.setId(Integer.valueOf(id));
			count = objectService.updateByPrimaryKey(apiName, input);			
			if (count == 1) {
				rec = objectService.selectByPrimaryKey(apiName, Integer.valueOf(id));
			}
		}
		return rec;
	}
	
	@RequestMapping(value="/{apiName}/{id}",method=RequestMethod.DELETE)
	public int deleteRecordByPrimaryKey(@PathVariable String apiName, @PathVariable String id){
		int count = objectService.deleteByPrimaryKey(apiName, Integer.valueOf(id));
		
		return count;
	}
	
	@RequestMapping(value="/deployment", method=RequestMethod.POST)
	public String deployRecord(@RequestBody Object input) {
		String sql = ((LinkedHashMap)input).get("sql").toString();
		System.out.println(sql);
		return "Deployed";
		
	}
}
