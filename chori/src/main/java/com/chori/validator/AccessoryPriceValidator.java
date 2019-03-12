package com.chori.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.chori.model.AccessoryPriceModel;
import com.chori.model.CurrencyexchangeModel;

public class AccessoryPriceValidator implements Validator {
	// unitpriceperunit
	@SuppressWarnings("rawtypes")
	public boolean supports(Class clazz) {
		return CurrencyexchangeModel.class.equals(clazz);
	}

	public static boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\.\\d+)?"); // match a number with optional
												// '-' and decimal.
	}

	@Override
	public void validate(Object obj, Errors e) {
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "unitpriceperunit",
				"field.empty.or.white.space");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "fromdate",
				"field.empty.or.white.space");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "todate",
				"field.empty.or.white.space");

		// validate AccessoryConsumption
		AccessoryPriceModel accessoryPriceModel = (AccessoryPriceModel) obj;

		if (AccessoryConsumptionValidator.isNumeric(accessoryPriceModel
				.getUnitpriceperunit().toString())) {
			System.err.println("Only number");
			e.rejectValue("unitpriceperunit", "typeMismatch");
		}

		if (accessoryPriceModel.getFromdate().after(
				accessoryPriceModel.getTodate())) {
			System.err.println("Date of From cant greater than Date of To");
		}
	}
}
