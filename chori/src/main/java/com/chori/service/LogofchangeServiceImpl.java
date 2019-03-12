package com.chori.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.chori.AbstractDao;
import com.chori.AbstractServiceImpl;
import com.chori.dao.LogofchangeDao;
import com.chori.dao.PIDao;
import com.chori.dao.UserDao;
import com.chori.dao.WidthDao;
import com.chori.entity.Logofchange;
import com.chori.entity.Width;
import com.chori.model.LogofchangeModel;
import com.chori.model.WidthModel;

@Repository("logofchangeService")
public class LogofchangeServiceImpl extends AbstractServiceImpl<Logofchange, Integer>
implements LogofchangeService {
	
	private LogofchangeDao logofchangeDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PIDao piDao;
		
	public LogofchangeServiceImpl() {
	}

	@Autowired
	public LogofchangeServiceImpl(
			@Qualifier("logofchangeDao") AbstractDao<Logofchange, Integer> abstractDao) {
		super(abstractDao);
		this.logofchangeDao = (LogofchangeDao) abstractDao;
	}
	
	@Override
	public List<LogofchangeModel> getAllLogofchangeModel() {
		log.info(String.format("getAllLogofchange in class: %s", getClass()));
		try {
			log.debug("get all Role in DB after that return a list RoleEntity");
			List<Logofchange> lstLogofchange = logofchangeDao.getAll();

			LogofchangeModel logofchangeMod;
			List<LogofchangeModel> lst = new ArrayList<LogofchangeModel>();

			for (Logofchange logofchange : lstLogofchange) {

				logofchangeMod = new LogofchangeModel();
				logofchangeMod.setFromemail(logofchange.getFromemail());
				logofchangeMod.setLogofchangecode(logofchange.getLogofchangecode());
				logofchangeMod.setLotnumber(logofchange.getPi().getLotnumber());
				logofchangeMod.setToemail(logofchange.getToemail());
				logofchangeMod.setSubject(logofchange.getSubject());
				logofchangeMod.setCreatedate(logofchange.getCreatedate());

				lst.add(logofchangeMod);
			}
			log.debug("getAllLogofchange successfully");
			return lst;
		} catch (NullPointerException ne) {
			log.error(String.format("getAllLogofchange in class: %s has error: %s",
					getClass(), ne.getMessage()));

		}
		return null;
	}

	@Override
	public boolean addLogofchange(LogofchangeModel logofchangeModel, String userName) {
		log.info(String.format("addLogofchange in class: %s", getClass()));
		try {
			Logofchange logofchange = new Logofchange();
			//logofchange.setLogofchangecode(logofchange.getLogofchangecode());
			logofchange.setPi(piDao.findById(logofchangeModel.getLotnumber()));
			logofchange.setToemail(logofchangeModel.getToemail());
			logofchange.setCreatedate(Calendar.getInstance().getTime());
			logofchange.setSubject(logofchangeModel.getSubject());
			logofchange.setCcmailstring(logofchangeModel.getCcmailstring());
			logofchange.setAttachedfileurl1(logofchangeModel.getAttachfileurl1());
			logofchange.setAttachedfileurl2(logofchangeModel.getAttachfileurl2());
			logofchange.setAttachedfileurl3(logofchangeModel.getAttachfileurl3());
			logofchange.setAttachedfileurl4(logofchangeModel.getAttachfileurl4());
			logofchange.setAttachedfileurl5(logofchangeModel.getAttachfileurl5());
			logofchange.setAttachedfileurl6(logofchangeModel.getAttachfileurl6());
			logofchange.setAttachedfileurl7(logofchangeModel.getAttachfileurl7());
			logofchange.setAttachedfileurl8(logofchangeModel.getAttachfileurl8());
			logofchange.setAttachedfileurl9(logofchangeModel.getAttachfileurl9());
			logofchange.setAttachedfileurl10(logofchangeModel.getAttachfileurl10());
			

			logofchangeDao.save(logofchange);
			log.debug("add new logofchange into database successfully");
			return true;
		} catch (Exception e) {
			log.debug("add new logofchange into database fail");

			System.err
					.println("add new logofchange into database fail, method addLogofchange(), class LogofchangeService");
			return false;
		}
	}

}
