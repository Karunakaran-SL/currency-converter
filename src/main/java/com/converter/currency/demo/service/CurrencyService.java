package com.converter.currency.demo.service;

import java.util.List;

import com.converter.currency.demo.exception.CurrencyException;
import com.converter.currency.demo.model.Currency;

public interface CurrencyService {
    void save(Currency user);

    List<Currency> findTop10ByUsername();
    
    Currency findLatest();
    
    Double getCurrencyRateFor(String source, String currency, String date) throws CurrencyException;
}
