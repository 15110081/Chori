package com.chori.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.chori.AbstractDao;
import com.chori.AbstractServiceImpl;
import com.chori.dao.GarmentConsumptionDao;
import com.chori.dao.GarmentConsumptionDetailDao;
import com.chori.dao.UnitDao;
import com.chori.dao.UserDao;
import com.chori.dao.WidthDao;
import com.chori.entity.Garmentconsumption;
import com.chori.entity.Garmentconsumptiondetail;
import com.chori.entity.Width;
import com.chori.model.WidthModel;

@Repository("widthService")
public class WidthServiceImpl extends AbstractServiceImpl<Width, String> implements WidthService {

	private WidthDao dao;

	@Autowired
	private UnitDao unitDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private GarmentConsumptionDao garCondao;

	@Autowired
	GarmentConsumptionDetailDao garmentConsumptionDetailDao;

	public WidthServiceImpl() {
	}

	@Autowired
	public WidthServiceImpl(@Qualifier("widthDao") AbstractDao<Width, String> abstractDao) {
		super(abstractDao);
		this.dao = (WidthDao) abstractDao;
	}

	/**
	 * This function is used get all Width
	 * 
	 * @return list<WidthModel>
	 */
	@Override
	public List<WidthModel> getAllWidthModel() {
		log.info(String.format("getAllWidth in class: %s", getClass()));
		try {
			log.debug("get all Width in DB after that return a list WidthEntity");
			List<Width> lstWidth = dao.getAll();

			WidthModel widthMod;
			List<WidthModel> lst = new ArrayList<WidthModel>();

			for (Width wid : lstWidth) {

				widthMod = new WidthModel();
				widthMod.setWidthcode(wid.getWidthcode());
				widthMod.setUnitcode(wid.getUnit().getUnitcode());
				widthMod.setWidthvalues(wid.getWidthvalues());
				widthMod.setUnitname(wid.getUnit().getName());
				widthMod.setCreator(wid.getUser().getUsername());
				widthMod.setCreatedate(wid.getCreatedate());

				lst.add(widthMod);
			}
			log.debug("getAllWidth successfully");
			return lst;
		} catch (NullPointerException ne) {
			log.error(String.format("getAllWidth in class: %s has error: %s", getClass(), ne.getMessage()));

		}
		return null;
	}

	/**
	 * This function find a width by id.
	 * 
	 * @param widthCode
	 * @return a WidthModel
	 */

