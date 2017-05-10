package com.converter.currency.demo;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(strict=false, features="src/test/resources/features", format={"html:target/site/cucumber-pretty", "json:target/cucumber.json"},
		tags = {"~@ignore"}, glue="currency")
public class IntegrationTest {

}
