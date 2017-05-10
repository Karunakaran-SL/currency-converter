package com.converter.currency.demo.service;

import java.util.List;

import com.converter.currency.demo.exception.CurrencyException;
import com.converter.currency.demo.model.CurrencyRecord;

public interface CurrencyService {
    void save(CurrencyRecord user);

    List<CurrencyRecord> findTop10ByUsername();
    
    CurrencyRecord findLatest();
    
    Double getCurrencyRateFor(String source, String currency, String date) throws CurrencyException;
}
