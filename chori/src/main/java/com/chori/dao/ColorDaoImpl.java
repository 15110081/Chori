package com.chori.dao;

import org.springframework.stereotype.Repository;

import com.chori.AbstractDaoImpl;
import com.chori.entity.Color;

@Repository("colorDao")
public class ColorDaoImpl extends AbstractDaoImpl<Color, String> implements
		ColorDao {

}
