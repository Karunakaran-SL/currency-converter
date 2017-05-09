package com.converter.currency.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.converter.currency.demo.exception.CurrencyException;
import com.converter.currency.demo.model.Currency;
import com.converter.currency.demo.service.CurrencyService;
import com.converter.currency.demo.validator.CurrencyValidator;

@Controller
public class CurrencyController {
	@Autowired
	private CurrencyService currencyService;

	@Autowired
	private CurrencyValidator currencyValidator;

	@RequestMapping(value = {"/","/welcome"}, method = RequestMethod.GET)
	public String registration(Model model) {
		model.addAttribute("currencyForm", new Currency());
		model.addAttribute("tasks", currencyService.findTop10ByUsername());
		Currency currency = currencyService.findLatest();
		if(currency!=null){
			model.addAttribute("searchedCurrency",currency);
		}
		return "welcome";
	}

	@RequestMapping(value = {"/","/welcome"}, method = RequestMethod.POST)
	public String registration(@ModelAttribute("currencyForm") Currency currencyForm, BindingResult bindingResult, Model model) {
		try {
			currencyValidator.validate(currencyForm, bindingResult);

			if (bindingResult.hasErrors()) {
				return "welcome";
			}
			currencyForm.setValue(currencyService.getCurrencyRateFor("USD", currencyForm.getCurrency(), currencyForm.getDate()));
			currencyService.save(currencyForm);
		} catch (CurrencyException e) {
			//TODO Add logger
			model.addAttribute("error", "Error while retrive data from server "+e.getMessage());
		}
		return "redirect:/welcome";
	}
}
