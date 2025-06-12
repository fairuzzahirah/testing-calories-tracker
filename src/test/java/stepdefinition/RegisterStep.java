package stepdefinition;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.RegisterPage;

public class RegisterStep {
    WebDriver driver;
    RegisterPage registerPage;

    @Given("User is on the form page")
    public void user_is_on_form_page() {
        driver = new ChromeDriver();
        registerPage = new RegisterPage(driver);
        registerPage.goTo();
    }

    @When("User enter username {string}")
    public void i_enter_username(String username) {
        registerPage.enterUsername(username);
    }

    @And("User enter email {string}")
    public void i_enter_email(String email) {
        registerPage.enterEmail(email);
    }

    @And("User enter password {string}")
    public void i_enter_password(String password) {
        registerPage.enterPassword(password);
    }

    @And("User enter password confirm {string}")
    public void i_enter_passwordconfirm(String passwordConfirm) {
        registerPage.enterPasswordConfirmation(passwordConfirm);
    }

    @And("User enter age {string}")
    public void i_enter_age(String age) {
        registerPage.enterAge(age);
    }

    @And("User enter height {string}")
    public void i_enter_height(String height) {
        registerPage.enterHeight(height);
    }

    @And("User enter weight {string}")
    public void i_enter_weight(String weight) {
        registerPage.enterWeight(weight);
    }

    @And("User select gender {string}")
    public void i_select_gender(String genderValue) {
        registerPage.selectGender(genderValue);
    }

    @And("User select goal {string}")
    public void i_select_goal(String goalValue) {
        registerPage.selectGoal(goalValue);
    }

    @And("User select activity level {string}")
    public void i_select_activityLevel(String activityLevelValue) {
        registerPage.selectActivityLevel(activityLevelValue);
    }

    @And("User submits the form")
    public void user_submits_the_form() {
        registerPage.submitForm();
    }

    @Then("User should see password too short error")
    public void user_should_see_password_too_short_error() {
        assert registerPage.isPasswordTooShortErrorShown();
        driver.quit();
    }

    @Then("User should be redirected to dashboard")
    public void user_should_be_redirected_to_dashboard() {
        assert registerPage.isAtDashboard();
        registerPage.clickAddFoodEntry();
        driver.quit();
    }
}
