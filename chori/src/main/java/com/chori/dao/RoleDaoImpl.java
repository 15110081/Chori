package com.chori.dao;

import org.springframework.stereotype.Repository;

import com.chori.AbstractDaoImpl;
import com.chori.entity.Role;

@Repository("roleDao")
public class RoleDaoImpl extends AbstractDaoImpl<Role, String> implements
		RoleDao {

}
