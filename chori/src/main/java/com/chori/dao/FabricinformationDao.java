package com.chori.dao;

import java.util.List;

import com.chori.AbstractDao;
import com.chori.entity.Fabricinformation;

public interface FabricinformationDao extends
		AbstractDao<Fabricinformation, String> {
	
	public List<Fabricinformation> getListFabricInformationByCustomerCode(
			String customerCode);
	
	List<Fabricinformation> getAllFabricInformationByCustomerCodeFactoryCode(
			String customerCode , String factoryCode);
	
}
