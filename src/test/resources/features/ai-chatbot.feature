Feature: AI Chatbot
  As a logged-in user
  I want to interact with the AI chatbot
  So that I can get personalized food recommendations and nutrition advice

  Background:
    Given I am logged in as "demo@example.com"
  @positive @TC-016
  Scenario: TC-016 - Get food recommendations from AI chatbot
    When I navigate to the chatbot page
    And I type the question "What are some healthy breakfast options for weight loss?"
    And I click the Send button
    Then the AI chatbot should provide personalized food recommendations
    And the response should include a list of foods with calorie information
    And the recommendations should be based on my user profile

  @positive @TC-017
  Scenario: TC-017 - View chat history
    Given I have previous chat interactions
    When I navigate to the chatbot page
    Then I should see my previous chat history with timestamps
    And I should be able to scroll through the conversation flow
    And each message should show user and AI responses clearly

  @positive @TC-018
  Scenario: TC-018 - Clear chat history
    Given I have chat history
    When I click the chatbot "Clear History" button
    Then I should see a chatbot confirmation dialog
    When I confirm the action
    Then all chat history should be deleted
    And I should see the confirmation message "Chat history cleared successfully"

  @negative @TC-041
  Scenario: TC-041 - Send empty chatbot query
    When I navigate to the chatbot page
    And I try to submit an empty query
    Then the form validation should prevent submission
    And I should see the chatbot message "Please enter a question"

  @negative @TC-042
  Scenario: TC-042 - Send chatbot query that is too long
    When I navigate to the chatbot page
    And I enter a query with more than 500 characters
    And I click the Send button
    Then I should see the chatbot validation error "Query must be maximum 500 characters"

  @negative @api @TC-043
  Scenario: TC-043 - Chatbot with Gemini API error
    Given the Gemini API is experiencing errors
    When I navigate to the chatbot page
    And I submit a valid query "What should I eat today?"
    Then the chatbot should display a fallback message "AI service is temporarily unavailable. Please try again later."
 
  @negative @security @TC-044
  Scenario: TC-044 - Send chatbot query with special characters
    When I navigate to the chatbot page
    And I enter a query with special characters "<script>alert('test')</script> What should I eat?"
    And I click the Send button
    Then the input should be sanitized
    And the chatbot should provide a normal response
    And chatbot no script should be executed
    
  @negative @rate-limiting @TC-045
  Scenario: TC-045 - Spam multiple queries in short time
    When I navigate to the chatbot page
    And I send 10 queries rapidly within 1 minute
    Then the system should detect spam behavior
    And I should see the chatbot message "Please wait before sending another message"
    And the send button should be temporarily disabled
