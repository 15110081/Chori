package com.chori.service;

import java.util.List;

import com.chori.AbstractService;
import com.chori.entity.Brand;
import com.chori.model.BrandModel;

public interface BrandService extends AbstractService<Brand, Integer> {
	public List<BrandModel> getAllBrandModel();
	public BrandModel findBrandById(int brandCode);
}
