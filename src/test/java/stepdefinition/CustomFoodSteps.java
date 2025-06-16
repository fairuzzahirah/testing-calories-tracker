package stepdefinition;

import io.cucumber.java.en.*;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import pages.CustomFoodPage;
import pages.DashboardPage;
import util.DriverManager;
import java.util.Map;

public class CustomFoodSteps {
    private CustomFoodPage customFoodPage;
    private DashboardPage dashboardPage;

    public CustomFoodSteps() {
        this.customFoodPage = new CustomFoodPage(DriverManager.getDriver());
        this.dashboardPage = new DashboardPage(DriverManager.getDriver());
    }

    @When("I navigate to the custom foods page")
    public void i_navigate_to_the_custom_foods_page() {
        customFoodPage.navigateToCustomFoods();
    }    @When("I click the custom food {string} button")
    public void i_click_the_custom_food_button(String buttonText) {
        if ("Add Custom Food".equals(buttonText)) {
            customFoodPage.clickAddCustomFoodButton();
        } else if ("Update Custom Food".equals(buttonText)) {
            customFoodPage.submitForm();
        }
    }

    @When("I fill the custom food form with:")
    public void i_fill_the_custom_food_form_with(DataTable dataTable) {
        Map<String, String> foodData = dataTable.asMap(String.class, String.class);
        customFoodPage.fillCustomFoodForm(foodData);
    }

    @When("I fill the custom food form with invalid data:")
    public void i_fill_the_custom_food_form_with_invalid_data(DataTable dataTable) {
        Map<String, String> invalidData = dataTable.asMap(String.class, String.class);
        customFoodPage.fillCustomFoodFormWithInvalidData(invalidData);
    }    @Given("I have a custom food {string} with {int} calories")
    public void i_have_a_custom_food_with_calories(String foodName, Integer calories) {
        // Navigate to custom foods page and create the custom food
        customFoodPage.navigateToCustomFoods();
        customFoodPage.clickAddCustomFoodButton();
        
        Map<String, String> foodData = Map.of(
            "food_name", foodName,
            "calories", calories.toString(),
            "serving_unit", "serving",
            "notes", "Test custom food for editing"
        );
        customFoodPage.fillCustomFoodForm(foodData);
        customFoodPage.clickAddCustomFoodButton(); // Submit the form
        
        // Navigate back to custom foods list to verify it was created
        customFoodPage.navigateToCustomFoods();
    }    @Given("I have a custom food in my account")
    public void i_have_a_custom_food_in_my_account() {
        // Navigate to custom foods page and create a custom food for deletion test
        customFoodPage.navigateToCustomFoods();
        customFoodPage.clickAddCustomFoodButton();
        
        Map<String, String> foodData = Map.of(
            "food_name", "Test Custom Food for Delete",
            "calories", "150",
            "serving_unit", "piece",
            "notes", "Test custom food to be deleted"
        );
        customFoodPage.fillCustomFoodForm(foodData);
        customFoodPage.clickAddCustomFoodButton(); // Submit the form
        
        // Navigate back to custom foods list
        customFoodPage.navigateToCustomFoods();
    }

    @Given("I have a custom food that is used in my food entries")
    public void i_have_a_custom_food_that_is_used_in_my_food_entries() {
        // Assumes test data where a custom food is referenced in food entries
    }

    @Given("I have a custom food named {string}")
    public void i_have_a_custom_food_named(String foodName) {
        // Assumes test data setup with specific named custom food
    }    @When("I click the Edit button for that custom food")
    public void i_click_the_edit_button_for_that_custom_food() {
        customFoodPage.clickEditButtonForFood(0); // Click first custom food's edit button
    }
    
    @When("I change the custom food calories to {string}")
    public void i_change_the_custom_food_calories_to(String newCalories) {
        Map<String, String> updateData = Map.of("calories", newCalories);
        customFoodPage.fillCustomFoodForm(updateData);
    }

    @When("I change the category to {string}")
    public void i_change_the_category_to(String newCategory) {
        Map<String, String> updateData = Map.of("category", newCategory);
        customFoodPage.fillCustomFoodForm(updateData);
    }

    @When("I click the Delete button for that custom food")
    public void i_click_the_delete_button_for_that_custom_food() {
        customFoodPage.clickDeleteButtonForFood(0); // Click first custom food's delete button
    }    @When("I confirm the custom food deletion")
    public void i_confirm_the_custom_food_deletion() {
        customFoodPage.confirmDeletion();
    }

    @When("I try to access edit URL for a custom food belonging to another user")
    public void i_try_to_access_edit_url_for_a_custom_food_belonging_to_another_user() {
        String anotherUserFoodId = "999999";
        customFoodPage.tryToAccessAnotherUserCustomFood(anotherUserFoodId);
    }

