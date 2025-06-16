Feature: Custom Food
  Background:
    Given User is logged in

  Scenario: Fail to add custom food due to empty food name
    Given User is on the custom food page
    When User clicks the Add Custom Food button
    When User enters custom food name ""
    And User enters custom food calories "120"
    And User enters custom food serving unit "piece"
    And User selects custom food category "Vegetables"
    And User enters custom food notes "Goreng pakai minyak jagung"
    And User submits the custom food form
    Then User should see custom food validation error

  Scenario: Add custom food successfully
    Given User is on the custom food page
    When User clicks the Add Custom Food button
    When User enters custom food name "Tahu Goreng"
    And User enters custom food calories "120"
    And User enters custom food serving unit "piece"
    And User selects custom food category "Vegetables"
    And User enters custom food notes "Goreng pakai minyak jagung"
    And User submits the custom food form
    Then User should see custom food success message