package com.converter.currency.demo.service.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import com.converter.currency.demo.service.api.StatsService;

@Service
public class StatsServiceImpl implements StatsService {

	private Map<String, AtomicInteger> stats = new ConcurrentHashMap<>();
	@Override
	public void incrementCount(String key) {
		if(stats.containsKey(key)){
			stats.get(key).incrementAndGet();
		}else{
			stats.put(key, new AtomicInteger(1));
		}	
	}
	
	@Override
	public Map<String, AtomicInteger> getStats(){
		return stats;
	}

	@Override
	public long getStats(String key) {

		if(stats.containsKey(key)){
			return stats.get(key).longValue();
		}
		return 0;
	}

}
