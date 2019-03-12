package com.chori.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chori.AbstractDaoImpl;
import com.chori.entity.Fpi;

@Repository("fpiDao")
public class FpiDaoImpl extends AbstractDaoImpl<Fpi, Integer> implements FpiDao {
	/**
	 * This function is used to get list fpi By Lot Number 
	 * @param lotnumber

	 * @return list fpi
	 */
	@SuppressWarnings("unchecked")
	public List<Fpi> getListFpiByLotNumber(String lotNumber){
		logger.debug(String
				.format("getListFpiByLotNumber in class: %s",
						getClass()));
		try {
			List<Fpi> results = (List<Fpi>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Fpi where pi.lotnumber = '"
									+ lotNumber
								    + "'").list();
			logger.debug("getListFpiByLotNumber successfull, result size: "
					+ results.size());

			return results;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getListFpiByLotNumber in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}

	/**
	 * This function is used to get fpi that has biggest version
	 * @param lotNumber
	 * @return
	 */
	public Fpi getFpiHasBiggestVersionByLotNumber(String lotNumber){
		logger.debug(String
				.format("getFpiHasBiggestVersionByLotNumber in class: %s",
						getClass()));
		try {
			List<Fpi> lstFpi = getListFpiByLotNumber(lotNumber);
			int lstLength = lstFpi.size();
			int maxVersion = 1;
			int index = 0;
			for(int i=0;i<lstLength;++i){
				if(lstFpi.get(i).getVersion()>maxVersion){
					maxVersion = lstFpi.get(i).getVersion();
					index = i;
				}
			}
			return lstFpi.get(index);
		} catch (RuntimeException re) {
			logger.error(String
					.format("getFpiHasBiggestVersionByLotNumber in class %s has error: %s",
							getClass(), re.getMessage()));
//			throw re;
			return null;
		}
	}
}
