package com.chori.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chori.AbstractDaoImpl;
import com.chori.entity.Rfpi;

@Repository("rfpiDao")
public class RfpiDaoImpl extends AbstractDaoImpl<Rfpi, Integer> implements RfpiDao{
	/**
	 * This function is used to get list rfpi By Lot Number 
	 * @param lotnumber

	 * @return list rfpi
	 */
	
	@SuppressWarnings("unchecked")
	public List<Rfpi> getListRfpiByLotNumber(String lotNumber){
		logger.debug(String
				.format("getListRfpiByLotNumber in class: %s",
						getClass()));
		try {
			List<Rfpi> results = (List<Rfpi>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Rfpi where pi.lotnumber = '"
									+ lotNumber
								    + "'").list();
			logger.debug("getListRfpiByLotNumber successfull, result size: "
					+ results.size());

			return results;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getListRfpiByLotNumber in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}
	
	/**
	 * This function is used to get rfpi that has biggest version
	 * @param lotNumber
	 * @return
	 */
	public Rfpi getRfpiHasBiggestVersionByLotNumber(String lotNumber){
		logger.debug(String
				.format("getRfpiHasBiggestVersionByLotNumber in class: %s",
						getClass()));
		try {
			List<Rfpi> lstRfpi = getListRfpiByLotNumber(lotNumber);
			int lstLength = lstRfpi.size();
			int maxVersion = 1;
			int index = 0;
			for(int i=0;i<lstLength;++i){
				if(lstRfpi.get(i).getVersion()>maxVersion){
					maxVersion = lstRfpi.get(i).getVersion();
					index = i;
				}
			}
			return lstRfpi.get(index);
		} catch (RuntimeException re) {
			logger.error(String
					.format("getRfpiHasBiggestVersionByLotNumber in class %s has error: %s",
							getClass(), re.getMessage()));
//			throw re;
			return null;
		}
	}
}
