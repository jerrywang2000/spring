package com.tools.objectManagement.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tools.objectManagement.entity.*;
import com.tools.objectManagement.service.ObjectService;
import com.tools.objectManagement.service.impl.ObjectGenerator;

@RestController
public class ObjectController {

	@Autowired
	private ObjectService objectService;

	private final static String ENTITY_CLASS_PATH = "com.tools.objectManagement.entity";
	private final static String CUSTOMACTION_CLASS_PATH = "com.tools.objectManagement.service.custom";
	
	@RequestMapping(value = "/{apiName}", method = RequestMethod.GET)
	public List<?> getRecords(@PathVariable String apiName) {
		return objectService.selectAll(apiName);
	}

	// @RequestMapping(value="/{apiName}/{id}",method=RequestMethod.GET)
	@GetMapping(value = "/{apiName}/{id}", produces = MediaTypes.HAL_JSON_VALUE)
	public Resource<BaseEntity> getRecordByPrimaryKey(@PathVariable String apiName, @PathVariable Integer id) {
		BaseEntity rec = (BaseEntity) objectService.selectByPrimaryKey(apiName, id);
		rec.add(linkTo(methodOn(ObjectController.class).getRecordByPrimaryKey(apiName, id)).withSelfRel());

		return new Resource<BaseEntity>(rec);
	}

	@RequestMapping(value = "/{apiName}", method = RequestMethod.POST)
	public Object addRecord(@PathVariable String apiName, @RequestBody Object input) {
		int count = 0;
		try {
			count = this.objectService.insert(apiName, input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (count == 1) {
			return input;
		} else {
			return null;
		}

	}

	@RequestMapping(value = "/{apiName}/{id}", method = RequestMethod.PATCH)
	public Object updateRecordByPrimaryKey(@PathVariable String apiName, @PathVariable String id,
			@RequestBody Object input) {
		int count;
		Object rec = objectService.selectByPrimaryKey(apiName, Integer.valueOf(id));

		if (rec != null) {

			Class<?> entityClass;
			Object entity;
			Method setId = null;
			Set keys = ((LinkedHashMap) input).keySet();
			keys.forEach(System.out::println);

			((LinkedHashMap) input).put(apiName.toLowerCase() + "Id", Integer.valueOf(id));

			/*
			 * try { entityClass = Class.forName(ENTITY_CLASS_PATH + "." + apiName); entity
			 * = entityClass.newInstance(); Class[] cArg = new Class[1]; cArg[0] =
			 * Integer.class; // Object[] oPara = {Integer.valueOf(id)}; setId =
			 * entityClass.getDeclaredMethod("set"+apiName+"Id", cArg); setId.invoke(entity,
			 * Integer.valueOf(id));
			 * 
			 * } catch (ClassNotFoundException e) { // TODO Auto-generated catch block
			 * e.printStackTrace(); } catch (IllegalAccessException e) { // TODO
			 * Auto-generated catch block e.printStackTrace(); } catch
			 * (IllegalArgumentException e) { // TODO Auto-generated catch block
			 * e.printStackTrace(); } catch (InvocationTargetException e) { // TODO
			 * Auto-generated catch block e.printStackTrace(); } catch
			 * (NoSuchMethodException e) { // TODO Auto-generated catch block
			 * e.printStackTrace(); } catch (SecurityException e) { // TODO Auto-generated
			 * catch block e.printStackTrace(); } catch (InstantiationException e) { // TODO
			 * Auto-generated catch block e.printStackTrace(); }
			 */
			// input.setId(Integer.valueOf(id));
			count = objectService.updateByPrimaryKey(apiName, input);
			if (count == 1) {
				rec = objectService.selectByPrimaryKey(apiName, Integer.valueOf(id));
			}
		}
		return rec;
	}

	@RequestMapping(value = "/{apiName}/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteRecordByPrimaryKey(@PathVariable String apiName, @PathVariable String id) {
		String count = "";
		try {
			objectService.deleteByPrimaryKey(apiName, Integer.valueOf(id));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Exception happened during deleting.");
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.OK).body("");
	}

	@PreAuthorize("#oauth2.hasScope('server')")
	@RequestMapping(value = "/deployment", method = RequestMethod.POST)
	public String deployRecord(@RequestBody Object input) {
		String sql = ((LinkedHashMap) input).get("sql").toString();
		String objectId = ((LinkedHashMap) input).get("recordId").toString();
		ObjectGenerator generator = new ObjectGenerator();
		new Thread() {
			public void run() {
				try {
					generator.generateObject(objectId);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();

		System.out.println(sql);
		return "Deployed";

	}
	@RequestMapping(value = "/{apiName}/customAction", method = RequestMethod.POST)
	public Object postCustomAction(@PathVariable String apiName, @RequestBody Object input) {
		String methodName= "";
		ObjectMapper objectMapper = new ObjectMapper();
		Parameter[] paraArray = null;
//			JsonNode node = objectMapper.readTree(((LinkedHashMap)input).toString());
		methodName = ((LinkedHashMap)input).get("name").toString();
		List array = (ArrayList)((LinkedHashMap)input).get("parameters");
		if (array != null) {
			paraArray = new Parameter[array.size()];
			for (int i = 0; i < array.size(); i++) {
				Parameter para = new Parameter();
				para.setSequence(((Integer)((LinkedHashMap)array.get(i)).get("sequence")).intValue());
				para.setType((((LinkedHashMap)array.get(i)).get("type")).toString());
				para.setValue((((LinkedHashMap)array.get(i)).get("value")).toString());
				paraArray[i] = para;
			}
		}
		Arrays.sort(paraArray);
		
		Class[] inputParaClass = new Class[paraArray.length];
		Object[] inputValue = new Object[paraArray.length];
		for (int i = 0; i < paraArray.length; i++) {
//			try {
//				inputParaClass[i] = Class.forName(paraArray[i].getType());
//				inputValue[i] = paraArray[i].getValue();
//			} catch (ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			switch (paraArray[i].getType().toLowerCase()) {
				case "integer":
					inputParaClass[i] = Integer.class;
					inputValue[i] = new Integer(paraArray[i].getValue());
					break;
				case "int":
					inputParaClass[i] = Integer.class;
					inputValue[i] = new Integer(paraArray[i].getValue());
					break;
				case "string":
					inputParaClass[i] = String.class;
					inputValue[i] = new String(paraArray[i].getValue());
					break;
			}
		}
		
		
		
		Class<?> customActionClass;
    	Object customActionObj;
    	Method method = null;
    	Object methodReturns = null;
		try {
			customActionClass = Class.forName(CUSTOMACTION_CLASS_PATH + "." + apiName + "ServiceImpl");		
			customActionObj = customActionClass.newInstance();
			
			method = customActionClass.getMethod("setObjectService", ObjectService.class); 
			method.invoke(customActionObj, this.objectService);
			
			method = customActionClass.getDeclaredMethod(methodName, inputParaClass); 
			methodReturns = method.invoke(customActionObj, inputValue);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return methodReturns;
	}	
}
