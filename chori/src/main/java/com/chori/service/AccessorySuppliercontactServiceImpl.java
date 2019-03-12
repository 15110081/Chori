package com.chori.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.chori.AbstractDao;
import com.chori.AbstractServiceImpl;
import com.chori.dao.AccessorySupplierContactDao;
import com.chori.entity.Accessorysuppliercontact;

@Repository("accessorysuppliercontactService")
public class AccessorySuppliercontactServiceImpl extends
		AbstractServiceImpl<Accessorysuppliercontact, Integer> implements
		AccessorySuppliercontactService {

	private AccessorySupplierContactDao dao;

	public AccessorySuppliercontactServiceImpl() {
	}

	@Autowired
	public AccessorySuppliercontactServiceImpl(
			@Qualifier("accessorysuppliercontactDao") AbstractDao<Accessorysuppliercontact, Integer> abstractDao) {
		super();
		this.dao = (AccessorySupplierContactDao) abstractDao;
	}
}
