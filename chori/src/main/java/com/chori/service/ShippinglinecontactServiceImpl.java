package com.chori.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.chori.AbstractDao;
import com.chori.AbstractServiceImpl;
import com.chori.dao.ShippinglinecontactDao;
import com.chori.entity.Shippinglinecontact;

@Repository("shippinglinecontactService")
public class ShippinglinecontactServiceImpl extends
		AbstractServiceImpl<Shippinglinecontact, Integer> implements
		ShippinglinecontactService {

	private ShippinglinecontactDao dao;

	// public ShippinglinecontactServiceImpl(){
	// }

	@Autowired
	public ShippinglinecontactServiceImpl(
			@Qualifier("shippinglinecontactDao") AbstractDao<Shippinglinecontact, Integer> abstractDao) {
		super(abstractDao);
		this.dao = (ShippinglinecontactDao) abstractDao;
	}
}