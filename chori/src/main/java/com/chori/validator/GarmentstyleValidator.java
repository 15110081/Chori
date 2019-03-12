package com.chori.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.chori.model.GarmentstyleModel;

public class GarmentstyleValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return GarmentstyleModel.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors e) {
		GarmentstyleModel garmentstyleModel = (GarmentstyleModel) obj;

		if (garmentstyleModel.getGarmentstylecode().length() > 100) {
			e.rejectValue("garmentstylecode", "field.size.too.long");
		} else if (garmentstyleModel.getGarmentstylecode().trim().length() != garmentstyleModel
				.getGarmentstylecode().length()) {
			e.rejectValue("garmentstylecode",
					"field.not.starting.ending.white.space");
		}

		if (garmentstyleModel.getGarmentkindcode() != null
				&& garmentstyleModel.getGarmentkindcode().length() > 50) {
			e.rejectValue("garmentkindcode", "field.size.too.long");
		} else if (garmentstyleModel.getGarmentkindcode() != null
				&& garmentstyleModel.getGarmentkindcode().trim().length() != garmentstyleModel
						.getGarmentkindcode().length()) {
			e.rejectValue("garmentkindcode",
					"field.not.starting.ending.white.space");
		}

		if (garmentstyleModel.getFactorycode() != null
				&& garmentstyleModel.getFactorycode().length() > 50) {
			e.rejectValue("factorycode", "field.size.too.long");
		} else if (garmentstyleModel.getFactorycode() != null
				&& garmentstyleModel.getFactorycode().trim().length() != garmentstyleModel
						.getFactorycode().length()) {
			e.rejectValue("factorycode",
					"field.not.starting.ending.white.space");
		}

		if (garmentstyleModel.getCustomercode() != null
				&& garmentstyleModel.getCustomercode().length() > 50) {
			e.rejectValue("customercode", "field.size.too.long");
		} else if (garmentstyleModel.getCustomercode() != null
				&& garmentstyleModel.getCustomercode().trim().length() != garmentstyleModel
						.getCustomercode().length()) {
			e.rejectValue("customercode",
					"field.not.starting.ending.white.space");
		}

		if (garmentstyleModel.getDescription().length() > 500) {
			e.rejectValue("customercode", "field.size.too.long");
		}

		// if(garmentstyleModel.getImgurl1().length()>500){
		// e.rejectValue("imgurl1", "field.size.too.long");
		// }else
		// if(garmentstyleModel.getImgurl1().trim().length()!=garmentstyleModel.getImgurl1().length()){
		// e.rejectValue("imgurl1", "field.not.starting.ending.white.space");
		// }
		//
		// if(garmentstyleModel.getImgurl2().length()>500){
		// e.rejectValue("imgurl2", "field.size.too.long");
		// }else
		// if(garmentstyleModel.getImgurl2().trim().length()!=garmentstyleModel.getImgurl2().length()){
		// e.rejectValue("imgurl2", "field.not.starting.ending.white.space");
		// }
		//
		// if(garmentstyleModel.getImgurl3().length()>500){
		// e.rejectValue("imgurl3", "field.size.too.long");
		// }else
		// if(garmentstyleModel.getImgurl3().trim().length()!=garmentstyleModel.getImgurl3().length()){
		// e.rejectValue("imgurl3", "field.not.starting.ending.white.space");
		// }
		//
		// if(garmentstyleModel.getImgurl4().length()>500){
		// e.rejectValue("imgurl4", "field.size.too.long");
		// }else
		// if(garmentstyleModel.getImgurl4().trim().length()!=garmentstyleModel.getImgurl4().length()){
		// e.rejectValue("imgurl4", "field.not.starting.ending.white.space");
		// }
		//
		// if(garmentstyleModel.getImgurl5().length()>500){
		// e.rejectValue("imgurl5", "field.size.too.long");
		// }else
		// if(garmentstyleModel.getImgurl5().trim().length()!=garmentstyleModel.getImgurl5().length()){
		// e.rejectValue("imgurl5", "field.not.starting.ending.white.space");
		// }

		// validate referprice (add check number when have time)
		if (garmentstyleModel.getReferprice() != null
				&& garmentstyleModel.getReferprice() < 0) {
			e.rejectValue("referprice",
					"referprice must greater or atleast equals 0");
		}

		System.err.println("validated garment style!");
	}

}
