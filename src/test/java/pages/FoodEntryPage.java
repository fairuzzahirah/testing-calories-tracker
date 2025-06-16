package pages;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FoodEntryPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Page URLs
    private static final String FOOD_ENTRIES_URL = "http://localhost:8080/food";
    private static final String ADD_FOOD_ENTRY_URL = "http://localhost:8080/food/create";

    // Web Elements - Add Food Entry Form
    @FindBy(id = "food_name")
    private WebElement foodNameField;

    @FindBy(id = "calories_per_serving")
    private WebElement caloriesPerServingField;

    @FindBy(id = "serving_amount")
    private WebElement servingAmountField;

    @FindBy(id = "serving_unit")
    private WebElement servingUnitField;

    @FindBy(id = "source")
    private WebElement sourceSelect;

    @FindBy(id = "consumed_at")
    private WebElement consumedAtField;    // Submit button uses x-primary-button component  
    @FindBy(xpath = "//button[@type='submit'] | //button[contains(@class, 'bg-gray-800')] | //button[contains(text(), 'Add Food Entry')]")
    private WebElement submitButton;

    @FindBy(xpath = "//a[contains(text(), 'Cancel') and contains(@href, 'food')]")
    private WebElement cancelButton;

    // Web Elements - Food Entries List    @FindBy(css = "table.min-w-full")
    private WebElement foodEntriesTable;

    @FindBy(css = ".food-entry-row, tr")
    private List<WebElement> foodEntryRows;    @FindBy(xpath = "//a[contains(@href, '/food/') and contains(@href, '/edit')] | //a[contains(text(), 'Edit')]")
    private List<WebElement> editButtons;    @FindBy(xpath = "//button[@type='submit' and contains(@onclick, 'confirm')] | //button[contains(text(), 'Delete')] | //a[contains(text(), 'Delete')]")
    private List<WebElement> deleteButtons;

    @FindBy(css = ".pagination")
    private WebElement paginationElement;

    // Messages and Alerts
    @FindBy(css = ".alert-success, .bg-green-100, .text-green-600")
    private WebElement successMessage;

    @FindBy(css = ".alert-danger, .text-red-600, .bg-red-100")
    private WebElement errorMessage;

    @FindBy(css = ".text-red-600, .text-sm.text-red-600")
    private List<WebElement> validationErrors;

    // Constructor
    public FoodEntryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }    // Navigation Actions
    public void navigateToFoodEntries() {
        driver.get(FOOD_ENTRIES_URL);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("table.min-w-full, .text-center.py-8")));
    }

    public void navigateToAddFoodEntry() {
        driver.get(ADD_FOOD_ENTRY_URL);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("food_name")));
    }

    public void navigateToEditFoodEntry(String entryId) {
        String editUrl = "http://localhost:8080/food/" + entryId + "/edit";
        driver.get(editUrl);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("food_name")));
    }

    public void navigateToNonExistentEditPage(String invalidId) {
        String editUrl = "http://localhost:8080/food/" + invalidId + "/edit";
        driver.get(editUrl);
    }    // Form Actions
    public void fillFoodEntryForm(Map<String, String> foodData) {
        wait.until(ExpectedConditions.elementToBeClickable(foodNameField));
        
        foodNameField.clear();
        foodNameField.sendKeys(foodData.get("food_name"));
        
        caloriesPerServingField.clear();
        caloriesPerServingField.sendKeys(foodData.get("calories_per_serving"));
        
        servingAmountField.clear();
        servingAmountField.sendKeys(foodData.get("serving_amount"));
        
        servingUnitField.clear();
        servingUnitField.sendKeys(foodData.get("serving_unit"));
        
        if (foodData.containsKey("source")) {
            Select sourceDropdown = new Select(sourceSelect);
            sourceDropdown.selectByValue(foodData.get("source"));
        }
        
        // Note: consumed_at field is automatically filled by JavaScript, so we skip it
        // The frontend auto-populates this with current date/time
    }

    public void fillFoodEntryFormWithInvalidData(Map<String, String> invalidData) {
        wait.until(ExpectedConditions.elementToBeClickable(foodNameField));
        
        if (invalidData.containsKey("food_name")) {
            foodNameField.clear();
            if (!invalidData.get("food_name").isEmpty()) {
                foodNameField.sendKeys(invalidData.get("food_name"));
            }
        }
        
        if (invalidData.containsKey("calories_per_serving")) {
            caloriesPerServingField.clear();
            if (!invalidData.get("calories_per_serving").isEmpty()) {
                caloriesPerServingField.sendKeys(invalidData.get("calories_per_serving"));
            }
        }
        
        if (invalidData.containsKey("serving_amount")) {
            servingAmountField.clear();
            if (!invalidData.get("serving_amount").isEmpty()) {
                servingAmountField.sendKeys(invalidData.get("serving_amount"));
            }
        }
          if (invalidData.containsKey("serving_unit")) {
            servingUnitField.clear();
            if (!invalidData.get("serving_unit").isEmpty()) {
                servingUnitField.sendKeys(invalidData.get("serving_unit"));
            }
        }
        
        // Note: consumed_at field is automatically filled by JavaScript, so we skip it
        // The frontend auto-populates this with current date/time
    }

    public void fillFoodNameWithXSSPayload(String xssPayload) {
        wait.until(ExpectedConditions.elementToBeClickable(foodNameField));
        foodNameField.clear();
        foodNameField.sendKeys(xssPayload);
    }    public void submitForm() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(submitButton));
            submitButton.click();
        } catch (Exception e) {
            try {
                // Fallback: Try to find submit button by multiple selectors
                WebElement fallbackSubmit = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[@type='submit'] | //button[contains(@class, 'bg-gray-800')] | //input[@type='submit']")
                ));
                fallbackSubmit.click();
            } catch (Exception e2) {
                // Last resort: try primary button class
                WebElement primaryButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(@class, 'inline-flex') and contains(@class, 'bg-gray-800')]")
                ));
                primaryButton.click();
            }
        }
    }

    public void clickAddFoodEntryButton() {
        try {
            // Try primary button first
            WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(), 'Add Food Entry')] | //a[contains(text(), 'Add Food Entry')]")
            ));
            addButton.click();
        } catch (Exception e) {
            // Fallback for submit button
            submitForm();
        }
    }

    // List Actions
    public void clickEditButtonForEntry(int index) {
        wait.until(ExpectedConditions.elementToBeClickable(editButtons.get(index)));
        editButtons.get(index).click();
    }

    public void clickDeleteButtonForEntry(int index) {
        wait.until(ExpectedConditions.elementToBeClickable(deleteButtons.get(index)));
        deleteButtons.get(index).click();
    }

    public void confirmDeletion() {
        // Handle browser confirmation dialog
        driver.switchTo().alert().accept();
    }

    // Verifications
    public boolean isOnFoodEntriesPage() {
        return driver.getCurrentUrl().contains("/food");
    }

    public boolean isOnAddFoodEntryPage() {
        return driver.getCurrentUrl().contains("/food/create");
    }

    public boolean isOnEditFoodEntryPage() {
        return driver.getCurrentUrl().contains("/edit");
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

    public boolean hasValidationErrors() {
        try {
            return !validationErrors.isEmpty() && validationErrors.get(0).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public List<String> getValidationErrorMessages() {
        return validationErrors.stream()
                .map(WebElement::getText)
                .toList();
    }

    public boolean isFoodEntryDisplayed(String foodName) {
        try {
            WebElement entry = driver.findElement(By.xpath("//td[contains(text(), '" + foodName + "')]"));
            return entry.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPaginationDisplayed() {
        try {
            return paginationElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean is404ErrorDisplayed() {
        return driver.getPageSource().contains("404") || 
               driver.getPageSource().contains("Not Found") ||
               getErrorMessage().contains("Food entry not found");
    }

    public boolean is403ErrorDisplayed() {
        return driver.getPageSource().contains("403") || 
               driver.getPageSource().contains("Forbidden") ||
               getErrorMessage().contains("Access denied");
    }

    public boolean isXSSExecuted() {
        // Check if any alert is present (which would indicate XSS execution)
        try {
            driver.switchTo().alert();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public int getFoodEntriesCount() {
        try {
            return foodEntryRows.size();
        } catch (Exception e) {
            return 0;
        }
    }

    // Update individual fields (for edit operations)
    public void updateFoodName(String newFoodName) {
        wait.until(ExpectedConditions.elementToBeClickable(foodNameField));
        foodNameField.clear();
        foodNameField.sendKeys(newFoodName);
    }
    
    public void updateCaloriesPerServing(String newCalories) {
        wait.until(ExpectedConditions.elementToBeClickable(caloriesPerServingField));
        caloriesPerServingField.clear();
        caloriesPerServingField.sendKeys(newCalories);
    }
    
    public void updateServingAmount(String newServingAmount) {
        wait.until(ExpectedConditions.elementToBeClickable(servingAmountField));
        servingAmountField.clear();
        servingAmountField.sendKeys(newServingAmount);
    }
    
    public boolean isEmpty() {
        try {
            // Check if the empty state message is displayed
            WebElement emptyState = driver.findElement(By.cssSelector(".text-center.py-8"));
            return emptyState.isDisplayed();
        } catch (Exception e) {
            // If empty state not found, check if table has no rows (aside from header)
            try {
                List<WebElement> rows = driver.findElements(By.cssSelector("table.min-w-full tbody tr"));
                return rows.isEmpty();
            } catch (Exception ex) {
                return true; // Assume empty if can't find table
            }
        }
    }
}
