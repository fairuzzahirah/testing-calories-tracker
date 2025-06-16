package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import java.time.Duration;
import java.util.Map;

public class ProfilePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Page URL
    private static final String PROFILE_URL = "http://localhost:8080/profile";

    // Web Elements - Profile Form
    @FindBy(id = "name")
    private WebElement nameField;

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "age")
    private WebElement ageField;

    @FindBy(id = "gender")
    private WebElement genderSelect;

    @FindBy(id = "height_cm")
    private WebElement heightField;

    @FindBy(id = "weight_kg")
    private WebElement weightField;

    @FindBy(id = "goal")
    private WebElement goalSelect;

    @FindBy(id = "activity_level")
    private WebElement activityLevelSelect;

    @FindBy(id = "current_password")
    private WebElement currentPasswordField;

    @FindBy(id = "profile-submit-btn")
    private WebElement saveProfileButton;    // Web Elements - Password Change Form
    @FindBy(id = "update_password_current_password")
    private WebElement currentPasswordChangeField;

    @FindBy(id = "update_password_password")
    private WebElement newPasswordField;

    @FindBy(id = "update_password_password_confirmation")
    private WebElement confirmPasswordField;

    @FindBy(xpath = "//button[contains(text(), 'Save')]")
    private WebElement savePasswordButton;

    // Web Elements - Delete Account Section
    @FindBy(id = "delete_password")
    private WebElement deletePasswordField;

    @FindBy(xpath = "//button[contains(text(), 'Delete Account')]")
    private WebElement deleteAccountButton;    // Messages and Alerts
    @FindBy(css = ".alert-success")
    private WebElement successMessage;

    @FindBy(css = ".alert-danger, .invalid-feedback")
    private WebElement errorMessage;

    @FindBy(css = ".validation-error")
    private WebElement validationError;

    @FindBy(id = "form-errors")
    private WebElement formErrors;

    @FindBy(css = ".text-red-600, .text-red-800")
    private WebElement fieldError;

    @FindBy(css = ".calorie-goal-display")
    private WebElement calorieGoalDisplay;

    // Constructor
    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Navigation Actions
    public void navigateToProfile() {
        driver.get(PROFILE_URL);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("name")));
    }

    // Profile Update Actions
    public void updateProfileInformation(Map<String, String> profileData) {
        wait.until(ExpectedConditions.elementToBeClickable(nameField));
        System.out.println("Updating profile with data: " + profileData);
        
        if (profileData.containsKey("name")) {
            nameField.clear();
            nameField.sendKeys(profileData.get("name"));
            System.out.println("Updated name to: " + profileData.get("name"));
        }
        
        if (profileData.containsKey("email")) {
            emailField.clear();
            emailField.sendKeys(profileData.get("email"));
            System.out.println("Updated email to: " + profileData.get("email"));
        }
        
        if (profileData.containsKey("age")) {
            ageField.clear();
            ageField.sendKeys(profileData.get("age"));
            System.out.println("Updated age to: " + profileData.get("age"));
        }
        
        if (profileData.containsKey("gender")) {
            Select genderDropdown = new Select(genderSelect);
            genderDropdown.selectByValue(profileData.get("gender"));
            System.out.println("Updated gender to: " + profileData.get("gender"));
        }
        
        if (profileData.containsKey("height") || profileData.containsKey("height_cm")) {
            heightField.clear();
            String heightValue = profileData.getOrDefault("height_cm", profileData.get("height"));
            heightField.sendKeys(heightValue);
            System.out.println("Updated height to: " + heightValue);
        }
        
        if (profileData.containsKey("weight") || profileData.containsKey("weight_kg")) {
            weightField.clear();
            String weightValue = profileData.getOrDefault("weight_kg", profileData.get("weight"));
            weightField.sendKeys(weightValue);
            System.out.println("Updated weight to: " + weightValue);
        }
        
        if (profileData.containsKey("goal")) {
            Select goalDropdown = new Select(goalSelect);
            goalDropdown.selectByValue(profileData.get("goal"));
            System.out.println("Updated goal to: " + profileData.get("goal"));
        }
        
        if (profileData.containsKey("activity_level")) {
            Select activityDropdown = new Select(activityLevelSelect);
            activityDropdown.selectByValue(profileData.get("activity_level"));
            System.out.println("Updated activity level to: " + profileData.get("activity_level"));
        }
        
        System.out.println("Profile update completed");
    }

    public void fillInvalidProfileData(Map<String, String> invalidData) {
        wait.until(ExpectedConditions.elementToBeClickable(ageField));
        
        if (invalidData.containsKey("age")) {
            ageField.clear();
            ageField.sendKeys(invalidData.get("age"));
        }
        
        if (invalidData.containsKey("height")) {
            heightField.clear();
            heightField.sendKeys(invalidData.get("height"));
        }
        
        if (invalidData.containsKey("weight")) {
            weightField.clear();
            weightField.sendKeys(invalidData.get("weight"));
        }
    }

    public void fillCurrentPasswordForProfile(String currentPassword) {
        wait.until(ExpectedConditions.elementToBeClickable(currentPasswordField));
        currentPasswordField.clear();
        currentPasswordField.sendKeys(currentPassword);
    }

    public void clickSaveProfile() {
        wait.until(ExpectedConditions.elementToBeClickable(saveProfileButton));
        System.out.println("Clicking save profile button");
        saveProfileButton.click();
        
        // Wait a moment after clicking to allow the form to process
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Password Change Actions
    public void changePassword(String currentPassword, String newPassword, String confirmPassword) {
        wait.until(ExpectedConditions.elementToBeClickable(currentPasswordChangeField));
        
        currentPasswordChangeField.clear();
        currentPasswordChangeField.sendKeys(currentPassword);
        
        newPasswordField.clear();
        newPasswordField.sendKeys(newPassword);
        
        confirmPasswordField.clear();
        confirmPasswordField.sendKeys(confirmPassword);
    }

    public void clickSavePassword() {
        wait.until(ExpectedConditions.elementToBeClickable(savePasswordButton));
        savePasswordButton.click();
    }

    // Delete Account Actions
    public void fillDeletePassword(String password) {
        wait.until(ExpectedConditions.elementToBeClickable(deletePasswordField));
        deletePasswordField.clear();
        deletePasswordField.sendKeys(password);
    }

    public void clickDeleteAccount() {
        wait.until(ExpectedConditions.elementToBeClickable(deleteAccountButton));
        deleteAccountButton.click();
    }

    public void confirmAccountDeletion() {
        // Handle confirmation dialog if present
        driver.switchTo().alert().accept();
    }

    public void updateEmailToExistingEmail(String existingEmail) {
        wait.until(ExpectedConditions.elementToBeClickable(emailField));
        emailField.clear();
        emailField.sendKeys(existingEmail);
    }

    // Verification Methods
    public boolean isOnProfilePage() {
        return driver.getCurrentUrl().contains("/profile");
    }

    public String getSuccessMessage() {
        try {
            System.out.println("Looking for success message...");
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".alert-success")));
            String message = successMessage.getText();
            System.out.println("Found success message: " + message);
            return message;
        } catch (Exception e) {
            System.out.println("No success message found, checking other selectors...");
            try {
                // Try alternative selectors for success message
                WebElement alertElement = driver.findElement(By.cssSelector(".alert, .flash-message, .success"));
                String message = alertElement.getText();
                System.out.println("Found alternative success message: " + message);
                return message;
            } catch (Exception ex) {
                System.out.println("No success message found with any selector: " + ex.getMessage());
                return "";
            }
        }
    }

    public String getErrorMessage() {
        try {
            wait.until(ExpectedConditions.visibilityOf(errorMessage));
            return errorMessage.getText();
        } catch (Exception e) {
            return "";
        }
    }    public String getValidationError() {
        try {
            // Try form errors first
            if (formErrors.isDisplayed() && !formErrors.getText().trim().isEmpty()) {
                return formErrors.getText();
            }
        } catch (Exception e) {
            // Ignore and try next
        }
        
        try {
            // Try field errors
            if (fieldError.isDisplayed() && !fieldError.getText().trim().isEmpty()) {
                return fieldError.getText();
            }
        } catch (Exception e) {
            // Ignore and try next
        }
        
        try {
            // Try validation error
            if (validationError.isDisplayed()) {
                return validationError.getText();
            }
        } catch (Exception e) {
            // Ignore and try next
        }
        
        try {
            // Try general error message
            if (errorMessage.isDisplayed()) {
                return errorMessage.getText();
            }
        } catch (Exception e) {
            // Ignore
        }
        
        return "";
    }public boolean hasValidationErrors() {
        System.out.println("Checking for validation errors...");
        
        try {
            // Check for client-side form errors
            if (formErrors.isDisplayed() && !formErrors.getText().trim().isEmpty()) {
                System.out.println("Found form errors: " + formErrors.getText());
                return true;
            }
        } catch (Exception e) {
            System.out.println("No form errors found");
        }
        
        try {
            // Check for field-specific errors
            if (fieldError.isDisplayed() && !fieldError.getText().trim().isEmpty()) {
                System.out.println("Found field error: " + fieldError.getText());
                return true;
            }
        } catch (Exception e) {
            System.out.println("No field errors found");
        }
        
        try {
            // Check for general validation errors
            if (validationError.isDisplayed()) {
                System.out.println("Found validation error: " + validationError.getText());
                return true;
            }
        } catch (Exception e) {
            System.out.println("No validation errors found");
        }
        
        try {
            // Check for alert danger messages
            if (errorMessage.isDisplayed() && !errorMessage.getText().trim().isEmpty()) {
                System.out.println("Found error message: " + errorMessage.getText());
                return true;
            }
        } catch (Exception e) {
            System.out.println("No error messages found");
        }
        
        // Check for any elements with error text content
        try {
            java.util.List<org.openqa.selenium.WebElement> errorElements = driver.findElements(
                By.xpath("//*[contains(text(), 'must be') or contains(text(), 'required') or contains(text(), 'invalid')]")
            );
            if (!errorElements.isEmpty()) {
                System.out.println("Found error elements with error text: " + errorElements.size());
                for (org.openqa.selenium.WebElement elem : errorElements) {
                    if (elem.isDisplayed()) {
                        System.out.println("Error text: " + elem.getText());
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("No error text elements found");
        }
        
        System.out.println("No validation errors detected");
        return false;
    }

    public boolean isCalorieGoalUpdated() {
        try {
            wait.until(ExpectedConditions.visibilityOf(calorieGoalDisplay));
            return calorieGoalDisplay.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isRedirectedToLogin() {
        wait.until(ExpectedConditions.urlContains("/login"));
        return driver.getCurrentUrl().contains("/login");
    }

    public String getCurrentFieldValue(String fieldName) {
        try {
            WebElement field = driver.findElement(By.id(fieldName));
            return field.getDomProperty("value");
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isProfileUpdated(Map<String, String> expectedData) {
        // Verify that the profile fields contain the expected updated values
        for (Map.Entry<String, String> entry : expectedData.entrySet()) {
            String actualValue = getCurrentFieldValue(entry.getKey());
            if (!actualValue.equals(entry.getValue())) {
                return false;
            }
        }
        return true;
    }

    // Helper methods for verification
    public String getFieldValue(String fieldName) {
        try {
            WebElement field = null;
            switch (fieldName) {
                case "name":
                    field = nameField;
                    break;
                case "email":
                    field = emailField;
                    break;
                case "age":
                    field = ageField;
                    break;
                case "height_cm":
                    field = heightField;
                    break;
                case "weight_kg":
                    field = weightField;
                    break;
                default:
                    return "";
            }
            
            if (field != null) {
                return field.getDomProperty("value");
            }
        } catch (Exception e) {
            System.out.println("Error getting field value for " + fieldName + ": " + e.getMessage());
        }
        return "";
    }

    public String getSelectedValue(String selectName) {
        try {
            WebElement selectElement = null;
            switch (selectName) {
                case "gender":
                    selectElement = genderSelect;
                    break;
                case "goal":
                    selectElement = goalSelect;
                    break;
                case "activity_level":
                    selectElement = activityLevelSelect;
                    break;
                default:
                    return "";
            }
            
            if (selectElement != null) {
                Select select = new Select(selectElement);
                WebElement selectedOption = select.getFirstSelectedOption();
                return selectedOption.getDomProperty("value");
            }
        } catch (Exception e) {
            System.out.println("Error getting selected value for " + selectName + ": " + e.getMessage());
        }
        return "";
    }

    public boolean isPasswordUpdatedSuccessfully() {
        try {
            // Check for the "Saved." message from session status
            WebElement savedMessage = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//p[contains(text(), 'Saved.')]")
            ));
            return savedMessage.isDisplayed();
        } catch (Exception e) {
            System.out.println("Password update success message not found: " + e.getMessage());
            return false;
        }
    }
}
