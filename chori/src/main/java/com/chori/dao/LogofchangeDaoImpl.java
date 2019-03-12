package com.chori.dao;

import org.springframework.stereotype.Repository;

import com.chori.AbstractDaoImpl;
import com.chori.entity.Logofchange;

@Repository("logofchangeDao")
public class LogofchangeDaoImpl extends AbstractDaoImpl<Logofchange, Integer>
		implements LogofchangeDao {

}
