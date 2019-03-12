package com.chori.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.chori.AbstractDao;
import com.chori.AbstractServiceImpl;
import com.chori.dao.FunctionDao;
import com.chori.entity.Function;
import com.chori.model.FunctionModel;

@Service("functionService")
public class FunctionServiceImpl extends AbstractServiceImpl<Function, String>
		implements FunctionService {
	private FunctionDao dao;

	@Autowired
	public FunctionServiceImpl(
			@Qualifier("functionDao") AbstractDao<Function, String> abstractDao) {
		super(abstractDao);
		this.dao = (FunctionDao) abstractDao;
	}

	/**
	 * This method is used to get a list of all functions in database
	 * 
	 * @return List<FunctionEntity>
	 */
	public List<FunctionModel> getAllFunctionModel() {
		log.info(String.format("getAllFunctionModel in class: %s", getClass()));
		try {
			List<Function> lstFunction = dao.getAll();

			FunctionModel functionModel;
			List<FunctionModel> lst = new ArrayList<FunctionModel>();
			for (Function function : lstFunction) {
				functionModel = new FunctionModel();
				functionModel.setFunctionid(function.getFunctionid());
				functionModel.setFunctionname(function.getFunctionname());
				functionModel.setDescription(function.getDescription());

				lst.add(functionModel);
			}
			log.debug("getAllFunctionModel successfully");
			return lst;
		} catch (Exception e) {
			log.error(String.format(
					"getAllFunctionModel in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}
}
