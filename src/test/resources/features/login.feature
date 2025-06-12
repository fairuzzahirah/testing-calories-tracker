Feature: Login functionality
  As a user
  I want to login to the application
  So that I can access my calorie tracking dashboard

  Scenario: Successful login with valid credentials
    Given User is on the login page
    When User enters valid email
    And User enters valid password
    And User clicks login button
    Then User should be redirected to dashboard
    And User should see welcome message

  Scenario: Login with invalid credentials
    Given User is on the login page
    When User enters invalid email
    And User enters invalid password
    And User clicks login button
    Then User should see error message
    And User should remain on login page

  Scenario: Login with demo credentials
    Given User is on the login page
    When User enters demo email
    And User enters demo password
    And User clicks login button
    Then User should be redirected to dashboard
    And User should see dashboard content
