package com.chori.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.chori.model.ShippinglineModel;

public class ShippingLineValidator implements Validator {
	@SuppressWarnings("rawtypes")
	public boolean supports(Class clazz) {
		return ShippinglineModel.class.equals(clazz);
	}

	public static boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\.\\d+)?"); // match a number with optional
												// '-' and decimal.
	}

	@Override
	public void validate(Object obj, Errors e) {
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "shippinglinecode",
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

		// validate shippingline
		ShippinglineModel shippinglineModel = (ShippinglineModel) obj;

		if (shippinglineModel.getShippinglinecode().length() > 20) {
			e.rejectValue("shippinglinecode", "field.size.too.long");
		} else if (shippinglineModel.getShippinglinecode().trim().length() != shippinglineModel
				.getShippinglinecode().length()) {
			e.rejectValue("shippinglinecode",
					"field.not.starting.ending.white.space");
		}

		if (shippinglineModel.getShortname().length() > 20) {
			e.rejectValue("shortname", "field.size.too.long");
		} else if (shippinglineModel.getShortname().trim().length() != shippinglineModel
				.getShortname().length()) {
			e.rejectValue("shortname", "field.not.starting.ending.white.space");
		}

		if (shippinglineModel.getLongname().length() > 50) {
			e.rejectValue("longname", "field.size.too.long");
		} else if (shippinglineModel.getLongname().trim().length() != shippinglineModel
				.getLongname().length()) {
			e.rejectValue("longname", "field.not.starting.ending.white.space");
		}

		if (shippinglineModel.getAddress().length() > 200) {
			e.rejectValue("address", "field.size.too.long");
		} else if (shippinglineModel.getAddress().trim().length() != shippinglineModel
				.getAddress().length()) {
			e.rejectValue("address", "field.not.starting.ending.white.space");
		}

		if (shippinglineModel.getTel().length() > 20) {
			e.rejectValue("tel", "field.size.too.long");
		} else if (shippinglineModel.getTel().trim().length() != shippinglineModel
				.getTel().length()) {
			e.rejectValue("tel", "field.not.starting.ending.white.space");
		}

		if (shippinglineModel.getFax().length() > 20) {
			e.rejectValue("fax", "field.size.too.long");
		} else if (shippinglineModel.getFax().trim().length() != shippinglineModel
				.getFax().length()) {
			e.rejectValue("fax", "field.not.starting.ending.white.space");
		}

		if (shippinglineModel.getTaxno().length() > 20) {
			e.rejectValue("taxno", "field.size.too.long");
		} else if (shippinglineModel.getTaxno().trim().length() != shippinglineModel
				.getTaxno().length()) {
			e.rejectValue("taxno", "field.not.starting.ending.white.space");
		}

		if (shippinglineModel.getStatus().length() > 20) {
			e.rejectValue("status", "field.size.too.long");
		} else if (shippinglineModel.getStatus().trim().length() != shippinglineModel
				.getStatus().length()) {
			e.rejectValue("status", "field.not.starting.ending.white.space");
		}
	}
}
