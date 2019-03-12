package com.chori.service;

import java.util.List;

import com.chori.AbstractService;
import com.chori.entity.Width;
import com.chori.model.WidthModel;

public interface WidthService extends AbstractService<Width, String> {

	public List<WidthModel> getAllWidthModel();

	public WidthModel findWidthEntityById(String widthCode);

	public boolean deleteWidth(WidthModel widMod);

	public boolean addWidth(WidthModel widMod, String userName);

	public boolean editWidth(WidthModel widMod, String userName);

	public boolean isWidthExistedById(String widthCode);
}