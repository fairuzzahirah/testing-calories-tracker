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

    @When("User fills out the form")
    public void user_fills_out_the_form() {
        driver.findElement(By.id("name")).sendKeys("Frederick");
        driver.findElement(By.id("email")).sendKeys("frederic@gmail.com");
        driver.findElement(By.id("password")).sendKeys("password123");
        driver.findElement(By.id("password_confirmation")).sendKeys("password123");
        driver.findElement(By.id("age")).sendKeys("22");
        driver.findElement(By.id("height_cm")).sendKeys("175");
        driver.findElement(By.id("weight_kg")).sendKeys("65");
        Select gender = new Select(driver.findElement(By.id("gender")));
        gender.selectByValue("female");

        Select goal = new Select(driver.findElement(By.id("goal")));
        goal.selectByValue("maintain");

        Select activityLevel = new Select(driver.findElement(By.id("activity_level")));
        activityLevel.selectByValue("light");
    }

    @And("User submits the form")
    public void user_submits_the_form() {
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    @Then("User should be redirected to dashboard")
    public void user_should_be_redirected_to_login_page() {
        boolean isLoginPage = driver.getCurrentUrl().contains("/dashboard") ||
                driver.getTitle().toLowerCase().contains("login") ||
                driver.getPageSource().contains("Log in");

        assert isLoginPage;
        driver.quit();
    }

}
