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
import com.chori.dao.AgentDao;
import com.chori.dao.AgentcontactDao;
import com.chori.dao.UserDao;
import com.chori.entity.Agent;
import com.chori.entity.Agentcontact;
import com.chori.model.AgentModel;
import com.chori.model.AgentcontactModel;

@Service("agentService")
public class AgentServiceImpl extends AbstractServiceImpl<Agent, Integer>
		implements AgentService {
	private AgentDao dao;

	@Autowired
	UserDao userDao;

	@Autowired
	AgentcontactDao agentcontactDao;

	@Autowired
	public AgentServiceImpl(
			@Qualifier("agentDao") AbstractDao<Agent, Integer> abstractDao) {
		super(abstractDao);
		this.dao = (AgentDao) abstractDao;
	}

	/**
	 * This function is used to get a list of all agent in database
	 * 
	 * @return List<UserModel>
	 */
	public List<AgentModel> getAllAgentModel() {
		log.info(String.format("getAllAgentModel in class: %s", getClass()));
		try {
			log.debug("get all Agent in DB after that return a list agentModel");
			List<Agent> lstAgent = dao.getAll();

			AgentModel am;
			List<AgentModel> lstAgentModel = new ArrayList<AgentModel>();
			AgentcontactModel agentcontactModel;

			for (Agent a : lstAgent) {

				am = new AgentModel();
				am.setAgentcode(a.getAgentcode());
				am.setShortname(a.getShortname());
				am.setLongname(a.getLongname());
				am.setAddress(a.getAddress());
				am.setTel(a.getTel());
				am.setFax(a.getFax());
				am.setTaxno(a.getTaxno());
				am.setCreatedate(a.getCreatedate());
				am.setStatus(a.getStatus());
				am.setRemark(a.getRemark());
				am.setCreator(a.getUser() == null ? "" : a.getUser()
						.getUsername());

				Set<Agentcontact> lstAgentContact = a.getAgentcontacts();
				for (Agentcontact agentcontact : lstAgentContact) {
					agentcontactModel = new AgentcontactModel();
					agentcontactModel.setAgentCode(agentcontact.getAgent()
							.getAgentcode());
					agentcontactModel.setAgentcontactcode(agentcontact
							.getAgentcontactcode());
					agentcontactModel.setName(agentcontact.getName());
					agentcontactModel.setEmail(agentcontact.getEmail());
					agentcontactModel.setTel(agentcontact.getTel());
					agentcontactModel
							.setCreator(agentcontact.getUser() == null ? ""
									: agentcontact.getUser().getUsername());
					agentcontactModel.setCreatedate(agentcontact
							.getCreatedate());
					//

					am.getAgentcontactModellist().add(agentcontactModel);
				}

				lstAgentModel.add(am);
			}
			log.debug("getAllAgentModel successfully");
			return lstAgentModel;
		} catch (Exception e) {
			log.error(String.format(
					"getAllAgentModel in class: %s has error: %s", getClass(),
					e.getMessage()));
			throw e;
		}
	}

	@Override
	public AgentModel findAgentModelById(Integer id) {
		log.info(String.format(
				"findAgentModelById with param 'id' in class: %s", getClass()));
		try {
			AgentModel am = new AgentModel();
			Agent a = dao.findById(id);

			// field for NV
			am.setAgentcode(a.getAgentcode());
			am.setShortname(a.getShortname());
			am.setLongname(a.getLongname());
			am.setAddress(a.getAddress());
			am.setTel(a.getTel());
			am.setFax(a.getFax());
			am.setTaxno(a.getTaxno());
			am.setCreatedate(a.getCreatedate());
			am.setStatus(a.getStatus());
			am.setRemark(a.getRemark());
			am.setCreator(a.getUser() == null ? " " : a.getUser().getUsername());

			log.debug("findAgentModelById successfully");
			AgentcontactModel agentcontactModel;

			Set<Agentcontact> lstAgentcontact = a.getAgentcontacts();
			for (Agentcontact agentcontact : lstAgentcontact) {
				agentcontactModel = new AgentcontactModel();
				agentcontactModel.setAgentcontactcode(agentcontact
						.getAgentcontactcode());
				agentcontactModel.setAgentCode(agentcontact.getAgent()
						.getAgentcode());
				agentcontactModel
						.setCreator(agentcontact.getUser() == null ? " "
								: agentcontact.getUser().getUsername());
				agentcontactModel.setName(agentcontact.getName());
				agentcontactModel.setEmail(agentcontact.getEmail());
				agentcontactModel.setTel(agentcontact.getTel());
				agentcontactModel.setCreatedate(agentcontact.getCreatedate());

				am.getAgentcontactModellist().add(agentcontactModel);
			}

			return am;
		} catch (Exception e) {
			log.error(String
					.format("findAgentModelById with param 'id' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	@Override
	public boolean addNewAgent(AgentModel agentAddModel, String userId) {
		try {
			Agent a = new Agent();

			a.setShortname(agentAddModel.getShortname());
			a.setLongname(agentAddModel.getLongname());
			a.setAddress(agentAddModel.getAddress());
			a.setTel(agentAddModel.getTel());
			a.setFax(agentAddModel.getFax());
			a.setTaxno(agentAddModel.getTaxno());
			a.setRemark(agentAddModel.getRemark());
			a.setStatus(agentAddModel.getStatus());
			a.setCreatedate(new Date());
			a.setUser(userDao.findById(userId));

			dao.save(a);

			Agentcontact agentcontact;

			Set<AgentcontactModel> newList = agentAddModel
					.getAgentcontactModellist();
			for (AgentcontactModel agentcontactModel : newList) {
				agentcontact = new Agentcontact();
				agentcontact.setAgent(dao.findById(a.getAgentcode()));
				agentcontact.setUser(userDao.findById(userId));
				agentcontact.setName(agentcontactModel.getName());
				agentcontact.setEmail(agentcontactModel.getEmail());
				agentcontact.setTel(agentcontactModel.getTel());
				agentcontact.setCreatedate(new Date());

				agentcontactDao.save(agentcontact);
			}
			return true;
		} catch (Exception e) {
			log.error(String
					.format("addNewAgent with params 'agentAddModel', 'userId' in class %s has error: %s",
							getClass(), e.getMessage()));
			// return false;
			throw e;
		}
	}

	@Override
	public boolean editAgent(AgentModel agentAddModel, String userId) {
		log.info(String.format(
				"editAgent with params 'agentAddModel', 'userId' in class: %s",
				getClass()));
		try {
			Agent a = dao.findById(agentAddModel.getAgentcode());
			a.setAgentcode(agentAddModel.getAgentcode());
			a.setShortname(agentAddModel.getShortname());
			a.setLongname(agentAddModel.getLongname());
			a.setAddress(agentAddModel.getAddress());
			a.setTel(agentAddModel.getTel());
			a.setFax(agentAddModel.getFax());
			a.setTaxno(agentAddModel.getTaxno());
			a.setRemark(agentAddModel.getRemark());
			a.setStatus(agentAddModel.getStatus());
			dao.update(a);

			Set<AgentcontactModel> newList = agentAddModel
					.getAgentcontactModellist();
			Set<Agentcontact> oldList = a.getAgentcontacts();

			if (oldList.size() == 0) {
				if (newList.size() == 0) {
					return true;// 2 list rá»—ng nhÆ° nhau thÃ¬ ko lÃ m gÃ¬
				} else {// náº¿u list má»›i cÃ³ contact, thÃ¬ add háº¿t list
						// má»›i Ä‘Ã³ vÃ o
					Agentcontact agentcontact;

					for (AgentcontactModel agentcontactModel : newList) {
						agentcontact = new Agentcontact();
						agentcontact.setAgent(dao.findById(agentAddModel
								.getAgentcode()));
						agentcontact.setUser(userDao.findById(userId));
						agentcontact.setName(agentcontactModel.getName());
						agentcontact.setEmail(agentcontactModel.getEmail());
						agentcontact.setTel(agentcontactModel.getTel());
						agentcontact.setCreatedate(new Date());

						agentcontactDao.save(agentcontact);
					}
					return true;
				}
			} else {// TrÆ°á»�ng há»£p list cÅ© ko rá»—ng
				if (newList.size() == 0) {// vÃ  list má»›i rá»—ng, thÃ¬ del
											// háº¿t contact
					for (Agentcontact agentcontact : oldList) {
						agentcontactDao.delete(agentcontactDao
								.findById(agentcontact.getAgentcontactcode()));
					}
					return true;

				} else {// list má»›i khÃ´ng rá»—ng

					// lá»�c qua list má»›i, agentcontactcode ==0 thÃ¬ thÃªm
					// má»›i
					Agentcontact agentcontact;

					for (AgentcontactModel agentcontactModel : newList) {
						if (agentcontactModel.getAgentcontactcode() == 0) {
							agentcontact = new Agentcontact();
							agentcontact.setAgent(dao.findById(agentAddModel
									.getAgentcode()));
							agentcontact.setUser(userDao.findById(userId));
							agentcontact.setName(agentcontactModel.getName());
							agentcontact.setEmail(agentcontactModel.getEmail());
							agentcontact.setTel(agentcontactModel.getTel());
							agentcontact.setCreatedate(new Date());

							agentcontactDao.save(agentcontact);
						}
					}

					// true lÃ  tá»“n táº¡i cáº£ 2 list, false lÃ  ko tá»“n
					// táº¡i trong list má»›i
					boolean flag = false;

					// Táº¡o list Customercontact Ä‘á»ƒ remove
					List<Agentcontact> lstToBeRemove = new ArrayList<Agentcontact>();
					List<AgentcontactModel> lstToBeUpdate = new ArrayList<AgentcontactModel>();

					for (AgentcontactModel agentcontactModel : newList) {
						for (Agentcontact agentcontact2 : oldList) {
							// náº¿u contact trong list cÅ© ko tá»“n táº¡i trong
							// list má»›i, náº¿u contact code mÃ  = nhau thÃ¬
							// lÃ  cÃ³ tá»‘n táº¡i, gÃ¡n flag = true
							if (agentcontact2.getAgentcontactcode() == agentcontactModel
									.getAgentcontactcode()) {
								flag = true;
								break;
							}
						}
						if (flag == true) {
							lstToBeUpdate.add(agentcontactModel);
							flag = false;// set láº¡i thÃ nh ko tá»“n táº¡i
						}
					}

					// Lá»�c ra nhá»¯ng contact ko tá»“n táº¡i trong list má»›i
					flag = false;// máº·c Ä‘á»‹nh lÃ  ko tá»“n táº¡i
					for (Agentcontact agentcontact2 : oldList) {
						for (AgentcontactModel agentcontactModel : newList) {
							if (agentcontact2.getAgentcontactcode() == agentcontactModel
									.getAgentcontactcode()) {
								flag = true;
								break;
							}
						}

						if (flag == false) {
							lstToBeRemove.add(agentcontact2);
						}
						flag = false;
					}

					// láº·p qua list cáº§n remove
					for (Agentcontact agentcontact2 : lstToBeRemove) {
						agentcontactDao.delete(agentcontactDao
								.findById(agentcontact2.getAgentcontactcode()));
					}

					Agentcontact ccNeedUpdate;
					// láº·p qua list cáº§n update
					for (AgentcontactModel agentcontactModel : lstToBeUpdate) {
						ccNeedUpdate = agentcontactDao
								.findById(agentcontactModel
										.getAgentcontactcode());

						ccNeedUpdate.setName(agentcontactModel.getName());
						ccNeedUpdate.setEmail(agentcontactModel.getEmail());
						ccNeedUpdate.setTel(agentcontactModel.getTel());

						agentcontactDao.update(ccNeedUpdate);
					}

					return true;
				}
			}

			// return false;
		} catch (Exception e) {
			log.error(String
					.format("editAgent with params 'agentAddModel', 'userId' in class %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	// @Override
	// public boolean isAgentExistedById(Integer id) {
	// log.info(String.format("isAgentExistedById with param 'id' in class: %s",
	// getClass()));
	// try{
	// if(null==dao.findById(id))
	// return false;
	// return true;
	// }catch(Exception e){
	// log.error(String.format("isAgentExistedById with param 'id' in class: %s has error: %s",
	// getClass(), e.getMessage()));
	// throw e;
	// }
	// }

	@Override
	public boolean isAgentExistedByShortname(String shortname) {
		log.info(String
				.format("isAgentExistedByShortname with param 'shortname' in class: %s",
						getClass()));
		try {
			List<Agent> lstAgent = dao.getAll();

			for (Agent a : lstAgent) {
				if (a.getShortname().equals(shortname))
					return true;
			}

			return false;
		} catch (Exception e) {
			log.error(String
					.format("isAgentExistedByShortname with param 'shortname' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	public boolean deleteAgent(Integer id) {
		log.info(String.format("delete with params 'id' in class: %s",
				getClass()));
		try {
			Agent agent = dao.findById(id);

			Set<Agentcontact> lst = agent.getAgentcontacts();
			for (Agentcontact agentcontact : lst) {
				agentcontactDao.delete(agentcontactDao.findById(agentcontact
						.getAgentcontactcode()));
			}

			dao.deleteById(id);
			return true;
		} catch (Exception e) {
			log.error(String.format(
					"delete with params 'id' in class %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}
}
