Feature: User Registration

  Scenario: Registration fails due to unmatched password confirmation
    Given User is on the form page
    When User enter username "Frederick"
    And User enter email "AUTO"
    And User enter password "pasword"
    And User enter password confirm "pasword"
    And User enter age "22"
    And User enter height "175"
    And User enter weight "65"
    And User select gender "female"
    And User select goal "maintain"
    And User select activity level "light"
    And User submits the form
    Then User should see password too short error

  Scenario: Successful registration redirects user to dashboard
    Given User is on the form page
    When User enter username "Frederick"
    And User enter email "AUTO"
    And User enter password "password123"
    And User enter password confirm "password123"
    And User enter age "22"
    And User enter height "175"
    And User enter weight "65"
    And User select gender "female"
    And User select goal "maintain"
    And User select activity level "light"
    And User submits the form
    Then User should be redirected to dashboard
