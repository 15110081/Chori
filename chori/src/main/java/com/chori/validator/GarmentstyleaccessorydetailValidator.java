package com.chori.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.chori.model.GarmentstyleaccessorydetailModel;

public class GarmentstyleaccessorydetailValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return GarmentstyleaccessorydetailModel.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors e) {
		GarmentstyleaccessorydetailModel garmentstyleaccessorydetailModel = (GarmentstyleaccessorydetailModel) obj;

		if (garmentstyleaccessorydetailModel.getGarmentstylecode().length() > 50) {
			e.rejectValue("garmentstylecode", "field.size.too.long");
		} else if (garmentstyleaccessorydetailModel.getGarmentstylecode()
				.trim().length() != garmentstyleaccessorydetailModel
				.getGarmentstylecode().length()) {
			e.rejectValue("garmentstylecode",
					"field.not.starting.ending.white.space");
		}

		if (garmentstyleaccessorydetailModel.getAccessorycode().length() > 20) {
			e.rejectValue("accessorycode", "field.size.too.long");
		} else if (garmentstyleaccessorydetailModel.getAccessorycode().trim()
				.length() != garmentstyleaccessorydetailModel
				.getAccessorycode().length()) {
			e.rejectValue("accessorycode",
					"field.not.starting.ending.white.space");
		}

		if (garmentstyleaccessorydetailModel.getUsedvalue() <= 0) {
			e.rejectValue("usedvalue", "used value must greater than 0");
		}

		System.err.println("validated garmentStyleAccessoryDeatail!");
	}

}
