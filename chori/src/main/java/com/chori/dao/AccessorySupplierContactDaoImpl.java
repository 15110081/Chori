package com.chori.dao;

import org.springframework.stereotype.Repository;

import com.chori.AbstractDaoImpl;
import com.chori.entity.Accessorysuppliercontact;

@Repository("accessorysuppliercontactDao")
public class AccessorySupplierContactDaoImpl extends
		AbstractDaoImpl<Accessorysuppliercontact, Integer> implements
		AccessorySupplierContactDao {

}
