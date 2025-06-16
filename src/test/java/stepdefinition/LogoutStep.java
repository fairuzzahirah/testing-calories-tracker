package stepdefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import pages.LogoutPage;

public class LogoutStep {
    WebDriver driver = SharedDriver.getDriver();
    LogoutPage logoutPage = new LogoutPage(driver);

    @Given("User is on the homepage for logout")
    public void userIsOnHomepage() {
        logoutPage.goToDashboard();
    }

    @When("User clicks the dropdown menu on the header for logout")
    public void userClicksDropdown() throws InterruptedException  {
        logoutPage.clickDropdown();
        Thread.sleep(1000);
    }

    @And("User clicks the logout option")
    public void userClicksLogoutOption() throws InterruptedException  {
        logoutPage.clickLogout();
        Thread.sleep(1000);
    }

    @Then("User should be redirected to the login page")
    public void userShouldSeeLoginPage() {
        Assertions.assertTrue(driver.getCurrentUrl().contains("/login"),
                "Expected to be on login page, but was: " + driver.getCurrentUrl());
    }
}