Feature: Boundary Value Testing
  As a QA tester
  I want to verify that the application handles boundary values correctly
  So that I can ensure data validation works properly at edge cases

  Background:
    Given the calories tracker application is running
    And I am logged in as "demo@example.com"

  @boundary @negative @TC-BV001
  Scenario: TC-BV001 - Profile age boundary values - minimum invalid
    When I navigate to the profile page
    And I enter age "0"
    And I click the Save button
    Then I should see the validation error "Age must be between 1 and 120"

  @boundary @positive @TC-BV002
  Scenario: TC-BV002 - Profile age boundary values - minimum valid
    When I navigate to the profile page
    And I enter age "1"
    And I click the Save button
    Then the profile should be updated successfully

  @boundary @positive @TC-BV003
  Scenario: TC-BV003 - Profile age boundary values - maximum valid
    When I navigate to the profile page
    And I enter age "120"
    And I click the Save button
    Then the profile should be updated successfully

  @boundary @negative @TC-BV004
  Scenario: TC-BV004 - Profile age boundary values - maximum invalid
    When I navigate to the profile page
    And I enter age "121"
    And I click the Save button
    Then I should see the validation error "Age must be between 1 and 120"

  @boundary @negative @TC-BV005
  Scenario: TC-BV005 - Profile weight boundary values - minimum invalid
    When I navigate to the profile page
    And I enter weight "0"
    And I click the Save button
    Then I should see the validation error "Weight must be greater than 0"

  @boundary @positive @TC-BV006
  Scenario: TC-BV006 - Profile weight boundary values - minimum valid
    When I navigate to the profile page
    And I enter weight "1"
    And I click the Save button
    Then the profile should be updated successfully

  @boundary @positive @TC-BV007
  Scenario: TC-BV007 - Profile weight boundary values - maximum valid
    When I navigate to the profile page
    And I enter weight "500"
    And I click the Save button
    Then the profile should be updated successfully

  @boundary @negative @TC-BV008
  Scenario: TC-BV008 - Profile weight boundary values - maximum invalid
    When I navigate to the profile page
    And I enter weight "501"
    And I click the Save button
    Then I should see the validation error "Weight must be between 1 and 500 kg"

  @boundary @negative @TC-BV009
  Scenario: TC-BV009 - Profile height boundary values - minimum invalid
    When I navigate to the profile page
    And I enter height "49"
    And I click the Save button
    Then I should see the validation error "Height must be between 50 and 250 cm"

  @boundary @positive @TC-BV010
  Scenario: TC-BV010 - Profile height boundary values - minimum valid
    When I navigate to the profile page
    And I enter height "50"
    And I click the Save button
    Then the profile should be updated successfully

  @boundary @positive @TC-BV011
  Scenario: TC-BV011 - Profile height boundary values - maximum valid
    When I navigate to the profile page
    And I enter height "250"
    And I click the Save button
    Then the profile should be updated successfully

  @boundary @negative @TC-BV012
  Scenario: TC-BV012 - Profile height boundary values - maximum invalid
    When I navigate to the profile page
    And I enter height "251"
    And I click the Save button
    Then I should see the validation error "Height must be between 50 and 250 cm"

  @boundary @negative @TC-BV013
  Scenario: TC-BV013 - Food calories boundary values - negative value
    When I navigate to custom food page
    And I enter food name "Test Food"
    And I enter calories "-1"
    And I enter serving unit "piece"
    And I click Add Food button
    Then I should see the validation error "Calories must be greater than 0"

  @boundary @positive @TC-BV014
  Scenario: TC-BV014 - Food calories boundary values - minimum valid
    When I navigate to custom food page
    And I enter food name "Test Food"
    And I enter calories "1"
    And I enter serving unit "piece"
    And I click Add Food button
    Then the food should be created successfully

  @boundary @positive @TC-BV015
  Scenario: TC-BV015 - Food calories boundary values - maximum valid
    When I navigate to custom food page
    And I enter food name "Test Food"
    And I enter calories "9999"
    And I enter serving unit "piece"
    And I click Add Food button
    Then the food should be created successfully

  @boundary @negative @TC-BV016
  Scenario: TC-BV016 - Food calories boundary values - maximum invalid
    When I navigate to custom food page
    And I enter food name "Test Food"
    And I enter calories "10000"
    And I enter serving unit "piece"
    And I click Add Food button
    Then I should see the validation error "Calories must be between 1 and 9999"

  @boundary @equivalence @TC-EQ001
  Scenario Outline: TC-EQ001 - Password length equivalence partitioning
    Given I am on the register page
    When I enter password "<password>"
    And I enter password confirmation "<password>"
    And I fill other required fields
    And I click the register button
    Then I should see "<result>"

    Examples:
      | password    | result                                    |
      | 1234567     | Password must be at least 8 characters   |
      | 12345678    | Registration successful                   |
      | 123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890 | Password must be less than 128 characters |

  @boundary @equivalence @TC-EQ002
  Scenario Outline: TC-EQ002 - Email format equivalence partitioning
    Given I am on the register page
    When I enter email "<email>"
    And I fill other required fields with valid data
    And I click the register button
    Then I should see "<result>"

    Examples:
      | email                    | result                          |
      | invalid                  | Please enter a valid email     |
      | invalid@                 | Please enter a valid email     |
      | invalid@domain           | Please enter a valid email     |
      | valid@example.com        | Registration successful         |
      | valid.email@example.com  | Registration successful         |
      | valid+tag@example.com    | Registration successful         |
