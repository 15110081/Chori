package com.chori.dao;

import java.util.List;

import com.chori.AbstractDao;
import com.chori.entity.Accessory;

public interface AccessoryDao extends AbstractDao<Accessory, String> {
	List<Accessory> getListAccessoryWithKindInternal();
}
