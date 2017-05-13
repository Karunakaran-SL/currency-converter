package com.converter.currency.demo.service.api;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public interface StatsService {
	void incrementCount(String key);
	long getStats(String key);
	Map<String, AtomicInteger> getStats();
}
