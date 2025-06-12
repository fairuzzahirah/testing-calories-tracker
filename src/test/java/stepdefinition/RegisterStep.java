package stepdefinition;

import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class RegisterStep {
    WebDriver driver;

    @Given("User is on the form page")
    public void user_is_on_form_page() {
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/register");
    }

    @When("User enter username {string}")
    public void i_enter_username(String username) {
        driver.findElement(By.id("name")).sendKeys(username);
    }

    @And("User enter email {string}")
    public void i_enter_email(String email) {
        if (email.equalsIgnoreCase("AUTO")) {
            email = "user" + System.currentTimeMillis() + "@example.com";
        }
        driver.findElement(By.id("email")).sendKeys(email);
    }

    @And("User enter password {string}")
    public void i_enter_password(String password) {
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @And("User enter password confirm {string}")
    public void i_enter_passwordconfirm(String passwordConfirm) {
        driver.findElement(By.id("password_confirmation")).sendKeys(passwordConfirm);
    }

    @And("User enter age {string}")
    public void i_enter_age(String age) {
        driver.findElement(By.id("age")).sendKeys(age);
    }

    @And("User enter height {string}")
    public void i_enter_height(String height) {
        driver.findElement(By.id("height_cm")).sendKeys(height);
    }

    @And("User enter weight {string}")
    public void i_enter_weight(String weight) {
        driver.findElement(By.id("weight_kg")).sendKeys(weight);
    }

    @And("User select gender {string}")
    public void i_select_gender(String genderValue) {
        Select gender = new Select(driver.findElement(By.id("gender")));
        gender.selectByValue(genderValue);
    }

    @And("User select goal {string}")
    public void i_select_goal(String goalValue) {
        Select goal = new Select(driver.findElement(By.id("goal")));
        goal.selectByValue(goalValue);
    }

    @And("User select activity level {string}")
    public void i_select_activityLevel(String activityLevelValue) {
        Select activityLevel = new Select(driver.findElement(By.id("activity_level")));
        activityLevel.selectByValue(activityLevelValue);
    }

    @And("User submits the form")
    public void user_submits_the_form() {
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    @Then("User should see password too short error")
    public void user_should_see_password_too_short_error() {
        String pageSource = driver.getPageSource().toLowerCase();
        assert pageSource.contains("Password must be at least 8 characters") ||
                pageSource.contains("password minimal 8 karakter");
        driver.quit();
    };
    @Then("User should be redirected to dashboard")
    public void user_should_be_redirected_to_dashboard() {
        String currentUrl = driver.getCurrentUrl();
        assert currentUrl.contains("/dashboard");
        driver.findElement(By.linkText("Add Food Entry")).click();
    }

}
