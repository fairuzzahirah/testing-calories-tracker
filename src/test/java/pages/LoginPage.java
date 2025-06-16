package pages;


import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    public void goTo() {
        driver.get("http://localhost:8080/login");
    }

    public void enterEmail(String email) {
        driver.findElement(By.id("email")).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(By.id("password")).sendKeys(password);
    }
    public boolean isAtDashboard() {
        return driver.getCurrentUrl().contains("/dashboard");
    }
    public void clickAddFoodEntry() {
        driver.findElement(By.linkText("Add Food Entry")).click();
    }
    public String getLoginErrorMessage() {
        return driver.findElement(By.xpath("//ul[contains(@class,'text-red-600')]/li")).getText();
    }
    public void submit() {
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }


}
