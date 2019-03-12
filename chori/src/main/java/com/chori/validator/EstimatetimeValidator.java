package com.chori.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.chori.model.EstimatetimeModel;

public class EstimatetimeValidator implements Validator {
	@SuppressWarnings("rawtypes")
	public boolean supports(Class clazz) {
		return EstimatetimeModel.class.equals(clazz);
	}

	public static boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\.\\d+)?"); // match a number with optional
												// '-' and decimal.
	}

	@Override
	public void validate(Object obj, Errors e) {
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "estimateCode",
				"field.empty.or.white.space");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "piconpletion",
				"field.empty.or.white.space");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "packingaccdelv",
				"field.empty.or.white.space");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "manuaccdelv",
				"field.empty.or.white.space");

		// validate Estimatetime
		EstimatetimeModel estimatetimeModel = (EstimatetimeModel) obj;

		if (estimatetimeModel.getEstimateCode() < 0) {
			e.rejectValue("estimateCode", "must be greater than 0");
		}

		if (estimatetimeModel.getPiconpletion() < 0) {
			e.rejectValue("piconpletion", "must be greater than 0");
		}
		if (estimatetimeModel.getPackingaccdelv() < 0) {
			e.rejectValue("packingaccdelv", "must be greater than 0");
		}
		if (estimatetimeModel.getManuaccdelv() < 0) {
			e.rejectValue("manuaccdelv", "must be greater than 0");
		}

	}

}
