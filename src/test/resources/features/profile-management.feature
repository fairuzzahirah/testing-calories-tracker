Feature: Profile Management
  As a logged-in user
  I want to manage my profile information
  So that I can keep my account and calorie goals up to date
  Background:
    Given I am logged in as "demo@example.com"
    
  @positive @TC-021
  Scenario: TC-021 - Update profile information
    When I navigate to the profile page
    And I update my profile information:
      | field  | old_value | new_value |
      | age    | 25        | 26        |
      | weight | 70 kg     | 72 kg     |
      | goal   | lose      | maintain  |
    And I click the Save button
    Then my profile should be updated successfully
    And I should remain on the profile page    And the updated values should be saved in the form fields
    
  @positive @TC-022
  Scenario: TC-022 - Update password
    Given I register a new account for testing:
      | name     | Test Password User    |
      | email    | testpass@example.com  |
      | password | password123           |
    And I am logged in as "testpass@example.com"
    When I navigate to the profile page
    And I fill the change password form:
      | current_password | password123    |
      | new_password     | newpassword123 |
      | confirm_password | newpassword123 |
    And I click the Save button for password
    Then my password should be updated successfully
    And I should remain on the profile page
  @negative @TC-023
  Scenario: TC-023 - Update profile with invalid data
    Given I am logged in as "demo@example.com"
    When I navigate to the profile page
    And I fill invalid profile data:
      | field  | value |
      | age    | 999   |
      | height | -10   |
      | weight | 0     |
    And I click the Save button
    Then I should see profile validation errors:
      | field  | error_message                        |
      | age    | Age must be between 1 and 120        |
      | height | Height must be between 50 and 250 cm |
      | weight | Weight must be greater than 0        |

  @negative @TC-049
  Scenario: TC-049 - Update profile with wrong current password
    When I navigate to the profile page
    And I fill the profile update form with current password "wrongpassword"
    And I enter new profile data
    And I click the Save button
    Then I should see the profile validation error "Current password is incorrect"
    And the profile should not be updated

  @negative @TC-050
  Scenario: TC-050 - Update password with same new password as current
    When I navigate to the profile page
    And I fill the change password form:
      | current_password | password123 |
      | new_password     | password123 |
      | confirm_password | password123 |
    And I click the Save button for password
    Then I should see the profile validation error "New password must be different from current password"
 
  @negative @TC-051
  Scenario: TC-051 - Update profile with email already used by another user
    When I navigate to the profile page
    And I change my email to "demo@example.com" (already used by another user)
    And I click the Save button
    Then I should see the profile validation error "Email already taken by another user"
 
  @negative @session @TC-052
  Scenario: TC-052 - Update profile with expired session
    Given my session has expired
    When I navigate to the profile page
    And I fill valid profile data
    And I click the Save button
    Then I should be redirected to the profile login page
    And I should see the profile message "Session expired. Please login again."

  @negative @TC-053
  Scenario: TC-053 - Delete account with wrong password
    When I navigate to the delete account section
    And I enter the wrong password "wrongpassword"
    And I confirm account deletion
    Then I should see the profile validation error "Password is incorrect"
    And my account should not be deleted
