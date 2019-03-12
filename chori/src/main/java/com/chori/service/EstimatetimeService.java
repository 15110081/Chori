package com.chori.service;

import java.util.List;

import com.chori.model.EstimatetimeModel;

public interface EstimatetimeService {

	public EstimatetimeModel findEstimatetimeModelById(int estimatetimeCode);

	public boolean editEstimatetime(EstimatetimeModel estimatetimeModel,
			String userName);

	public List<EstimatetimeModel> getAllEstimatetimeModel();
}