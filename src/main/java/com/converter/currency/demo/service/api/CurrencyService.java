package com.converter.currency.demo.service.api;

import java.util.List;

import com.converter.currency.demo.exception.CurrencyException;
import com.converter.currency.demo.model.CurrencyRecord;

public interface CurrencyService {
	CurrencyRecord save(CurrencyRecord user);

    List<CurrencyRecord> findTop10ByUsername();
    
    CurrencyRecord findLatest();
    
    Double getCurrencyRateFor(String source, String currency, String date) throws CurrencyException;
}
