package com.chori.dao;

import org.springframework.stereotype.Repository;

import com.chori.AbstractDaoImpl;
import com.chori.entity.Customeraccountinformation;

@Repository("customeraccountinformationDao")
public class CustomeraccountinformationDaoImpl extends
		AbstractDaoImpl<Customeraccountinformation, Integer> implements
		CustomeraccountinformationDao {

}
