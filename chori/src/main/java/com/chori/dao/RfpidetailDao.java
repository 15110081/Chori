package com.chori.dao;

import java.util.List;

import com.chori.AbstractDao;
import com.chori.entity.Rfpidetail;

public interface RfpidetailDao extends AbstractDao<Rfpidetail, Integer>{
	List<Rfpidetail> getListRfpidetailByLotNumberAndVersion(
			String lotnumber, Integer version);
	
	public Integer getRfpiValueOfRfpidetailByRfpidetailcodeColorCodeGarmentStyleCodeSizeCode(
			Integer rfpicode, String colorcode, String garmentstylecode,
			Integer sizecode);
}
