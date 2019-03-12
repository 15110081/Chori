package com.chori.service;

import java.util.List;

import com.chori.AbstractService;
import com.chori.entity.Fpidetail;
import com.chori.model.FpidetailModel;

public interface FpidetailService extends AbstractService<Fpidetail, Integer>{
	public int calculateTotalFpi(String lotnumber, Integer version);
//	public void getListFpiDetailByLotNumberAndVersion(String lotnumber, Integer version);
	public List<FpidetailModel> getListFpiDetailByLotNumberAndVersion(String lotnumber, Integer version);
	public List<Integer> getListFpiVersionByLotNumber(String lotnumber);
}
