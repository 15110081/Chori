package com.chori.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.chori.AbstractDao;
import com.chori.AbstractServiceImpl;
import com.chori.dao.FabricsuppliercontactDao;
import com.chori.entity.Fabricsuppliercontact;

@Repository("fabricsuppliercontactService")
public class FabricsuppliercontactServiceImpl extends
		AbstractServiceImpl<Fabricsuppliercontact, Integer> implements
		FabricsuppliercontactService {
	private FabricsuppliercontactDao dao;

	public FabricsuppliercontactServiceImpl() {

	}

	@Autowired
	public FabricsuppliercontactServiceImpl(
			@Qualifier("fabricsuppliercontactDao") AbstractDao<Fabricsuppliercontact, Integer> abstractDao) {
		super(abstractDao);
		this.dao = (FabricsuppliercontactDao) abstractDao;
	}
}
