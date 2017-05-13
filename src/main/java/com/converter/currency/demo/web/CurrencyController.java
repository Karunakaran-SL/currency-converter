package com.converter.currency.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.converter.currency.demo.exception.CurrencyException;
import com.converter.currency.demo.model.CurrencyRecord;
import com.converter.currency.demo.service.api.CurrencyService;
import com.converter.currency.demo.validator.CurrencyValidator;

@Controller
public class CurrencyController {
	@Autowired
	private CurrencyService currencyService;

	@Autowired
	private CurrencyValidator currencyValidator;

	@RequestMapping(value = {"/","/welcome"}, method = RequestMethod.GET)
	public String currency(Model model) {
		model.addAttribute("currencyForm", new CurrencyRecord());
		model.addAttribute("tasks", currencyService.findTop10ByUsername());
		CurrencyRecord currency = currencyService.findLatest();
		if(currency!=null){
			model.addAttribute("searchedCurrency",currency);
		}
		return "welcome";
	}

	@RequestMapping(value = {"/","/welcome"}, method = RequestMethod.POST)
	public String currency(@ModelAttribute("currencyForm") CurrencyRecord currencyForm, BindingResult bindingResult, Model model) {
		try {
			currencyValidator.validate(currencyForm, bindingResult);

			if (bindingResult.hasErrors()) {
				return "welcome";
			}
			currencyForm.setValue(currencyService.getCurrencyRateFor("USD", currencyForm.getCurrency(), currencyForm.getDate()));
			currencyService.save(currencyForm);
		} catch (CurrencyException e) {
			model.addAttribute("error", "Error while retrive data from server "+e.getMessage());
		}
		return "redirect:/welcome";
	}
	
	@RequestMapping(value = {"/api/currency"}, method = RequestMethod.GET)
	@ResponseBody
	public String currencyApi(@RequestParam("currency") String currency,@RequestParam("date")String dateString) {
		String result = currencyValidator.validateApi(currency,dateString);
		try {
			if ("success".equalsIgnoreCase(result)) {
				return String.valueOf(currencyService.getCurrencyRateFor("USD", currency, dateString));
			}
			return result;
		} catch (CurrencyException e) {
			result = e.getMessage();
		}
		return result;
	}
	
	
}
