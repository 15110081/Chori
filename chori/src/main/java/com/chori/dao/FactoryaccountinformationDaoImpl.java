package com.chori.dao;

import org.springframework.stereotype.Repository;

import com.chori.AbstractDaoImpl;
import com.chori.entity.Factoryaccountinformation;

@Repository("factoryaccountinformationDao")
public class FactoryaccountinformationDaoImpl extends
		AbstractDaoImpl<Factoryaccountinformation, Integer> implements
		FactoryaccountinformationDao {

}
