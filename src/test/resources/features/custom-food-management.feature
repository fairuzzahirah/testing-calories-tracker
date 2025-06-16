Feature: Custom Food Management
  As a logged-in user
  I want to manage my custom foods
  So that I can add personalized food items to my entries
  Background:
    Given I am logged in as "demo@example.com"
    @positive @TC-011
  Scenario: TC-011 - Add new custom food
    When I navigate to the custom foods page
    And I click the custom food "Add Custom Food" button
    And I fill the custom food form with:
      | food_name     | Homemade Granola           |
      | calories      | 200                        |
      | serving_unit  | cup                        |
      | category      |                            |
      | notes         | Contains oats, nuts, honey |
    And I click the custom food "Add Custom Food" button
    Then I should see the custom food success message "Custom food created successfully!"
    And the custom food should appear in the custom foods list

  @positive @TC-012
  Scenario: TC-012 - Edit existing custom food
    Given I have a custom food "Homemade Granola" with 200 calories
    When I click the Edit button for that custom food
    Then I should be redirected to the edit form
    When I change the custom food calories to "180"
    And I change the category to ""
    And I click the custom food "Update Custom Food" button
    Then the custom food should be updated with the new data

  @positive @TC-013
  Scenario: TC-013 - Delete custom food
    Given I have a custom food in my account
    When I click the Delete button for that custom food
    Then I should see a custom food confirmation dialog
    When I confirm the custom food deletion
    Then the custom food should be removed from the database and list

  @negative @TC-033
  Scenario: TC-033 - Add custom food with invalid boundary data
    When I navigate to the custom foods page
    And I click the custom food "Add Custom Food" button
    And I fill the custom food form with invalid data:
      | food_name     | Lorem ipsum dolor sit amet consectetur adipiscing elit sed do eiusmod tempor incididunt ut labore et dolore magna aliqua ut enim ad minim veniam quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat duis aute irure dolor |
      | calories      | -1                         |
      | serving_unit  |                            |
      | category      |                            |
    And I click the custom food "Add Custom Food" button
    Then I should see custom food validation errors:
      | field        | error_message                    |
      | food_name    | Food name max 255 characters    |
      | calories     | Calories must be 0 or positive  |
      | serving_unit | Serving unit is required         |
      | category     | Category is required             |

  @negative @security @TC-034
  Scenario: TC-034 - Edit custom food belonging to another user
    When I try to access edit URL for a custom food belonging to another user
    Then I should see a custom food 403 Forbidden error
    And I should be redirected with message "Access denied"

  @negative @TC-035
  Scenario: TC-035 - Delete custom food that is being used in food entries
    Given I have a custom food that is used in my food entries
    When I try to delete that custom food
    Then I should see a custom food error message "Cannot delete custom food that is being used in food entries"
    And the deletion should be prevented

  @negative @TC-036
  Scenario: TC-036 - Add custom food with duplicate name
    Given I have a custom food named "Homemade Granola"
    When I try to add another custom food with the same name "Homemade Granola"
    And I fill other fields with:
      | calories      | 180       |
      | serving_unit  | bowl      |
      | category      | Breakfast |    And I click the custom food "Add Custom Food" button
    Then I should see the custom food validation error "You already have a custom food with this name"
