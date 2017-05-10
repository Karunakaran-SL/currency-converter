package com.converter.currency.demo.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.converter.currency.demo.model.CurrencyRecord;
import com.converter.currency.demo.model.User;
import com.converter.currency.demo.service.CurrencyService;

@Component
public class CurrencyValidator implements Validator {
	SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
    @Autowired
    private CurrencyService currencyService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

	@Override
    public void validate(Object o, Errors errors) {
        CurrencyRecord currency = (CurrencyRecord) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "date", "NotEmpty");
        try {
			if(format.parse(currency.getDate()).getTime() > new Date().getTime()){
				errors.rejectValue("date", "FutureDate.currencyForm.date");
			}
		} catch (ParseException e) {
			errors.rejectValue("date", "Invalid.currencyForm.date");
		}
    }
}
