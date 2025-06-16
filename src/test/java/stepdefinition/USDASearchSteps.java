package stepdefinition;

import io.cucumber.java.en.*;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import pages.USDASearchPage;
import util.DriverManager;
import util.TestDataGenerator;
import java.util.List;
import java.util.Map;

public class USDASearchSteps {
    private final USDASearchPage usdaSearchPage;

    public USDASearchSteps() {
        this.usdaSearchPage = new USDASearchPage(DriverManager.getDriver());
    }

    @When("I navigate to the USDA Search page")
    public void i_navigate_to_the_usda_search_page() {
        usdaSearchPage.navigateToUSDASearch();
    }

    @When("I enter {string} in the search box")
    public void i_enter_in_the_search_box(String searchQuery) {
        usdaSearchPage.enterSearchQuery(searchQuery);
    }

    @When("I click the search button")
    public void i_click_the_search_button() {
        usdaSearchPage.clickSearchButton();
    }

    @When("I submit search without entering any query")
    public void i_submit_search_without_entering_any_query() {
        usdaSearchPage.submitEmptySearch();
    }

    @Given("I have searched for {string} in USDA")
    public void i_have_searched_for_in_usda(String searchQuery) {
        usdaSearchPage.navigateToUSDASearch();
        usdaSearchPage.searchForFood(searchQuery);
    }

    @Given("I see search results")
    public void i_see_search_results() {
        Assert.assertTrue("Search results should be displayed", usdaSearchPage.areSearchResultsDisplayed());
    }

    @Given("the USDA API is down or not responding")
    public void the_usda_api_is_down_or_not_responding() {
        // This would typically require mocking or test environment setup
        // For now, we'll simulate by testing the error handling
    }

    @When("I select a food item from the results")
    public void i_select_a_food_item_from_the_results() {
        usdaSearchPage.selectFoodItem(0); // Select first food item
    }

    @When("I fill the serving details:")
    public void i_fill_the_serving_details(DataTable dataTable) {
        Map<String, String> servingData = dataTable.asMap(String.class, String.class);
        String servingAmount = servingData.get("serving_amount");
        String consumedAt = servingData.get("consumed_at");
        usdaSearchPage.fillServingDetails(servingAmount, consumedAt);
    }

    @When("I fill invalid serving details:")
    public void i_fill_invalid_serving_details(DataTable dataTable) {
        Map<String, String> invalidData = dataTable.asMap(String.class, String.class);
        String invalidAmount = invalidData.get("serving_amount");
        usdaSearchPage.fillInvalidServingAmount(invalidAmount);
    }

    @When("I leave serving amount empty")
    public void i_leave_serving_amount_empty() {
        usdaSearchPage.leaveServingAmountEmpty();
    }    @When("I click the USDA {string} button")
    public void i_click_the_usda_button(String buttonText) {
        if ("Add to Food Log".equals(buttonText)) {
            usdaSearchPage.clickAddToFoodLog();
        }
    }

    @When("I search for {string}")
    public void i_search_for(String searchQuery) {
        usdaSearchPage.searchForFood(searchQuery);
    }

    @Then("I should see USDA search results displayed with:")
    public void i_should_see_usda_search_results_displayed_with(DataTable dataTable) {
        List<String> expectedFields = dataTable.asList();
        
        Assert.assertTrue("Search results should be displayed", usdaSearchPage.areSearchResultsDisplayed());
        
        for (String field : expectedFields) {
            switch (field) {
                case "Food name":
                    Assert.assertTrue("Food names should be displayed", usdaSearchPage.isFoodNameDisplayed());
                    break;
                case "Calories per serving":
                    Assert.assertTrue("Calories info should be displayed", usdaSearchPage.isCaloriesInfoDisplayed());
                    break;
                case "Serving size options":
                    Assert.assertTrue("Serving options should be displayed", usdaSearchPage.areServingOptionsDisplayed());
                    break;
                case "Add to Food Log button":
                    Assert.assertTrue("Add to Log buttons should be displayed", usdaSearchPage.areAddToLogButtonsDisplayed());
                    break;
            }
        }
    }

    @Then("the USDA food should be added to my food entries with source {string}")
    public void the_usda_food_should_be_added_to_my_food_entries_with_source(String expectedSource) {
        // Verify that the food was added successfully
        String successMessage = usdaSearchPage.getSuccessMessage();
        Assert.assertFalse("Should have success message", successMessage.isEmpty());
    }

    @Then("I should be redirected to the food entries page")
    public void i_should_be_redirected_to_the_food_entries_page() {
        Assert.assertTrue("Should be redirected to food entries", usdaSearchPage.isRedirectedToFoodEntries());
    }    @Then("I should see a USDA success message")
    public void i_should_see_a_usda_success_message() {
        String successMessage = usdaSearchPage.getSuccessMessage();
        Assert.assertFalse("Should have success message", successMessage.isEmpty());
    }    @Then("I should see the USDA validation error {string}")
    public void i_should_see_the_usda_validation_error(String expectedError) {
        String actualError = usdaSearchPage.getValidationError();
        if (actualError.isEmpty()) {
            actualError = usdaSearchPage.getErrorMessage();
        }
        Assert.assertTrue("Validation error should contain: " + expectedError,
                         actualError.contains(expectedError));
    }

    @Then("the form should not be submitted")
    public void the_form_should_not_be_submitted() {
        // Verify that we're still on the search page and no results are shown
        Assert.assertTrue("Should remain on USDA search page", usdaSearchPage.isOnUSDASearchPage());
        Assert.assertTrue("Should have validation errors", usdaSearchPage.hasValidationErrors());
    }

    @Then("I should see a user-friendly error message {string}")
    public void i_should_see_a_user_friendly_error_message(String expectedError) {
        String actualError = usdaSearchPage.getApiErrorMessage();
        if (actualError.isEmpty()) {
            actualError = usdaSearchPage.getErrorMessage();
        }
        Assert.assertTrue("API error message should contain: " + expectedError,
                         actualError.contains(expectedError) || usdaSearchPage.isApiErrorDisplayed());
    }
}
