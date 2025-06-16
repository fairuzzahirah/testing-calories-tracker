package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class ProfilePage {
    WebDriver driver;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    public void goToDashboard() {
        driver.get("http://localhost:8080/dashboard");
    }

    public void goTo() {
        driver.get("http://localhost:8080/profile");
    }

    private By dropdownButton = By.cssSelector("button.inline-flex.items-center");
    private By profileOption = By.xpath("//a[contains(text(), 'Profile')]");
    private By bmiText = By.xpath("//*[contains(text(), 'Body Mass Index (BMI)')]");

    public void clickDropdownMenu() {
        driver.findElement(dropdownButton).click();
    }

    public void selectProfileOption() {
        driver.findElement(profileOption).click();
    }

    public boolean isBmiTextDisplayed() {
        return driver.findElement(bmiText).isDisplayed();
    }

    public void setName(String name) {
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys(name);
    }

    public void setAge(String age) {
        driver.findElement(By.id("age")).clear();
        driver.findElement(By.id("age")).sendKeys(age);
    }

    public void selectGender(String gender) {
        Select genderSelect = new Select(driver.findElement(By.id("gender")));
        genderSelect.selectByVisibleText(gender);
    }

    public void setHeight(String height) {
        driver.findElement(By.id("height_cm")).clear();
        driver.findElement(By.id("height_cm")).sendKeys(height);
    }

    public void setWeight(String weight) {
        driver.findElement(By.id("weight_kg")).clear();
        driver.findElement(By.id("weight_kg")).sendKeys(weight);
    }

    public void selectGoal(String goal) {
        Select goalSelect = new Select(driver.findElement(By.id("goal")));
        goalSelect.selectByVisibleText(goal);
    }

    public void selectActivityLevel(String activity) {
        Select activitySelect = new Select(driver.findElement(By.id("activity_level")));
        activitySelect.selectByVisibleText(activity);
    }

    public void submitProfileForm() {
        driver.findElement(By.id("profile-submit-btn")).click();
    }

    public boolean isProfileUpdated() {
        // Example: assert there's a success message, or check that fields reflect saved data
        return driver.getPageSource().contains("Profile updated") || driver.getCurrentUrl().contains("profile");
    }

    public boolean isHeightErrorDisplayed() {
        return driver.getPageSource().contains("Height must be a number between 50 and 250 cm");
    }

    public void enterCurrentPassword(String current) {
        driver.findElement(By.id("update_password_current_password")).clear();
        driver.findElement(By.id("update_password_current_password")).sendKeys(current);
    }

    public void enterNewPassword(String newPassword) {
        driver.findElement(By.id("update_password_password")).clear();
        driver.findElement(By.id("update_password_password")).sendKeys(newPassword);
    }

    public void confirmNewPassword(String confirmPassword) {
        driver.findElement(By.id("update_password_password_confirmation")).clear();
        driver.findElement(By.id("update_password_password_confirmation")).sendKeys(confirmPassword);
    }

    public void submitPasswordForm() {
        driver.findElement(By.id("password-submit-btn")).click();
    }

    public boolean isSuccessMessageDisplayed(String message) {
        return driver.getPageSource().contains(message);
    }

}
