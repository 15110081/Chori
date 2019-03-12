package com.chori.service;

import java.util.List;

import com.chori.AbstractService;
import com.chori.entity.Customer;
import com.chori.model.CustomerModel;

public interface CustomerService extends AbstractService<Customer, String> {
	public List<CustomerModel> getAllCustomerModel();

	public boolean addNewCustomer(CustomerModel cm, String userName);

	public CustomerModel findCustomerModelById(String id);

	public boolean editCustomer(CustomerModel fsm, String userName);

	public boolean isCustomerExistedById(String id);
}
