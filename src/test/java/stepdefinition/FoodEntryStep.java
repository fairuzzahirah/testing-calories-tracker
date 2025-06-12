package stepdefinition;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.FoodEntryPage;

public class FoodEntryStep {
    WebDriver driver = util.SharedDriver.getDriver();
    FoodEntryPage foodPage = new FoodEntryPage(driver);

    @Given("User is on the food entry page")
    public void user_is_on_food_entry_page() {
        foodPage.goTo();
    }

    @When("User clicks the Add Food Entry button")
    public void user_clicks_add_food_entry() {
        foodPage.clickAddFoodEntryButton();
    }

    @And("User enters food name {string}")
    public void user_enters_food_name(String foodName) {
        foodPage.enterFoodName(foodName);
    }

    @And("User enters calories per serving {string}")
    public void user_enters_calories(String calories) {
        foodPage.enterCaloriesPerServing(calories);
    }

    @And("User enters serving amount {string}")
    public void user_enters_serving_amount(String amount) {
        foodPage.enterServingAmount(amount);
    }

    @And("User enters serving unit {string}")
    public void user_enters_serving_unit(String unit) {
        foodPage.enterServingUnit(unit);
    }

    @And("User submits the food entry form")
    public void user_submits_food_entry_form() {
        foodPage.clickSubmit();
    }

    @Then("User should see success message")
    public void user_should_see_success_message() {
        assert foodPage.isSuccessMessageShown();
    }

    @Then("User should see validation error")
    public void user_should_see_validation_error() {
        assert foodPage.isValidationErrorShown();
    }
}
