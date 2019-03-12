package com.chori.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.chori.AbstractDaoImpl;
import com.chori.entity.Pigriddetail;

@Repository("pigriddetailDao")
public class PigriddetailDaoImpl extends AbstractDaoImpl<Pigriddetail, Integer>
		implements PigriddetailDao {

	/**
	 * This function is used to get Pigriddetail by pigridCode
	 * 
	 * @param garmentStyleName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Pigriddetail> getListPigriddetailByPigridcode(Integer pigridcode) {
		logger.debug(String.format(
				"getListPigriddetailByPigridcode in class: %s", getClass()));
		try {
			List<Pigriddetail> results = (List<Pigriddetail>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Pigriddetail where pigrid.pigridcode = "
									+ pigridcode).list();
			logger.debug("getListPigriddetailByPigridcode successfull, result size: "
					+ results.size());

			return results;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getListPigriddetailByPigridcode in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}

	/**
	 * This function is used to get Pi grid detail by Pigridcode ColorCode
	 * GarmentStyleCode (for white)
	 * 
	 * @param pigridcode
	 * @param colorcode
	 * @param garmentstylecode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Pigriddetail> getListPigriddetailByPigridcodeColorCodeGarmentStyleCode(
			Integer pigridcode, String colorcode, String garmentstylecode) {
		logger.debug(String
				.format("getListPigriddetailByPigridcodeColorCodeGarmentStyleCode in class: %s",
						getClass()));
		try {
			List<Pigriddetail> results = (List<Pigriddetail>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Pigriddetail where pigrid.pigridcode = "
									+ pigridcode + " and color.colorcode = '"
									+ colorcode
									+ "' and garmentstyle.garmentstylecode= '"
									+ garmentstylecode + "'").list();
			logger.debug("getListPigriddetailByPigridcodeColorCodeGarmentStyleCode successfull, result size: "
					+ results.size());

			return results;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getListPigriddetailByPigridcodeColorCodeGarmentStyleCode in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}

	/**
	 * This function is used to get Pi grid detail by Pigridcode ColorCode
	 * GarmentStyleCode (for color, not white)
	 */
	@SuppressWarnings("unchecked")
	public List<Pigriddetail> getListPigriddetailByPigridcodeColorCodeGarmentStyleCodeNotWhite(
			Integer pigridcode, String colorcode, String garmentstylecode) {
		logger.debug(String
				.format("getListPigriddetailByPigridcodeColorCodeGarmentStyleCode in class: %s",
						getClass()));
		try {
			List<Pigriddetail> results = (List<Pigriddetail>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Pigriddetail where pigrid.pigridcode = "
									+ pigridcode + " and color.colorcode != '"
									+ colorcode
									+ "' and garmentstyle.garmentstylecode= '"
									+ garmentstylecode + "'").list();
			logger.debug("getListPigriddetailByPigridcodeColorCodeGarmentStyleCode successfull, result size: "
					+ results.size());

			return results;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getListPigriddetailByPigridcodeColorCodeGarmentStyleCode in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}

	/**
	 * This function is used to get list pi grid detail by Pigridcode ColorCode
	 * GarmentStyleCode TypeCode For White
	 * 
	 * @param pigridcode
	 * @param colorcode
	 * @param garmentstylecode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Pigriddetail> getListPigriddetailByPigridcodeColorCodeGarmentStyleCodeTypeCodeForWhite(
			Integer pigridcode, String colorcode, String garmentstylecode,
			String typecode) {
		logger.debug(String
				.format("getListPigriddetailByPigridcodeColorCodeGarmentStyleCodeTypeCodeForWhite in class: %s",
						getClass()));
		try {
			List<Pigriddetail> results = (List<Pigriddetail>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Pigriddetail where pigrid.pigridcode = "
									+ pigridcode + " and color.colorcode = '"
									+ colorcode
									+ "' and garmentstyle.garmentstylecode= '"
									+ garmentstylecode
									+ "' and size.type.typecode = '" + typecode
									+ "'").list();
			logger.debug("getListPigriddetailByPigridcodeColorCodeGarmentStyleCodeTypeCodeForWhite successfull, result size: "
					+ results.size());

			return results;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getListPigriddetailByPigridcodeColorCodeGarmentStyleCodeTypeCodeForWhite in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}

	/**
	 * This function is used to get list pi grid detail by Pigridcode ColorCode
	 * GarmentStyleCode TypeCode Not For White
	 * 
	 * @param pigridcode
	 * @param colorcode
	 * @param garmentstylecode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Pigriddetail> getListPigriddetailByPigridcodeColorCodeGarmentStyleCodeTypeCodeNotForWhite(
			Integer pigridcode, String colorcode, String garmentstylecode,
			String typecode) {
		logger.debug(String
				.format("getListPigriddetailByPigridcodeColorCodeGarmentStyleCodeTypeCodeForWhite in class: %s",
						getClass()));
		try {
			List<Pigriddetail> results = (List<Pigriddetail>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Pigriddetail where pigrid.pigridcode = "
									+ pigridcode + " and color.colorcode != '"
									+ colorcode
									+ "' and garmentstyle.garmentstylecode= '"
									+ garmentstylecode
									+ "' and size.type.typecode = '" + typecode
									+ "'").list();
			logger.debug("getListPigriddetailByPigridcodeColorCodeGarmentStyleCodeTypeCodeForWhite successfull, result size: "
					+ results.size());

			return results;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getListPigriddetailByPigridcodeColorCodeGarmentStyleCodeTypeCodeForWhite in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}
	
	/**
	 * This function is used to get Fa OF Pigrid detail By Pigrid code Color Code Garment Style Code Size Code
	 * @param pigridcode
	 * @param colorcode
	 * @param garmentstylecode
	 * @param sizecode
	 * @return
	 */
	public Integer getFaOFPigriddetailByPigridcodeColorCodeGarmentStyleCodeSizeCode(
			Integer pigridcode, String colorcode, String garmentstylecode,
			Integer sizecode) {
		logger.debug(String
				.format("getFaOFPigriddetailByPigridcodeColorCodeGarmentStyleCodeSizeCode in class: %s",
						getClass()));
		try {
			Pigriddetail results = (Pigriddetail) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Pigriddetail where pigrid.pigridcode = "
									+ pigridcode + " and color.colorcode = '"
									+ colorcode
									+ "' and garmentstyle.garmentstylecode= '"
									+ garmentstylecode
									+ "' and size.sizecode = " + sizecode).uniqueResult();
			logger.debug("getFaOFPigriddetailByPigridcodeColorCodeGarmentStyleCodeSizeCode successfull, Fa value: "
					+ results.getFavalue());

			return results.getFavalue();
		} catch (RuntimeException re) {
			logger.error(String
					.format("getFaOFPigriddetailByPigridcodeColorCodeGarmentStyleCodeSizeCode in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}
	@SuppressWarnings("unchecked")
		public boolean deleteAllbyPigridCode(int pigridCode){
				
			logger.debug(String.format(
					"deleteAllbyPigridCode in class: %s", getClass()));
			try {
				Query q = sessionFactory
						.getCurrentSession()
						.createQuery(
								"delete Pigriddetail where pigrid.pigridcode = "
										+ pigridCode);
				logger.debug("deleteAllbyPigridCode successfull, result size: "
						+ q);
				if(q.executeUpdate()>0)
					return true;
				else
					return false;
			} catch (RuntimeException re) {
				logger.error(String
						.format("getListPigriddetailByPigridcode in class %s has error: %s",
								getClass(), re.getMessage()));
				throw re;
		//			return false;
			}			
		}

	/**
	 * This function is used to get list pi grid detail by Pigridcode ColorCode
	 * GarmentStyleCode TypeCode For White
	 * 
	 * @param pigridcode
	 * @param colorcode
	 * @param garmentstylecode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Pigriddetail> getListPigriddetailByPigridcodeColorCodeGarmentStyleCodeTypeCode(
			Integer pigridcode, String colorcode, String garmentstylecode,
			Integer sizecode) {
		logger.debug(String
				.format("getListPigriddetailByPigridcodeColorCodeGarmentStyleCodeTypeCode in class: %s",
						getClass()));
		try {
			List<Pigriddetail> results = (List<Pigriddetail>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Pigriddetail where pigrid.pigridcode = "
									+ pigridcode + " and color.colorcode = '"
									+ colorcode
									+ "' and garmentstyle.garmentstylecode= '"
									+ garmentstylecode
									+ "' and size.sizecode = '" + sizecode
									+ "'").list();
			logger.debug("getListPigriddetailByPigridcodeColorCodeGarmentStyleCodeTypeCode successfull, result size: "
					+ results.size());

			return results;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getListPigriddetailByPigridcodeColorCodeGarmentStyleCodeTypeCode in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}
	
	/**
	 * This function is used to get all pi grid detail by Pigridcode ColorCode
	 * GarmentStyleCode 
	 * 
	 * @param pigridcode
	 * @param colorcode
	 * @param garmentstylecode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Pigriddetail> getAllPigridDetailByPigridCodeColorCodeGarmentStyleCode(
			Integer pigridcode, String colorcode, String garmentstylecode) {
		logger.debug(String
				.format("getAllPigridDetailByPigridCodeColorCodeGarmentStyleCode in class: %s",
						getClass()));
		try {
			List<Pigriddetail> results = (List<Pigriddetail>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Pigriddetail where pigrid.pigridcode = "+ pigridcode 
									+ " and color.colorcode = '" + colorcode
									+ "' and garmentstyle.garmentstylecode= '" + garmentstylecode									
									+ "'").list();
			logger.debug("getAllPigridDetailByPigridCodeColorCodeGarmentStyleCode successfull, result size: "
					+ results.size());

			return results;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getAllPigridDetailByPigridCodeColorCodeGarmentStyleCode in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}
	
	/**
	 * This function is used to get all pi grid detail by pigridcode colorcode
	 * garmentstylecode sizecode
	 * 
	 * @param pigridcode
	 * @param colorcode
	 * @param garmentstylecode
	 * @param sizecode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Pigriddetail> getAllPigridDetailByPigridCodeColorCodeGarmentStyleCodeSizeCode(
			Integer pigridcode, String colorcode, String garmentstylecode, Integer sizecode) {
		logger.debug(String
				.format("getAllPigridDetailByPigridCodeColorCodeGarmentStyleCodeSizeCode in class: %s",
						getClass()));
		try {
			List<Pigriddetail> results = (List<Pigriddetail>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Pigriddetail where pigrid.pigridcode = " + pigridcode 
									+ " and color.colorcode = '" + colorcode
									+ "' and garmentstyle.garmentstylecode= '" + garmentstylecode	
									+ "' and size.sizecode= " + sizecode	
									+ "").list();
			logger.debug("getAllPigridDetailByPigridCodeColorCodeGarmentStyleCodeSizeCode successfull, result size: "
					+ results.size());

			return results;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getAllPigridDetailByPigridCodeColorCodeGarmentStyleCodeSizeCode in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}
	
	/**
	 * This function is used to getListPigriddetailByPigridcodeAndListColor by pigridCode
	 * 
	 * @param garmentStyleName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Pigriddetail> getListPigriddetailByPigridcodeAndListColor(Integer pigridcode, List<String> lstColor) {
		logger.debug(String.format(
				"getListPigriddetailByPigridcodeAndListColor in class: %s", getClass()));
		try {
//			Query query = sessionFactory
//					.getCurrentSession().createQuery("from Pigriddetail where pigrid.pigridcode = :pigridcodetemp and color.colorcode IN :lstcolor ");
//			query.setParameter("pigridcodetemp", pigridcode);
//			query.setParameterList("lstcolor", lstColor);			
//          List<Pigriddetail> results = query.list();
			List<Pigriddetail> sumResults = new ArrayList<Pigriddetail>();
			for(int i = 0; i < lstColor.size() ; i++)
			{
				List<Pigriddetail> results = (List<Pigriddetail>) sessionFactory
						.getCurrentSession()
						.createQuery(
								"from Pigriddetail where pigrid.pigridcode = "+ pigridcode 
										+ " and color.colorcode = '" + lstColor.get(i) + "'").list();
				sumResults.addAll(results);
			}

			logger.debug("getListPigriddetailByPigridcodeAndListColor successfull, result size: "
					+ sumResults.size());

			return sumResults;
		} catch (RuntimeException re) {
			logger.error(String
					.format("getListPigriddetailByPigridcodeAndListColor in class %s has error: %s",
							getClass(), re.getMessage()));
			throw re;
		}
	}
}
