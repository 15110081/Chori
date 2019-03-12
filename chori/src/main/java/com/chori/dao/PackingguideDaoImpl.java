package com.chori.dao;

import org.springframework.stereotype.Repository;

import com.chori.AbstractDaoImpl;

import com.chori.entity.Packingguide;

@Repository("packingguideDao")
public class PackingguideDaoImpl extends AbstractDaoImpl<Packingguide, String> 
	implements PackingguideDao{
	
}
