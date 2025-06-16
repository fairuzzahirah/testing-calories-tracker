package pages;

import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {
    private WebDriver driver;
    private WebDriverWait wait;    // Page URL
    private static final String REGISTER_URL = "http://localhost:8080/register";

    // Web Elements
    @FindBy(id = "name")
    private WebElement nameField;

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "password_confirmation")
    private WebElement passwordConfirmationField;

    @FindBy(id = "age")
    private WebElement ageField;

    @FindBy(id = "gender")
    private WebElement genderSelect;    @FindBy(id = "height_cm")
    private WebElement heightField;

    @FindBy(id = "weight_kg")
    private WebElement weightField;

    @FindBy(id = "goal")
    private WebElement goalSelect;

    @FindBy(id = "activity_level")
    private WebElement activityLevelSelect;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement registerButton;

    @FindBy(css = ".alert-success")
    private WebElement successMessage;    @FindBy(css = ".text-red-600, .alert-danger, .invalid-feedback")
    private WebElement errorMessage;

    // Constructor
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Actions
    public void navigateToRegisterPage() {
        driver.get(REGISTER_URL);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("name")));
    }

    public void fillRegistrationForm(Map<String, String> userData) {
        wait.until(ExpectedConditions.elementToBeClickable(nameField));
        
        nameField.clear();
        nameField.sendKeys(userData.get("name"));
        
        emailField.clear();
        emailField.sendKeys(userData.get("email"));
        
        passwordField.clear();
        passwordField.sendKeys(userData.get("password"));
        
        passwordConfirmationField.clear();
        passwordConfirmationField.sendKeys(userData.get("password_confirmation"));
        
        ageField.clear();
        ageField.sendKeys(userData.get("age"));
        
        Select genderDropdown = new Select(genderSelect);
        genderDropdown.selectByValue(userData.get("gender"));
        
        heightField.clear();
        heightField.sendKeys(userData.get("height"));
        
        weightField.clear();
        weightField.sendKeys(userData.get("weight"));
        
        Select goalDropdown = new Select(goalSelect);
        goalDropdown.selectByValue(userData.get("goal"));
        
        Select activityDropdown = new Select(activityLevelSelect);
        activityDropdown.selectByValue(userData.get("activity_level"));
    }

    public void fillRegistrationFormWithExistingEmail(String existingEmail) {
        wait.until(ExpectedConditions.elementToBeClickable(nameField));
        
        nameField.clear();
        nameField.sendKeys("Jane Doe");
        
        emailField.clear();
        emailField.sendKeys(existingEmail);
        
        passwordField.clear();
        passwordField.sendKeys("password123");
        
        passwordConfirmationField.clear();
        passwordConfirmationField.sendKeys("password123");
        
        ageField.clear();
        ageField.sendKeys("30");
        
        Select genderDropdown = new Select(genderSelect);
        genderDropdown.selectByValue("female");
        
        heightField.clear();
        heightField.sendKeys("165");
        
        weightField.clear();
        weightField.sendKeys("60");
        
        Select goalDropdown = new Select(goalSelect);
        goalDropdown.selectByValue("maintain");
        
        Select activityDropdown = new Select(activityLevelSelect);
        activityDropdown.selectByValue("light");
    }

    public void fillRegistrationFormWithMismatchedPassword(String password, String confirmation) {
        wait.until(ExpectedConditions.elementToBeClickable(nameField));
        
        nameField.clear();
        nameField.sendKeys("Test User");
        
        emailField.clear();
        emailField.sendKeys("test@example.com");
        
        passwordField.clear();
        passwordField.sendKeys(password);
        
        passwordConfirmationField.clear();
        passwordConfirmationField.sendKeys(confirmation);
        
        ageField.clear();
        ageField.sendKeys("25");
        
        Select genderDropdown = new Select(genderSelect);
        genderDropdown.selectByValue("male");
        
        heightField.clear();
        heightField.sendKeys("180");
        
        weightField.clear();
        weightField.sendKeys("75");
        
        Select goalDropdown = new Select(goalSelect);
        goalDropdown.selectByValue("gain");
        
        Select activityDropdown = new Select(activityLevelSelect);
        activityDropdown.selectByValue("active");
    }

    public void clickRegisterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(registerButton));
        registerButton.click();
    }

    // Verifications
    public boolean isOnRegisterPage() {
        return driver.getCurrentUrl().contains("/register");
    }

    public String getSuccessMessage() {
        try {
            wait.until(ExpectedConditions.visibilityOf(successMessage));
            return successMessage.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public String getErrorMessage() {
        try {
            wait.until(ExpectedConditions.visibilityOf(errorMessage));
            return errorMessage.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean hasErrorMessage() {
        try {
            return errorMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isRedirectedToDashboard() {
        wait.until(ExpectedConditions.urlContains("/dashboard"));
        return driver.getCurrentUrl().contains("/dashboard");
    }
}
