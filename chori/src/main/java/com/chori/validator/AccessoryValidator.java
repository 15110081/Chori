package com.chori.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.chori.model.AccessoryAddModel;

public class AccessoryValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return AccessoryAddModel.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors e) {

		ValidationUtils.rejectIfEmptyOrWhitespace(e, "accessorycode",
				"field.empty.or.white.space");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "name",
				"field.empty.or.white.space");
		// ValidationUtils.rejectIfEmptyOrWhitespace(e, "dimension",
		// "field.empty.or.white.space");

		AccessoryAddModel accessoryAddModel = (AccessoryAddModel) obj;

		if (accessoryAddModel.getAccessorycode().length() > 100) {
			e.rejectValue("accessorycode", "field.size.too.long");
		} else if (accessoryAddModel.getAccessorycode().trim().length() != accessoryAddModel
				.getAccessorycode().length()) {
			e.rejectValue("accessorycode",
					"field.not.starting.ending.white.space");
		}

		if (accessoryAddModel.getName().length() > 50) {
			e.rejectValue("name", "field.size.too.long");
		} else if (accessoryAddModel.getName().trim().length() != accessoryAddModel
				.getName().length()) {
			e.rejectValue("name", "field.not.starting.ending.white.space");
		}

		if (accessoryAddModel.getDimension().length() > 100) {
			e.rejectValue("dimension", "field.size.too.long");
		} else if (accessoryAddModel.getDimension().trim().length() != accessoryAddModel
				.getDimension().length()) {
			e.rejectValue("dimension", "field.not.starting.ending.white.space");
		}

		System.err.println("Accessory Validated!");
	}

}
