package com.chori.dao;

import org.springframework.stereotype.Repository;

import com.chori.AbstractDaoImpl;
import com.chori.entity.Type;

@Repository("typeDao")
public class TypeDaoImpl extends AbstractDaoImpl<Type, String> implements
		TypeDao {

}
