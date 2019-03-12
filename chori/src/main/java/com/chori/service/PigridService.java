package com.chori.service;

import com.chori.entity.Pigrid;
import com.chori.model.PigridModel;

public interface PigridService {

	int checkPigridcode(String lotNumber);

	public boolean addPigrid(Pigrid pg);

	boolean deletePigrid(int pigridCode);

}