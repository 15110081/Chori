package com.chori.dao;

import org.springframework.stereotype.Repository;

import com.chori.AbstractDaoImpl;
import com.chori.entity.Orderinternalaccessory;

@Repository("orderInternalAccessoryDao")
public class OrderInternalAccessoryDaoImpl extends
		AbstractDaoImpl<Orderinternalaccessory, String> implements
		OrderInternalAccessoryDao {

}
