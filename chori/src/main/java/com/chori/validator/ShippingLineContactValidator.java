package com.chori.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.chori.model.ShippinglineContactModel;

public class ShippingLineContactValidator implements Validator {
	@SuppressWarnings("rawtypes")
	public boolean supports(Class clazz) {
		return ShippinglineContactModel.class.equals(clazz);
	}

	public static boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\.\\d+)?"); // match a number with optional
												// '-' and decimal.
	}

	public static boolean isValidEmailAddress(String email) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(email);
		return m.matches();
	}

	@Override
	public void validate(Object obj, Errors e) {
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "name",
				"field.empty.or.white.space");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "email",
				"field.empty.or.white.space");

		// Validate Shippinglinecontact
		ShippinglineContactModel shippinglineContactModel = (ShippinglineContactModel) obj;

		if (shippinglineContactModel.getName().length() > 20) {
			e.rejectValue("name", "field.size.too.long");
		} else if (shippinglineContactModel.getName().trim().length() != shippinglineContactModel
				.getName().length()) {
			e.rejectValue("name", "field.not.starting.ending.white.space");
		}

		if (shippinglineContactModel.getEmail().length() > 20) {
			e.rejectValue("email", "field.size.too.long");
		} else if (shippinglineContactModel.getEmail().trim().length() != shippinglineContactModel
				.getEmail().length()) {
			e.rejectValue("email", "field.not.starting.ending.white.space");
		} else if (ShippingLineContactValidator
				.isValidEmailAddress(shippinglineContactModel.getEmail()
						.toString())) {
			e.rejectValue("email", "typeMismatch");
		}
	}
}
