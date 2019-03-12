package com.chori.dao;

import org.springframework.stereotype.Repository;

import com.chori.AbstractDaoImpl;
import com.chori.entity.Customer;

@Repository("customerDao")
public class CustomerDaoImpl extends AbstractDaoImpl<Customer, String>
		implements CustomerDao {

}
