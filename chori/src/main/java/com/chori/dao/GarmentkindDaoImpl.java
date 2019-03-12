package com.chori.dao;

import org.springframework.stereotype.Repository;

import com.chori.AbstractDaoImpl;
import com.chori.entity.Garmentkind;

@Repository("garmentkindDao")
public class GarmentkindDaoImpl extends AbstractDaoImpl<Garmentkind, String>
		implements GarmentkindDao {

}
