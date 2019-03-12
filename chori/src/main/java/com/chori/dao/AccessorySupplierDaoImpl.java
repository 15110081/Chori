package com.chori.dao;

import org.springframework.stereotype.Repository;

import com.chori.AbstractDaoImpl;
import com.chori.entity.Accessorysupplier;

@Repository("accessorysupplierDao")
public class AccessorySupplierDaoImpl extends
		AbstractDaoImpl<Accessorysupplier, String> implements
		AccessorySupplierDao {

}
