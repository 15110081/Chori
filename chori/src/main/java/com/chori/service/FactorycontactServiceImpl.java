package com.chori.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.chori.AbstractDao;
import com.chori.AbstractServiceImpl;
import com.chori.dao.FactorycontactDao;
import com.chori.entity.Factorycontact;

@Repository("factorycontactService")
public class FactorycontactServiceImpl extends
		AbstractServiceImpl<Factorycontact, Integer> implements
		FactorycontactService {

	private FactorycontactDao dao;

	public FactorycontactServiceImpl() {
	}

	@Autowired
	public FactorycontactServiceImpl(
			@Qualifier("factorycontactDao") AbstractDao<Factorycontact, Integer> abstractDao) {
		super(abstractDao);
		this.dao = (FactorycontactDao) abstractDao;
	}
}
