Feature: Profile Page

  Background:
    Given User is logged in

  Scenario: User navigates to profile from dropdown menu
    When User clicks the dropdown menu on the header
    And User selects the Profile option
    Then User should be redirected to the profile page
    And User should see the text Body Mass Index (BMI) on the profile page

  Scenario: User updates profile information
    Given User is on the profile page
    When User updates the name to "Faza Cantik Buanget"
    And User updates the age to "16"
    And User selects gender "Other"
    And User enters height "168.5"
    And User enters weight "45.0"
    And User selects goal "Weight Loss"
    And User selects activity level "Active (Heavy exercise 6-7 days/week)"
    And User submits the profile form
    Then Profile should be successfully updated

  Scenario: User submits invalid height below minimum
    Given User is on the profile page
    When User updates the name to "Faza Cantik"
    And User updates the age to "19"
    And User selects gender "Male"
    And User enters height "40"
    And User enters weight "44"
    And User selects goal "Weight Gain"
    And User selects activity level "Active (Heavy exercise 6-7 days/week)"
    And User submits the profile form
    Then An error message "Height must be a number between 50 and 250 cm" should be displayed

  Scenario: User enters too short new password
    Given User is on the profile page
    When User enters current password "password123"
    And User enters new password "pass"
    And User confirms new password "pass"
    And User submits the password form
    Then An error message "The password field must be at least 8 characters." should be displayed

  Scenario: User enters incorrect current password
    Given User is on the profile page
    When User enters current password "passwordsalah"
    And User enters new password "passwordbaru"
    And User confirms new password "passwordbaru"
    And User submits the password form
    Then An error message "The password is incorrect." should be displayed

  Scenario: User successfully changes password
    Given User is on the profile page
    When User enters current password "password123"
    And User enters new password "passwordbaru"
    And User confirms new password "passwordbaru"
    And User submits the password form
