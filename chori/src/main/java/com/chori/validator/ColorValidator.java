package com.chori.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.chori.model.ColorModel;

public class ColorValidator implements Validator {
	@SuppressWarnings("rawtypes")
	@Override
	public boolean supports(Class clazz) {
		return ColorModel.class.equals(clazz);
	}

	public static boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\.\\d+)?"); // match a number with optional
												// '-' and decimal.
	}

	@Override
	public void validate(Object obj, Errors e) {
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "colorcode",
				"field.empty.or.white.space");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "description",
				"field.empty.or.white.space");

		// validate color
		ColorModel colorModel = (ColorModel) obj;
		if (colorModel.getColorcode().length() > 20) {
			e.rejectValue("colorcode", "field.size.too.long");
		} else if (colorModel.getColorcode().trim().length() != colorModel
				.getColorcode().length()) {
			e.rejectValue("colorcode", "field.not.starting.ending.white.space");
		}

		if (colorModel.getDescription().length() > 20) {
			e.rejectValue("description", "field.size.too.long");
		} else if (colorModel.getDescription().trim().length() != colorModel
				.getDescription().length()) {
			e.rejectValue("description",
					"field.not.starting.ending.white.space");
		}

	}
}
