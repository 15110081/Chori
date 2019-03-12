package com.chori.validator;

import java.util.Set;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.chori.model.FabricsupplierModel;
import com.chori.model.FabricsuppliercontactModel;

public class FabricsupplierValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return FabricsupplierModel.class.equals(clazz);
	}

	public static boolean isValidEmailAddress(String email) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(email);
		return m.matches();
	}

	@Override
	public void validate(Object obj, Errors e) {
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "fabricsupcode",
				"field.empty.or.white.space");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "shortname",
				"field.empty.or.white.space");

		FabricsupplierModel fabricsupplierModel = (FabricsupplierModel) obj;

		if (fabricsupplierModel.getFabricsupcode().length() > 50) {
			e.rejectValue("fabricsupcode", "field.size.too.long");
		} else if (fabricsupplierModel.getFabricsupcode().trim().length() != fabricsupplierModel
				.getFabricsupcode().length()) {
			e.rejectValue("fabricsupcode",
					"field.not.starting.ending.white.space");
		}

		if (fabricsupplierModel.getShortname().length() > 50) {
			e.rejectValue("shortname", "field.size.too.long");
		} else if (fabricsupplierModel.getShortname().trim().length() != fabricsupplierModel
				.getShortname().length()) {
			e.rejectValue("shortname", "field.not.starting.ending.white.space");
		}

		if (fabricsupplierModel.getLongname().length() > 100) {
			e.rejectValue("longname", "field.size.too.long");
		} else if (fabricsupplierModel.getLongname().trim().length() != fabricsupplierModel
				.getLongname().length()) {
			e.rejectValue("longname", "field.not.starting.ending.white.space");
		}

		if (fabricsupplierModel.getAddress().length() > 200) {
			e.rejectValue("address", "field.size.too.long");
		} else if (fabricsupplierModel.getAddress().trim().length() != fabricsupplierModel
				.getAddress().length()) {
			e.rejectValue("address", "field.not.starting.ending.white.space");
		}

		if (fabricsupplierModel.getTel().length() > 50) {
			e.rejectValue("tel", "field.size.too.long");
		} else if (fabricsupplierModel.getTel().trim().length() != fabricsupplierModel
				.getTel().length()) {
			e.rejectValue("tel", "field.not.starting.ending.white.space");
		}

		if (fabricsupplierModel.getFax().length() > 50) {
			e.rejectValue("fax", "field.size.too.long");
		} else if (fabricsupplierModel.getFax().trim().length() != fabricsupplierModel
				.getFax().length()) {
			e.rejectValue("fax", "field.not.starting.ending.white.space");
		}

		if (fabricsupplierModel.getTaxno().length() > 50) {
			e.rejectValue("taxno", "field.size.too.long");
		} else if (fabricsupplierModel.getTaxno().trim().length() != fabricsupplierModel
				.getTaxno().length()) {
			e.rejectValue("taxno", "field.not.starting.ending.white.space");
		}

		Set<FabricsuppliercontactModel> lst = fabricsupplierModel
				.getFabricsuppliercontactModelList();
		for (FabricsuppliercontactModel fabricsuppliercontactModel : lst) {
			if (fabricsuppliercontactModel.getName().length() > 50) {
				e.rejectValue("fabricsuppliercontactModelList",
						"field.size.too.long");
			} 
//			else if (fabricsuppliercontactModel.getName().trim().length() != fabricsuppliercontactModel
//					.getName().length()) {
//				e.rejectValue("fabricsuppliercontactModelList",
//						"field.not.starting.ending.white.space");
//			} else if (fabricsuppliercontactModel.getName().length() == 0) {
//				e.rejectValue("fabricsuppliercontactModelList", "field.empty");
//			}

			if (fabricsuppliercontactModel.getEmail().length() > 100) {
				e.rejectValue("fabricsuppliercontactModelList",
						"field.size.too.long");
			} else if (fabricsuppliercontactModel.getEmail().trim().length() != fabricsuppliercontactModel
					.getEmail().length()) {
				e.rejectValue("fabricsuppliercontactModelList",
						"field.not.starting.ending.white.space");
			} else if (fabricsuppliercontactModel.getEmail().length() > 0
					&& !ShippingLineContactValidator
							.isValidEmailAddress(fabricsuppliercontactModel
									.getEmail().toString())) {
				e.rejectValue("fabricsuppliercontactModelList", "typeMismatch");
			}
		}

		System.err.println("Validated factory");
	}

}
