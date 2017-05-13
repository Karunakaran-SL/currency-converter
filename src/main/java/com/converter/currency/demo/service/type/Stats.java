package com.converter.currency.demo.service.type;

public enum Stats {
	TOTAL_CURRENCY_CALL("total-currency-call"),TOTAL_CURRENCY_CALL_ERROR("total-currency-call-error");
	private String value;
	Stats(String value){
		this.value = value;
	}
	public String getValue() {
		return value;
	}
}
