Feature: User Registration

  As a new user
  I want to fill out the registration form
  So that I can create an account and be redirected to the login page

  Scenario: Successful registration redirects user to login page
    Given User is on the form page
    When User enter username
    And User enter email
    And User enter password
    And User enter password confirm
    And User enter age
    And User enter height
    And User enter weight
    And User select gender
    And User select goal
    And User select activity level
    And User submits the form
    Then User should be redirected to login page
