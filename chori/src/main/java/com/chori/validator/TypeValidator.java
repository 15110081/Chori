package com.chori.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.chori.model.TypeModel;

public class TypeValidator implements Validator {
	@SuppressWarnings("rawtypes")
	@Override
	public boolean supports(Class clazz) {
		return TypeModel.class.equals(clazz);

	}

	public static boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\.\\d+)?"); // match a number with optional
												// '-' and decimal.
	}

	@Override
	public void validate(Object obj, Errors e) {
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "typecode",
				"field.empty.or.white.space");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "description",
				"field.empty.or.white.space");

		// validate Unit
		TypeModel typeModel = (TypeModel) obj;

		if (typeModel.getTypeCode().length() > 20) {
			e.rejectValue("typecode", "field.size.too.long");
		} else if (typeModel.getTypeCode().trim().length() != typeModel
				.getTypeCode().length()) {
			e.rejectValue("typecode", "field.not.starting.ending.white.space");
		}

		if (typeModel.getDescription().length() > 20) {
			e.rejectValue("description", "field.size.too.long");
		} else if (typeModel.getDescription().trim().length() != typeModel
				.getDescription().length()) {
			e.rejectValue("description",
					"field.not.starting.ending.white.space");
		}

	}

}
