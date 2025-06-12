package stepdefinition;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStep {
    WebDriver driver;
    WebDriverWait wait;

    @Given("User is on the login page")
    public void user_is_on_login_page() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("http://localhost:8080/login");
        
        // Wait for page to load
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
    }

    @When("User enters valid email")
    public void user_enters_valid_email() {
        driver.findElement(By.id("email")).sendKeys("demo@example.com");
    }

    @When("User enters valid password")
    public void user_enters_valid_password() {
        driver.findElement(By.id("password")).sendKeys("password123");
    }

    @When("User enters invalid email")
    public void user_enters_invalid_email() {
        driver.findElement(By.id("email")).sendKeys("invalid@example.com");
    }

    @When("User enters invalid password")
    public void user_enters_invalid_password() {
        driver.findElement(By.id("password")).sendKeys("wrongpassword");
    }

    @When("User enters demo email")
    public void user_enters_demo_email() {
        driver.findElement(By.id("email")).sendKeys("demo@example.com");
    }

    @When("User enters demo password")
    public void user_enters_demo_password() {
        driver.findElement(By.id("password")).sendKeys("password123");
    }

    @When("User clicks login button")
    public void user_clicks_login_button() {
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        
        // Wait a bit for the form submission
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Then("User should be redirected to dashboard")
    public void user_should_be_redirected_to_dashboard() {
        // Wait for redirect to complete
        wait.until(ExpectedConditions.urlContains("/dashboard"));
        
        boolean isDashboardPage = driver.getCurrentUrl().contains("/dashboard");
        assert isDashboardPage : "User was not redirected to dashboard. Current URL: " + driver.getCurrentUrl();
    }

    @Then("User should see welcome message")
    public void user_should_see_welcome_message() {
        // Look for dashboard elements that indicate successful login
        boolean hasWelcomeContent = driver.getPageSource().toLowerCase().contains("dashboard") ||
                                  driver.getPageSource().toLowerCase().contains("welcome") ||
                                  driver.getPageSource().toLowerCase().contains("calories");
        
        assert hasWelcomeContent : "Welcome message or dashboard content not found";
        driver.quit();
    }

    @Then("User should see error message")
    public void user_should_see_error_message() {
        // Wait for error message to appear
        try {
            WebElement errorElement = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[contains(text(), 'credentials') or contains(text(), 'error') or contains(text(), 'invalid')]")
            ));
            assert errorElement.isDisplayed() : "Error message not displayed";
        } catch (Exception e) {
            // Alternative check for error in page source
            boolean hasErrorMessage = driver.getPageSource().toLowerCase().contains("credentials") ||
                                    driver.getPageSource().toLowerCase().contains("invalid") ||
                                    driver.getPageSource().toLowerCase().contains("error");
            assert hasErrorMessage : "No error message found on page";
        }
    }

    @Then("User should remain on login page")
    public void user_should_remain_on_login_page() {
        boolean isLoginPage = driver.getCurrentUrl().contains("/login") ||
                            driver.getPageSource().toLowerCase().contains("log in") ||
                            driver.getPageSource().toLowerCase().contains("sign in");
        
        assert isLoginPage : "User was not kept on login page. Current URL: " + driver.getCurrentUrl();
        driver.quit();
    }

    @Then("User should see dashboard content")
    public void user_should_see_dashboard_content() {
        // Wait for dashboard content to load
        try {
            wait.until(ExpectedConditions.or(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Today')]")),
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Calories')]")),
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Food')]"))
            ));
        } catch (Exception e) {
            // Fallback check
            boolean hasDashboardContent = driver.getPageSource().toLowerCase().contains("today") ||
                                        driver.getPageSource().toLowerCase().contains("calories") ||
                                        driver.getPageSource().toLowerCase().contains("food entries");
            assert hasDashboardContent : "Dashboard content not found";
        }
        
        driver.quit();
    }
}
