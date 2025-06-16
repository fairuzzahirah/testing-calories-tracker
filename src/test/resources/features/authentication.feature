Feature: Authentication
  As a user
  I want to login and register to the calories tracker application
  So that I can access the application features
  Background:
    Given the calories tracker application is running
    And I am on the login page

  @positive @smoke @TC-001
  Scenario: TC-001 - Login with valid credentials (demo user)
    When I enter email "demo@example.com" and password "password123"
    And I click the login button
    Then I should be redirected to the dashboard
    And I should see the message "Login successful!"

  @negative @TC-002
  Scenario: TC-002 - Login with unregistered email
    When I enter email "notregistered@example.com" and password "anypassword"
    And I click the login button
    Then I should see the error message "The provided credentials are incorrect."
    And I should remain on the login page

  @negative @TC-003
  Scenario: TC-003 - Login with wrong password
    When I enter email "demo@example.com" and password "wrongpassword"
    And I click the login button
    Then I should see the error message "The provided credentials are incorrect."
    And I should remain on the login page

  @positive @TC-004
  Scenario: TC-004 - Register new user with valid data
    Given I am on the register page
    When I fill the registration form with:
      | name                  | John Doe              |
      | email                | john.doe@example.com  |
      | password             | password123           |
      | password_confirmation | password123           |
      | age                  | 25                    |
      | gender               | male                  |
      | height               | 175                   |
      | weight               | 70                    |
      | goal                 | lose                  |
      | activity_level       | moderate              |
    And I click the register button
    Then I should be automatically logged in
    And I should be redirected to the dashboard
    And my data should be saved in the database

  @negative @TC-005
  Scenario: TC-005 - Register with existing email
    Given I am on the register page
    When I fill the registration form with existing email "demo@example.com"
    And I click the register button
    Then I should see the error message "The email has already been taken"
    And registration should fail

  @negative @TC-006
  Scenario: TC-006 - Register with mismatched password confirmation
    Given I am on the register page
    When I fill the registration form with password "password123" and confirmation "differentpassword"
    And I click the register button
    Then I should see the error message "Password confirmation does not match"
    And registration should fail

  @positive @TC-024
  Scenario: TC-024 - User logout from system
    Given I am logged in as "demo@example.com"
    When I click the logout button
    Then I should be redirected to the login page
    And my session should be cleared

  @positive @integration @TC-025
  Scenario: TC-025 - Complete user journey - Registration to full application usage
    Given I am on the register page
    When I register a new user with:
      | name                  | Integration Test User     |
      | email                | integration@test.com      |
      | password             | testpass123               |
      | password_confirmation | testpass123               |
      | age                  | 30                        |
      | gender               | female                    |
      | height               | 165                       |
      | weight               | 60                        |
      | goal                 | maintain                  |
      | activity_level       | active                    |
    Then I should be automatically logged in
    And I should be redirected to the dashboard
    When I add a food entry from database
    And I create a custom food item
    And I search for food in USDA database
    And I use the AI chatbot for nutrition advice
    And I view my dashboard analytics
    And I update my profile information
    Then all features should work correctly
    When I logout from the application
    Then I should be redirected to the login page
    And I can login again with the same credentials
