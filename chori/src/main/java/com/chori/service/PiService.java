package com.chori.service;

import java.util.List;

import com.chori.AbstractService;
import com.chori.entity.Pi;
import com.chori.model.PiFabricListModel;
import com.chori.model.PiModel;

public interface PiService extends AbstractService<Pi, String> {

	public boolean isPiExistedById(String piCode);

	public List<PiModel> getAllPiModel();

	public PiModel findPiById(String piCode);

	public boolean editPi(PiModel piModel, String userName);

	public boolean addPi(PiModel piModel, String username);

	public boolean deletePi(PiModel piModel);

	List<PiFabricListModel> getFabricList();
}
