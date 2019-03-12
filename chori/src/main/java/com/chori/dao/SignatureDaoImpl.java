package com.chori.dao;

import org.springframework.stereotype.Repository;

import com.chori.AbstractDaoImpl;
import com.chori.entity.Accessoryordersignature;

@Repository("signatureDao")
public class SignatureDaoImpl extends
		AbstractDaoImpl<Accessoryordersignature, Integer> implements
		SignatureDao {

}
