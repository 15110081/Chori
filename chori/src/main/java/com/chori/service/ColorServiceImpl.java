package com.chori.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chori.AbstractDao;
import com.chori.AbstractServiceImpl;
import com.chori.dao.ColorDao;
import com.chori.dao.UserDao;
import com.chori.entity.Color;
import com.chori.model.ColorModel;

@Repository("colorService")
public class ColorServiceImpl extends AbstractServiceImpl<Color, String>
		implements ColorService {

	private ColorDao dao;

	@Autowired
	private UserDao userDao;

	// public ColorServiceImpl() {
	//
	// }

	@Autowired
	public ColorServiceImpl(AbstractDao<Color, String> abstractDao, ColorDao dao) {
		super(abstractDao);
		this.dao = dao;
	}

	/**
	 * This function check if there is an color with clCode existed in database
	 * 
	 * @param clCode
	 * @return false if not existed, true if existed
	 */
	public boolean isColorExistedById(String clCode) {
		if (null == dao.findById(clCode))
			return false;
		return true;
	}

	/**
	 * This function is used get all Color
	 * 
	 * @return list<ColorModel>
	 */
	public List<ColorModel> getAllColor() {
		log.debug("in color service list");
		try {
			List<Color> lstColor = dao.getAll();
			ColorModel colorMo;
			List<ColorModel> lstColorModel = new ArrayList<ColorModel>();
			for (Color cl : lstColor) {
				colorMo = new ColorModel();
				colorMo.setColorcode(cl.getColorcode());
				colorMo.setDescription(cl.getDescription());
				colorMo.setCreatedate(cl.getCreatedate());
				colorMo.setCreator(cl.getUser() == null ? "" : cl.getUser()
						.getUsername());

				lstColorModel.add(colorMo);
			}
			log.debug("getAllColor successfully");
			return lstColorModel;
		} catch (Exception e) {
			log.error(String.format("getAllColor in class: %s has error: %s",
					getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to add new color into database
	 * 
	 * @param colorMo
	 * @param userId
	 * @return true if add successfully, false if have exception
	 */
	public boolean addColor(ColorModel colorMo, String username) {
		try {
			Color color = new Color();
			color.setColorcode(colorMo.getColorcode());
			color.setDescription(colorMo.getDescription());
			color.setCreatedate(Calendar.getInstance().getTime());
			color.setUser(userDao.findById(username));

			dao.save(color);
			log.debug("addColor successfullly");
			return true;
		} catch (Exception e) {
			log.debug("add new Color into database fail");
			System.err.println(String.format(
					"addColor with param 'colorMo' in class: %s has error: %s",
					getClass(), e.getMessage()));
			return false;
		}

	}

	/**
	 * This function find a colorModel by colorCode.
	 * 
	 * @param colorCode
	 * @return a ColorModel
	 */
	public ColorModel findColorModelById(String colorCode) {
		log.info(String.format(
				"findColorEntityById with param 'colorId' in class: %s",
				getClass()));
		try {
			ColorModel mo = new ColorModel();
			Color color = dao.findById(colorCode);

			// field for NV

			mo.setColorcode(color.getColorcode());
			mo.setDescription(color.getDescription());
			mo.setCreator(color.getUser() == null ? "" : color.getUser()
					.getUsername());
			mo.setCreatedate(color.getCreatedate());
			log.debug("findColorEntityById successfully");
			return mo;
		} catch (Exception e) {
			log.error(String
					.format("findColorModelById with param 'colorId' in class: %s has error: %s",
							getClass(), e.getMessage()));
			throw e;
		}
	}

	/**
	 * This function is used to edit Color into database
	 * 
	 * @param colorMo
	 * @param userId
	 * @return true if edit successfully, false if have exception
	 */
	public boolean editColor(ColorModel colorMo) {
		log.info(String
				.format("editColor with param 'colorMo' in class: %s",
						getClass()));
		try {
			Color color = dao.findById(colorMo.getColorcode());
			color.setDescription(colorMo.getDescription());

			dao.update(color);
			log.debug("editColor successfully");
			return true;
		} catch (Exception e) {
			log.error(String
					.format("editColor with param 'colorMo' in class: %s has error: %s",
							getClass(), e.getMessage()));
			System.err
					.println(String
							.format("editColor with param 'colorMo' in class: %s has error: %s",
									getClass(), e.getMessage()));
			return false;
		}
	}

	/**
	 * This function is used to delete an Color in database.
	 * 
	 * @param colorId
	 * @return delete successfully =>true , else => false
	 */
	public boolean deleteColor(ColorModel colorModel) {
		log.info(String
				.format("deleteColor with param 'deleteColor' in class: %s",
						getClass()));
		Color color = dao.findById(colorModel.getColorcode());
		try {
			dao.delete(color);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

}
