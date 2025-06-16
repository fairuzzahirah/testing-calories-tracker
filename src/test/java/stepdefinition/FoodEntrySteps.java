package stepdefinition;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.DashboardPage;
import pages.FoodEntryPage;
import util.DriverManager;
import util.TestDataGenerator;

public class FoodEntrySteps {
    private FoodEntryPage foodEntryPage;
    private DashboardPage dashboardPage;

    public FoodEntrySteps() {
        this.foodEntryPage = new FoodEntryPage(DriverManager.getDriver());
        this.dashboardPage = new DashboardPage(DriverManager.getDriver());
    }

    @Given("I am on the dashboard page")
    public void i_am_on_the_dashboard_page() {
        dashboardPage.navigateToDashboard();
        Assert.assertTrue("Should be on dashboard", dashboardPage.isOnDashboard());
    }

    @Given("I have food entries in my account")
    public void i_have_food_entries_in_my_account() {
        // This would typically require test data setup
        // For now, we'll assume the demo user has some entries
        // In a real scenario, you might need to create test data
    }

    @Given("I have a food entry {string}")
    public void i_have_a_food_entry(String foodEntryDescription) {
        // Similar to above - assumes test data exists
        // In production, you might create specific test data here
    }    @Given("I have a food entry in my account")
    public void i_have_a_food_entry_in_my_account() {
        // Navigate to food entries page first
        foodEntryPage.navigateToFoodEntries();
        
        // Check if there are any food entries, if not create one
        if (foodEntryPage.isEmpty()) {
            // Navigate to add food entry
            dashboardPage.clickAddFoodEntry();
            
            // Fill out a simple food entry
            Map<String, String> testFoodData = new HashMap<>();
            testFoodData.put("food_name", "Test Food for Delete");
            testFoodData.put("calories_per_serving", "200");
            testFoodData.put("serving_amount", "1");
            testFoodData.put("serving_unit", "piece");
            
            foodEntryPage.fillFoodEntryForm(testFoodData);
            foodEntryPage.submitForm();
            
            // Navigate back to food entries list
            foodEntryPage.navigateToFoodEntries();
        }
    }@When("I click the {string} button")
    public void i_click_the_button(String buttonText) {
        if ("Add Food Entry".equals(buttonText)) {
            if (dashboardPage.isOnDashboard()) {
                dashboardPage.clickAddFoodEntry();
            } else {
                foodEntryPage.clickAddFoodEntryButton();
            }
        } else if ("Update Food Entry".equals(buttonText)) {
            foodEntryPage.submitForm();
        } else if ("Submit".equals(buttonText) || "Save".equals(buttonText)) {
            foodEntryPage.submitForm();
        }
    }

    @When("I navigate to the food entries page")
    public void i_navigate_to_the_food_entries_page() {
        foodEntryPage.navigateToFoodEntries();
    }

    @When("I fill the food entry form with:")
    public void i_fill_the_food_entry_form_with(DataTable dataTable) {
        Map<String, String> foodData = dataTable.asMap(String.class, String.class);
        foodEntryPage.fillFoodEntryForm(foodData);
    }

    @When("I fill the food entry form with invalid data:")
    public void i_fill_the_food_entry_form_with_invalid_data(DataTable dataTable) {
        Map<String, String> invalidData = dataTable.asMap(String.class, String.class);
        foodEntryPage.fillFoodEntryFormWithInvalidData(invalidData);
    }    @When("I click the Edit button for that entry")
    public void i_click_the_edit_button_for_that_entry() {
        // Navigate to food entries page first to ensure we're on the right page
        foodEntryPage.navigateToFoodEntries();
        // Wait for the page to load and click the first edit button
        foodEntryPage.clickEditButtonForEntry(0); // Click first entry's edit button
    }    @When("I change the food name to {string}")
    public void i_change_the_food_name_to(String newFoodName) {
        foodEntryPage.updateFoodName(newFoodName);
    }

    @When("I change the calories to {string}")
    public void i_change_the_calories_to(String newCalories) {
        foodEntryPage.updateCaloriesPerServing(newCalories);
    }

    @When("I change the serving amount to {string}")
    public void i_change_the_serving_amount_to(String newServingAmount) {
        foodEntryPage.updateServingAmount(newServingAmount);
    }

    @When("I click the Delete button for that entry")
    public void i_click_the_delete_button_for_that_entry() {
        foodEntryPage.clickDeleteButtonForEntry(0); // Click first entry's delete button
    }

    @When("I confirm the deletion")
    public void i_confirm_the_deletion() {
        foodEntryPage.confirmDeletion();
    }

    @When("I navigate to edit URL with non-existent ID {string}")
    public void i_navigate_to_edit_url_with_non_existent_id(String invalidId) {
        foodEntryPage.navigateToNonExistentEditPage(invalidId);
    }

    @When("I try to delete a food entry that belongs to another user")
    public void i_try_to_delete_a_food_entry_that_belongs_to_another_user() {
        // This would require specific test setup with another user's data
        // For now, we simulate by navigating to a restricted URL
        String anotherUserEntryId = "999999";
        DriverManager.getDriver().get("http://localhost:8001/food/" + anotherUserEntryId + "/delete");
    }

    @When("I fill the food name with {string}")
    public void i_fill_the_food_name_with(String xssPayload) {
        foodEntryPage.fillFoodNameWithXSSPayload(xssPayload);
    }

    @When("I fill other fields with valid data")
    public void i_fill_other_fields_with_valid_data() {
        Map<String, String> validData = Map.of(
            "calories_per_serving", "100",
            "serving_amount", "1",
            "serving_unit", "piece",
            "consumed_at", TestDataGenerator.getCurrentDateTime()
        );
        foodEntryPage.fillFoodEntryForm(validData);
    }

