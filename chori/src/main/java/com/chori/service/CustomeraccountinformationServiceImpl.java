package com.chori.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.chori.AbstractDao;
import com.chori.AbstractServiceImpl;
import com.chori.dao.CustomeraccountinformationDao;
import com.chori.entity.Customeraccountinformation;

@Repository("customeraccountinformationService")
public class CustomeraccountinformationServiceImpl extends
		AbstractServiceImpl<Customeraccountinformation, Integer> implements
		CustomeraccountinformationService {

	private CustomeraccountinformationDao cusaccountinfoDao;

	public CustomeraccountinformationServiceImpl() {

	}

	@Autowired
	public CustomeraccountinformationServiceImpl(
			@Qualifier("customeraccountinformationDao") AbstractDao<Customeraccountinformation, Integer> abstractDao) {
		super(abstractDao);
		this.cusaccountinfoDao = (CustomeraccountinformationDao) abstractDao;
	}
}
