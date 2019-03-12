package com.chori.service;

import java.util.List;

import com.chori.AbstractService;
import com.chori.entity.Agent;
import com.chori.model.AgentModel;

public interface AgentService extends AbstractService<Agent, Integer> {
	public List<AgentModel> getAllAgentModel();

	public boolean deleteAgent(Integer id);

	public boolean addNewAgent(AgentModel agentAddModel, String userId);

	public AgentModel findAgentModelById(Integer id);

	public boolean editAgent(AgentModel agentAddModel, String userId);

	// public boolean isAgentExistedById(Integer id);
	boolean isAgentExistedByShortname(String shortname);
}
