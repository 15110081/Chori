package com.chori.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.chori.model.GarmentkindModel;

public class GarmentkindValidator implements Validator {
	@SuppressWarnings("rawtypes")
	@Override
	public boolean supports(Class clazz) {
		return GarmentkindModel.class.equals(clazz);
	}

	public static boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\.\\d+)?"); // match a number with optional
												// '-' and decimal.
	}

	@Override
	public void validate(Object obj, Errors e) {
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "garmentkindcode",
				"field.empty.or.white.space");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "description",
				"field.empty.or.white.space");

		// validate garmentkind
		GarmentkindModel gmkModel = (GarmentkindModel) obj;
		if (gmkModel.getGarmentkindcode().length() > 20) {
			e.rejectValue("garmentkindcode", "field.size.too.long");
		} else if (gmkModel.getGarmentkindcode().trim().length() != gmkModel
				.getGarmentkindcode().length()) {
			e.rejectValue("garmentkindcode",
					"field.not.starting.ending.white.space");
		}

		if (gmkModel.getDescription().length() > 500) {
			e.rejectValue("description", "field.size.too.long");
		} else if (gmkModel.getDescription().trim().length() != gmkModel
				.getDescription().length()) {
			e.rejectValue("description",
					"field.not.starting.ending.white.space");
		}

	}

}
