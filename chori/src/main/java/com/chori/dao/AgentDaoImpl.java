package com.chori.dao;

import org.springframework.stereotype.Repository;

import com.chori.AbstractDaoImpl;
import com.chori.entity.Agent;

@Repository("agentDao")
public class AgentDaoImpl extends AbstractDaoImpl<Agent, Integer> implements
		AgentDao {

}
