package com.chori.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.chori.model.CurrencyModel;

public class CurrencyValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return CurrencyModel.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors e) {
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "currencycode",
				"field.empty.or.white.space");

		CurrencyModel currencyModel = (CurrencyModel) obj;

		if (currencyModel.getCurrencycode().length() > 20) {
			e.rejectValue("currencycode", "field.size.too.long");
		} else if (currencyModel.getCurrencycode().trim().length() != currencyModel
				.getCurrencycode().length()) {
			e.rejectValue("currencycode",
					"field.not.starting.ending.white.space");
		}

		if (currencyModel.getName().length() > 50) {
			e.rejectValue("name", "field.size.too.long");
		}

		System.err.println("Currency validated");
	}

}
