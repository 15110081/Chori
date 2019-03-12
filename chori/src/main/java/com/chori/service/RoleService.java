package com.chori.service;

import java.util.ArrayList;
import java.util.List;

import com.chori.AbstractService;
import com.chori.entity.Role;
import com.chori.model.FunctionModel;
import com.chori.model.RoleModel;

public interface RoleService extends AbstractService<Role, String> {
	public List<RoleModel> getAllRoleModel();

	public RoleModel findRoleModelById(String roleId);

	public boolean isRoleExistedById(String roleId);

	public boolean deleteRole(String roleId);

	public List<FunctionModel> listAllFuncOfRoleById(String roleId);

	public boolean assignFunctionForRole(String roleId,
			ArrayList<FunctionModel> lst);

	public boolean detectFunc4User(String userName, String functionId);

	public boolean addNewRole(RoleModel roleModel, String userName);

	public boolean editRole(RoleModel roleModel, String editor);
}
