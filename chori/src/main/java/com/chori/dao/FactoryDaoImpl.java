package com.chori.dao;

import org.springframework.stereotype.Repository;

import com.chori.AbstractDaoImpl;
import com.chori.entity.Factory;

@Repository("factoryDao")
public class FactoryDaoImpl extends AbstractDaoImpl<Factory, String> implements
		FactoryDao {

}
