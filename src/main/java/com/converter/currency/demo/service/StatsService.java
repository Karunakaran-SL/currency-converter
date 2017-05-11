package com.converter.currency.demo.service;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public interface StatsService {
	final static String TOTAL_CURRENCY_CALL = "total-currency-call";
	final static String TOTAL_CURRENCY_CALL_ERROR = "total-currency-call-error";
	void incrementCount(String key);
	long getStats(String key);
	Map<String, AtomicInteger> getStats();
}
