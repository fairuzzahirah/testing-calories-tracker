package stepdefinition;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pages.CustomFoodPage;

public class CustomFoodStep {
    WebDriver driver = stepdefinition.SharedDriver.getDriver();
    CustomFoodPage customFoodPage = new CustomFoodPage(driver);

    @Given("User is on the custom food page")
    public void user_is_on_custom_food_page() {
        customFoodPage.goTo();
    }

    @When("User clicks the Add Custom Food button")
    public void user_clicks_add_custom_food() {
        customFoodPage.clickCustomFoodButton();
    }

    @When("User enters custom food name {string}")
    public void user_enters_custom_food_name(String foodName) {
        customFoodPage.enterFoodName(foodName);
    }

    @And("User enters custom food calories {string}")
    public void user_enters_custom_food_calories(String calories) {
        customFoodPage.enterCalories(calories);
    }

    @And("User enters custom food serving unit {string}")
    public void user_enters_custom_food_serving_unit(String unit) {
        customFoodPage.enterServingUnit(unit);
    }

    @And("User selects custom food category {string}")
    public void user_selects_custom_food_category(String category) {
        customFoodPage.selectCategory(category);
    }

    @And("User enters custom food notes {string}")
    public void user_enters_custom_food_notes(String notes) {
        customFoodPage.enterNotes(notes);
    }

    @And("User submits the custom food form")
    public void user_submits_custom_food_form() {
        customFoodPage.clickSubmit();
    }

    @Then("User should see custom food validation error")
    public void user_should_see_custom_food_validation_error() {
        assert customFoodPage.isValidationErrorShown();
    }

    @Then("User should see custom food success message")
    public void user_should_see_custom_food_success_message() {
        assert customFoodPage.isSuccessMessageShown();
    }
}