package com.chori.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.chori.model.UserModel;

public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return UserModel.class.equals(clazz);
	}

	public boolean isValidEmailAddress(String email) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(email);
		return m.matches();
	}

	@Override
	public void validate(Object obj, Errors e) {
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "username",
				"field.empty.or.white.space");
		// ValidationUtils.rejectIfEmptyOrWhitespace(e, "password",
		// "field.empty.or.white.space");

		UserModel userModel = (UserModel) obj;

		if (userModel.getUsername().length() > 50) {
			e.rejectValue("username", "field.size.too.long");
		} else if (userModel.getUsername().trim().length() != userModel
				.getUsername().length()) {
			e.rejectValue("username", "field.not.starting.ending.white.space");
		}

		if (userModel.getPassword() != null
				&& userModel.getPassword().length() > 50) {
			e.rejectValue("password", "field.size.too.long");
		} else if (userModel.getPassword() != null
				&& userModel.getPassword().trim().length() != userModel
						.getPassword().length()) {
			e.rejectValue("password", "field.not.starting.ending.white.space");
		}

		if (userModel.getFirstname().length() > 50) {
			e.rejectValue("firstname", "field.size.too.long");
		} else if (userModel.getFirstname().trim().length() != userModel
				.getFirstname().length()) {
			e.rejectValue("firstname", "field.not.starting.ending.white.space");
		}

		if (userModel.getLastname().length() > 50) {
			e.rejectValue("lastname", "field.size.too.long");
		} else if (userModel.getLastname().trim().length() != userModel
				.getLastname().length()) {
			e.rejectValue("lastname", "field.not.starting.ending.white.space");
		}

		if (userModel.getEmail().length() > 50) {
			e.rejectValue("email", "field.size.too.long");
		} else if (userModel.getEmail().trim().length() != userModel.getEmail()
				.length()) {
			e.rejectValue("email", "field.not.starting.ending.white.space");
		} else if (userModel.getEmail().length() > 0
				&& !isValidEmailAddress(userModel.getEmail().toString())) {
			e.rejectValue("email", "typeMismatch");
		}

		// if (userModel.getEmailpassword().length() > 50) {
		// e.rejectValue("emailpassword", "field.size.too.long");
		// } else if (userModel.getEmailpassword().trim().length() != userModel
		// .getEmailpassword().length()) {
		// e.rejectValue("emailpassword",
		// "field.not.starting.ending.white.space");
		// }

		if (userModel.getPhone().length() > 20) {
			e.rejectValue("phone", "field.size.too.long");
		} else if (userModel.getPhone().trim().length() != userModel.getPhone()
				.length()) {
			e.rejectValue("phone", "field.not.starting.ending.white.space");
		}

		System.err.println("C� Validate user");
	}

}
