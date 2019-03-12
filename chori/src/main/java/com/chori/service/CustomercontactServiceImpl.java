package com.chori.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.chori.AbstractDao;
import com.chori.AbstractServiceImpl;
import com.chori.dao.CustomercontactDao;
import com.chori.entity.Customercontact;

public class CustomercontactServiceImpl extends
		AbstractServiceImpl<Customercontact, Integer> implements
		CustomercontactService {
	private CustomercontactDao dao;

	public CustomercontactServiceImpl() {
	}

	@Autowired
	public CustomercontactServiceImpl(
			@Qualifier("customercontactDao") AbstractDao<Customercontact, Integer> abstractDao) {
		super(abstractDao);
		this.dao = (CustomercontactDao) abstractDao;
	}
}
