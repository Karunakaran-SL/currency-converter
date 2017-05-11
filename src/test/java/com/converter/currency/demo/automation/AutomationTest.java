package com.converter.currency.demo.automation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.converter.currency.demo.WebApplication;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
@SuppressWarnings("deprecation")
@ContextConfiguration(classes = WebApplication.class, loader = SpringApplicationContextLoader.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@org.springframework.boot.test.IntegrationTest
public class AutomationTest {
	private static WebDriver driver;
	@Before({ "@requires_browser" })
	public void buildDriver() {
		driver = BDDUtilities.getDriver();
	}
	
	@After({ "@requires_browser" })
	public void destroyDriver() {
		driver.quit();
	}
	
	@Given("^User is on LoginPage$")
	public void user_is_on_LoginPage() throws Throwable {
	    driver.get("http://localhost:8080");
	}

	@Given("^User enters Username as \"([^\"]*)\" and Password as \"([^\"]*)\"$")
	public void user_enters_Username_as_and_Password_as(String arg1, String arg2) throws Throwable {
		driver.findElement(By.xpath("/html/body/div/form/div/input[1]")).sendKeys(arg1);
		driver.findElement(By.xpath("/html/body/div/form/div/input[2]")).sendKeys(arg2);
	}

	@Given("^User submit the credentials by pressing SignIn$")
	public void user_submit_the_credentials_by_pressing_SignIn() throws Throwable {
		driver.findElement(By.xpath("/html/body/div/form/div/button")).click();
	}

	@Then("^Message displayed Invalid Credentials$")
	public void message_displayed_Invalid_Credentials() throws Throwable {
		String value = driver.findElement(By.xpath("/html/body/div/form/div/span[2]")).getText();
	    assertEquals("Your username and password is invalid.", value);
	}
	
	@Then("^Message displayed Login Success$")
	public void message_displayed_succes() throws Throwable {
		String value = driver.findElement(By.xpath("/html/body/div/h2")).getText();
	    assertTrue(value.contains("Welcome"));
	}
	
	@Given("^Click on Create Account Link$")
	public void click_on_Create_Account_Link() throws Throwable {
		driver.findElement(By.xpath("/html/body/div/form/div/h4/a")).click();
	}

	@Given("^User enter Username as \"([^\"]*)\" and Password as \"([^\"]*)\" and Conform as \"([^\"]*)\" and Email as \"([^\"]*)\" and Dob as \"([^\"]*)\" and Address as \"([^\"]*)\" and zipcode as \"([^\"]*)\" and city as \"([^\"]*)\" and Country as \"([^\"]*)\"$")
	public void user_enter_Details(String userName, String password, String passwordConf, String email, String dob, String address, String zipcode, String city, String country) throws Throwable {
		driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys(userName);
		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(password);
		driver.findElement(By.xpath("//*[@id=\"passwordConfirm\"]")).sendKeys(passwordConf);
		driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys(email);
		driver.findElement(By.xpath("//*[@id=\"datefield\"]")).sendKeys(dob);
		driver.findElement(By.xpath("//*[@id=\"address\"]")).sendKeys(address);
		driver.findElement(By.xpath("//*[@id=\"zipCode\"]")).sendKeys(zipcode);
		driver.findElement(By.xpath("//*[@id=\"city\"]")).sendKeys(city);
	}

	@Given("^User submit the details by pressing submit$")
	public void user_submit_the_details_by_pressing_submit() throws Throwable {
		driver.findElement(By.xpath("//*[@id=\"userForm\"]/button")).click();
	}
	
	@Then("^Message displayed Fields are empty$")
	public void message_displayed_Fields_are_empty() throws Throwable {
		String value = driver.findElement(By.xpath("//*[@id=\"username.errors\"]")).getText();
		assertTrue(value.contains("This field is required."));
	}

	@Then("^Message displayed Password Mismatch$")
	public void message_displayed_Password_Mismatch() throws Throwable {
		String value = driver.findElement(By.xpath("//*[@id=\"passwordConfirm.errors\"]")).getText();
		assertTrue(value.contains("These passwords don't match."));
	}

	@Then("^Message displayed Username Length min (\\d+)$")
	public void message_displayed_Username_Length_min(int arg1) throws Throwable {
		String value = driver.findElement(By.xpath("//*[@id=\"username.errors\"]")).getText();
		assertTrue(value.contains("Please use between 4 and 32 characters."));
	}
	
	@Then("^Message displayed Invalid Email$")
	public void message_displayed_Invalid_Email() throws Throwable {
		String value = driver.findElement(By.xpath("//*[@id=\"email.errors\"]")).getText();
		assertTrue(value.contains("Invalid email address"));
	}
}
