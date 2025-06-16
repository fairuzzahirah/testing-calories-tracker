Feature: Profile Page

  Background:
    Given User is logged in

  Scenario: User logs out from dropdown menu
    When User clicks the dropdown menu on the header for logout
    And User clicks the logout option
    Then User should be redirected to the login page
