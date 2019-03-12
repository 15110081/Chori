package com.chori.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.chori.AbstractDao;
import com.chori.AbstractServiceImpl;
import com.chori.dao.TypeDao;
import com.chori.dao.UserDao;
import com.chori.entity.Type;
import com.chori.model.TypeModel;

@Repository("typeService")
public class TypeServiceImpl extends AbstractServiceImpl<Type, String>
		implements TypeService {
	private TypeDao dao;
	@Autowired
	private UserDao userDao;

	@Autowired
	public TypeServiceImpl(
			@Qualifier("typeDao") AbstractDao<Type, String> abstractDao) {
		super(abstractDao);
		this.dao = (TypeDao) abstractDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chori.service.TypeService#getAllTypeModel()
	 */
	/**
	 * This function is used get all Type
	 * 
	 * @return list<TypeModel>
	 */
	@Override
	public List<TypeModel> getAllTypeModel() {
		log.debug("in Type service list");
		try {
			List<Type> lstTypes = dao.getAll();
			TypeModel typeModel;
			List<TypeModel> lst = new ArrayList<TypeModel>();
			for (Type type : lstTypes) {
				typeModel = new TypeModel();
				typeModel.setTypeCode(type.getTypecode());
				typeModel.setDescription(type.getDescription());

				lst.add(typeModel);
			}
			return lst;
		} catch (NullPointerException ne) {
			log.error("list service type err: " + ne.getMessage());
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chori.service.TypeService#findTypeModelById(java.lang.String)
	 */
	/**
	 * This function find 1 TypeModel by Typecode
	 * 
	 * @param Typecode
	 * @return
	 */
	@Override
	public TypeModel findTypeModelById(String Typecode) {
		log.info(String.format(
				"findTypeModelById with param 'Typecode' in class: %s",
				getClass()));
		try {
			TypeModel typeModel = new TypeModel();
			Type type = dao.findById(Typecode);

			// field for NV
			typeModel.setTypeCode(type.getTypecode().toString());
			typeModel.setDescription(type.getDescription().toString());

			log.debug("findTypeEntityById successfully");
			return typeModel;
		} catch (Exception e) {
			log.error(String
					.format("findTypeModelById with param 'Typecode' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chori.service.TypeService#editType(com.chori.model.TypeModel,
	 * java.lang.String)
	 */
	/**
	 * This function is used to update an type
	 * 
	 * @param typeModel
	 * 
	 * @return
	 */
	@Override
	public boolean editType(TypeModel typeModel, String creator) {
		log.info(String.format(
				"editType with param 'typeModel', 'typeCode' in class: %s",
				getClass()));
		try {
			Type type = dao.findById(typeModel.getTypeCode());
			type.setUser(userDao.findById(creator));
			type.setDescription(typeModel.getDescription());
			type.setCreatedate(new Date());

			dao.update(type);
			log.debug("editType successfully");
			return true;
		} catch (Exception e) {
			log.error(String
					.format("editType with param 'typeModel', 'typeCode' in class: %s has error: %s",
							getClass(), e.getMessage()));
			System.err
					.println(String
							.format("editType with param 'typeModel', 'typeCode' in class: %s has error: %s",
									getClass(), e.getMessage()));
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chori.service.TypeService#deleteType(java.lang.String)
	 */
	/**
	 * This function is used to delete 1 type
	 */
	@Override
	public boolean deleteType(String typeCode) {
		log.info(String.format("deleteType with param 'typeCode' in class: %s",
				getClass()));
		try {
			Type type = dao.findById(typeCode);
			dao.delete(type);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chori.service.TypeService#addType(com.chori.model.TypeModel,
	 * java.lang.String)
	 */
	/**
	 * This function is used to add new Type
	 * 
	 * @param typeModel
	 * @param creator
	 * @return
	 */
	@Override
	public boolean addType(TypeModel typeModel, String creator) {
		try {
			Type type = new Type();
			type.setTypecode(typeModel.getTypeCode());
			type.setDescription(typeModel.getDescription());
			type.setUser(userDao.findById(creator));

			type.setCreatedate(new Date());
			dao.save(type);
			log.debug("addType successfullly");
			return true;
		} catch (Exception e) {
			log.error(String
					.format("addType with param 'typeModel' in class: %s has error: %s",
							getClass(), e.getMessage()));
			System.err
					.println(String
							.format("addType with param 'typeModel' in class: %s has error: %s",
									getClass(), e.getMessage()));
			return false;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chori.service.TypeService#isUnitExistedById(java.lang.String)
	 */
	/**
	 * This function is used check isExisted of Type in db
	 * 
	 * @return true or false
	 */
	@Override
	public boolean isTypeExistedById(String typeCode) {
		if (null == dao.findById(typeCode))
			return false;
		return true;
	}
}