package com.chori.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.chori.model.CurrencyexchangeModel;

public class CurrencyexchangeValidator implements Validator {

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
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "currencyexchange",
				"field.empty.or.white.space");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "amount",
				"field.empty.or.white.space");

		// validate customer
		CurrencyexchangeModel currencyexchangeModel = (CurrencyexchangeModel) obj;

		if (currencyexchangeModel.getCurrencyexchangecode() > 0) {
			e.rejectValue("Currencyexchangecode", "Must be greater than 0");
		} else if (currencyexchangeModel.getCurrencyexchangecode() != currencyexchangeModel
				.getCurrencyexchangecode()) {
			e.rejectValue("Currencyexchangecode",
					"field.not.starting.ending.white.space");
		}

		if (currencyexchangeModel.getAmount() > 0) {
			e.rejectValue("getAmount", "Must be greater than 0");
		} else if (currencyexchangeModel.getAmount() != currencyexchangeModel
				.getAmount()) {
			e.rejectValue("getAmount", "field.not.starting.ending.white.space");
		}

	}
}
