package com.chori.dao;

import org.springframework.stereotype.Repository;

import com.chori.AbstractDaoImpl;
import com.chori.entity.Ctnrtype;

@Repository("ctnrtypeDao")
public class CtnrtypeDaoImpl extends AbstractDaoImpl<Ctnrtype, String>
		implements CtnrtypeDao {

}