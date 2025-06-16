Feature: User Login

  Scenario: Failed login with invalid password
    Given the user is on the login page
    When the user logs in with email "user@example.com" and password "WrongPassword"
    And User submit the form
    Then an error message "The provided credentials are incorrect." should be shown
  Scenario: Successful login with valid credentials
    Given the user is on the login page
    When the user logs in with email "demo@example.com" and password "password123"
    And User submit the form
    Then User should be redirected to dashboard

