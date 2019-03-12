package com.chori.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.chori.AbstractDao;
import com.chori.AbstractServiceImpl;
import com.chori.dao.AgentcontactDao;
import com.chori.entity.Agentcontact;

public class AgentcontactServiceImpl extends
		AbstractServiceImpl<Agentcontact, Integer> implements
		AgentcontactService {
	private AgentcontactDao dao;

	public AgentcontactServiceImpl() {

	}

	@Autowired
	public AgentcontactServiceImpl(
			@Qualifier("agentcontactDao") AbstractDao<Agentcontact, Integer> abstractDao) {
		super(abstractDao);
		this.dao = (AgentcontactDao) abstractDao;
	}
}
