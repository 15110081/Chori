package com.chori.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.chori.model.AccessoryConsumptionModel;

public class AccessoryConsumptionValidator implements Validator {

	@SuppressWarnings("rawtypes")
	public boolean supports(Class clazz) {
		return AccessoryConsumptionModel.class.equals(clazz);
	}

	public static boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\.\\d+)?"); // match a number with optional
												// '-' and decimal.
	}

	@Override
	public void validate(Object obj, Errors e) {
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "consumption",
				"field.empty.or.white.space");

		// validate AccessoryConsumption
		AccessoryConsumptionModel accessoryConsumptionModel = (AccessoryConsumptionModel) obj;

		// validate accessoryConsumption >0
		if (accessoryConsumptionModel.getConsumption() <= 0) {
			e.rejectValue("consumption", "field.must.greater.than.zero");
		}
		if (AccessoryConsumptionValidator.isNumeric(accessoryConsumptionModel
				.getConsumption().toString())) {
			System.err.println("fap fapf apf apf apfpaf pafpaf");
			e.rejectValue("consumption", "typeMismatch");
		}

	}
}
