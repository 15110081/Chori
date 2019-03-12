package com.chori.dao;

import org.springframework.stereotype.Repository;

import com.chori.AbstractDaoImpl;
import com.chori.entity.Width;

@Repository("widthDao")
public class WidthDaoImpl extends AbstractDaoImpl<Width, String> implements
		WidthDao {

}
