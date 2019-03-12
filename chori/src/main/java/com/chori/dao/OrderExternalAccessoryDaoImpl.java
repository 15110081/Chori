package com.chori.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chori.AbstractDaoImpl;
import com.chori.entity.Orderexternalaccessory;
import com.chori.entity.Piassignexternalaccessory;

@Repository("orderExternalAccessoryDao")
public class OrderExternalAccessoryDaoImpl extends
		AbstractDaoImpl<Orderexternalaccessory, String> implements
		OrderExternalAccessoryDao {
	@Autowired
	PiassignexternalaccessoryDao dao;
	
	
	
	@SuppressWarnings("unchecked")
	public float getActualAssignQuantity(String accessoryCode,String lotNumber,String orderSheetNo){
		logger.debug(String.format("getActualAssignQuantity in class: %s",
				getClass()));
		try {
			float total=0;
			List<Piassignexternalaccessory> results = (List<Piassignexternalaccessory> ) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Piassignexternalaccessory where accessory.accessorycode = '"
									+ accessoryCode+"' and pi.lotnumber ='"+lotNumber+"' and orderexternalaccessory.ordersheetno='"+orderSheetNo+"'").list();
			for (Piassignexternalaccessory piassignexternalaccessory : results) {
				total+=piassignexternalaccessory.getActualassignqty();
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
	public void  UpdateOrderQuantity(String orderSheetNo,float orderQuantity){
		logger.debug(String.format("getActualAssignQuantity in class: %s",
				getClass()));
		try {
			List<Piassignexternalaccessory> results = (List<Piassignexternalaccessory> ) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Piassignexternalaccessory where  orderexternalaccessory.ordersheetno='"+orderSheetNo+"'").list();
			for (Piassignexternalaccessory piassignexternalaccessory : results) {
				piassignexternalaccessory.setOrderqty(orderQuantity);
				dao.update(piassignexternalaccessory);
				
			} 
			logger.debug("getEstimateQuantity, result  " + results.toString());
		
		} catch (RuntimeException re) {
			logger.error(String.format(
					"getEstimateQuantity in class %s has error: %s", getClass(),
					re.getMessage()));
			throw re;
		}
		
		
	}
	
}
