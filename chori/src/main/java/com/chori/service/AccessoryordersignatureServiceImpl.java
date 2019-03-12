package com.chori.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.chori.AbstractDao;
import com.chori.AbstractServiceImpl;
import com.chori.dao.SignatureDao;
import com.chori.dao.UserDao;
import com.chori.entity.Accessoryordersignature;
import com.chori.model.SignatureAddModel;
import com.chori.model.SignatureModel;

@Repository("accessoryordersignatureService")
public class AccessoryordersignatureServiceImpl extends
		AbstractServiceImpl<Accessoryordersignature, Integer> implements
		AccessoryordersignatureService {

	private SignatureDao dao;

	@Autowired
	private UserDao userDao;

	@Autowired
	public AccessoryordersignatureServiceImpl(
			@Qualifier("signatureDao") AbstractDao<Accessoryordersignature, Integer> abstractDao) {
		super(abstractDao);
		this.dao = (SignatureDao) abstractDao;
	}

	/**
	 * This function is used get all Accessory
	 * 
	 * @return list<AccessoryModel>
	 */
	@Override
	public List<SignatureModel> getAllSignature() {
		log.info(String.format("getAllSignature in class: %s", getClass()));
		try {
			List<Accessoryordersignature> lstSignature = dao.getAll();
			SignatureModel signatureModel;
			List<SignatureModel> lstsignatureModel = new ArrayList<SignatureModel>();
			for (Accessoryordersignature aos : lstSignature) {
				signatureModel = new SignatureModel();
				signatureModel.setAccordersigncode(aos.getAccordersigncode());
				signatureModel.setName(aos.getName());
				signatureModel.setText1(aos.getText1());
				signatureModel.setImgurl(aos.getImgurl());
				signatureModel.setText2(aos.getText2());
				signatureModel.setCreatedate(aos.getCreatedate());
				lstsignatureModel.add(signatureModel);
			}
			log.debug("getAllSignature successful");
			return lstsignatureModel;
		} catch (Exception e) {
			log.error(String.format(
					"getAllSignature in class: %s has error: %s", getClass(),
					e.getMessage()));
			throw e;
		}
	}

	public boolean deleteSignature(Integer id) {
		try {
			dao.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean addNewSignature(SignatureAddModel signatureAddModel,
			String userId) {
		try {
			Accessoryordersignature signature = new Accessoryordersignature();

			signature.setAccordersigncode(signatureAddModel
					.getAccordersigncode());
			signature.setUser(userDao.findById(userId));
			signature.setName(signatureAddModel.getName());
			signature.setText1(signatureAddModel.getText1());
			signature.setImgurl(signatureAddModel.getImgurl());
			signature.setText2(signatureAddModel.getText2());
			signature.setCreatedate(new Date());

			dao.save(signature);
			return true;
		} catch (Exception e) {
			log.error(String
					.format("addNewSignature with params 'signatureAddModel', 'userId' in class %s has error: %s",
							getClass(), e.getMessage()));
			// return false;
			throw e;
		}
	}

	@Override
	public SignatureModel findSignatureModelById(Integer id) {
		log.info(String.format(
				"findSignatureModelById with params 'id' in class: %s",
				getClass()));
		try {
			Accessoryordersignature accsig = dao.findById(id);
			SignatureModel signatureModel = new SignatureModel();

			signatureModel.setAccordersigncode(accsig.getAccordersigncode());
			signatureModel.setName(accsig.getName());
			signatureModel.setText1(accsig.getText1());
			signatureModel.setImgurl(accsig.getImgurl());
			signatureModel.setText2(accsig.getText2());
			signatureModel.setCreatedate(accsig.getCreatedate());

			return signatureModel;
		} catch (Exception e) {
			log.error(String
					.format("findSignatureModelById with params 'id' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	@Override
	public boolean editSignature(SignatureAddModel signatureAddModel,
			String userId) {
		log.info(String
				.format("editSignature with params 'signatureAddModel', 'userId' in class: %s",
						getClass()));
		try {
			Accessoryordersignature accsig = dao.findById(signatureAddModel
					.getAccordersigncode());
			accsig.setAccordersigncode(signatureAddModel.getAccordersigncode());
			accsig.setName(signatureAddModel.getName());
			accsig.setText1(signatureAddModel.getText1());
			// set new img only if they != null
			if (signatureAddModel.getImgurl() != null)
				accsig.setImgurl(signatureAddModel.getImgurl());
			accsig.setText2(signatureAddModel.getText2());

			dao.update(accsig);
			return true;
		} catch (Exception e) {
			log.error(String
					.format("editSignature with params 'signatureAddModel', 'userId' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	@Override
	public boolean isSignatureExistedById(Integer id) {
		log.info(String.format(
				"isSignatureExistedById with param 'id' in class: %s",
				getClass()));
		try {
			if (null == dao.findById(id))
				return false;
			return true;
		} catch (Exception e) {
			log.error(String
					.format("isSignatureExistedById with param 'id' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
}
