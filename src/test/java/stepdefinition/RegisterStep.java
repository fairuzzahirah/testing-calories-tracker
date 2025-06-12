package stepdefinition;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegisterStep {
    WebDriver driver;

    @Given("User is on the form page")
    public void user_is_on_form_page() {
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/register");
    }

    @When("User enter username")
    public void i_enter_username() {
        driver.findElement(By.id("name")).sendKeys("Frederick");
    }

    @And("User enter email")
    public void i_enter_email() {
        driver.findElement(By.id("email")).sendKeys("fredec@gmail.com");
    }
    @And("User enter password")
    public void i_enter_password() {
            driver.findElement(By.id("password")).sendKeys("password123");
    }
    @And("User enter password confirm")
    public void i_enter_passwordconfirm() {
        driver.findElement(By.id("password_confirmation")).sendKeys("password123");
    }
    @And("User enter age")
    public void i_enter_age() {
        driver.findElement(By.id("age")).sendKeys("22");
    }

    @And("User enter height")
    public void i_enter_height() {
        driver.findElement(By.id("height_cm")).sendKeys("175");
    }
    @And("User enter weight")
    public void i_enter_weight() {
        driver.findElement(By.id("weight_kg")).sendKeys("65");
    }
    @And("User select gender")
    public void i_select_gender() {
        Select gender = new Select(driver.findElement(By.id("gender")));
        gender.selectByValue("female");
    }
    @And("User select goal")
    public void i_select_goal() {
        Select goal = new Select(driver.findElement(By.id("goal")));
        goal.selectByValue("maintain");
    }
    @And("User select activity level")
    public void i_select_activityLevel() {
        Select activityLevel = new Select(driver.findElement(By.id("activity_level")));
        activityLevel.selectByValue("light");
    }    @And("User submits the form")
    public void user_submits_the_form() {
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    @Then("User should be redirected to login page")
    public void user_should_be_redirected_to_login_page() {
        boolean isLoginPage = driver.getCurrentUrl().contains("/login") ||
                driver.getTitle().toLowerCase().contains("login") ||
                driver.getPageSource().contains("Log in");

        assert isLoginPage;
        driver.quit();
    }

}
