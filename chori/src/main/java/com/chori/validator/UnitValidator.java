package com.chori.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.chori.model.UnitModel;

public class UnitValidator implements Validator {
	@SuppressWarnings("rawtypes")
	public boolean supports(Class clazz) {
		return UnitModel.class.equals(clazz);
	}

	public static boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\.\\d+)?"); // match a number with optional
												// '-' and decimal.
	}

	@Override
	public void validate(Object obj, Errors e) {
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "unitcode",
				"field.empty.or.white.space");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "name",
				"field.empty.or.white.space");

		// validate Unit
		UnitModel unitModel = (UnitModel) obj;

		if (unitModel.getUnitcode().length() > 20) {
			e.rejectValue("unitcode", "field.size.too.long");
		} else if (unitModel.getUnitcode().trim().length() != unitModel
				.getUnitcode().length()) {
			e.rejectValue("unitcode", "field.not.starting.ending.white.space");
		}

		if (unitModel.getName().length() > 20) {
			e.rejectValue("name", "field.size.too.long");
		} else if (unitModel.getName().trim().length() != unitModel.getName()
				.length()) {
			e.rejectValue("name", "field.not.starting.ending.white.space");
		}
	}

}
