package com.chori.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.chori.AbstractDao;
import com.chori.AbstractServiceImpl;
import com.chori.dao.BrandDao;
import com.chori.dao.CustomerDao;
import com.chori.dao.UserDao;
import com.chori.entity.Brand;
import com.chori.entity.Pi;
import com.chori.model.BrandModel;
import com.chori.model.PiModel;

@Repository("brandService")
public class BrandServiceImpl extends AbstractServiceImpl<Brand, Integer>
		implements BrandService {
	BrandDao dao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private CustomerDao customerDao;

	public BrandServiceImpl() {

	}

	@Autowired
	public BrandServiceImpl(
			@Qualifier("brandDao") AbstractDao<Brand, Integer> abstractDao) {
		super(abstractDao);
		this.dao = (BrandDao) abstractDao;
	}

	public List<BrandModel> getAllBrandModel() {
		log.info(String.format("getAllBrand in class: %s", getClass()));
		try {
			log.debug("get all Role in DB after that return a list RoleEntity");
			List<Brand> lstBrand = dao.getAll();

			BrandModel brandMod;
			List<BrandModel> lst = new ArrayList<BrandModel>();

			for (Brand brand : lstBrand) {
				brandMod = new BrandModel();
				brandMod.setBrandcode(brand.getBrandcode());
				brandMod.setBrandname(brand.getBrandname());
				brandMod.setCreatedate(brand.getCreatedate());
				brandMod.setCreator(brand.getUser().getUsername());
				brandMod.setCustomerCode(brand.getCustomer().getShortname());

				lst.add(brandMod);
			}
			log.debug("getAllBrand successfully");
			return lst;
		} catch (Exception e) {
			log.error(String.format("getAllBrand in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}
	
	public BrandModel findBrandById(int brandCode) {
		log.info(String.format("findBrandById with param 'brandCode' in class: %s", getClass()));
		try {
			BrandModel en = new BrandModel();
			Brand brand = dao.findById(brandCode);

			en.setBrandcode(brand.getBrandcode());
			en.setBrandname(brand.getBrandname());
			en.setCustomerCode(brand.getCustomer().getCustomercode());
			//en.setFpicode(pi.getFpis().getClass());
			en.setCreator(brand.getUser() == null ? "" : brand.getUser()
					.getUsername());
			en.setCreatedate(brand.getCreatedate());
			//en.set
			
			log.debug("findBrandById successfully");
			return en;
		} catch (Exception e) {
			log.error(String.format("findBrandById with param 'brandCode' in class: %s has error: %s", getClass(),
					e.getMessage()));
			throw e;
		}
	}
}
