package com.chori.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.chori.model.CustomerModel;

public class CustomerValidator implements Validator {
	@SuppressWarnings("rawtypes")
	public boolean supports(Class clazz) {
		return CustomerModel.class.equals(clazz);
	}

	public static boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\.\\d+)?"); // match a number with optional
												// '-' and decimal.
	}

	@Override
	public void validate(Object obj, Errors e) {
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "customercode",
				"field.empty.or.white.space");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "shortname",
				"field.empty.or.white.space");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "longname",
				"field.empty.or.white.space");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "address",
				"field.empty.or.white.space");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "tel",
				"field.empty.or.white.space");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "fax",
				"field.empty.or.white.space");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "taxno",
				"field.empty.or.white.space");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "status",
				"field.empty.or.white.space");

		ValidationUtils.rejectIfEmptyOrWhitespace(e, "contactname",
				"field.empty.or.white.space");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "contactemail",
				"field.empty.or.white.space");

		// validate customer
		CustomerModel customerModel = (CustomerModel) obj;

		if (customerModel.getCustomercode().length() > 20) {
			e.rejectValue("customercode", "field.size.too.long");
		} else if (customerModel.getCustomercode().trim().length() != customerModel
				.getCustomercode().length()) {
			e.rejectValue("customercode",
					"field.not.starting.ending.white.space");
		}

		if (customerModel.getShortname().length() > 20) {
			e.rejectValue("shortname", "field.size.too.long");
		} else if (customerModel.getShortname().trim().length() != customerModel
				.getShortname().length()) {
			e.rejectValue("shortname", "field.not.starting.ending.white.space");
		}

		if (customerModel.getLongname().length() > 50) {
			e.rejectValue("longname", "field.size.too.long");
		} else if (customerModel.getLongname().trim().length() != customerModel
				.getLongname().length()) {
			e.rejectValue("longname", "field.not.starting.ending.white.space");
		}

		if (customerModel.getAddress().length() > 200) {
			e.rejectValue("address", "field.size.too.long");
		} else if (customerModel.getAddress().trim().length() != customerModel
				.getAddress().length()) {
			e.rejectValue("address", "field.not.starting.ending.white.space");
		}

		if (customerModel.getTel().length() > 20) {
			e.rejectValue("tel", "field.size.too.long");
		} else if (customerModel.getTel().trim().length() != customerModel
				.getTel().length()) {
			e.rejectValue("tel", "field.not.starting.ending.white.space");
		}

		if (customerModel.getFax().length() > 20) {
			e.rejectValue("fax", "field.size.too.long");
		} else if (customerModel.getFax().trim().length() != customerModel
				.getFax().length()) {
			e.rejectValue("fax", "field.not.starting.ending.white.space");
		}

		if (customerModel.getTaxno().length() > 20) {
			e.rejectValue("taxno", "field.size.too.long");
		} else if (customerModel.getTaxno().trim().length() != customerModel
				.getTaxno().length()) {
			e.rejectValue("taxno", "field.not.starting.ending.white.space");
		}

		if (customerModel.getStatus().length() > 20) {
			e.rejectValue("status", "field.size.too.long");
		} else if (customerModel.getStatus().trim().length() != customerModel
				.getStatus().length()) {
			e.rejectValue("status", "field.not.starting.ending.white.space");
		}
	}
}
