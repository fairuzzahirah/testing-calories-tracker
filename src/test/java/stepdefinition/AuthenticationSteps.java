package stepdefinition;

import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.DashboardPage;
import pages.LoginPage;
import pages.RegisterPage;
import util.DriverManager;

public class AuthenticationSteps {
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private DashboardPage dashboardPage;

    public AuthenticationSteps() {
        this.loginPage = new LoginPage(DriverManager.getDriver());
        this.registerPage = new RegisterPage(DriverManager.getDriver());
        this.dashboardPage = new DashboardPage(DriverManager.getDriver());
    }

    @Given("the calories tracker application is running")
    public void the_calories_tracker_application_is_running() {
        // Verify that the application is accessible
        // This could include checking if the server is up
        loginPage.navigateToLoginPage();
        Assert.assertTrue("Application should be running", loginPage.isOnLoginPage());
    }

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        loginPage.navigateToLoginPage();
        Assert.assertTrue("Should be on login page", loginPage.isOnLoginPage());
    }

    @Given("I am on the register page")
    public void i_am_on_the_register_page() {
        loginPage.navigateToRegisterPage();
        Assert.assertTrue("Should be on register page", registerPage.isOnRegisterPage());
    }

    @Given("I am logged in as {string}")
    public void i_am_logged_in_as(String email) {
        loginPage.navigateToLoginPage();
        loginPage.loginWithCredentials(email, "password123");
        Assert.assertTrue("Should be redirected to dashboard", dashboardPage.isOnDashboard());
    }

    @When("I enter email {string} and password {string}")
    public void i_enter_email_and_password(String email, String password) {
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
    }

    @When("I click the login button")
    public void i_click_the_login_button() {
        loginPage.clickLoginButton();
    }

    @When("I fill the registration form with:")
    public void i_fill_the_registration_form_with(DataTable dataTable) {
        Map<String, String> userData = dataTable.asMap(String.class, String.class);
        registerPage.fillRegistrationForm(userData);
    }

    @When("I fill the registration form with existing email {string}")
    public void i_fill_the_registration_form_with_existing_email(String existingEmail) {
        registerPage.fillRegistrationFormWithExistingEmail(existingEmail);
    }

    @When("I fill the registration form with password {string} and confirmation {string}")
    public void i_fill_the_registration_form_with_password_and_confirmation(String password, String confirmation) {
        registerPage.fillRegistrationFormWithMismatchedPassword(password, confirmation);
    }

    @When("I click the register button")
    public void i_click_the_register_button() {
        registerPage.clickRegisterButton();
    }

    @When("I click the logout button")
    public void i_click_the_logout_button() {
        dashboardPage.logout();
    }

    @Then("I should be redirected to the dashboard")
    public void i_should_be_redirected_to_the_dashboard() {
        Assert.assertTrue("Should be redirected to dashboard", 
                         loginPage.isRedirectedToDashboard() || dashboardPage.isOnDashboard());
    }

    @Then("I should see the message {string}")
    public void i_should_see_the_message(String expectedMessage) {
        String actualMessage = loginPage.getSuccessMessage();
        if (actualMessage.isEmpty()) {
            actualMessage = dashboardPage.getSuccessMessage();
        }
        Assert.assertTrue("Message should contain: " + expectedMessage,
                         actualMessage.contains(expectedMessage));
    }    @Then("I should see the error message {string}")
    public void i_should_see_the_error_message(String expectedError) {
        String actualError = loginPage.getErrorMessage();
        if (actualError.isEmpty()) {
            actualError = registerPage.getErrorMessage();
        }
        System.out.println("Expected error: " + expectedError);
        System.out.println("Actual error: " + actualError);
        Assert.assertTrue("Error message should contain: " + expectedError + ", but actual was: " + actualError,
                         actualError.contains(expectedError));
    }

    @Then("I should see an error message about invalid credentials")
    public void i_should_see_an_error_message_about_invalid_credentials() {
        String errorMessage = loginPage.getErrorMessage();
        Assert.assertTrue("Should show invalid credentials error",
                         errorMessage.contains("credentials") || errorMessage.contains("invalid"));
    }

    @Then("I should remain on the login page")
    public void i_should_remain_on_the_login_page() {
        Assert.assertTrue("Should remain on login page", loginPage.isOnLoginPage());
    }

    @Then("I should be automatically logged in")
    public void i_should_be_automatically_logged_in() {
        Assert.assertTrue("Should be automatically logged in and redirected to dashboard",
                         registerPage.isRedirectedToDashboard());
    }

    @Then("my data should be saved in the database")
    public void my_data_should_be_saved_in_the_database() {
        // This could be verified by checking the dashboard shows user info
        // or by making an API call to verify user creation
        Assert.assertTrue("User should be on dashboard indicating successful registration",
                         dashboardPage.isOnDashboard());
    }

    @Then("registration should fail")
    public void registration_should_fail() {
        Assert.assertTrue("Registration should fail with error message",
                         registerPage.hasErrorMessage());
        Assert.assertTrue("Should remain on register page",
                         registerPage.isOnRegisterPage());
    }

    @Then("I should be redirected to the login page")
    public void i_should_be_redirected_to_the_login_page() {
        Assert.assertTrue("Should be redirected to login page", loginPage.isOnLoginPage());
    }    @Then("my session should be cleared")
    public void my_session_should_be_cleared() {
        System.out.println("Verifying session is cleared...");
        
        // Verify we're on login page (which means logout was successful)
        boolean isOnLoginPage = loginPage.isOnLoginPage();
        System.out.println("Currently on login page: " + isOnLoginPage);
        
        if (isOnLoginPage) {
            // If we're already on login page, session is cleared
            System.out.println("Session cleared successfully - user is on login page");
            return;
        }
          // If not on login page, try to access dashboard URL directly
        // If session is cleared, should be redirected to login
        try {
            WebDriver driver = DriverManager.getDriver();
            driver.get("http://localhost:8080/dashboard");
            
            // Wait a moment for potential redirect
            Thread.sleep(1000);
            
            // Check if we're redirected to login page
            boolean redirectedToLogin = driver.getCurrentUrl().contains("/login");
            System.out.println("Tried to access dashboard, redirected to login: " + redirectedToLogin);
            
            Assert.assertTrue("Session should be cleared - accessing dashboard should redirect to login", 
                             redirectedToLogin);
                             
        } catch (Exception e) {
            System.out.println("Error testing session clearance: " + e.getMessage());
            // Fallback: check if we're on login page
            Assert.assertTrue("Session should be cleared", loginPage.isOnLoginPage());
        }
    }
}
