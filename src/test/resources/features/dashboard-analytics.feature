Feature: Dashboard Analytics
  As a logged-in user
  I want to view my dashboard analytics
  So that I can track my calorie consumption and progress

  Background:
    Given I am logged in as "demo@example.com"
  @positive @TC-019
  Scenario: TC-019 - View dashboard with complete data
    Given I have food entries from the past week
    When I navigate to the dashboard
    Then I should see the dashboard displaying:
      | component              |
      | Today's calories consumed |
      | Weekly calorie chart      |
      | Calorie goal progress     |
      | Recent food entries (5)   |
      | Quick action buttons      |

  @positive @TC-020
  Scenario: TC-020 - Dashboard with empty data
    Given I am a new user with no food entries
    When I navigate to the dashboard
    Then I should see the empty state with:
      | component                    |
      | 0 calories consumed today    |
      | Empty charts                 |
      | Call-to-action to add food   |

  @negative @error-handling @TC-046
  Scenario: TC-046 - Dashboard with corrupted data
    Given I have food entries with corrupted or null values
    When I navigate to the dashboard
    Then the dashboard should display partial data that is valid
    And I should see an error message "Some data could not be displayed"
    And the application should not crash

  @negative @database @TC-047
  Scenario: TC-047 - Dashboard with database connection error
    Given the database connection is experiencing issues
    When I navigate to the dashboard
    Then I should see an error state message "Unable to load dashboard data. Please refresh the page."
  
  @negative @performance @TC-048
  Scenario: TC-048 - Dashboard with slow API response
    Given the backend API is responding very slowly (>30 seconds)
    When I navigate to the dashboard
    Then I should see a loading state initially
    And after timeout, I should see the error message "Dashboard is taking too long to load. Please try again."
