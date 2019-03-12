package com.chori.service;

import java.util.List;

import com.chori.AbstractService;
import com.chori.entity.Size;
import com.chori.model.SizeModel;

public interface SizeService extends AbstractService<Size, Integer> {

	List<SizeModel> getAllSizeModel();

	List<SizeModel> findSizeCodeByCustomerCode(String customerCode);

	boolean deleteSizeById(Integer sizecode);

	SizeModel findSizeModelById(Integer sizecode);

	boolean editSize(SizeModel sizeModel);

	boolean isSizeExisted(SizeModel sizeModel);

	boolean addSize(SizeModel sizeModel, String userName);

	boolean duplicateSize(String customerCodeFrom, String customerCodeTo,
			String userName);

}