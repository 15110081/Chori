package com.chori.service;

import java.util.List;

import com.chori.AbstractService;
import com.chori.entity.Color;
import com.chori.model.ColorModel;

public interface ColorService extends AbstractService<Color, String> {

	boolean isColorExistedById(String clCode);

	List<ColorModel> getAllColor();

	ColorModel findColorModelById(String colorCode);

	boolean addColor(ColorModel colorMo, String username);

	boolean editColor(ColorModel colorMo);

	boolean deleteColor(ColorModel colorModel);

}
