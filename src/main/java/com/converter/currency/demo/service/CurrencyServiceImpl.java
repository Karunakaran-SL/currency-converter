package com.converter.currency.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.converter.currency.demo.exception.CurrencyException;
import com.converter.currency.demo.model.Currency;
import com.converter.currency.demo.repository.CurrencyRepository;

@Service
public class CurrencyServiceImpl implements CurrencyService {
    @Autowired
    private CurrencyRepository currencyRepository;
    
    @Autowired
    private SecurityService securityService;

    @Override
    public void save(Currency currency) {
        currencyRepository.save(currency);
    }

    @Override
    public List<Currency> findTop10ByUsername() {
        try {
			return currencyRepository.findAllByUsernameTop2ByOrderByIdDesc(securityService.findLoggedInUsername());
		} catch (Exception e) {
			//Incase of no element found
			e.printStackTrace();
		}
        return new ArrayList<>();
    }
    
    @Override
    public Double getCurrencyRateFor(String source, String currency, String date) throws CurrencyException{
    	RestTemplate restTemplate = new RestTemplate();
    	String value = restTemplate.getForObject(String.format("http://apilayer.net/api/historical?access_key=86ff9d67eabd34d308bd8d42d7cbc61a&date=%s&currencies=%s", date,currency), String.class);
    	JSONObject jsonObject = new JSONObject(value);
    	if(jsonObject.getBoolean("success")){
    		JSONObject quotes = jsonObject.getJSONObject("quotes");
        	for(String key : quotes.keySet()){
        		return quotes.getDouble(key);
        	}
    	}else{
    		throw new CurrencyException("Invalid response from external API");
    	}
    	return new Double(0.0);
    }
    
    @Override
    public Currency findLatest(){
    	try {
			return currencyRepository.findLatest(securityService.findLoggedInUsername());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }
}
