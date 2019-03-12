package com.chori.dao;

import org.springframework.stereotype.Repository;

import com.chori.AbstractDaoImpl;
import com.chori.entity.Shippinglinecontact;

@Repository("shippinglinecontactDao")
public class ShippinglinecontactDaoImpl extends
		AbstractDaoImpl<Shippinglinecontact, Integer> implements
		ShippinglinecontactDao {

}
