package com.chori.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chori.AbstractDaoImpl;
import com.chori.entity.Externalaccessorystock;
import com.chori.entity.Piassignexternalaccessory;

@Repository("externalAccessoryStockDao")
public class ExternalAccessoryStockDaoImpl extends
		AbstractDaoImpl<Externalaccessorystock, String> implements
		ExternalAccessoryStockDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.chori.dao.ExternalAccessStockDao#findById(int)
	 */

	@Override
	public Externalaccessorystock findByordersheetno(String ordersheetno) {
		logger.debug(String.format("findByordersheetno in class: %s",
				getClass()));
		try {
			Externalaccessorystock results = (Externalaccessorystock) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from externalaccessorystock where orderexternalaccessory.ordersheetno = '"
									+ ordersheetno).list();
			logger.debug("findByordersheetno, result  " + results.toString());
			return results;
		} catch (RuntimeException re) {
			logger.error(String.format(
					"findByordersheetno in class %s has error: %s", getClass(),
					re.getMessage()));
			throw re;
		}
	}
	@SuppressWarnings("unchecked")
	public float getInventoryQuantity(String accessoryCode){
		logger.debug(String.format("getInventoryQuantity in class: %s",
				getClass()));
		try {
			float total=0;
			List<Externalaccessorystock> results = (List<Externalaccessorystock> ) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Externalaccessorystock where orderexternalaccessory.accessory.accessorycode = '"
									+ accessoryCode+"'").list();
			for (Externalaccessorystock externalaccessorystock : results) {
				total+=externalaccessorystock.getAvailableqty();
			} 
			logger.debug("getInventoryQuantity, result  " + results.toString());
			return total;
		} catch (RuntimeException re) {
			logger.error(String.format(
					"getInventoryQuantity in class %s has error: %s", getClass(),
					re.getMessage()));
			throw re;
		}
		
		
	}
	
	@SuppressWarnings("unchecked")
	public float getEstimateQuantity(String lotNumber,String accessoryCode){
		logger.debug(String.format("getEstimateQuantity in class: %s",
				getClass()));
		try {
			float total=0;
			List<Piassignexternalaccessory> results = (List<Piassignexternalaccessory> ) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Piassignexternalaccessory where accessory.accessorycode = '"
									+ accessoryCode+"' and pi.lotnumber ='"+lotNumber+"'").list();
			for (Piassignexternalaccessory piassignexternalaccessory : results) {
				total+=piassignexternalaccessory.getEstimateqty();
			} 
			logger.debug("getEstimateQuantity, result  " + results.toString());
			return total;
		} catch (RuntimeException re) {
			logger.error(String.format(
					"getEstimateQuantity in class %s has error: %s", getClass(),
					re.getMessage()));
			throw re;
		}
		
		
	}
	
	@SuppressWarnings("unchecked")
	public float getActualAssignQuantity(String lotNumber,String accessoryCode){
		logger.debug(String.format("getActualAssignQuantity in class: %s",
				getClass()));
		try {
			float total=0;
			List<Piassignexternalaccessory> results = (List<Piassignexternalaccessory> ) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Piassignexternalaccessory where accessory.accessorycode = '"
									+ accessoryCode+"' and pi.lotnumber ='"+lotNumber+"'").list();
			for (Piassignexternalaccessory piassignexternalaccessory : results) {
				total+=piassignexternalaccessory.getActualassignqty();
			} 
			logger.debug("getActualAssignQuantity, result  " + results.toString());
			return total;
		} catch (RuntimeException re) {
			logger.error(String.format(
					"getActualAssignQuantity in class %s has error: %s", getClass(),
					re.getMessage()));
			throw re;
		}
		
		
	}
	

}