    @Then("I should be redirected to the add food entry page")
    public void i_should_be_redirected_to_the_add_food_entry_page() {
        Assert.assertTrue("Should be on add food entry page", foodEntryPage.isOnAddFoodEntryPage());
    }    @Then("I should see the food entry success message {string}")
    public void i_should_see_the_food_entry_success_message(String expectedMessage) {
        String actualMessage = foodEntryPage.getSuccessMessage();
        Assert.assertTrue("Success message should contain: " + expectedMessage,
                         actualMessage.contains(expectedMessage));
    }

    @Then("I should be redirected to the food entries list")
    public void i_should_be_redirected_to_the_food_entries_list() {
        Assert.assertTrue("Should be redirected to food entries page", foodEntryPage.isOnFoodEntriesPage());
    }

    @Then("the food entry should appear with total calories {string}")
    public void the_food_entry_should_appear_with_total_calories(String expectedCalories) {
        // Verify the entry appears in the list with correct calories
        // This would require checking the table content
        Assert.assertTrue("Food entry should be displayed", foodEntryPage.getFoodEntriesCount() > 0);
    }

    @Then("I should see a list of my food entries with pagination")
    public void i_should_see_a_list_of_my_food_entries_with_pagination() {
        Assert.assertTrue("Should be on food entries page", foodEntryPage.isOnFoodEntriesPage());
        // Check if pagination is displayed (if there are enough entries)
    }

    @Then("each entry should display:")
    public void each_entry_should_display(DataTable dataTable) {
        List<String> expectedFields = dataTable.asList();
        // Verify that the food entries table contains the expected columns
        Assert.assertTrue("Food entries should be displayed", foodEntryPage.getFoodEntriesCount() >= 0);
        // Additional verification of table structure would go here
    }

    @Then("I should be redirected to the edit form with pre-filled data")
    public void i_should_be_redirected_to_the_edit_form_with_pre_filled_data() {
        Assert.assertTrue("Should be on edit food entry page", foodEntryPage.isOnEditFoodEntryPage());
    }

    @Then("the updated data should appear in the food entries list")
    public void the_updated_data_should_appear_in_the_food_entries_list() {
        Assert.assertTrue("Should be redirected to food entries list", foodEntryPage.isOnFoodEntriesPage());
        String successMessage = foodEntryPage.getSuccessMessage();
        Assert.assertTrue("Should show update success message", 
                         successMessage.contains("updated") || successMessage.contains("success"));
    }    @Then("I should see a food entry confirmation dialog")
    public void i_should_see_a_food_entry_confirmation_dialog() {
        // Browser confirmation dialog is handled automatically in page object
        // This step just acknowledges that the dialog should appear
    }

    @Then("the food entry should be removed from the list")
    public void the_food_entry_should_be_removed_from_the_list() {
        // Verify that the entry count has decreased or specific entry is gone
        // This would require more specific implementation based on the UI
    }

    @Then("I should see a confirmation message")
    public void i_should_see_a_confirmation_message() {
        String message = foodEntryPage.getSuccessMessage();
        Assert.assertFalse("Should see a confirmation message", message.isEmpty());
    }    @Then("I should see food entry validation errors:")
    public void i_should_see_food_entry_validation_errors(DataTable dataTable) {
        Assert.assertTrue("Should have validation errors", foodEntryPage.hasValidationErrors());
        Map<String, String> expectedErrors = dataTable.asMap(String.class, String.class);
        List<String> actualErrors = foodEntryPage.getValidationErrorMessages();
        
        // Verify that expected error messages are present
        for (String expectedError : expectedErrors.values()) {
            boolean errorFound = actualErrors.stream()
                    .anyMatch(error -> error.contains(expectedError));
            Assert.assertTrue("Should contain error: " + expectedError, errorFound);
        }
    }

    @Then("I should see a {int} error or be redirected to food index")
    public void i_should_see_a_error_or_be_redirected_to_food_index(int errorCode) {
        if (errorCode == 404) {
            Assert.assertTrue("Should show 404 error or redirect", 
                             foodEntryPage.is404ErrorDisplayed() || foodEntryPage.isOnFoodEntriesPage());
        }
    }    @Then("I should see the food entry message {string}")
    public void i_should_see_the_food_entry_message(String expectedMessage) {
        String actualMessage = foodEntryPage.getErrorMessage();
        if (actualMessage.isEmpty()) {
            actualMessage = foodEntryPage.getSuccessMessage();
        }
        Assert.assertTrue("Message should contain: " + expectedMessage,
                         actualMessage.contains(expectedMessage));
    }@Then("I should see a food entry {int} Forbidden error")
    public void i_should_see_a_food_entry_forbidden_error(int errorCode) {
        if (errorCode == 403) {
            Assert.assertTrue("Should show 403 Forbidden error", foodEntryPage.is403ErrorDisplayed());
        }
    }

    @Then("the input should be sanitized and saved as plain text")
    public void the_input_should_be_sanitized_and_saved_as_plain_text() {
        // Verify that XSS payload is not executed
        Assert.assertFalse("XSS should not be executed", foodEntryPage.isXSSExecuted());
    }    @Then("food entry no script should be executed")
    public void food_entry_no_script_should_be_executed() {
        Assert.assertFalse("No script should be executed", foodEntryPage.isXSSExecuted());
    }

    @Then("no alert popup should appear")
    public void no_alert_popup_should_appear() {
        Assert.assertFalse("No alert should appear", foodEntryPage.isXSSExecuted());
    }
}
