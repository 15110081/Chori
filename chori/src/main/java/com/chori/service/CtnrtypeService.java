package com.chori.service;

import java.util.List;

import com.chori.AbstractService;
import com.chori.entity.Ctnrtype;
import com.chori.model.CtnrtypeModel;

public interface CtnrtypeService extends AbstractService<Ctnrtype, String> {

	public List<CtnrtypeModel> getAllCtnrtypeModel();

	public CtnrtypeModel findCtnrtypeEntityById(String ctnrCode);

	public boolean deleteCtnrtype(String id);

	public boolean addCtnrtype(CtnrtypeModel ctnrtypeMod, String userName);

	public boolean isCtnrtypeExistedById(String ctnrtypeCode);

	public boolean editCtnrtype(CtnrtypeModel ctnrtypeMod, String userName);

}