	@Override
	public WidthModel findWidthEntityById(String widthCode) {
		log.info(String.format("findWidthById with param 'widthId' in class: %s", getClass()));
		try {
			WidthModel widthMod = new WidthModel();
			Width wid = dao.findById(widthCode);

			widthMod.setWidthcode(wid.getWidthcode());
			widthMod.setUnitcode(wid.getUnit().getUnitcode());
			widthMod.setUnitname(wid.getUnit().getName());
			widthMod.setWidthvalues(wid.getWidthvalues());

			log.debug("findWidthById successfully");
			return widthMod;
		} catch (Exception e) {
			log.error(String.format("findWidthEntityById with param 'widthId' in class: %s has error: %s", getClass(),
					e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to delete an Width in database.
	 * 
	 * @param widthModel
	 * @return delete successfully =>true , else => false
	 */
	@Override
	public boolean deleteWidth(WidthModel widthMod) {
		log.info(String.format("deleteWidth with param 'widthId' in class: %s", getClass()));
		Width wid = dao.findById(widthMod.getWidthcode());
		try {
			// delete garment consumption foreign key before delete width
			deleteGarmentConsumption(widthMod.getWidthcode());

			// delete width
			dao.delete(wid);

			return true;
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * This function is used to add an Width in database.
	 * 
	 * @param widthModel
	 *            , userName
	 * @return add successfully =>true , else => false
	 */

	@Override
	public boolean addWidth(WidthModel widthMod, String userName) {
		log.info(String.format("addWidth in class: %s", getClass()));
		try {
			if (validateForeignKey(widthMod.getUnitcode()) == true) {

				Width wid = new Width();
				wid.setWidthcode(widthMod.getWidthcode());
				wid.setWidthvalues(widthMod.getWidthvalues());
				wid.setCreatedate(Calendar.getInstance().getTime());
				wid.setUnit(unitDao.findById(widthMod.getUnitcode()));
				wid.setUser(userDao.findById(userName));
				dao.save(wid);
				// add garment consumption
				addGarmentConsumption(widthMod.getWidthcode(), userName);

				log.debug("add new width into database successfully");
				return true;
			}
			return false;
		} catch (Exception e) {
			log.debug("add new width into database fail");

			System.err.println("add new Width into database fail, method addWidth(), class WidthService");
			return false;
		}
	}

	/**
	 * This function is used to add new garment consumption when add new width
	 * 
	 * @param widthCode
	 * @author Son
	 */
	public void addGarmentConsumption(String widthCode, String userName) {
		log.info(String.format("addGarmentConsumption in class: %s", getClass()));
		try {
			List<Garmentconsumption> lstGarcon = garCondao.getAll();
			Garmentconsumptiondetail garmentconsumptiondetail;
			for (Garmentconsumption garcon : lstGarcon) {
				garmentconsumptiondetail = new Garmentconsumptiondetail();
				garmentconsumptiondetail.setGarmentconsumption(garCondao.findById(garcon.getGarmentconsumptioncode()));
				garmentconsumptiondetail.setConvalue((float) 0);
				garmentconsumptiondetail.setUser(userDao.findById(userName));
				garmentconsumptiondetail.setWidth(dao.findById(widthCode));
				garmentConsumptionDetailDao.save(garmentconsumptiondetail);
			}
			log.debug("add Garment Consumption into database successfully");
		} catch (Exception e) {
			log.debug("add Garment Consumption into database fail");
			System.err.println("add Garment Consumption into database fail, method addWidth(), class WidthService");
		}
	}

	/**
	 * This function is used to delete garment consumption when delete a width
	 * 
	 * @param widthCode
	 * @author Son
	 */
	public void deleteGarmentConsumption(String widthCode) {
		log.info(String.format("deleteGarmentConsumption in class: %s", getClass()));
		try {
			List<Garmentconsumptiondetail> lstGarcondetail = garmentConsumptionDetailDao.getAll();
			for (Garmentconsumptiondetail garcondetail : lstGarcondetail) {
				if (garcondetail.getWidth().getWidthcode().equals(widthCode)) {
					garmentConsumptionDetailDao.delete(garcondetail);
				}
			}
			log.debug("delete Garment Consumption into database successfully");
		} catch (Exception e) {
			log.debug("delete Garment Consumption into database fail");
			System.err.println("delete Garment Consumption into database fail, method addWidth(), class WidthService");
		}
	}

	/**
	 * This function check if there is an width with widthCode existed in
	 * database
	 * 
	 * @param widthCode
	 * @return false if not existed, true if existed
	 */

	public boolean isWidthExistedById(String widthCode) {
		if (null == dao.findById(widthCode))
			return false;
		return true;
	}

	/**
	 * This function is used to edit an Width in database.
	 * 
	 * @param widthModel
	 *            , username
	 * @return edit successfully =>true , else => false
	 */

	@Override
	public boolean editWidth(WidthModel widthMod, String userName) {
		log.info(String.format("editWidth with param 'widthMod', 'userName' in class: %s", getClass()));
		try {
		//	if (validateForeignKey(widthMod.getUnitcode()) == true) {
				Width wid = dao.findById(widthMod.getWidthcode());
				wid.setWidthvalues(widthMod.getWidthvalues());
				wid.setUnit(unitDao.findById(widthMod.getUnitcode()));
				wid.setUser(userDao.findById(userName));
				dao.update(wid);
				log.debug("editWidth successfully");
				return true;
		//	}
		//	return false;
		} catch (Exception e) {
			log.error(String.format("editWidth with param 'widthMod', 'userName' in class: %s has error: %s",
					getClass(), e.getMessage()));
			System.err.println(String.format("editWidth with param 'widthMod', 'userName' in class: %s has error: %s",
					getClass(), e.getMessage()));
			return false;
		}
	}

	public boolean validateForeignKey(String unitcode) {
		log.debug("in WidthService at validateForeignKey method");
		try {
			if (unitDao.findById(unitcode) != null) {
				return true;
			}
			return false;
		} catch (NullPointerException ne) {
			log.error("in WidthService at validateForeignKey method error: " + ne.getMessage());
			throw ne;
		}
	}
}