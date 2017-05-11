@requires_browser
Feature: Login Action Test
Description: This feature will test a Login and Logout functionality

  Scenario: Unsuccessful Login with Invalid Credentials
    Given User is on LoginPage
    And User enters Username as "test" and Password as "test"
    And User submit the credentials by pressing SignIn
    Then Message displayed Invalid Credentials 
    