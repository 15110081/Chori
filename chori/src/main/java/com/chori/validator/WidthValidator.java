package com.chori.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.chori.model.WidthModel;

public class WidthValidator implements Validator {
	@SuppressWarnings("rawtypes")
	public boolean supports(Class clazz) {
		return WidthModel.class.equals(clazz);
	}

	public static boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\.\\d+)?"); // match a number with optional
												// '-' and decimal.
	}

	@Override
	public void validate(Object obj, Errors e) {
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "widthcode",
				"field.empty.or.white.space");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "widthvalues",
				"field.empty.or.white.space");

		WidthModel widthModel = (WidthModel) obj;

		if (widthModel.getWidthcode().length() > 20) {
			e.rejectValue("widthcode", "field.size.too.long");
		} else if (widthModel.getWidthcode().trim().length() != widthModel
				.getWidthcode().length()) {
			e.rejectValue("widthcode", "field.not.starting.ending.white.space");
		}

		if (widthModel.getWidthvalues().length() > 20) {
			e.rejectValue("widthvalues", "field.size.too.long");
		} else if (widthModel.getWidthvalues().trim().length() != widthModel
				.getWidthvalues().length()) {
			e.rejectValue("widthvalues",
					"field.not.starting.ending.white.space");
		}
	}
}
