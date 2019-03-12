package com.chori.service;

import java.util.List;

import com.chori.AbstractService;
import com.chori.entity.Logofchange;
import com.chori.model.LogofchangeModel;


public interface LogofchangeService extends AbstractService<Logofchange, Integer> {
	public List<LogofchangeModel> getAllLogofchangeModel();

	public boolean addLogofchange(LogofchangeModel logofchangeModel, String userName);
}
