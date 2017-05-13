package com.converter.currency.demo.web;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.converter.currency.demo.service.api.StatsService;
import com.converter.currency.demo.service.type.Stats;

@Controller
public class StatsController {
	@Autowired
	private StatsService statsService;
	
	@RequestMapping(value = "/stats", method = RequestMethod.GET)
	public String getStats(Model model){
		model.addAttribute("total",statsService.getStats(Stats.TOTAL_CURRENCY_CALL.getValue()));
		model.addAttribute("error",statsService.getStats(Stats.TOTAL_CURRENCY_CALL_ERROR.getValue()));
		return "stats";
	}
	
	@RequestMapping(value = "/api/stats", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, AtomicInteger> getStatsApi(){
		return statsService.getStats();
	}
	
	
}
