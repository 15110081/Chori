//package com.chori.validator;
//
//import org.springframework.validation.Errors;
//import org.springframework.validation.ValidationUtils;
//import org.springframework.validation.Validator;
//
//import com.chori.model.SizeModel;
//
//
//public class SizeValidator implements Validator {
//	@SuppressWarnings("rawtypes")
//
//	@Override
//	public boolean supports(Class clazz) {
//		return SizeModel.class.equals(clazz);
//
//	}
//	public static boolean isNumeric(String str)
//	{
//	  return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
//	}
//	
//	@Override
//	public void validate(Object obj, Errors e) {
//		ValidationUtils.rejectIfEmptyOrWhitespace(e, "sizeCode", "field.empty.or.white.space");
//		ValidationUtils.rejectIfEmptyOrWhitespace(e, "description", "field.empty.or.white.space");
//		
//		//validate Unit
//		SizeModel sizeModel = (SizeModel) obj;
//
//		if (sizeModel.getSizeCode().length() > 20) {
//			e.rejectValue("sizeCode", "field.size.too.long");
//		} else if (sizeModel.getSizeCode().trim().length() != sizeModel.getSizeCode().length()) {
//			e.rejectValue("sizeCode", "field.not.starting.ending.white.space");
//		}
//		
//		if (sizeModel.getDescription().length() > 20) {
//			e.rejectValue("description", "field.size.too.long");
//		} else if (sizeModel.getDescription().trim().length() != sizeModel.getDescription().length()) {
//			e.rejectValue("description", "field.not.starting.ending.white.space");
//		}
//	
//	}
//	
//
// }
