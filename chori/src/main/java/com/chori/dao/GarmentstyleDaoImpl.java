package com.chori.dao;

import org.springframework.stereotype.Repository;

import com.chori.AbstractDaoImpl;
import com.chori.entity.Garmentstyle;

@Repository("garmentstyleDao")
public class GarmentstyleDaoImpl extends AbstractDaoImpl<Garmentstyle, String>
		implements GarmentstyleDao {

}
