package com.chori.service;

import java.util.List;

import com.chori.AbstractService;
import com.chori.entity.Piassigninternalaccessoriesdetail;
import com.chori.model.PiassigninternalaccessoriesdetailModel;

public interface PiassigninternalaccessoriesdetailService extends
		AbstractService<Piassigninternalaccessoriesdetail, Integer> {
	List<PiassigninternalaccessoriesdetailModel> getAllPiassigninternalaccessoriesdetailModel();

	List<PiassigninternalaccessoriesdetailModel> getAnAssignInternalAccessoryDetailOfAPi(
			String lotnumber, String accessorycode);

	boolean editPiassigninternalaccessoriesdetail(
			PiassigninternalaccessoriesdetailModel piassigninternalaccessoriesdetailModel);
}
