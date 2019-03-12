package com.chori.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.chori.model.AgentModel;

public class AgentValidator implements Validator {
	@SuppressWarnings("rawtypes")
	public boolean supports(Class clazz) {
		return AgentModel.class.equals(clazz);
	}

	public static boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\.\\d+)?"); // match a number with optional
												// '-' and decimal.
	}

	@Override
	public void validate(Object obj, Errors e) {
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

		// validate agent
		AgentModel agentModel = (AgentModel) obj;

		if (agentModel.getShortname().length() > 20) {
			e.rejectValue("shortname", "field.size.too.long");
		} else if (agentModel.getShortname().trim().length() != agentModel
				.getShortname().length()) {
			e.rejectValue("shortname", "field.not.starting.ending.white.space");
		}

		if (agentModel.getLongname().length() > 50) {
			e.rejectValue("longname", "field.size.too.long");
		} else if (agentModel.getLongname().trim().length() != agentModel
				.getLongname().length()) {
			e.rejectValue("longname", "field.not.starting.ending.white.space");
		}

		if (agentModel.getAddress().length() > 200) {
			e.rejectValue("address", "field.size.too.long");
		} else if (agentModel.getAddress().trim().length() != agentModel
				.getAddress().length()) {
			e.rejectValue("address", "field.not.starting.ending.white.space");
		}

		if (agentModel.getTel().length() > 20) {
			e.rejectValue("tel", "field.size.too.long");
		} else if (agentModel.getTel().trim().length() != agentModel.getTel()
				.length()) {
			e.rejectValue("tel", "field.not.starting.ending.white.space");
		}

		if (agentModel.getFax().length() > 20) {
			e.rejectValue("fax", "field.size.too.long");
		} else if (agentModel.getFax().trim().length() != agentModel.getFax()
				.length()) {
			e.rejectValue("fax", "field.not.starting.ending.white.space");
		}

		if (agentModel.getTaxno().length() > 20) {
			e.rejectValue("taxno", "field.size.too.long");
		} else if (agentModel.getTaxno().trim().length() != agentModel
				.getTaxno().length()) {
			e.rejectValue("taxno", "field.not.starting.ending.white.space");
		}

		if (agentModel.getStatus().length() > 20) {
			e.rejectValue("status", "field.size.too.long");
		} else if (agentModel.getStatus().trim().length() != agentModel
				.getStatus().length()) {
			e.rejectValue("status", "field.not.starting.ending.white.space");
		}
	}
}
