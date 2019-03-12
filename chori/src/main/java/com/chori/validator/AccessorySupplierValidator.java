package com.chori.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.chori.model.AccessorySupplierModel;

public class AccessorySupplierValidator implements Validator {
	@SuppressWarnings("rawtypes")
	public boolean supports(Class clazz) {
		return AccessorySupplierModel.class.equals(clazz);
	}

	public static boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\.\\d+)?"); // match a number with optional
												// '-' and decimal.
	}
	
	public boolean isValidEmailAddress(String email) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(email);
		return m.matches();
	}

	@Override
	public void validate(Object obj, Errors e) {
//		ValidationUtils.rejectIfEmptyOrWhitespace(e, "accessorysuppliercode",
//				"field.empty.or.white.space");
//		ValidationUtils.rejectIfEmptyOrWhitespace(e, "shortname",
//				"field.empty.or.white.space");
//		ValidationUtils.rejectIfEmptyOrWhitespace(e, "longname",
//				"field.empty.or.white.space");
//		ValidationUtils.rejectIfEmptyOrWhitespace(e, "address",
//				"field.empty.or.white.space");
//		ValidationUtils.rejectIfEmptyOrWhitespace(e, "tel",
//				"field.empty.or.white.space");
//		ValidationUtils.rejectIfEmptyOrWhitespace(e, "fax",
//				"field.empty.or.white.space");
//		ValidationUtils.rejectIfEmptyOrWhitespace(e, "taxno",
//				"field.empty.or.white.space");
//		ValidationUtils.rejectIfEmptyOrWhitespace(e, "status",
//				"field.empty.or.white.space");
//
//		ValidationUtils.rejectIfEmptyOrWhitespace(e, "namr",
//				"field.empty.or.white.space");
//		ValidationUtils.rejectIfEmptyOrWhitespace(e, "email",
//				"field.empty.or.white.space");

		// validate customer
		AccessorySupplierModel accsupModel = (AccessorySupplierModel) obj;

		if (accsupModel.getAccessorysuppliercode().length() > 20) {
			e.rejectValue("accessorysuppliercode", "field.size.too.long");
		} else if (accsupModel.getAccessorysuppliercode().trim().length() != accsupModel
				.getAccessorysuppliercode().length()) {
			e.rejectValue("accessorysuppliercode",
					"field.not.starting.ending.white.space");
		}

		if (accsupModel.getShortname().length() > 20) {
			e.rejectValue("shortname", "field.size.too.long");
		} else if (accsupModel.getShortname().trim().length() != accsupModel
				.getShortname().length()) {
			e.rejectValue("shortname", "field.not.starting.ending.white.space");
		}

		if (accsupModel.getLongname().length() > 50) {
			e.rejectValue("longname", "field.size.too.long");
		} else if (accsupModel.getLongname().trim().length() != accsupModel
				.getLongname().length()) {
			e.rejectValue("longname", "field.not.starting.ending.white.space");
		}

		if (accsupModel.getAddress().length() > 200) {
			e.rejectValue("address", "field.size.too.long");
		} else if (accsupModel.getAddress().trim().length() != accsupModel
				.getAddress().length()) {
			e.rejectValue("address", "field.not.starting.ending.white.space");
		}

		if (accsupModel.getTel().length() > 20) {
			e.rejectValue("tel", "field.size.too.long");
		} else if (accsupModel.getTel().trim().length() != accsupModel.getTel()
				.length()) {
			e.rejectValue("tel", "field.not.starting.ending.white.space");
		}

		if (accsupModel.getFax().length() > 20) {
			e.rejectValue("fax", "field.size.too.long");
		} else if (accsupModel.getFax().trim().length() != accsupModel.getFax()
				.length()) {
			e.rejectValue("fax", "field.not.starting.ending.white.space");
		}

		if (accsupModel.getTaxno().length() > 20) {
			e.rejectValue("taxno", "field.size.too.long");
		} else if (accsupModel.getTaxno().trim().length() != accsupModel
				.getTaxno().length()) {
			e.rejectValue("taxno", "field.not.starting.ending.white.space");
		}

		if (accsupModel.getStatus().length() > 20) {
			e.rejectValue("status", "field.size.too.long");
		} else if (accsupModel.getStatus().trim().length() != accsupModel
				.getStatus().length()) {
			e.rejectValue("status", "field.not.starting.ending.white.space");
		}
		
		if (accsupModel.getEmail().length() > 50) {
			e.rejectValue("email", "field.size.too.long");
		} else if (accsupModel.getEmail().trim().length() != accsupModel.getEmail()
				.length()) {
			e.rejectValue("email", "field.not.starting.ending.white.space");
		} else if (accsupModel.getEmail().length() > 0
				&& !isValidEmailAddress(accsupModel.getEmail().toString())) {
			e.rejectValue("email", "typeMismatch");
		}
		
		System.err.println("có validate supplier");
	}
}
