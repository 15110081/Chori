package com.chori.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chori.AbstractDao;
import com.chori.AbstractServiceImpl;
import com.chori.dao.BrandDao;
import com.chori.dao.CustomerDao;
import com.chori.dao.DestinationDao;
import com.chori.dao.FabricinformationdetailDao;
import com.chori.dao.FactoryDao;
import com.chori.dao.FpiDao;
import com.chori.dao.LogofchangeDao;
import com.chori.dao.PIAssignFabricDetailDao;
import com.chori.dao.PIDao;
import com.chori.dao.PackingguideDao;
import com.chori.dao.PigridDao;
import com.chori.dao.UserDao;
import com.chori.entity.Fabricinformationdetail;
import com.chori.entity.FabricinformationdetailId;
import com.chori.entity.Pi;
import com.chori.entity.Piassignfabricdetail;
import com.chori.model.PiFabricListModel;
import com.chori.model.PiModel;

@Repository("piService")
public class PiServiceImpl extends AbstractServiceImpl<Pi, String> implements PiService {
	private PIDao dao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private CustomerDao cusDao;

	@Autowired
	private DestinationDao desDao;

	@Autowired
	private FactoryDao facDao;

	@Autowired
	private FpiDao fpiDao;

	@Autowired
	private LogofchangeDao logofchangeDao;

	@Autowired
	private PigridDao pigridDao;

	@Autowired
	private BrandDao branDao;

	@Autowired
	private FabricinformationdetailDao fabricinformationdetailDao;

	@Autowired
	private PIAssignFabricDetailDao piassignfabricdetailDao;

	@Autowired
	private PackingguideDao packingguideDao;

	final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	final String SAIGON_TIMEZONE = "Asia/Saigon";

	@Autowired
	public PiServiceImpl(AbstractDao<Pi, String> abstractDao, PIDao dao) {
		super(abstractDao);
		this.dao = dao;
	}

	public boolean isPiExistedById(String piCode) {
		if (null == dao.findById(piCode))
			return false;
		return true;
	}

