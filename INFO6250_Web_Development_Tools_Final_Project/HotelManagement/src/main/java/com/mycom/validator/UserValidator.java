package com.mycom.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.mycom.dao.UserDao;
import com.mycom.pojo.User;

public class UserValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		User user = (User)target;
		System.out.print("validations");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstname", "errors.firstname","First Name is requred");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname", "errors.lastname","Last Name is requred");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userEmail", "errors.userEmail","Email is requred");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userPassword", "errors.userPassword","Password is requred");
		
		String userEmail = user.getUserEmail();
		UserDao userdao = new UserDao();
		
		try {
			System.out.println("The Uname inside try is  : " +user.getUserEmail());
			User u = userdao.getUserEmail(userEmail);
			if (u != null){
				errors.rejectValue("userEmail", "errors.userEmail", "email address already exists");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Exception: " + e.getMessage());
			
			
		}
		
	}

}
