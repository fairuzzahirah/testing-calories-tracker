package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class RegisterPage {
    WebDriver driver;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void goTo() {
        driver.get("http://localhost:8080/register");
    }

    public void enterUsername(String username) {
        driver.findElement(By.id("name")).sendKeys(username);
    }

    public void enterEmail(String email) {
        if (email.equalsIgnoreCase("AUTO")) {
            email = "user" + System.currentTimeMillis() + "@example.com";
        }
        driver.findElement(By.id("email")).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(By.id("password")).sendKeys(password);
    }

    public void enterPasswordConfirmation(String passwordConfirm) {
        driver.findElement(By.id("password_confirmation")).sendKeys(passwordConfirm);
    }

    public void enterAge(String age) {
        driver.findElement(By.id("age")).sendKeys(age);
    }

    public void enterHeight(String height) {
        driver.findElement(By.id("height_cm")).sendKeys(height);
    }

    public void enterWeight(String weight) {
        driver.findElement(By.id("weight_kg")).sendKeys(weight);
    }

    public void selectGender(String genderValue) {
        Select gender = new Select(driver.findElement(By.id("gender")));
        gender.selectByValue(genderValue);
    }

    public void selectGoal(String goalValue) {
        Select goal = new Select(driver.findElement(By.id("goal")));
        goal.selectByValue(goalValue);
    }

    public void selectActivityLevel(String activityLevelValue) {
        Select activity = new Select(driver.findElement(By.id("activity_level")));
        activity.selectByValue(activityLevelValue);
    }

    public void submitForm() {
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    public boolean isPasswordTooShortErrorShown() {
        String pageSource = driver.getPageSource().toLowerCase();
        return pageSource.contains("password must be at least 8 characters") ||
                pageSource.contains("password minimal 8 karakter");
    }

    public boolean isAtDashboard() {
        return driver.getCurrentUrl().contains("/dashboard");
    }

    public void clickAddFoodEntry() {
        driver.findElement(By.linkText("Add Food Entry")).click();
    }
}
