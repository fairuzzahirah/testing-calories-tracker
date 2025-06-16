package stepdefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.ProfilePage;

public class ProfileStep {
    WebDriver driver = SharedDriver.getDriver();
    ProfilePage profilePage = new ProfilePage(driver);

    @Given("User is on the homepage")
    public void user_is_on_homepage() {
        profilePage.goToDashboard();
    }

    @When("User clicks the dropdown menu on the header")
    public void user_clicks_dropdown_menu() {
        profilePage.clickDropdownMenu();
    }

    @And("User selects the Profile option")
    public void user_selects_profile_option() {
        profilePage.selectProfileOption();
    }

    @Then("User should be redirected to the profile page")
    public void user_should_be_redirected_to_profile_page() {
        Assertions.assertTrue(driver.getCurrentUrl().contains("/profile"));
    }

    @And("User should see the text Body Mass Index \\(BMI\\) on the profile page")
    public void user_should_see_bmi_text() {
        Assertions.assertTrue(profilePage.isBmiTextDisplayed());
    }

    @Given("User is on the profile page")
    public void user_on_profile_page() {
        profilePage.goTo();
    }

    @When("User updates the name to {string}")
    public void user_updates_name(String name) {
        profilePage.setName(name);
    }

    @And("User updates the age to {string}")
    public void user_updates_age(String age) {
        profilePage.setAge(age);
    }

    @And("User selects gender {string}")
    public void user_selects_gender(String gender) {
        profilePage.selectGender(gender);
    }

    @And("User enters height {string}")
    public void user_enters_height(String height) {
        profilePage.setHeight(height);
    }

    @And("User enters weight {string}")
    public void user_enters_weight(String weight) {
        profilePage.setWeight(weight);
    }

    @And("User selects goal {string}")
    public void user_selects_goal(String goal) {
        profilePage.selectGoal(goal);
    }

    @And("User selects activity level {string}")
    public void user_selects_activity_level(String activity) {
        profilePage.selectActivityLevel(activity);
    }

    @And("User submits the profile form")
    public void user_submits_profile_form() {
        profilePage.submitProfileForm();
    }

    @Then("Profile should be successfully updated")
    public void profile_should_be_updated() {
        Assertions.assertTrue(profilePage.isProfileUpdated());
    }

    @Then("An error message {string} should be displayed")
    public void error_message_should_be_displayed(String expectedMessage) {
        Assertions.assertTrue(profilePage.isHeightErrorDisplayed(), "Expected error message not displayed");
    }

    @When("User enters current password {string}")
    public void user_enters_current_password(String current) {
        profilePage.enterCurrentPassword(current);
    }

    @And("User enters new password {string}")
    public void user_enters_new_password(String password) {
        profilePage.enterNewPassword(password);
    }

    @And("User confirms new password {string}")
    public void user_confirms_new_password(String confirmation) {
        profilePage.confirmNewPassword(confirmation);
    }

    @And("User submits the password form")
    public void user_submits_password_form() {
        System.out.println("Submitting password form...");
        profilePage.submitPasswordForm();
    }
}