	/**
	 * This function is used to delete a Accessory price in database.
	 * 
	 * @param accessorypriceCode
	 * @return true if delete successfully, false if cant
	 */
	@Override
	public boolean deletePi(PiModel piModel) {
		log.info(String.format("deletePi with param 'lotNumber' in class: %s", getClass()));
		Pi pi = dao.findById(piModel.getLotnumber());
		try {
			dao.delete(pi);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<PiModel> getAllPiModel() {
		log.info(String.format("getAllPi in class: %s", getClass()));
		try {
			log.debug("get all Role in DB after that return a list RoleEntity");
			List<Pi> lstPi = dao.getAll();

			PiModel en;
			List<PiModel> lst = new ArrayList<PiModel>();

			for (Pi pi : lstPi) {

				en = new PiModel();
				en.setLotnumber(pi.getLotnumber());
				en.setCustomer1(pi.getCustomerByCustomer1code().getShortname());
				en.setPireceiveddate(pi.getPireceiveddate());
				en.setPiestshipdate(pi.getPiestshipdate());
				en.setDestinationcode(pi.getDestination().getDestinationcode());
				en.setStatus(pi.getStatus());
				en.setShipmentstatus(pi.getShippingstatus());

				lst.add(en);
			}
			log.debug("getAllPi successfully");
			return lst;
		} catch (NullPointerException ne) {
			log.error(String.format("getAllPi in class: %s has error: %s", getClass(), ne.getMessage()));

		}
		return null;
	}

	public PiModel findPiById(String piCode) {
		log.info(String.format("findPiById with param 'piCode' in class: %s", getClass()));
		try {
			PiModel en = new PiModel();
			Pi pi = dao.findById(piCode);

			en.setLotnumber(pi.getLotnumber());
			// en.setCustomer1(pi.getCustomerByCustomer1code() == null ? "" : pi
			// .getCustomerByCustomer1code().getCustomercode());
			en.setCustomer1(pi.getCustomerByCustomer1code().getCustomercode());
			en.setConsignee(pi.getCustomerByConsigneee() == null ? "" : pi.getCustomerByConsigneee().getCustomercode());
			en.setCountry(pi.getDestination() == null ? "" : pi.getDestination().getDescription());
			en.setFactorycode(pi.getFactory() == null ? "" : pi.getFactory().getFactorycode());
			en.setDestinationcode(pi.getDestination() == null ? "" : pi.getDestination().getDestinationcode());
			// en.setFpicode(pi.getFpis().getClass());
			en.setCreator(pi.getUser() == null ? "" : pi.getUser().getUsername());
			// en.setBrandcode(pi.getBrand().getBrandcode());
			en.setSewingguidename(pi.getSewingguideurl());
			en.setNoneorderaccessories(pi.getNoneorderaccessories() == null ? false : pi.getNoneorderaccessories());
			en.setPackingguidename(pi.getPackingguideurl());
			en.setPackingguidecode(pi.getPackingguide() == null ? "" : pi.getPackingguide().getCode());
			en.setPiattachedfilename(pi.getPiattachedfileurl());
//				en.setPiestshipdate(pi.getPiestshipdate().setHours(7));
			en.setPiestshipdate(pi.getPiestshipdate());
			en.setPigridcode(pi.getPigrid() == null ? 0 : pi.getPigrid().getPigridcode());
			en.setPireceiveddate(pi.getPireceiveddate());
			en.setMfgstarteddate(pi.getMfgstarteddate());
			en.setMfgfinisheddate(pi.getMfgfinisheddate());
			en.setRemark(pi.getRemark());
			en.setStatus(pi.getStatus());
			en.setShipmentstatus(pi.getShippingstatus());

			log.debug("findPiById successfully");
			return en;
		} catch (Exception e) {
			log.error(String.format("findPiById with param 'lotNumber' in class: %s has error: %s", getClass(),
					e.getMessage()));
			throw e;
		}
	}

	public boolean editPi(PiModel piModel, String userName) {
		log.info(String.format("editPi with param 'piMod', 'lotNumber' in class: %s", getClass()));
		try {
			Pi pi = dao.findById(piModel.getLotnumber());
			pi.setLotnumber(piModel.getLotnumber());
			pi.setCustomerByCustomer1code(cusDao.findById(piModel.getCustomer1()));
			pi.setCustomerByConsigneee(cusDao.findById(piModel.getConsignee()));
			pi.setLotnumber(piModel.getLotnumber());
			pi.setDestination(desDao.findById(piModel.getDestinationcode()));
			// pi.getDestination().getDestinationcode();
			// pi.getFactory().getFactorycode();
			pi.setFactory(facDao.findById(piModel.getFactorycode()));
			pi.setBrand(branDao.findById(piModel.getBrandcode()));
			// pi.getFpi().getFpicode();
			pi.setNoneorderaccessories(piModel.isNoneorderaccessories());
			if (piModel.getPiattachedfilename() != null)
				pi.setPiattachedfileurl(piModel.getPiattachedfilename());
			pi.setPiestshipdate(piModel.getPiestshipdate());
			pi.setPackingguide(packingguideDao.findById(piModel.getPackingguidecode()));
			// pi.getPigrid().getPigridcode();
			// pi.setPigrid(pigridDao.findById(piModel.getPigridcode()));
			pi.setPireceiveddate(piModel.getPireceiveddate());
			pi.setMfgstarteddate(piModel.getMfgstarteddate());
			pi.setMfgfinisheddate(piModel.getMfgfinisheddate());
			pi.setRemark(piModel.getRemark());
			pi.setStatus(piModel.getStatus());
			pi.setShippingstatus(piModel.getShipmentstatus());

			dao.update(pi);
			log.debug("editPi successfully");
			return true;
		} catch (Exception e) {
			log.error(String.format("editPi with param 'piMod', 'lotNumber' in class: %s has error: %s", getClass(),
					e.getMessage()));
			System.err.println(String.format("editWidth with param 'piMod', 'lotNumber' in class: %s has error: %s",
					getClass(), e.getMessage()));
			return false;
		}
	}

	@SuppressWarnings("deprecation")
	public boolean addPi(PiModel piMod, String userName) {
		log.info(String.format("addPi in class: %s", getClass()));
		try {
			if (validateForeignKey(piMod.getCustomer1(), piMod.getDestinationcode(), piMod.getFactorycode()) == true) {
				Pi pi = new Pi();
				pi.setLotnumber(piMod.getLotnumber());
				pi.setCreatedate(Calendar.getInstance().getTime());
				pi.setNoneorderaccessories(piMod.isNoneorderaccessories());
				pi.setUser(userDao.findById(userName));
				pi.setCustomerByCustomer1code(cusDao.findById(piMod.getCustomer1()));
				if (piMod.getConsignee().equalsIgnoreCase("none")) {
					pi.setCustomerByConsigneee(cusDao.findById(piMod.getCustomer1()));
				} else
					pi.setCustomerByConsigneee(cusDao.findById(piMod.getConsignee()));
				pi.setDestination(desDao.findById(piMod.getDestinationcode()));
				pi.setFactory(facDao.findById(piMod.getFactorycode()));
				pi.setBrand(branDao.findById(piMod.getBrandcode()));
				pi.setPackingguide(packingguideDao.findById(piMod.getPackingguidecode()));
				pi.setFpis(null);
				pi.setSewingguideurl(piMod.getSewingguidename());
				pi.setPackingguideurl(piMod.getPackingguidename());
				pi.setPiattachedfileurl(piMod.getPiattachedfilename());


//				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//				String tmp = formatter.format(); 
//				 Calendar cal = Calendar.getInstance();
//				  cal.setTime(formatter.parse(tmp));
				pi.setPiestshipdate(piMod.getPiestshipdate());
				pi.setPireceiveddate(piMod.getPireceiveddate());

				pi.setMfgstarteddate(piMod.getMfgstarteddate());
				pi.setMfgfinisheddate(piMod.getMfgfinisheddate());
				pi.setPigrid(pigridDao.findById(piMod.getPigridcode()));
				pi.setRemark(piMod.getRemark() == null ? "" : piMod.getRemark());
				pi.setStatus(piMod.getStatus());
				pi.setShippingstatus(piMod.getShipmentstatus());

				dao.save(pi);
				log.debug("add new pi into database successfully");
				return true;
			}
			return false;
		} catch (Exception e) {
			log.debug("add new pi into database fail");

			System.err.println("add new pi into database fail, method addPi(), class PiService");
			return false;
		}
	}

	@Override
	public List<PiFabricListModel> getFabricList() {
		log.info(String.format("getAllPi in class: %s", getClass()));
		try {
			log.debug("get all Role in DB after that return a list RoleEntity");
			List<Fabricinformationdetail> lstFabricinformationdetail = fabricinformationdetailDao.getAll();

			PiFabricListModel piFabricListModel;
			List<PiFabricListModel> lstPiFabricListModel = new ArrayList<PiFabricListModel>();

			for (Fabricinformationdetail fabricinformationdetail : lstFabricinformationdetail) {
				piFabricListModel = new PiFabricListModel();
				piFabricListModel.setColorcode(fabricinformationdetail.getColor().getColorcode());
				piFabricListModel.setFabricno(fabricinformationdetail.getFabricinformation().getFabricno());
				piFabricListModel.setInventoryremained(
						getInventoryRemainedOfAFabric(fabricinformationdetail.getFabricinformation().getFabricno(),
								fabricinformationdetail.getColor().getColorcode()));
				piFabricListModel.setTotalremained(
						getTotalInventoryRemained(fabricinformationdetail.getFabricinformation().getFabricno()));
				lstPiFabricListModel.add(piFabricListModel);
			}
			log.debug("getAllPi successfully");
			return lstPiFabricListModel;
		} catch (NullPointerException ne) {
			log.error(String.format("getAllPi in class: %s has error: %s", getClass(), ne.getMessage()));

		}
		return null;
	}

	public Double getInventoryRemainedOfAFabric(String fabricNo, String colorCode) {
		FabricinformationdetailId fabricinformationdetailId = new FabricinformationdetailId(fabricNo, colorCode);
		// Double inventoryRemained = (double) 0;
		Double inventoryRemained = fabricinformationdetailDao.findById(fabricinformationdetailId).getYardinbl();

		List<Piassignfabricdetail> lstPiassignfabricdetail = piassignfabricdetailDao.getAll();
		for (Piassignfabricdetail piassignfabricdetail : lstPiassignfabricdetail) {
			if (piassignfabricdetail.getColor().getColorcode().equals(colorCode)
					&& piassignfabricdetail.getPiassignfabric().getFabricinformation().getFabricno().equals(fabricNo)) {
				inventoryRemained -= piassignfabricdetail.getAssignquantity();
			}
		}
		return inventoryRemained;
	}

	public Double getTotalInventoryRemained(String fabricNo) {
		Double totalInventoryRemained = (double) 0;
		Double totalYardInBL = (double) 0;

		List<Fabricinformationdetail> lstFabricinformationdetail = fabricinformationdetailDao.getAll();
		for (Fabricinformationdetail fabricinformationdetail : lstFabricinformationdetail) {
			if (fabricinformationdetail.getFabricinformation().getFabricno().equals(fabricNo)) {
				totalYardInBL += fabricinformationdetail.getYardinbl();
			}
		}

		List<Piassignfabricdetail> lstPiassignfabricdetail = piassignfabricdetailDao.getAll();
		for (Piassignfabricdetail piassignfabricdetail : lstPiassignfabricdetail) {
			if (piassignfabricdetail.getPiassignfabric().getFabricinformation().getFabricno().equals(fabricNo)) {
				totalYardInBL -= piassignfabricdetail.getAssignquantity();
			}
		}
		totalInventoryRemained = totalYardInBL;

		// List<Fabricinformationdetail> lstFabricinformationdetail =
		// fabricinformationdetailDao.getAll();
		// for (Fabricinformationdetail fabricinformationdetail :
		// lstFabricinformationdetail) {
		// if(fabricinformationdetail.getFabricinformation().getFabricno().equals(fabricNo))
		// {
		// totalInventoryRemained +=
		// getInventoryRemainedOfAFabric(fabricNo,colorCode);
		// }
		// }
		return totalInventoryRemained;
	}

	public boolean validateForeignKey(String customerCode, String destinationCode, String factoryCode) {
		log.debug("in PiService at validateForeignKey method");
		try {
			if (cusDao.findById(customerCode) != null && facDao.findById(factoryCode) != null
					&& desDao.findById(destinationCode) != null) {
				return true;
			}
			return false;
		} catch (NullPointerException ne) {
			log.error("in PiService at validateForeignKey method error: " + ne.getMessage());
			throw ne;
		}
	}

}
