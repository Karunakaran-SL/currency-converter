package com.converter.currency.demo.unit;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.View;

import com.converter.currency.demo.exception.CurrencyException;
import com.converter.currency.demo.model.CurrencyRecord;
import com.converter.currency.demo.service.CurrencyService;
import com.converter.currency.demo.validator.CurrencyValidator;
import com.converter.currency.demo.web.CurrencyController;
@RunWith(SpringRunner.class)
@SpringBootTest
public class CurrencyControllerTest {

	@Autowired
	CurrencyController controller;
	@MockBean
	private CurrencyService currencyService;
	@Mock
	private View view;

	@Mock
	BindingResult bindingResult;

	@MockBean
	private CurrencyValidator currencyValidator;

	@Test
	public void testRegistration(){
		CurrencyRecord currencyRecord = new CurrencyRecord();
		currencyRecord.setCurrency("INR");
		currencyRecord.setDate("2017-05-05");
		currencyRecord.setId(10L);
		currencyRecord.setUsername("testing");
		currencyRecord.setSource("USD");
		currencyRecord.setCreateTime(new Date());
		currencyRecord.setValue(Double.valueOf(0.0));
		Mockito.when(currencyService.findTop10ByUsername()).thenReturn(Arrays.asList(new CurrencyRecord()));
		Mockito.when(currencyService.findLatest()).thenReturn(currencyRecord);
		Model model = new ExtendedModelMap();
		String result = controller.registration(model);
		assertEquals("welcome", result);
		CurrencyRecord currencyRecord2 = (CurrencyRecord)model.asMap().get("searchedCurrency");
		assertEquals(currencyRecord.getCreateTime(), currencyRecord2.getCreateTime());
		assertEquals(currencyRecord.getCurrency(), currencyRecord2.getCurrency());
		assertEquals(currencyRecord.getDate(), currencyRecord2.getDate());
		assertEquals(currencyRecord.getId(), currencyRecord2.getId());
		assertEquals(currencyRecord.getSource(), currencyRecord2.getSource());
		assertEquals(currencyRecord.getUsername(), currencyRecord2.getUsername());
		assertEquals(currencyRecord.getValue(), currencyRecord2.getValue());
		
		Mockito.when(currencyService.findLatest()).thenReturn(null);
		model = new ExtendedModelMap();
		result = controller.registration(model);
		assertEquals("welcome", result);
	}

	@Test
	public void testRegistration2(){
		try {
			CurrencyRecord currencyRecord = new CurrencyRecord();
			currencyRecord.setCurrency("INR");
			currencyRecord.setDate("2017-05-05");
			currencyRecord.setId(10L);
			currencyRecord.setUsername("testing");
			currencyRecord.setSource("USD");
			currencyRecord.setCreateTime(new Date());
			Mockito.when(currencyService.save(currencyRecord)).thenReturn(currencyRecord);

			Mockito.when(currencyService.getCurrencyRateFor("USD",currencyRecord.getCurrency(),currencyRecord.getDate())).thenReturn(Double.valueOf(0.0));
			Mockito.when(bindingResult.hasErrors()).thenReturn(false);
			Model model = new ExtendedModelMap();

			String result = controller.registration(currencyRecord,bindingResult,model);
			assertEquals("redirect:/welcome", result);
			
			Mockito.when(currencyService.getCurrencyRateFor("USD",currencyRecord.getCurrency(),currencyRecord.getDate())).thenThrow(new CurrencyException("Testing"));
			result = controller.registration(currencyRecord,bindingResult,model);
			assertEquals("redirect:/welcome", result);
			
			Mockito.when(bindingResult.hasErrors()).thenReturn(true);
			result = controller.registration(currencyRecord,bindingResult,model);
			assertEquals("welcome", result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
