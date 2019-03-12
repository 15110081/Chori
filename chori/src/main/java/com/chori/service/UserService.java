package com.chori.service;

import java.util.List;

import com.chori.AbstractService;
import com.chori.entity.User;
import com.chori.model.UserModel;

public interface UserService extends AbstractService<User, String> {
	public List<UserModel> getAllUserModel();

	public boolean addNewUser(UserModel um, String userName);

	public UserModel findUserModelById(String id);

	public boolean editUser(UserModel userModel, String editor);

	public boolean delete(String id);

	public boolean isUserExistedById(String id);

	public boolean isCurrentMatch(UserModel userModel);

	public boolean editUserPassword(UserModel userModel, String editor,
			String newPassword);

	public boolean editUserProfile(UserModel userModel, String editor);
}
