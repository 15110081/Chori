package com.chori.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.chori.AbstractDao;
import com.chori.AbstractServiceImpl;
import com.chori.dao.AgentDao;
import com.chori.dao.RoleDao;
import com.chori.dao.UserDao;
import com.chori.entity.User;
import com.chori.model.UserModel;

@Service("userService")
public class UserServiceImpl extends AbstractServiceImpl<User, String>
		implements UserService {

	private UserDao dao;

	@Autowired
	private AgentDao agentDao;

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// public UserServiceImpl() {
	//
	// }

	@Autowired
	public UserServiceImpl(
			@Qualifier("userDao") AbstractDao<User, String> abstractDao) {
		super(abstractDao);
		this.dao = (UserDao) abstractDao;
	}

	/**
	 * This function is used to get a list of all users in database
	 * 
	 * @return List<UserModel>
	 */
	public List<UserModel> getAllUserModel() {
		log.info(String.format("getAllUserModel in class: %s", getClass()));
		try {
			log.debug("get all User in DB after that return a list UserModel");
			List<User> lstUser = dao.getAll();

			UserModel um;
			List<UserModel> lstUserModel = new ArrayList<UserModel>();

			for (User u : lstUser) {

				um = new UserModel();
				um.setUsername(u.getUsername());
				um.setAgentid(u.getAgent() == null ? 0 : u.getAgent()
						.getAgentcode());
				um.setAgentShortname(u.getAgent() == null ? "" : u.getAgent()
						.getShortname());
				um.setRoleid(u.getRole() == null ? "" : u.getRole().getRoleid());
				um.setRolename(u.getRole() == null ? "" : u.getRole()
						.getRolename());
				um.setCreator(u.getUser() == null ? "" : u.getUser()
						.getUsername());
				um.setPassword(u.getPassword());
				um.setFirstname(u.getFirstname());
				um.setLastname(u.getLastname());
				um.setEmail(u.getEmail());
				um.setEmailpassword(u.getEmailpassword());
				um.setPhone(u.getPhone());
				um.setStatus(u.getStatus());

				lstUserModel.add(um);
			}
			log.debug("getAllUserModel successfully");
			return lstUserModel;
		} catch (Exception e) {
			log.error(String.format(
					"getAllUserModel in class: %s has error: %s", getClass(),
					e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to add new User
	 * 
	 * @param um
	 * @param userName
	 * @return
	 */
	@Override
	public boolean addNewUser(UserModel um, String userName) {
		try {
			User u = new User();
			u.setUsername(um.getUsername());
			u.setAgent(agentDao.findById(um.getAgentid()));
			u.setRole(roleDao.findById(um.getRoleid()));
			u.setUser(userDao.findById(userName));
			u.setPassword(passwordEncoder.encode(um.getPassword()));
			u.setFirstname(um.getFirstname());
			u.setLastname(um.getLastname());
			u.setEmail(um.getEmail());
			u.setCcmailstring(um.getCcmailstring());
			u.setPhone(um.getPhone());
			u.setStatus(um.getStatus());

			dao.save(u);
			System.err.println("Save các factoryContact thành công");
			return true;
		} catch (Exception e) {
			System.err.println("Save factory ko thành công");
			return false;
		}
	}

	/**
	 * Function get Factory Model by id
	 * 
	 * @param id
	 * @return
	 */
	public UserModel findUserModelById(String id) {
		try {
			User user = dao.findById(id);
			UserModel userModel = new UserModel();

			userModel.setUsername(user.getUsername());
			userModel.setAgentid(user.getAgent() == null ? 0 : user.getAgent()
					.getAgentcode());
			userModel.setAgentShortname(user.getAgent() == null ? "" : user
					.getAgent().getShortname());
			userModel.setRoleid(user.getRole() == null ? "" : user.getRole()
					.getRoleid());
			userModel.setRolename(user.getRole() == null ? "" : user.getRole()
					.getRolename());
			userModel.setCreator(user.getUser() == null ? "" : user.getUser()
					.getUsername());
			userModel.setFirstname(user.getFirstname());
			userModel.setLastname(user.getLastname());
			userModel.setEmail(user.getEmail());
			userModel.setEmailpassword(user.getEmailpassword());
			userModel.setPhone(user.getPhone());
			userModel.setStatus(user.getStatus());
			userModel.setCcmailstring(user.getCcmailstring());

			return userModel;
		} catch (Exception e) {
			log.error(String
					.format("findUserModelById with params 'id' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to edit a user.
	 * 
	 * @param userModel
	 * @param editor
	 * @return
	 */
	public boolean editUser(UserModel userModel, String editor) {
		log.info(String.format(
				"editUser with params 'userModel', 'editor' in class: %s",
				getClass()));
		try {
			User user = dao.findById(userModel.getUsername());

			user.setUsername(userModel.getUsername());
			user.setAgent(agentDao.findById(userModel.getAgentid()));
			user.setRole(roleDao.findById(userModel.getRoleid()));
			if (userModel.getPassword().length() > 0)
				user.setPassword(passwordEncoder.encode(userModel.getPassword()));
			user.setFirstname(userModel.getFirstname());
			user.setLastname(userModel.getLastname());
			user.setEmail(userModel.getEmail());
			user.setPhone(userModel.getPhone());
			user.setStatus(userModel.getStatus());
			user.setCcmailstring(userModel.getCcmailstring());

			dao.update(user);
			return true;
		} catch (Exception e) {
			log.error(String
					.format("editUser with params 'userModel', 'editor' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to delete a user
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(String id) {
		log.info(String.format("delete with params 'id' in class: %s",
				getClass()));
		try {
			User user = dao.findById(id);
			dao.delete(user);
			return true;
		} catch (Exception e) {
			log.error(String.format(
					"delete with params 'id' in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function check if a user is existed in db
	 * 
	 * @param id
	 * @return true if existed.
	 */
	public boolean isUserExistedById(String id) {
		log.info(String.format(
				"isUserExistedById with param 'id' in class: %s", getClass()));
		try {
			if (null == dao.findById(id))
				return false;
			return true;
		} catch (Exception e) {
			log.error(String
					.format("isUserExistedById with param 'id' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function check if a user name's input password match
	 * 
	 * @param userModel
	 * @return
	 */
	public boolean isCurrentMatch(UserModel userModel) {
		log.info(String.format(
				"isCurrentMatch with params 'userModel' in class: %s",
				getClass()));
		try {
			User user = dao.findById(userModel.getUsername());

			if (passwordEncoder.matches(userModel.getPassword(),
					user.getPassword()))
				return true;
			return false;
		} catch (Exception e) {
			log.error(String
					.format("isCurrentMatch with params 'userModel' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to edit password of current user
	 * 
	 * @param userModel
	 * @param editor
	 * @param newPassword
	 * @return
	 */
	public boolean editUserPassword(UserModel userModel, String editor,
			String newPassword) {
		log.info(String
				.format("editUserPassword with params 'userModel', 'editor', 'newPassword' in class: %s",
						getClass()));
		try {
			User user = dao.findById(userModel.getUsername());

			user.setPassword(passwordEncoder.encode(newPassword));

			dao.update(user);
			return true;
		} catch (Exception e) {
			log.error(String
					.format("editUser with params 'userModel', 'editor' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function handle edit current user profile
	 * 
	 * @param userModel
	 * @param editor
	 * @return
	 */
	public boolean editUserProfile(UserModel userModel, String editor) {
		log.info(String.format(
				"editUser with params 'userModel', 'editor' in class: %s",
				getClass()));
		try {
			User user = dao.findById(userModel.getUsername());

			if (userModel.getEmailpassword().length() > 0)
				user.setEmailpassword(userModel.getEmailpassword());
			user.setFirstname(userModel.getFirstname());
			user.setLastname(userModel.getLastname());
			user.setEmail(userModel.getEmail());
			user.setPhone(userModel.getPhone());

			dao.update(user);
			return true;
		} catch (Exception e) {
			log.error(String
					.format("editUser with params 'userModel', 'editor' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}
}
