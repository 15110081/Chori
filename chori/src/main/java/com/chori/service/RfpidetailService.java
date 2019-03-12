package com.chori.service;

import java.util.List;

import com.chori.AbstractService;
import com.chori.entity.Rfpidetail;
import com.chori.model.RfpidetailModel;

public interface RfpidetailService extends AbstractService<Rfpidetail, Integer>{
	int calculateTotalRfpi(String lotnumber, Integer version);
	List<RfpidetailModel> getListRfpiDetailByLotNumberAndVersion(String lotnumber, Integer version);
	List<Integer> getListRfpiVersionByLotNumber(String lotnumber);
}
