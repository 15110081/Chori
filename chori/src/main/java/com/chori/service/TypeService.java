package com.chori.service;

import java.util.List;

import com.chori.AbstractService;
import com.chori.entity.Type;
import com.chori.model.TypeModel;

public interface TypeService extends AbstractService<Type, String> {

	public List<TypeModel> getAllTypeModel();

	public TypeModel findTypeModelById(String Typecode);

	public boolean editType(TypeModel typeModel, String creator);

	public boolean deleteType(String typeCode);

	public boolean addType(TypeModel typeModel, String creator);

	public boolean isTypeExistedById(String typeCode);

}