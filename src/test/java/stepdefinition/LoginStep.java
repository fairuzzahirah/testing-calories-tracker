package stepdefinition;

import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginStep {
    WebDriver driver;

    @Given("User is on the login form")
    public void user_is_on_form_page() {
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/register");
    }
}
