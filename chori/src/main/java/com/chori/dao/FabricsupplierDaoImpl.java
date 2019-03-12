package com.chori.dao;

import org.springframework.stereotype.Repository;

import com.chori.AbstractDaoImpl;
import com.chori.entity.Fabricsupplier;

@Repository("fabricsupplierDao")
public class FabricsupplierDaoImpl extends
		AbstractDaoImpl<Fabricsupplier, String> implements FabricsupplierDao {

}
