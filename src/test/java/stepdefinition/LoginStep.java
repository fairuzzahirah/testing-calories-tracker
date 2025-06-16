package stepdefinition;

import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import pages.RegisterPage;

import java.time.Duration;

public class LoginStep {
    WebDriver driver = stepdefinition.SharedDriver.getDriver();
    LoginPage loginPage = new LoginPage(driver);


    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        loginPage.goTo();
    }

    @When("the user logs in with email {string} and password {string}")
    public void the_user_logs_in_with_email_and_password(String email, String password) {
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
    }
    @And("User submit the form")
    public void user_submits_the_form() {

        loginPage.submit();
    }
    @Then("User redirected to dashboard")
    public void user_should_be_redirected_to_dashboard() {
        assert loginPage.isAtDashboard();
        loginPage.clickAddFoodEntry(); // pindah ke halaman food entry
    }
    @Then("an error message {string} should be shown")
    public void an_error_message_should_be_shown(String expectedMessage) {
        String actualMessage = loginPage.getLoginErrorMessage();
        Assertions.assertEquals(expectedMessage, actualMessage, "The provided credentials are incorrect.");
    }
    @Given("User is logged in")
    public void user_is_logged_in() {
        driver = SharedDriver.getDriver();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.goTo();
        String email = "demo@example.com";
        String password = "password123";
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.submit();
        Assertions.assertTrue(loginPage.isAtDashboard());
        // Wait for dashboard
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/dashboard"));

        Assertions.assertTrue(loginPage.isAtDashboard(), "The provided credentials are incorrect.");
    }



}
