package com.converter.currency.demo.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.converter.currency.demo.model.User;
import com.converter.currency.demo.service.api.UserService;

@Component
public class UserValidator implements Validator {
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
	private static final String NOT_EMPTY = "NotEmpty";
	private static final String DOB = "dob";
	private static final String ADDRESS="address";
	private static final String ZIPCODE ="zipCode";
	private static final String CITY = "city";
	private static final String COUNTRY = "country";
	private static final String USERNAME = "username";
	private static final String P_FILED = "password";
	private static final String P_CONF = "passwordConfirm";
	private static final String EMAIL = "email";

	@Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

	@Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        validatePrimaryFields(user, errors);
        validateSecondaryFields(user,errors);
    }

	private void validateSecondaryFields(User user, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, DOB, NOT_EMPTY);
        try {
			Date date = format.parse(user.getDob());
			if(date.getTime() > new Date().getTime()){
				errors.rejectValue(DOB, "Invalid.userForm.dob");
			}
		} catch (ParseException e) {
			errors.rejectValue(DOB, "Invalid.userForm.dob");
		}
        //Add Validation for DOB

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, ADDRESS, NOT_EMPTY);
        if (user.getAddress().length() < 6 || user.getAddress().length() > 40) {
            errors.rejectValue(ADDRESS, "Size.userForm.address");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, ZIPCODE, NOT_EMPTY);
        if (user.getZipCode().length() < 6 || user.getZipCode().length() > 10) {
           errors.rejectValue(ZIPCODE, "Size.userForm.zipCode");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, CITY, NOT_EMPTY);
        if (user.getCity().length() < 2 || user.getCity().length() > 32) {
            errors.rejectValue(CITY, "Size.userForm.city");
        }

       ValidationUtils.rejectIfEmptyOrWhitespace(errors, COUNTRY, NOT_EMPTY);
	}

	private void validatePrimaryFields(User user, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, USERNAME, NOT_EMPTY);
        if (user.getUsername().length() < 4 || user.getUsername().length() > 32) {
            errors.rejectValue(USERNAME, "Size.userForm.username");
        }
        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue(USERNAME, "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, P_FILED, NOT_EMPTY);
        if (user.getPassword().length() < 4 || user.getPassword().length() > 32) {
            errors.rejectValue(P_FILED, "Size.userForm.password");
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue(P_CONF, "Diff.userForm.passwordConfirm");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, EMAIL, NOT_EMPTY);
        if (user.getEmail().length() < 6 || user.getEmail().length() > 32) {
            errors.rejectValue(EMAIL, "Size.userForm.email");
        }
        if(!EmailValidator.getInstance().isValid(user.getEmail())){
            errors.rejectValue(EMAIL, "Invalid.userForm.email");
        }
	}
}
