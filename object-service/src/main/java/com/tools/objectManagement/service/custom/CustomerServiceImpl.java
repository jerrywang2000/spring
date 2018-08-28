package com.tools.objectManagement.service.custom;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tools.objectManagement.entity.Customer;

@Service
public class CustomerServiceImpl extends CustomServiceImpl{
	
	public CustomerServiceImpl() {
	}
	@Transactional()
	public Customer copyRow (Integer oldKey, Integer newKey) {
		Customer oldRow = (Customer)this.getObjectService().selectByPrimaryKey("Customer", oldKey);
		oldRow.setCustomerId(newKey);
		int rowCount = -1;

		rowCount = this.getObjectService().insert("Customer", oldRow);
		this.getObjectService().deleteByPrimaryKey("Customer", oldKey);

		if (rowCount>0) {
			return oldRow;
		} else {
			return null;
		}
	}
}
