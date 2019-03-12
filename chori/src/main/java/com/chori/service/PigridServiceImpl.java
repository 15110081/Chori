package com.chori.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chori.AbstractServiceImpl;
import com.chori.dao.PigridDao;
import com.chori.dao.UserDao;
import com.chori.entity.Pigrid;
import com.chori.entity.Unit;
import com.chori.model.PigridModel;

@Repository("pigridService")

public class PigridServiceImpl extends AbstractServiceImpl<Pigrid, String> implements PigridService {
	
	@Autowired
	private PigridDao dao;
	@Autowired
	private UserDao userDao;
	
	/* (non-Javadoc)
	 * @see com.chori.service.PigridService#checkPigridcode(java.lang.String)
	 */
	@Override
	public int checkPigridcode(String lotNumber){
	return dao.checkPigridcode(lotNumber);
	}
	/* (non-Javadoc)
	 * @see com.chori.service.PigridService#addPigrid(com.chori.model.PigridModel, java.lang.String)
	 */
	@Override
	public boolean addPigrid(Pigrid pg) {
		try {
			
			
		
			
			
			dao.save(pg);
			System.err.println(pg.getPigridcode());
			log.debug("addPigrid successfullly");
			return true;
		} catch (Exception e) {
			log.error(String.format(
					"addPigrid with param 'PiGridModel' in class: %s has error: %s",
					getClass(), e.getMessage()));
			System.err.println(String.format(
					"addPigrid with param 'PiGridModel' in class: %s has error: %s",
					getClass(), e.getMessage()));
			return false;
		}

	}
	/* (non-Javadoc)
	 * @see com.chori.service.PigridService#deletePigrid(int)
	 */
	@Override
	public boolean deletePigrid(int pigridCode) {
		log.info(String.format("deletePigrid with param 'pigridCode' in class: %s",
				getClass()));
		try {
			Pigrid pg = dao.findById(pigridCode);
			dao.delete(pg);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
}
