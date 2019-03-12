package com.chori.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chori.AbstractDaoImpl;
import com.chori.entity.Fpidetail;
import com.chori.entity.Pigriddetail;
import com.chori.entity.Rfpidetail;

@Repository("rfpidetailDao")
public class RfpidetailDaoImpl extends AbstractDaoImpl<Rfpidetail, Integer> implements RfpidetailDao{
	/**
	 * This function is used to get List Rfpi detail By Lot Number And Version | Hàm đc dùng để lấy ra rfpi detail qua lot number và version
	 * @param lotnumber
	 * @param version
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Rfpidetail> getListRfpidetailByLotNumberAndVersion(
			String lotnumber, Integer version) {
		logger.debug(String
				.format("getListRfpidetailByLotNumberAndVersion in class: %s",
						getClass()));
		try {
			List<Rfpidetail> results = (List<Rfpidetail>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Rfpidetail where rfpi.pi.lotnumber = '"
									+ lotnumber
									+ "' and version = "
									+ version + "").list();
			logger.debug("getListRfpidetailByLotNumberAndVersion successfully, result size: "
					+ results.size());

			return results;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getListRfpidetailByLotNumberAndVersion in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}
	
	/**
	 * This function is used to calculate rfpi value Of Rfpi detail By Rfpi detail code ColorCode GarmentStyleCode SizeCode
	 * @param rfpicode
	 * @param colorcode
	 * @param garmentstylecode
	 * @param sizecode
	 * @return
	 */
	public Integer getRfpiValueOfRfpidetailByRfpidetailcodeColorCodeGarmentStyleCodeSizeCode(
			Integer rfpicode, String colorcode, String garmentstylecode,
			Integer sizecode) {
		logger.debug(String
				.format("getFpiValueOfFpidetailByFpidetailcodeColorCodeGarmentStyleCodeSizeCode in class: %s",
						getClass()));
		try {
			Rfpidetail results = (Rfpidetail) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Rfpidetail where rfpi.rfpigrid = "
									+ rfpicode + " and color.colorcode = '"
									+ colorcode
									+ "' and garmentstyle.garmentstylecode= '"
									+ garmentstylecode
									+ "' and size.sizecode = " + sizecode).uniqueResult();
			logger.debug("getFpiValueOfFpidetailByFpidetailcodeColorCodeGarmentStyleCodeSizeCode successfull, Fpi value: "
					+ results.getRfpivalue());

			return results.getRfpivalue();
		} catch (RuntimeException re) {
			logger.error(String
					.format("getFpiValueOfFpidetailByFpidetailcodeColorCodeGarmentStyleCodeSizeCode in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}
}
