package com.chori.dao;

import org.springframework.stereotype.Repository;

import com.chori.AbstractDaoImpl;
import com.chori.entity.Unit;

@Repository("unitDao")
public class UnitDaoImpl extends AbstractDaoImpl<Unit, String> implements
		UnitDao {

}
