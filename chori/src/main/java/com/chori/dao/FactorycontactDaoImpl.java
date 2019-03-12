package com.chori.dao;

import org.springframework.stereotype.Repository;

import com.chori.AbstractDaoImpl;
import com.chori.entity.Factorycontact;

@Repository("factorycontactDao")
public class FactorycontactDaoImpl extends
		AbstractDaoImpl<Factorycontact, Integer> implements FactorycontactDao {

}
