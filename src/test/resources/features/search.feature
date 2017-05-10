@requires_browser
Feature: Login Action Test
Description: This feature will test a Login and Logout functionality

  Scenario: Unsuccessful Login with Invalid Credentials
    Given User is on Homepage
    When User Navigate to Login Page
    And User enters Username as "subbannan" and Password as "di"
    And User submit the credentials by pressing SignIn
    Then Message displayed Welcome
    