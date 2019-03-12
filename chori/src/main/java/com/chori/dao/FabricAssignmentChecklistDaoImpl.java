package com.chori.dao;

import org.springframework.stereotype.Repository;

import com.chori.AbstractDaoImpl;
import com.chori.entity.Fabricinformation;

@Repository("fabricAssignmentChecklistDao")
public class FabricAssignmentChecklistDaoImpl extends
		AbstractDaoImpl<Fabricinformation, String> implements
		FabricAssignmentChecklistDao {

}
