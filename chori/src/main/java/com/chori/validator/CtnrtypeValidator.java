package com.chori.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.chori.model.CtnrtypeModel;

public class CtnrtypeValidator implements Validator {
	@SuppressWarnings("rawtypes")
	public boolean supports(Class clazz) {
		return CtnrtypeModel.class.equals(clazz);
	}

	public static boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\.\\d+)?"); // match a number with optional
												// '-' and decimal.
	}

	@Override
	public void validate(Object obj, Errors e) {
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "ctnrtypecode",
				"field.empty.or.white.space");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "ctnrtypecode",
				"field.empty.or.white.space");

		CtnrtypeModel ctnrModel = (CtnrtypeModel) obj;

		if (ctnrModel.getCtnrcode().length() > 20) {
			e.rejectValue("ctnrtypecode", "field.size.too.long");
		} else if (ctnrModel.getCtnrcode().trim().length() != ctnrModel
				.getCtnrcode().length()) {
			e.rejectValue("ctnrtypecode",
					"field.not.starting.ending.white.space");
		}

		if (ctnrModel.getDescription().length() > 20) {
			e.rejectValue("description", "field.size.too.long");
		} else if (ctnrModel.getDescription().trim().length() != ctnrModel
				.getDescription().length()) {
			e.rejectValue("description",
					"field.not.starting.ending.white.space");
		}
	}
}
