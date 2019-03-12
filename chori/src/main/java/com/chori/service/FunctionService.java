package com.chori.service;

import java.util.List;

import com.chori.AbstractService;
import com.chori.entity.Function;
import com.chori.model.FunctionModel;

public interface FunctionService extends AbstractService<Function, String> {
	public List<FunctionModel> getAllFunctionModel();
}
