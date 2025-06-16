Feature: Food Entry Management
  As a logged-in user
  I want to manage my food entries
  So that I can track my calorie consumption
  Background:
    Given I am logged in as "demo@example.com"
    And I am on the dashboard page

  @positive @smoke @TC-007
  Scenario: TC-007 - Add new food entry with valid data
    When I click the "Add Food Entry" button
    Then I should be redirected to the add food entry page
    When I fill the food entry form with:
      | food_name            | Grilled Chicken Breast |
      | calories_per_serving | 165                    |
      | serving_amount       | 1                      |
      | serving_unit         | piece                  |
      | source               | custom                 |
    And I click the "Add Food Entry" button
    Then I should see the food entry success message "Food entry created successfully!"
    And I should be redirected to the food entries list
    And the food entry should appear with total calories "165 kcal"
  @positive @TC-008
  Scenario: TC-008 - View list of food entries
    Given I have food entries in my account
    When I navigate to the food entries page
    Then I should see a list of my food entries with pagination
    And each entry should display:
      | field           |
      | Food name       |
      | Total calories  |
      | Serving info    |
      | Source          |
      | Consumed date   |
      | Edit button     |
      | Delete button   |

  @positive @TC-009
  Scenario: TC-009 - Edit existing food entry
    Given I have a food entry "Apple - 95 kcal"
    When I click the Edit button for that entry
    Then I should be redirected to the edit form with pre-filled data
    When I change the food name to "Green Apple"
    And I change the calories to "80"
    And I change the serving amount to "2"
    And I click the "Update Food Entry" button
    Then I should see the food entry success message "Food entry updated successfully!"
    And the updated data should appear in the food entries list

  @positive @TC-010
  Scenario: TC-010 - Delete food entry
    Given I have a food entry in my account
    When I click the Delete button for that entry
    Then I should see a food entry confirmation dialog
    When I confirm the deletion
    Then the food entry should be removed from the list
    And I should see a confirmation message

  @negative @TC-029
  Scenario: TC-029 - Add food entry with invalid data
    When I click the "Add Food Entry" button    And I fill the food entry form with invalid data:
      | food_name            |                          |
      | calories_per_serving | -50                      |
      | serving_amount       | 0                        |
      | serving_unit         |                          |
    And I click the "Add Food Entry" button
    Then I should see food entry validation errors:
      | field                | error_message                         |
      | food_name            | Food name is required                 |
      | calories_per_serving | Calories must be positive number      |
      | serving_amount       | Serving amount must be greater than 0 |
      | serving_unit         | Serving unit is required              |

  @negative @TC-030
  Scenario: TC-030 - Edit food entry with invalid ID
    When I navigate to edit URL with non-existent ID "999999"
    Then I should see a 404 error or be redirected to food index
    And I should see the food entry message "Food entry not found"

  @negative @security @TC-031
  Scenario: TC-031 - Delete food entry belonging to another user
    When I try to delete a food entry that belongs to another user
    Then I should see a food entry 403 Forbidden error
    And I should see the food entry message "Access denied"

  @negative @security @TC-032
  Scenario: TC-032 - Submit food entry with XSS payload
    When I click the "Add Food Entry" button
    And I fill the food name with "<script>alert('XSS')</script>"
    And I fill other fields with valid data
    And I click the "Add Food Entry" button
    Then the input should be sanitized and saved as plain text
    And food entry no script should be executed
    And no alert popup should appear
