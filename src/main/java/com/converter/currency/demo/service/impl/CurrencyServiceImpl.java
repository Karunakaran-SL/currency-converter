package com.converter.currency.demo.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.converter.currency.demo.cache.CacheConfig;
import com.converter.currency.demo.exception.CurrencyException;
import com.converter.currency.demo.model.CurrencyRecord;
import com.converter.currency.demo.repository.CurrencyRepository;
import com.converter.currency.demo.service.api.CurrencyService;
import com.converter.currency.demo.service.api.SecurityService;
import com.converter.currency.demo.service.api.StatsService;
import com.converter.currency.demo.service.type.Stats;

@Service
public class CurrencyServiceImpl implements CurrencyService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyServiceImpl.class);
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    @Autowired
    private CurrencyRepository currencyRepository;
    
    @Autowired
    private SecurityService securityService;
    
    @Autowired
    private StatsService statsService;

    @Override
    public CurrencyRecord save(CurrencyRecord currency) {
        return currencyRepository.save(currency);
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
    @Cacheable(CacheConfig.EXTERNAL_REQUEST)
    public Double getCurrencyRateFor(String source, String currency, String date) throws CurrencyException{
    	statsService.incrementCount(Stats.TOTAL_CURRENCY_CALL.getValue());
    	Double result = Double.valueOf(0.0);
    	RestTemplate restTemplate = new RestTemplate();
    	String url = formUrl(currency,date);
    	String value = restTemplate.getForObject(url, String.class);
    	JSONObject jsonObject = new JSONObject(value);
    	if(jsonObject.getBoolean("success")){
    		JSONObject quotes = jsonObject.getJSONObject("quotes");
        	for(String key : quotes.keySet()){
        		result = quotes.getDouble(key);
        	}
    	}else{
    		LOGGER.error("Error while questing external API "+jsonObject);
    		statsService.incrementCount(Stats.TOTAL_CURRENCY_CALL_ERROR.getValue());
    		throw new CurrencyException("Invalid response from external API");
    	}
    	return result;
    }
    
    private String formUrl(String currency, String date) {
    	if(format.format(new Date()).equalsIgnoreCase(date)){//Go for live query
    		return String.format("http://apilayer.net/api/live?access_key="
    				+ "86ff9d67eabd34d308bd8d42d7cbc61a&currencies=%s", currency);
    	}
    	return String.format("http://apilayer.net/api/historical?access_key"
				+ "=86ff9d67eabd34d308bd8d42d7cbc61a&date=%s&currencies=%s", 
				date,currency);
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
