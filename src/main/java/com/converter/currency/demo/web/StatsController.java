package com.converter.currency.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.converter.currency.demo.service.StatsService;

@Controller
public class StatsController {
	@Autowired
	private StatsService statsService;
	
	@RequestMapping(value = "/stats", method = RequestMethod.GET)
	public String getStats(Model model){
		model.addAttribute("total",statsService.getStats(StatsService.TOTAL_CURRENCY_CALL));
		model.addAttribute("error",statsService.getStats(StatsService.TOTAL_CURRENCY_CALL_ERROR));
		return "stats";
	}
}
