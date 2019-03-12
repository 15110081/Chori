package com.chori.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.chori.AbstractDao;
import com.chori.AbstractServiceImpl;
import com.chori.dao.FunctionDao;
import com.chori.dao.RoleDao;
import com.chori.dao.UserDao;
import com.chori.entity.Function;
import com.chori.entity.Role;
import com.chori.model.FunctionModel;
import com.chori.model.RoleModel;

@Service("roleService")
public class RoleServiceImpl extends AbstractServiceImpl<Role, String>
		implements RoleService {
	private RoleDao dao;

	@Autowired
	private FunctionDao funcDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	public RoleServiceImpl(
			@Qualifier("roleDao") AbstractDao<Role, String> abstractDao) {
		super(abstractDao);
		this.dao = (RoleDao) abstractDao;
	}

	/**
	 * This function is used to get a list of all role in database
	 * 
	 * @return List<UserModel>
	 */
	public List<RoleModel> getAllRoleModel() {
		log.info(String.format("getAllRoleModel in class: %s", getClass()));
		try {
			log.debug("get all role in DB after that return a list roleModel");
			List<Role> lstRole = dao.getAll();

			RoleModel rm;
			List<RoleModel> lstRoleModel = new ArrayList<RoleModel>();

			for (Role r : lstRole) {

				rm = new RoleModel();
				rm.setRoleid(r.getRoleid());
				rm.setCreator(r.getUser() == null ? "" : r.getUser()
						.getUsername());
				rm.setRolename(r.getRolename());
				rm.setDescription(r.getDescription());
				rm.setCreatedate(r.getCreatedate());

				lstRoleModel.add(rm);
			}
			log.debug("getAllRoleModel successfully");
			return lstRoleModel;
		} catch (Exception e) {
			log.error(String.format(
					"getAllRoleModel in class: %s has error: %s", getClass(),
					e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function find a RoleModel by id.
	 * 
	 * @param roleId
	 * @return a Role
	 */
	public RoleModel findRoleModelById(String roleId) {
		log.info(String.format(
				"findRoleModelById with param 'roleId' in class: %s",
				getClass()));
		try {
			RoleModel roleModel = new RoleModel();
			Role role = dao.findById(roleId);

			roleModel.setRoleid(role.getRoleid());
			roleModel.setRolename(role.getRolename());
			roleModel.setDescription(role.getDescription());
			roleModel.setCreatedate(role.getCreatedate());
			roleModel.setCreator(role.getUser() == null ? "" : role.getUser()
					.getUsername());
			roleModel.setCreatedate(role.getCreatedate());

			log.debug("findRoleModelById successfully");
			return roleModel;
		} catch (Exception e) {
			log.error(String
					.format("findRoleModelById with param 'roleId' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function check if there is a role with roleId existed in database
	 * 
	 * @param roleId
	 * @return false if not existed, true if existed
	 */
	public boolean isRoleExistedById(String roleId) {
		log.info(String.format(
				"isRoleExistedById with param 'roleId' in class: %s",
				getClass()));
		try {
			if (null == dao.findById(roleId))
				return false;
			return true;
		} catch (Exception e) {
			log.error(String
					.format("isRoleExistedById with param 'roleId' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to delete a Role in database.
	 * 
	 * @param roleId
	 * @return true if delete successfully, false if cant
	 */
	public boolean deleteRole(String roleId) {
		log.info(String.format("deleteRole with param 'roleId' in class: %s",
				getClass()));
		try {
			Role role = dao.findById(roleId);

			dao.delete(role);
			return true;
		} catch (Exception e) {
			log.error(String.format("deleteRole in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This method return all function that belong to Role by RoleID
	 * 
	 * @param roleId
	 * @return
	 */
	public List<FunctionModel> listAllFuncOfRoleById(String roleId) {
		log.info(String.format(
				"listAllFuncOfRoleById with param 'roleId' in class: %s",
				getClass()));
		try {
			Role role = dao.findById(roleId);
			Set<Function> listFunc = role.getFunctions();

			List<FunctionModel> lst = new ArrayList<FunctionModel>();
			FunctionModel functionModel;

			for (Function function : listFunc) {
				functionModel = new FunctionModel();
				functionModel.setFunctionid(function.getFunctionid());
				functionModel.setFunctionname(function.getFunctionname());
				functionModel.setDescription(function.getDescription());

				lst.add(functionModel);
			}

			log.debug("listAllFuncOfRoleById successfully");
			return lst;
		} catch (Exception e) {
			log.error(String
					.format("listAllFuncOfRoleById with param 'roleId' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This method is used to assign function for 1 role
	 * 
	 * @param roleId
	 * @param lst
	 * @return true if assign successfully, false is fail
	 */
	public boolean assignFunctionForRole(String roleId,
			ArrayList<FunctionModel> lst) {
		log.info(String
				.format("assignFunctionForRole with param 'roleId', 'lst' in class: %s",
						getClass()));
		try {
			Role role = dao.findById(roleId);

			if (lst.size() == 0) {

				if (role.getFunctions().size() == 0) {
					log.debug("assignFunctionForRole successfully");
					return true;
				} else {
					role.getFunctions().clear();
					try {
						dao.update(role);
						log.debug("assignFunctionForRole successfully");
						return true;
					} catch (Exception e) {
						return false;
					}
				}
			} else {

				if (role.getFunctions().size() == 0) {
					for (FunctionModel functionEntity : lst) {
						role.getFunctions()
								.add(funcDao.findById(functionEntity
										.getFunctionid()));
					}

					try {
						dao.update(role);
						log.debug("assignFunctionForRole successfully");
						return true;
					} catch (Exception e) {
						return false;
					}
				} else {
					List<Function> lstToBeRemove = new ArrayList<Function>();
					List<Function> lstToBeAdd = new ArrayList<Function>();
					Set<Function> oldLst = role.getFunctions();

					boolean flag = false;
					for (Function function : oldLst) {
						for (FunctionModel functionEntity : lst) {
							if (function.getFunctionid().equals(
									functionEntity.getFunctionid())) {
								flag = true;
							}
						}

						if (flag == false) {
							lstToBeRemove.add(function);
						}

						if (flag == true) {
							flag = false;
						}
					}

					for (FunctionModel functionEntity : lst) {
						for (Function function : oldLst) {
							if (function.getFunctionid().equals(
									functionEntity.getFunctionid())) {
								flag = true;
							}
						}

						if (flag == false) {
							lstToBeAdd.add(funcDao.findById(functionEntity
									.getFunctionid()));
						}

						if (flag == true) {
							flag = false;
						}
					}

					oldLst.addAll(lstToBeAdd);
					oldLst.removeAll(lstToBeRemove);

					try {
						dao.update(role);
						log.debug("assignFunctionForRole successfully");
						return true;
					} catch (Exception e) {
						return false;
					}
				}
			}
		} catch (Exception e) {
			log.error(String
					.format("assignFunctionForRole with param 'roleId' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This method return true if user have function to access page
	 * 
	 * @param userName
	 * @param functionId
	 * @return
	 */
	public boolean detectFunc4User(String userName, String functionId) {
		Set<Function> lst = userDao.findById(userName).getRole().getFunctions();

		Function func = funcDao.findById(functionId);

		boolean flag = false;

		for (Function function : lst) {
			if (function.getFunctionid().equals(func.getFunctionid())) {
				flag = true;
			}
		}

		if (flag) {
			return true;
		}

		return false;
	}

	/**
	 * This function is used to add new role
	 * 
	 * @param roleModel
	 * @param userName
	 * @return
	 */
	public boolean addNewRole(RoleModel roleModel, String userName) {
		try {
			Role role = new Role();
			role.setRoleid(roleModel.getRoleid());
			role.setRolename(roleModel.getRolename());
			role.setDescription(roleModel.getDescription());
			role.setUser(userDao.findById(userName));
			role.setCreatedate(new Date());

			dao.save(role);
			System.err.println("Save các factoryContact thành công");
			return true;
		} catch (Exception e) {
			System.err.println("Save factory ko thành công");
			throw e;
		}
	}

	/**
	 * This function is used to edit a role
	 * 
	 * @param roleModel
	 * @param editor
	 * @return
	 */
	public boolean editRole(RoleModel roleModel, String editor) {
		log.info(String.format(
				"editUser with params 'userModel', 'editor' in class: %s",
				getClass()));
		try {
			Role role = dao.findById(roleModel.getRoleid());

			role.setRoleid(roleModel.getRoleid());
			role.setRolename(roleModel.getRolename());
			role.setDescription(roleModel.getDescription());

			dao.update(role);
			return true;
		} catch (Exception e) {
			log.error(String
					.format("editUser with params 'userModel', 'editor' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
}
