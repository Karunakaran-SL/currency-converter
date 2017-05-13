package com.converter.currency.demo.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.converter.currency.demo.model.CurrencyRecord;
import com.converter.currency.demo.model.User;

@Component
public class CurrencyValidator implements Validator {
	List<String> currencyList = new ArrayList<>(Arrays.asList("INR", "EUR", "GBP", "NZD", "AUD", "JPY", "HUF"));
	SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
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

	public String validateApi(String currency, String dateString) {
		if(currency==null || dateString==null){
			return "Both currency and date is mandatory";
		}
		if(!currencyList.contains(currency)){
			return String.format("Currency %s is not supported, Supported currencies are INR EUR GBP NZD AUD JPY HUF", currency);
		}
		try {
			if(new Date().getTime() < format.parse(dateString).getTime()){
				return "Future date are not supported";
			}
		} catch (ParseException e) {
			return "Invalid date format, Supported format is yyyy-mm-dd";
		}
		return "success";
	}
}
