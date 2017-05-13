@requires_browser
Feature: Login Action Test
Description: This feature will test a Login and Logout functionality

  Scenario: Unsuccessful Login with Invalid Credentials
    Given User is on LoginPage
    And User enters Username as "test" and Password as "test"
    And User submit the credentials by pressing SignIn
    Then Message displayed Invalid Credentials 
    
  Scenario: Unsuccessful Login with Valid username and Invalid password
    Given User is on LoginPage
    And User enters Username as "test123" and Password as "test"
    And User submit the credentials by pressing SignIn
    Then Message displayed Invalid Credentials 
    
  Scenario: Unsuccessful Login with Invalid username and valid password
    Given User is on LoginPage
    And User enters Username as "test" and Password as "test123"
    And User submit the credentials by pressing SignIn
    Then Message displayed Invalid Credentials 
    
  Scenario: Successful Login with default username and password
  	Given User is on LoginPage
    And User enters Username as "test123" and Password as "test123"
    And User submit the credentials by pressing SignIn
    Then Message displayed Login Success
    
  Scenario: Register New User Validation with empty details
    Given User is on LoginPage
  	And Click on Create Account Link
  	And User enter Username as "" and Password as "" and Conform as "" and Email as "" and Dob as "" and Address as "" and zipcode as "" and city as "" and Country as "US"
  	And User submit the details by pressing submit
  	Then Message displayed Fields are empty
  	
  Scenario: Register New User Validation Username length less
  	Given User is on LoginPage
  	And Click on Create Account Link
  	And User enter Username as "my" and Password as "mytest" and Conform as "mytest" and Email as "mytest@gmail.com" and Dob as "2000-01-01" and Address as "address123" and zipcode as "1234567" and city as "Chennai" and Country as "US"
  	And User submit the details by pressing submit
  	Then Message displayed invalid Username
  	
 Scenario: Register New User Validation Username length more than max
  	Given User is on LoginPage
  	And Click on Create Account Link
  	And User enter Username as "my1111111111111111111111111111111111safsaaf" and Password as "mytest" and Conform as "mytest" and Email as "mytest@gmail.com" and Dob as "2000-01-01" and Address as "address123" and zipcode as "1234567" and city as "Chennai" and Country as "US"
  	And User submit the details by pressing submit
  	Then Message displayed invalid Username
  	
  Scenario: Register New User Validation Username already exist
  	Given User is on LoginPage
  	And Click on Create Account Link
  	And User enter Username as "test123" and Password as "mytest" and Conform as "mytest" and Email as "mytest@gmail.com" and Dob as "2000-01-01" and Address as "address123" and zipcode as "1234567" and city as "Chennai" and Country as "US"
  	And User submit the details by pressing submit
  	Then Message displayed Username already exist
  	
  Scenario: Register New User Validation password length less than min
  	Given User is on LoginPage
  	And Click on Create Account Link
  	And User enter Username as "mytest1" and Password as "my" and Conform as "mytest" and Email as "mytest@gmail.com" and Dob as "2000-01-01" and Address as "address123" and zipcode as "1234567" and city as "Chennai" and Country as "US"
  	And User submit the details by pressing submit
  	Then Message displayed invalid password
  	
 Scenario: Register New User Validation password length more than max
  	Given User is on LoginPage
  	And Click on Create Account Link
  	And User enter Username as "mytest1" and Password as "my1111111111111111111111111111111111safsaaf" and Conform as "mytest" and Email as "mytest@gmail.com" and Dob as "2000-01-01" and Address as "address123" and zipcode as "1234567" and city as "Chennai" and Country as "US"
  	And User submit the details by pressing submit
  	Then Message displayed invalid password

  Scenario: Register New User Validation Password Mismatch
  	Given User is on LoginPage
  	And Click on Create Account Link
  	And User enter Username as "mytest1" and Password as "mytest" and Conform as "mytestssss" and Email as "mytest@gmail.com" and Dob as "2000-01-01" and Address as "address123" and zipcode as "1234567" and city as "Chennai" and Country as "US"
  	And User submit the details by pressing submit
  	Then Message displayed Password Mismatch
  	
  Scenario: Register New User Validation Invalid Email 1
  	Given User is on LoginPage
  	And Click on Create Account Link
  	And User enter Username as "mytest344" and Password as "mytest" and Conform as "mytest" and Email as "mytest@gssfsf" and Dob as "2000-01-01" and Address as "address123" and zipcode as "1234567" and city as "Chennai" and Country as "US"
  	And User submit the details by pressing submit
  	Then Message displayed Invalid Email
  	
   Scenario: Register New User Validation Invalid Email 2
  	Given User is on LoginPage
  	And Click on Create Account Link
  	And User enter Username as "mytest344" and Password as "mytest" and Conform as "mytest" and Email as "mytest@gmdsafs.adsaaf" and Dob as "2000-01-01" and Address as "address123" and zipcode as "1234567" and city as "Chennai" and Country as "US"
  	And User submit the details by pressing submit
  	Then Message displayed Invalid Email
  	
  Scenario: Register New User Validation Future Dob
  	Given User is on LoginPage
  	And Click on Create Account Link
  	And User enter Username as "mytest344" and Password as "mytest" and Conform as "mytest" and Email as "mytest@gmail.com" and Dob as "2089-01-01" and Address as "address123" and zipcode as "1234567" and city as "Chennai" and Country as "US"
  	And User submit the details by pressing submit
  	Then Message displayed Invalid Dob
  	
 Scenario: Register New User Validation Address City Zip less length
  	Given User is on LoginPage
  	And Click on Create Account Link
  	And User enter Username as "mytest34" and Password as "mytest" and Conform as "mytest" and Email as "mytest@gmail.com" and Dob as "2000-01-01" and Address as "ad" and zipcode as "12" and city as "C" and Country as "US"
  	And User submit the details by pressing submit
  	Then Message displayed Invalid Address city zipcode
  	
 Scenario: Register New User Validation Address City Zip more length
  	Given User is on LoginPage
  	And Click on Create Account Link
  	And User enter Username as "mytest34" and Password as "mytest" and Conform as "mytest" and Email as "mytest@gmail.com" and Dob as "2000-01-01" and Address as "fsafdsfgdsgdfgddffrfffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff" and zipcode as "fsafdsfgdsgdfgddff" and city as "fsafdsfgdsgdfgddffsdsafsdfdsgdfgdfhfghgfhtjhggffffffffffffffdddddddd" and Country as "US"
  	And User submit the details by pressing submit
  	Then Message displayed Invalid Address city zipcode
  	
  Scenario: Register New User
  	Given User is on LoginPage
  	And Click on Create Account Link
  	And User enter Username as "mytest" and Password as "mytest" and Conform as "mytest" and Email as "mytest@gmail.com" and Dob as "2000-01-01" and Address as "address123" and zipcode as "1234567" and city as "Chennai" and Country as "US"
  	And User submit the details by pressing submit
  	Then Message displayed Login Success
  	
  Scenario: Currency Conversion with default
    Given User is on LoginPage
    And User enters Username as "test123" and Password as "test123"
    And User submit the credentials by pressing SignIn
    And Click Submit Button
    Then Result and History shows value for currency as "EUR" and today date
    
   Scenario: Currency Conversion by change currency
    Given User is on LoginPage
    And User enters Username as "test123" and Password as "test123"
    And User submit the credentials by pressing SignIn
    And User Change the Currency to AUD
    And Click Submit Button
    Then Result and History shows value for currency as "AUD" and today date
  
   Scenario: Currency Conversion by changing date
    Given User is on LoginPage
    And User enters Username as "test123" and Password as "test123"
    And User submit the credentials by pressing SignIn
    And User Change the Date to "03-03-2017"
    And Click Submit Button
    Then Result and History shows value for currency as "EUR" and date as "2017-03-03"
  	
  Scenario: Logout and Login
    Given User is on LoginPage
    And User enters Username as "test123" and Password as "test123"
    And User submit the credentials by pressing SignIn
    And Message displayed Login Success
    And Click Logout
    Then Go back to login screen
  