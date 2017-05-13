package com.converter.currency.demo.unit;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BindingResult;

import com.converter.currency.demo.model.CurrencyRecord;
import com.converter.currency.demo.model.User;
import com.converter.currency.demo.validator.CurrencyValidator;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurrencyValidatorTest {
	
	@Mock
	BindingResult bindingResult;
	
	@Autowired
	private CurrencyValidator currencyValidator;

	@Test
	public void testsupports(){
		assertEquals(true, currencyValidator.supports(User.class));
	}
	
	@Test
	public void testValidate(){
		CurrencyRecord currencyRecord = new CurrencyRecord();
		currencyRecord.setCurrency("INR");
		currencyRecord.setDate("2017-05-05");
		currencyRecord.setId(10L);
		currencyRecord.setUsername("testing");
		currencyRecord.setSource("USD");
		currencyRecord.setCreateTime(new Date());
		currencyRecord.setValue(Double.valueOf(0.0));
		currencyValidator.validate(currencyRecord, bindingResult);
		
		currencyRecord.setDate("2027-05-05");
		currencyValidator.validate(currencyRecord, bindingResult);
		
		currencyRecord.setDate("202sdsafaf7-05-05");
		currencyValidator.validate(currencyRecord, bindingResult);
	}
	
	@Test
	public void testValidateApi(){
		String actual = currencyValidator.validateApi(null, null);
		assertEquals("Both currency and date is mandatory",actual);
		
		actual = currencyValidator.validateApi("", null);
		assertEquals("Both currency and date is mandatory",actual);
		
		actual = currencyValidator.validateApi(null, "");
		assertEquals("Both currency and date is mandatory",actual);
		
		actual = currencyValidator.validateApi("", "");
		assertEquals("Currency  is not supported, Supported currencies are INR EUR GBP NZD AUD JPY HUF",actual);
		
		actual = currencyValidator.validateApi("sdasfd", "dsasf");
		assertEquals("Currency sdasfd is not supported, Supported currencies are INR EUR GBP NZD AUD JPY HUF",actual);
		
		actual = currencyValidator.validateApi("INR", null);
		assertEquals("Both currency and date is mandatory",actual);
		
		actual = currencyValidator.validateApi("INR", "");
		assertEquals("Invalid date format, Supported format is yyyy-mm-dd",actual);
		
		actual = currencyValidator.validateApi("INR", "dafaf");
		assertEquals("Invalid date format, Supported format is yyyy-mm-dd",actual);
		
		actual = currencyValidator.validateApi(null, "2017-03-03");
		assertEquals("Both currency and date is mandatory",actual);
		
		actual = currencyValidator.validateApi("", "2017-03-03");
		assertEquals("Currency  is not supported, Supported currencies are INR EUR GBP NZD AUD JPY HUF",actual);
		
		actual = currencyValidator.validateApi(null, "2017-03-03");
		assertEquals("Both currency and date is mandatory",actual);
		
		actual = currencyValidator.validateApi("INR", "2017-03-03");
		assertEquals("success",actual);
		
		actual = currencyValidator.validateApi("INR", "2018-03-03");
		assertEquals("Future date are not supported",actual);
		
	}
	
}
