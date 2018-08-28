package com.tools.objectManagement.service.custom;

import com.tools.objectManagement.service.ObjectService;

public class CustomServiceImpl {

	private ObjectService objectService;
	
	public CustomServiceImpl() {

	}

	public void setObjectService(ObjectService os) {
		this.objectService = os;
	}
	
	public ObjectService getObjectService() {
		return this.objectService;
	}
}