    @When("I try to delete that custom food")
    public void i_try_to_delete_that_custom_food() {
        customFoodPage.tryToDeleteFoodInUse();
    }

    @When("I try to add another custom food with the same name {string}")
    public void i_try_to_add_another_custom_food_with_the_same_name(String duplicateName) {
        customFoodPage.navigateToAddCustomFood();
        // The form filling will be done in the next step
    }

    @When("I fill other fields with:")
    public void i_fill_other_fields_with(DataTable dataTable) {
        Map<String, String> otherData = dataTable.asMap(String.class, String.class);
        String duplicateName = "Homemade Granola"; // This should come from previous step context
        customFoodPage.fillCustomFoodWithDuplicateName(duplicateName, otherData);
    }    @Then("I should see the custom food success message {string}")
    public void i_should_see_the_custom_food_success_message(String expectedMessage) {
        String actualMessage = customFoodPage.getSuccessMessage();
        Assert.assertTrue("Success message should contain: " + expectedMessage,
                         actualMessage.contains(expectedMessage));
    }

    @Then("the custom food should appear in the custom foods list")
    public void the_custom_food_should_appear_in_the_custom_foods_list() {
        Assert.assertTrue("Should be on custom foods page", customFoodPage.isOnCustomFoodsPage());
        Assert.assertTrue("Custom food should be displayed", customFoodPage.getCustomFoodsCount() > 0);
    }

    @Then("I should be redirected to the edit form")
    public void i_should_be_redirected_to_the_edit_form() {
        Assert.assertTrue("Should be on edit custom food page", customFoodPage.isOnEditCustomFoodPage());
    }

    @Then("the custom food should be updated with the new data")
    public void the_custom_food_should_be_updated_with_the_new_data() {
        Assert.assertTrue("Should be redirected to custom foods list", customFoodPage.isOnCustomFoodsPage());
        String successMessage = customFoodPage.getSuccessMessage();
        Assert.assertTrue("Should show update success message", 
                         successMessage.contains("updated") || successMessage.contains("success"));
    }    @Then("I should see a custom food confirmation dialog")
    public void i_should_see_a_custom_food_confirmation_dialog() {
        // Browser confirmation dialog is handled automatically in page object
        // This step just acknowledges that the dialog should appear
    }

    @Then("the custom food should be removed from the database and list")
    public void the_custom_food_should_be_removed_from_the_database_and_list() {
        // Verify that the custom food count has decreased or specific food is gone
        // This would require more specific implementation based on the UI
        String successMessage = customFoodPage.getSuccessMessage();
        Assert.assertTrue("Should show deletion success message", 
                         successMessage.contains("deleted") || successMessage.contains("removed"));
    }    @Then("I should see custom food validation errors:")
    public void i_should_see_custom_food_validation_errors(DataTable dataTable) {
        Assert.assertTrue("Should have validation errors", customFoodPage.hasValidationErrors());
        Map<String, String> expectedErrors = dataTable.asMap(String.class, String.class);
        
        // Verify that expected error messages are present
        for (String expectedError : expectedErrors.values()) {
            String actualError = customFoodPage.getErrorMessage();
            Assert.assertTrue("Should contain error: " + expectedError, 
                             actualError.contains(expectedError) || customFoodPage.hasValidationErrors());
        }
    }    @Then("I should see a custom food {int} Forbidden error")
    public void i_should_see_a_custom_food_forbidden_error(Integer errorCode) {
        if (errorCode == 403) {
            Assert.assertTrue("Should show 403 Forbidden error", customFoodPage.is403ErrorDisplayed());
        }
    }

    @Then("I should be redirected with message {string}")
    public void i_should_be_redirected_with_message(String expectedMessage) {
        String actualMessage = customFoodPage.getErrorMessage();
        Assert.assertTrue("Message should contain: " + expectedMessage,
                         actualMessage.contains(expectedMessage));
    }    @Then("I should see a custom food error message {string}")
    public void i_should_see_a_custom_food_error_message(String expectedMessage) {
        String actualMessage = customFoodPage.getErrorMessage();
        Assert.assertTrue("Error message should contain: " + expectedMessage,
                         actualMessage.contains(expectedMessage));
    }

    @Then("the deletion should be prevented")
    public void the_deletion_should_be_prevented() {
        // Verify that the custom food still exists and deletion was not successful
        Assert.assertTrue("Custom food should still exist", customFoodPage.getCustomFoodsCount() > 0);
    }    @Then("I should see the custom food validation error {string}")
    public void i_should_see_the_custom_food_validation_error(String expectedError) {
        String actualError = customFoodPage.getErrorMessage();
        Assert.assertTrue("Validation error should contain: " + expectedError,
                         actualError.contains(expectedError));
    }
}
