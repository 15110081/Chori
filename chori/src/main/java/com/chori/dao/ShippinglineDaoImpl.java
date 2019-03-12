package com.chori.dao;

import org.springframework.stereotype.Repository;

import com.chori.AbstractDaoImpl;
import com.chori.entity.Shippingline;

@Repository("shippinglineDao")
public class ShippinglineDaoImpl extends AbstractDaoImpl<Shippingline, String>
		implements ShippinglineDao {

}
