Feature: User Registration

  As a new user
  I want to fill out the registration form
  So that I can create an account and be redirected to the login page

  Scenario: Successful registration redirects user to login page
    Given User is on the form page
    When User fills out the form
    And User submits the form
    Then User should be redirected to dashboard
