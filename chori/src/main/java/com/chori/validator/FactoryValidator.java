package com.chori.validator;

import java.util.Set;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.chori.model.FactoryModel;
import com.chori.model.FactorycontactModel;

public class FactoryValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return FactoryModel.class.equals(clazz);
	}

	public static boolean isValidEmailAddress(String email) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(email);
		return m.matches();
	}

	@Override
	public void validate(Object obj, Errors e) {
		// validate factory
		FactoryModel factoryModel = (FactoryModel) obj;

		if (factoryModel.getFactorycode().length() > 50) {
			e.rejectValue("factorycode", "field.size.too.long");
		} else if (factoryModel.getFactorycode().trim().length() != factoryModel
				.getFactorycode().length()) {
			e.rejectValue("factorycode",
					"field.not.starting.ending.white.space");
		}

		if (factoryModel.getShortname().length() > 50) {
			e.rejectValue("shortname", "field.size.too.long");
		} else if (factoryModel.getShortname().trim().length() != factoryModel
				.getShortname().length()) {
			e.rejectValue("shortname", "field.not.starting.ending.white.space");
		}

		if (factoryModel.getLongname().length() > 100) {
			e.rejectValue("longname", "field.size.too.long");
		} else if (factoryModel.getLongname().trim().length() != factoryModel
				.getLongname().length()) {
			e.rejectValue("longname", "field.not.starting.ending.white.space");
		}

		if (factoryModel.getAddress().length() > 200) {
			e.rejectValue("address", "field.size.too.long");
		} else if (factoryModel.getAddress().trim().length() != factoryModel
				.getAddress().length()) {
			e.rejectValue("address", "field.not.starting.ending.white.space");
		}

		if (factoryModel.getTel().length() > 50) {
			e.rejectValue("tel", "field.size.too.long");
		} else if (factoryModel.getTel().trim().length() != factoryModel
				.getTel().length()) {
			e.rejectValue("tel", "field.not.starting.ending.white.space");
		}

		if (factoryModel.getFax().length() > 50) {
			e.rejectValue("fax", "field.size.too.long");
		} else if (factoryModel.getFax().trim().length() != factoryModel
				.getFax().length()) {
			e.rejectValue("fax", "field.not.starting.ending.white.space");
		}

		if (factoryModel.getTaxno().length() > 50) {
			e.rejectValue("taxno", "field.size.too.long");
		} else if (factoryModel.getTaxno().trim().length() != factoryModel
				.getTaxno().length()) {
			e.rejectValue("taxno", "field.not.starting.ending.white.space");
		}

		if (factoryModel.getStatus().length() > 20) {
			e.rejectValue("status", "field.size.too.long");
		} else if (factoryModel.getStatus().trim().length() != factoryModel
				.getStatus().length()) {
			e.rejectValue("status", "field.not.starting.ending.white.space");
		}

		Set<FactorycontactModel> lst = factoryModel
				.getFactorycontactModellist();
		for (FactorycontactModel factorycontactModel : lst) {
			if (factorycontactModel.getName().length() > 50) {
				e.rejectValue("factorycontactModellist", "field.size.too.long");
			} 
//			else if (factorycontactModel.getName().trim().length() != factorycontactModel
//					.getName().length()) {
//				e.rejectValue("factorycontactModellist",
//						"field.not.starting.ending.white.space");
//			} else if (factorycontactModel.getName().length() == 0) {
//				e.rejectValue("factorycontactModellist", "field.empty");
//			}

			if (factorycontactModel.getEmail().length() > 100) {
				e.rejectValue("factorycontactModellist", "field.size.too.long");
			} else if (factorycontactModel.getEmail().trim().length() != factorycontactModel
					.getEmail().length()) {
				e.rejectValue("factorycontactModellist",
						"field.not.starting.ending.white.space");
			} else if (factorycontactModel.getEmail().length() > 0
					&& !isValidEmailAddress(factorycontactModel.getEmail()
							.toString())) {
				e.rejectValue("factorycontactModellist", "typeMismatch");
			}
		}

		System.err.println("factory validated");
	}

}
