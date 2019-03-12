package com.chori.dao;

import org.springframework.stereotype.Repository;

import com.chori.AbstractDaoImpl;
import com.chori.entity.Fabricsuppliercontact;

@Repository("fabricsuppliercontactDao")
public class FabricsuppliercontactDaoImpl extends
		AbstractDaoImpl<Fabricsuppliercontact, Integer> implements
		FabricsuppliercontactDao {

}
