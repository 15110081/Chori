package com.chori.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chori.AbstractDaoImpl;
import com.chori.entity.Pi;
import com.chori.entity.Pigrid;

@Repository("pigridDao")
public class PigridDaoImpl extends AbstractDaoImpl<Pigrid, Integer> implements
		PigridDao {
	
	@SuppressWarnings("unchecked")
	public int checkPigridcode(String lotNumber){
		logger.debug(String.format("checkPigridcode in class: %s",
				getClass()));
		try {
			int total=0;
			List<Pi> results = (List<Pi> ) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Pi where lotnumber ='"+lotNumber+"'").list();
			for (Pi pi : results) {
				if(pi.getPigrid()!=null){
					
				
				total=pi.getPigrid().getPigridcode();
				}
//				else{
//					total=0;
//				}
			} 
			logger.debug("getPigrid, result  " + results.toString());
			return total;
		} catch (RuntimeException re) {
			logger.error(String.format(
					"getPigrid in class %s has error: %s", getClass(),
					re.getMessage()));
			throw re;
		}
		
		
	}
	

}
