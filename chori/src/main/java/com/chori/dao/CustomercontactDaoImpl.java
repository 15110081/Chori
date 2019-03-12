package com.chori.dao;

import org.springframework.stereotype.Repository;

import com.chori.AbstractDaoImpl;
import com.chori.entity.Customercontact;

@Repository("customercontactDao")
public class CustomercontactDaoImpl extends
		AbstractDaoImpl<Customercontact, Integer> implements CustomercontactDao {

}
