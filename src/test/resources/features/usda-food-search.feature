Feature: USDA Food Search
  As a logged-in user
  I want to search for foods in the USDA database
  So that I can add accurate nutritional information to my food entries

  Background:
    Given I am logged in as "demo@example.com"
  @positive @TC-014
  Scenario: TC-014 - Search for food in USDA database
    When I navigate to the USDA Search page
    And I enter "chicken breast" in the search box
    And I click the search button
    Then I should see USDA search results displayed with:
      | field            |
      | Food name        |
      | Calories per serving |
      | Serving size options |
      | Add to Food Log button |
  @positive @TC-015
  Scenario: TC-015 - Add USDA food to food entries
    Given I have searched for "chicken breast" in USDA
    And I see search results
    When I select a food item from the results
    And I fill the serving details:
      | serving_amount | 150 |
    And I click the USDA "Add to Food Log" button
    Then the USDA food should be added to my food entries with source "usda"
    And I should be redirected to the food entries page
    And I should see a USDA success message

  @negative @TC-037
  Scenario: TC-037 - Search USDA with empty query
    When I navigate to the USDA Search page
    And I submit search without entering any query
    Then I should see the validation error "Search query is required"
    And the form should not be submitted

  @negative @TC-038
  Scenario: TC-038 - Search USDA with query too short
    When I navigate to the USDA Search page
    And I enter "a" in the search box
    And I click the search button
    Then I should see the validation error "Search query must be at least 2 characters"

  @negative @TC-039
  Scenario: TC-039 - Add USDA food with invalid serving amount
    Given I have searched for "chicken breast" in USDA
    And I see search results
    When I select a food item from the results
    And I fill invalid serving details:
      | serving_amount | -5 |
    And I click the USDA "Add to Food Log" button
    Then I should see the USDA validation error "Serving amount must be positive number"

  @negative @TC-039b
  Scenario: TC-039b - Add USDA food with zero serving amount
    Given I have searched for "chicken breast" in USDA
    And I see search results
    When I select a food item from the results
    And I fill serving details:
      | serving_amount | 0 |
    And I click the USDA "Add to Food Log" button
    Then I should see the USDA validation error "Serving amount must be positive number"

  @negative @TC-039c
  Scenario: TC-039c - Add USDA food with empty serving amount
    Given I have searched for "chicken breast" in USDA
    And I see search results
    When I select a food item from the results
    And I leave serving amount empty
    And I click the USDA "Add to Food Log" button
    Then I should see the USDA validation error "Serving amount is required"

  @negative @api @TC-040
  Scenario: TC-040 - USDA API timeout or unavailable
    Given the USDA API is down or not responding
    When I navigate to the USDA Search page
    And I search for "chicken breast"
    Then I should see a user-friendly error message "Food search service is temporarily unavailable. Please try again later."
