package com.converter.currency.demo.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.converter.currency.demo.model.CurrencyRecord;
import com.converter.currency.demo.repository.CurrencyRepository;
import com.converter.currency.demo.service.api.CurrencyService;
import com.converter.currency.demo.service.api.SecurityService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurrencyServiceTest {
	@Autowired
	private CurrencyService currencyService;
	
	@MockBean
	private CurrencyRepository currencyRepository;
	
	@MockBean
	private SecurityService securityService;
	
	@Test
	public void testSave(){
		CurrencyRecord currencyRecord = new CurrencyRecord();
		currencyRecord.setCurrency("INR");
		currencyRecord.setDate("2017-05-05");
		currencyRecord.setId(10L);
		currencyRecord.setUsername("testing");
		currencyRecord.setSource("USD");
		currencyRecord.setCreateTime(new Date());
		currencyRecord.setValue(Double.valueOf(0.0));
		Mockito.when(currencyRepository.save(currencyRecord)).thenReturn(currencyRecord);
		CurrencyRecord currencyRecord2 = currencyService.save(currencyRecord);
		assertNotNull(currencyRecord2.getCreateTime());
		assertEquals(currencyRecord.getCurrency(), currencyRecord2.getCurrency());
		assertEquals(currencyRecord.getDate(), currencyRecord2.getDate());
		assertNotNull(currencyRecord2.getId());
		assertEquals(currencyRecord.getSource(), currencyRecord2.getSource());
		assertEquals(currencyRecord.getUsername(), currencyRecord2.getUsername());
		assertEquals(currencyRecord.getValue(), currencyRecord2.getValue());
	}
	
	@Test
	public void testFindTop10ByUsername(){
		Mockito.when(securityService.findLoggedInUsername()).thenReturn("test");
		Mockito.when(currencyRepository.findAllByUsernameTop2ByOrderByIdDesc("test")).thenReturn(Arrays.asList(new CurrencyRecord()));
		List<CurrencyRecord> currencyRecords = currencyService.findTop10ByUsername();
		assertEquals(0, currencyRecords.size());
		
		Mockito.when(securityService.findLoggedInUsername()).thenThrow(new NoSuchElementException());
		Mockito.when(currencyRepository.findAllByUsernameTop2ByOrderByIdDesc("test")).thenThrow(new NoSuchElementException());
		currencyRecords = currencyService.findTop10ByUsername();
		assertEquals(0, currencyRecords.size());
	}
	
	@Test
	public void testFindLatest(){
		Mockito.when(securityService.findLoggedInUsername()).thenReturn("test");
		Mockito.when(currencyRepository.findLatest("test")).thenReturn(new CurrencyRecord());
		CurrencyRecord currencyRecord = currencyService.findLatest();
		
		Mockito.when(securityService.findLoggedInUsername()).thenThrow(new NoSuchElementException());
		Mockito.when(currencyRepository.findLatest("test")).thenThrow(new NoSuchElementException());
		currencyRecord = currencyService.findLatest();
		assertEquals(null, currencyRecord);
	}
	
	@Test
	public void testGetCurrencyRateFor(){
		try {
			Double value = currencyService.getCurrencyRateFor("USD", "INR", "2017-05-05");
			assertEquals(Double.valueOf(64.300003), value);
			
			try {
				value = currencyService.getCurrencyRateFor("USD", "INR", "2097-05-05");
			} catch (Exception e) {
				assertTrue(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
