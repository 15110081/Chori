package com.chori.dao;

import org.springframework.stereotype.Repository;

import com.chori.AbstractDaoImpl;
import com.chori.entity.Accessorygroup;

@Repository("accessoryGroupDao")
public class AccessoryGroupDaoImpl extends
		AbstractDaoImpl<Accessorygroup, String> implements AccessoryGroupDao {

}
