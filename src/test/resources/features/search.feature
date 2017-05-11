@requires_browser
Feature: Login Action Test
Description: This feature will test a Login and Logout functionality

  Scenario: Unsuccessful Login with Invalid Credentials
    Given User is on LoginPage
    And User enters Username as "test" and Password as "test"
    And User submit the credentials by pressing SignIn
    Then Message displayed Invalid Credentials 
    
  Scenario: Successful Login with default username and password
  	Given User is on LoginPage
    And User enters Username as "test123" and Password as "test123"
    And User submit the credentials by pressing SignIn
    Then Message displayed Login Success
    
  Scenario: Register New User
  	Given User is on LoginPage
  	And Click on Create Account Link
  	And User enter Username as "mytest" and Password as "mytest" and Conform as "mytest" and Email as "mytest@gmail.com" and Dob as "2000-01-01" and Address as "address123" and zipcode as "1234567" and city as "Chennai" and Country as "US"
  	And User submit the details by pressing submit
  	Then Message displayed Login Success
  	
  Scenario: Register New User Validation 1
    Given User is on LoginPage
  	And Click on Create Account Link
  	And User enter Username as "" and Password as "" and Conform as "" and Email as "" and Dob as "" and Address as "" and zipcode as "" and city as "" and Country as "US"
  	And User submit the details by pressing submit
  	Then Message displayed Fields are empty
  	
  Scenario: Register New User Validation Password Mismatch
  	Given User is on LoginPage
  	And Click on Create Account Link
  	And User enter Username as "mytest122" and Password as "mytest" and Conform as "mytestssss" and Email as "mytest@gmail.com" and Dob as "2000-01-01" and Address as "address123" and zipcode as "1234567" and city as "Chennai" and Country as "US"
  	And User submit the details by pressing submit
  	Then Message displayed Password Mismatch
  	
  Scenario: Register New User Validation Username length less
  	Given User is on LoginPage
  	And Click on Create Account Link
  	And User enter Username as "my" and Password as "mytest" and Conform as "mytest" and Email as "mytest@gmail.com" and Dob as "2000-01-01" and Address as "address123" and zipcode as "1234567" and city as "Chennai" and Country as "US"
  	And User submit the details by pressing submit
  	Then Message displayed Username Length min 4
  	
  Scenario: Register New User Validation Invalid Email
  	Given User is on LoginPage
  	And Click on Create Account Link
  	And User enter Username as "mytest344" and Password as "mytest" and Conform as "mytest" and Email as "mytest@gmail" and Dob as "2000-01-01" and Address as "address123" and zipcode as "1234567" and city as "Chennai" and Country as "US"
  	And User submit the details by pressing submit
  	Then Message displayed Invalid Email
  	