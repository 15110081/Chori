package com.chori.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.chori.model.DestinationModel;

public class DestinationValidator implements Validator {
	@SuppressWarnings("rawtypes")
	@Override
	public boolean supports(Class clazz) {
		return DestinationModel.class.equals(clazz);
	}

	public static boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\.\\d+)?"); // match a number with optional
												// '-' and decimal.
	}

	@Override
	public void validate(Object obj, Errors e) {
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "destinationcode",
				"field.empty.or.white.space");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "description",
				"field.empty.or.white.space");

		// validate destination
		DestinationModel destinationModel = (DestinationModel) obj;
		if (destinationModel.getDestinationcode().length() > 20) {
			e.rejectValue("destinationcode", "field.size.too.long");
		} else if (destinationModel.getDestinationcode().trim().length() != destinationModel
				.getDestinationcode().length()) {
			e.rejectValue("destinationcode",
					"field.not.starting.ending.white.space");
		}

		if (destinationModel.getDescription().length() > 20) {
			e.rejectValue("description", "field.size.too.long");
		} else if (destinationModel.getDescription().trim().length() != destinationModel
				.getDescription().length()) {
			e.rejectValue("description",
					"field.not.starting.ending.white.space");
		}
	}
}
