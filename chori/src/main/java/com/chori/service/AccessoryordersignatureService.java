package com.chori.service;

import java.util.List;

import com.chori.AbstractService;
import com.chori.entity.Accessoryordersignature;
import com.chori.model.SignatureAddModel;
import com.chori.model.SignatureModel;

public interface AccessoryordersignatureService extends
		AbstractService<Accessoryordersignature, Integer> {
	List<SignatureModel> getAllSignature();

	public boolean deleteSignature(Integer id);

	public boolean addNewSignature(SignatureAddModel signatureAddModel,
			String userId);

	public SignatureModel findSignatureModelById(Integer id);

	public boolean editSignature(SignatureAddModel signatureAddModel,
			String userId);

	public boolean isSignatureExistedById(Integer id);
}
