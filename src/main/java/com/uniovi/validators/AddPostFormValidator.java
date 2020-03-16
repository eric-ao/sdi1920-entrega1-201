package com.uniovi.validators;

import com.uniovi.entities.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.*;

@Component
public class AddPostFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "Error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "body", "Error.empty");
	}
}