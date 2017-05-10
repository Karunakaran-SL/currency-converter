package com.converter.currency.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.converter.currency.demo.exception.CurrencyException;
import com.converter.currency.demo.model.CurrencyRecord;
import com.converter.currency.demo.repository.CurrencyRepository;

@Service
public class CurrencyServiceImpl implements CurrencyService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyServiceImpl.class);
    @Autowired
    private CurrencyRepository currencyRepository;
    
    @Autowired
    private SecurityService securityService;

    @Override
    public void save(CurrencyRecord currency) {
        currencyRepository.save(currency);
    }

    @Override
    public List<CurrencyRecord> findTop10ByUsername() {
        try {
			return currencyRepository.findAllByUsernameTop2ByOrderByIdDesc(securityService.findLoggedInUsername());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
        return new ArrayList<>();
    }
    
    @Override
    public Double getCurrencyRateFor(String source, String currency, String date) throws CurrencyException{
    	Double result = Double.valueOf(0.0);
    	RestTemplate restTemplate = new RestTemplate();
    	String value = restTemplate.getForObject(String.
    			format("http://apilayer.net/api/historical?access_key"
    					+ "=86ff9d67eabd34d308bd8d42d7cbc61a&date=%s&currencies=%s", 
    					date,currency), String.class);
    	JSONObject jsonObject = new JSONObject(value);
    	if(jsonObject.getBoolean("success")){
    		JSONObject quotes = jsonObject.getJSONObject("quotes");
        	for(String key : quotes.keySet()){
        		result = quotes.getDouble(key);
        	}
    	}else{
    		throw new CurrencyException("Invalid response from external API");
    	}
    	return result;
    }
    
    @Override
    public CurrencyRecord findLatest(){
    	try {
			return currencyRepository.findLatest(securityService.findLoggedInUsername());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
    	return null;
    }
}
