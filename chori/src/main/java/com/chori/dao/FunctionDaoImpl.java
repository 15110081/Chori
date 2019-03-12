package com.chori.dao;

import org.springframework.stereotype.Repository;

import com.chori.AbstractDaoImpl;
import com.chori.entity.Function;

@Repository("functionDao")
public class FunctionDaoImpl extends AbstractDaoImpl<Function, String>
		implements FunctionDao {

}
