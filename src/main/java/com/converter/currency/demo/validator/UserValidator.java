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
import com.converter.currency.demo.service.UserService;

@Component
public class UserValidator implements Validator {
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

	@Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (user.getUsername().length() < 4 || user.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }
        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 4 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
        if (user.getEmail().length() < 6 || user.getEmail().length() > 64) {
            errors.rejectValue("email", "Size.userForm.email");
        }
        if(!EmailValidator.getInstance().isValid(user.getEmail())){
            errors.rejectValue("email", "Invalid.userForm.email");
        }
        

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dob", "NotEmpty");
        try {
			Date date = format.parse(user.getDob());
			if(date.getTime() > new Date().getTime()){
				errors.rejectValue("dob", "Invalid.userForm.dob");
			}
		} catch (ParseException e) {
			errors.rejectValue("dob", "Invalid.userForm.dob");
		}
        //Add Validation for DOB

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "NotEmpty");
        if (user.getAddress().length() < 6 || user.getAddress().length() > 200) {
            errors.rejectValue("address", "Size.userForm.address");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "zipCode", "NotEmpty");
        if (user.getZipCode().length() < 6 || user.getZipCode().length() > 10) {
           errors.rejectValue("zipCode", "Size.userForm.zipCode");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "NotEmpty");
        if (user.getCity().length() < 2 || user.getCity().length() > 32) {
            errors.rejectValue("city", "Size.userForm.city");
        }

       ValidationUtils.rejectIfEmptyOrWhitespace(errors, "country", "NotEmpty");
    }
}
