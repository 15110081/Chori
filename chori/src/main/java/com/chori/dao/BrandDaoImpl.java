package com.chori.dao;

import org.springframework.stereotype.Repository;

import com.chori.AbstractDaoImpl;
import com.chori.entity.Brand;

@Repository("brandDao")
public class BrandDaoImpl extends AbstractDaoImpl<Brand, Integer> implements
		BrandDao {

}