package com.chori.dao;

import java.util.List;

import com.chori.AbstractDao;
import com.chori.entity.Fabricinformationdetail;
import com.chori.entity.FabricinformationdetailId;

public interface FabricinformationdetailDao extends
		AbstractDao<Fabricinformationdetail, FabricinformationdetailId> {
	
	public List<Fabricinformationdetail> getListFabricinformationdetailByFabricNo(
			String fabricNo);
	
	List<Fabricinformationdetail> getAllFabricInformationDetailByFabricNo(
			 String fabricno);
	
}
