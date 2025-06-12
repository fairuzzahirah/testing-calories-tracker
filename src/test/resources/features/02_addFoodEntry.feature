Feature: Food Entry
  Background:
    Given User has registered and is logged in

  Scenario: Fails to add food entry due to empty food name
    Given User is on the food entry page
    When User clicks the Add Food Entry button
    And User enters food name ""
    And User enters calories per serving "95"
    And User enters serving amount "1"
    And User enters serving unit "piece"
    And User submits the food entry form
    Then User should see validation error

  Scenario: Successfully adding a food entry
    Given User is on the food entry page
    When User clicks the Add Food Entry button
    And User enters food name "Apple"
    And User enters calories per serving "95"
    And User enters serving amount "1"
    And User enters serving unit "piece"
    And User submits the food entry form
    Then User should see success message
