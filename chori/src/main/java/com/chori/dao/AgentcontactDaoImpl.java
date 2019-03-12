package com.chori.dao;

import org.springframework.stereotype.Repository;

import com.chori.AbstractDaoImpl;
import com.chori.entity.Agentcontact;

@Repository("agentcontactDao")
public class AgentcontactDaoImpl extends AbstractDaoImpl<Agentcontact, Integer>
		implements AgentcontactDao {

}
