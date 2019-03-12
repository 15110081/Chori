package com.chori.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chori.AbstractDaoImpl;
import com.chori.entity.Fpidetail;

@Repository("fpidetailDao")
public class FpidetailDaoImpl extends AbstractDaoImpl<Fpidetail, Integer> implements FpidetailDao{

	/**
	 * This function is used to get List Fpi detail By Lot Number And Version | Hàm đc dùng để lấy ra fpi detail qua lot number và version
	 * @param lotnumber
	 * @param version
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Fpidetail> getListFpidetailByLotNumberAndVersion(
			String lotnumber, Integer version) {
		logger.debug(String
				.format("getListFpidetailByLotNumberAndVersion in class: %s",
						getClass()));
		try {
			List<Fpidetail> results = (List<Fpidetail>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Fpidetail where fpi.pi.lotnumber = '"
									+ lotnumber
									+ "' and fpi.version = "
									+ version + "").list();
			logger.debug("getListFpidetailByLotNumberAndVersion successfull, result size: "
					+ results.size());

			return results;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getListFpidetailByLotNumberAndVersion in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}	
	
	
	/**
	 * This function is used to get Fpidetail by fpiCode
	 * 
	 * @param garmentStyleName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Fpidetail> getListFpidetailByFpidetailcode(Integer fpidetailcode) {
		logger.debug(String.format(
				"getListFpidetailByFpidetailcode in class: %s", getClass()));
		try {
			List<Fpidetail> results = (List<Fpidetail>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from getListFpidetailByFpidetailcode where fpidetailcode = "
									+ fpidetailcode).list();
			logger.debug("getListFpidetailByFpidetailcode successfull, result size: "
					+ results.size());

			return results;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getListFpidetailByFpidetailcode in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}

	/**
	 * This function is used to get Fpi detail by Fpidetailcode ColorCode
	 * GarmentStyleCode (for white)
	 * 
	 * @param Fpidetailcode
	 * @param colorcode
	 * @param garmentstylecode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Fpidetail> getListFpidetailByFpidetailcodeColorCodeGarmentStyleCode(
			Integer fpidetailcode, String colorcode, String garmentstylecode) {
		logger.debug(String
				.format("getListFpidetailByFpidetailcodeColorCodeGarmentStyleCode in class: %s",
						getClass()));
		try {
			List<Fpidetail> results = (List<Fpidetail>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Fpidetail where fpidetailcode = "
									+ fpidetailcode + " and color.colorcode = '"
									+ colorcode
									+ "' and garmentstyle.garmentstylecode= '"
									+ garmentstylecode + "'").list();
			logger.debug("getListFpidetailByFpidetailcodeColorCodeGarmentStyleCode successfull, result size: "
					+ results.size());

			return results;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getListFpidetailByFpidetailcodeColorCodeGarmentStyleCode in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}

	/**
	 * This function is used to get Fpi detail by Fpidetailcode ColorCode
	 * GarmentStyleCode (for color, not white)
	 */
	@SuppressWarnings("unchecked")
	public List<Fpidetail> getListFpidetailByFpidetailcodeColorCodeGarmentStyleCodeNotWhite(
			Integer fpidetailcode, String colorcode, String garmentstylecode) {
		logger.debug(String
				.format("getListFpidetailByFpidetailcodeColorCodeGarmentStyleCodeNotWhite in class: %s",
						getClass()));
		try {
			List<Fpidetail> results = (List<Fpidetail>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Fpidetail where fpidetailcode = "
									+ fpidetailcode + " and color.colorcode != '"
									+ colorcode
									+ "' and garmentstyle.garmentstylecode= '"
									+ garmentstylecode + "'").list();
			logger.debug("getListFpidetailByFpidetailcodeColorCodeGarmentStyleCodeNotWhite successfull, result size: "
					+ results.size());

			return results;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getListFpidetailByFpidetailcodeColorCodeGarmentStyleCodeNotWhite in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}

	/**
	 * This function is used to get list fpi detail by fpidetailcode ColorCode 
	 * GarmentStyleCode TypeCode For White
	 * 
	 * @param fpidetailcode
	 * @param colorcode
	 * @param garmentstylecode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Fpidetail> getListFpidetailByFpidetailcodeColorCodeGarmentStyleCodeTypeCodeForWhite(
			Integer fpidetailcode, String colorcode, String garmentstylecode,
			String typecode) {
		logger.debug(String
				.format("getListFpidetailByFpidetailcodeColorCodeGarmentStyleCodeTypeCodeForWhite in class: %s",
						getClass()));
		try {
			List<Fpidetail> results = (List<Fpidetail>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Fpidetail where fpidetailcode = "
									+ fpidetailcode + " and color.colorcode = '"
									+ colorcode
									+ "' and garmentstyle.garmentstylecode= '"
									+ garmentstylecode
									+ "' and size.type.typecode = '" + typecode
									+ "'").list();
			logger.debug("getListFpidetailByFpidetailcodeColorCodeGarmentStyleCodeTypeCodeForWhite successfull, result size: "
					+ results.size());

			return results;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getListFpidetailByFpidetailcodeColorCodeGarmentStyleCodeTypeCodeForWhite in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}

	/**
	 * This function is used to get list fpi detail by Fpidetailcode ColorCode
	 * GarmentStyleCode TypeCode Not For White
	 * 
	 * @param fpidetailcode
	 * @param colorcode
	 * @param garmentstylecode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Fpidetail> getListFpidetailByFpidetailcodeColorCodeGarmentStyleCodeTypeCodeNotForWhite(
			Integer fpidetailcode, String colorcode, String garmentstylecode,
			String typecode) {
		logger.debug(String
				.format("getListFpidetailByFpidetailcodeColorCodeGarmentStyleCodeTypeCodeNotForWhite in class: %s",
						getClass()));
		try {
			List<Fpidetail> results = (List<Fpidetail>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Fpidetail where fpidetailcode = "
									+ fpidetailcode + " and color.colorcode != '"
									+ colorcode
									+ "' and garmentstyle.garmentstylecode= '"
									+ garmentstylecode
									+ "' and size.type.typecode = '" + typecode
									+ "'").list();
			logger.debug("getListFpidetailByFpidetailcodeColorCodeGarmentStyleCodeTypeCodeNotForWhite successfull, result size: "
					+ results.size());

			return results;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getListFpidetailByFpidetailcodeColorCodeGarmentStyleCodeTypeCodeNotForWhite in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}
	
	/**
	 * This function is used to get fpi value of Fpi detail By Fpidetailcode ColorCode GarmentStyle Code SizeCode
	 * @param fpicode
	 * @param colorcode
	 * @param garmentstylecode
	 * @param sizecode
	 * @return
	 */
	public Integer getFpiValueOfFpidetailByFpidetailcodeColorCodeGarmentStyleCodeSizeCode(
			Integer fpicode, String colorcode, String garmentstylecode,
			Integer sizecode) {
		logger.debug(String
				.format("getFpiValueOfFpidetailByFpidetailcodeColorCodeGarmentStyleCodeSizeCode in class: %s",
						getClass()));
		try {
			Fpidetail results = (Fpidetail) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Fpidetail where fpi.fpicode = "
									+ fpicode + " and color.colorcode = '"
									+ colorcode
									+ "' and garmentstyle.garmentstylecode= '"
									+ garmentstylecode
									+ "' and size.sizecode = " + sizecode).uniqueResult();
			logger.debug("getFpiValueOfFpidetailByFpidetailcodeColorCodeGarmentStyleCodeSizeCode successfull, Fpi value: "
					+ results.getFpivalue());

			return results.getFpivalue();
		} catch (RuntimeException re) {
			logger.error(String
					.format("getFpiValueOfFpidetailByFpidetailcodeColorCodeGarmentStyleCodeSizeCode in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}
}
