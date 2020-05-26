package com.mycom.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.mycom.pojo.Hotel;

public class HotelValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Hotel.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "hotelName", "errors.hotelName","Hotel Name is requred");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "location", "errors.location","Location is requred");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "image", "errors.image","Image path is requred");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "errors.price","Price is requred");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "errors.description","Description is requred");
	}

}
