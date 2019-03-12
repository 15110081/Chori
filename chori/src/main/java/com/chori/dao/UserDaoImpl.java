package com.chori.dao;

import org.springframework.stereotype.Repository;

import com.chori.AbstractDaoImpl;
import com.chori.entity.User;

@Repository("userDao")
public class UserDaoImpl extends AbstractDaoImpl<User, String> implements
		UserDao {

}
