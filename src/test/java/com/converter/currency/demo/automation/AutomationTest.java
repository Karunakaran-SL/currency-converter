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

	@Then("^Then Message displayed invalid Username$")
	public void message_displayed_Username_Length_min(int arg1) throws Throwable {
		String value = driver.findElement(By.xpath("//*[@id=\"username.errors\"]")).getText();
		assertTrue(value.contains("Please use between 4 and 32 characters."));
	}
	
	@Then("^Message displayed Invalid Email$")
	public void message_displayed_Invalid_Email() throws Throwable {
		String value = driver.findElement(By.xpath("//*[@id=\"email.errors\"]")).getText();
		assertTrue(value.contains("Invalid email address"));
	}
	
	@Then("^Message displayed invalid Username$")
	public void message_displayed_invalid_Username() throws Throwable {
		String value = driver.findElement(By.xpath("//*[@id=\"username.errors\"]")).getText();
		assertTrue(value.contains("Please use between 4 and 32 characters."));
	}

	@Then("^Message displayed Username already exist$")
	public void message_displayed_Username_already_exist() throws Throwable {
		String value = driver.findElement(By.xpath("//*[@id=\"username.errors\"]")).getText();
		assertTrue(value.contains("Someone already has that username"));
	}

	@Then("^Message displayed invalid password$")
	public void message_displayed_invalid_password() throws Throwable {
		String value = driver.findElement(By.xpath("//*[@id=\"password.errors\"]")).getText();
		assertTrue(value.contains("Try one with at least 4 characters."));
	}

	@Then("^Message displayed Invalid Dob$")
	public void message_displayed_Invalid_Dob() throws Throwable {
		driver.findElement(By.xpath("//*[@id=\"datefield\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"datefield\"]")).click();
	}

	@Then("^Message displayed Invalid Address city zipcode$")
	public void message_displayed_Invalid_Address_city_zipcode() throws Throwable {
		String address = driver.findElement(By.xpath("//*[@id=\"address.errors\"]")).getText();
		String city = driver.findElement(By.xpath("//*[@id=\"zipCode.errors\"]")).getText();
		String zipcode = driver.findElement(By.xpath("//*[@id=\"city.errors\"]")).getText();
		assertTrue(address.contains("Please use between 6 and 200 characters."));
		assertTrue(city.contains("Please use between 6 and 10 characters."));
		assertTrue(zipcode.contains("Please use between 2 and 32 characters."));
	}

	@Given("^Click Submit Button$")
	public void click_Submit_Button() throws Throwable {
		driver.findElement(By.xpath("//*[@id=\"currencyForm\"]/button")).click();	    
	}

	@Then("^Result and History shows value for currency as \"([^\"]*)\" and today date$")
	public void result_and_History_shows_value_for_currency_as_and_today_date(String arg1) throws Throwable {
		String value = driver.findElement(By.xpath("/html/body/div/div[2]/table/tbody/tr[2]/td[2]")).getText();
		assertTrue(arg1.contains(value));
	}

	@Given("^User Change the Currency to AUD$")
	public void user_Change_the_Currency_to_AUD() throws Throwable {
		driver.findElement(By.xpath("//*[@id=\"selectCurrency\"]/option[5]")).click();
	}
	@Given("^User Change the Date to \"([^\"]*)\"$")
	public void user_Change_the_Date_to(String arg1) throws Throwable {
		driver.findElement(By.xpath("//*[@id=\"datefield\"]")).sendKeys(arg1);
	}

	@Then("^Result and History shows value for currency as \"([^\"]*)\" and date as \"([^\"]*)\"$")
	public void result_and_History_shows_value_for_currency_as_and_date_as(String arg1, String arg2) throws Throwable {
		String value = driver.findElement(By.xpath("/html/body/div/div[2]/table/tbody/tr[2]/td[2]")).getText();
		String date = driver.findElement(By.xpath("/html/body/div/div[2]/table/tbody/tr[1]/td[2]")).getText();
		assertTrue(arg1.contains(value));
		assertTrue(arg2.contains(date));
	}

	@Given("^Click Logout$")
	public void click_Logout() throws Throwable {
		driver.findElement(By.xpath("/html/body/div/h2/a")).click();
	}

	@Then("^Go back to login screen$")
	public void go_back_to_login_screen() throws Throwable {
		String value = driver.findElement(By.xpath("/html/body/div/form/h2")).getText();
		assertTrue("Log in".contains(value));
	}
}
