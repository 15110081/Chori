package com.chori.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.chori.model.AccessoryGroupModel;

public class AccessoryGroupValidator implements Validator {

	@Override
	public boolean supports(Class clazz) {

		return AccessoryGroupModel.class.equals(clazz);
	}

	public static boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\.\\d+)?"); // match a number with optional
												// '-' and decimal.
	}

	@Override
	public void validate(Object obj, Errors e) {
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "accessorygroupCode",
				"field.empty.or.white.space");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "description",
				"field.empty.or.white.space");

		// validate AccessoryGroup
		AccessoryGroupModel accessoryGroupModel = (AccessoryGroupModel) obj;
		if (accessoryGroupModel.getAccessorygroupCode().length() > 20) {
			e.rejectValue("accessorygroupCode", "field.size.too.long");
		} else if (accessoryGroupModel.getAccessorygroupCode().trim().length() != accessoryGroupModel
				.getAccessorygroupCode().length()) {
			e.rejectValue("accessorygroupCode",
					"field.not.starting.ending.white.space");
		}

		if (accessoryGroupModel.getDescription().length() > 20) {
			e.rejectValue("description", "field.size.too.long");
		} else if (accessoryGroupModel.getDescription().trim().length() != accessoryGroupModel
				.getDescription().length()) {
			e.rejectValue("description",
					"field.not.starting.ending.white.space");
		}

	}

}
