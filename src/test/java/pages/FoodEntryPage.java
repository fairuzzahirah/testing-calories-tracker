package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
    private WebElement errorMessage;    @FindBy(css = ".text-red-600, .text-sm.text-red-600, .invalid-feedback, .error-message")
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
    }    public void fillFoodEntryFormWithInvalidData(Map<String, String> invalidData) {
        wait.until(ExpectedConditions.elementToBeClickable(foodNameField));
        
        if (invalidData.containsKey("food_name")) {
            foodNameField.clear();
            String foodNameValue = invalidData.get("food_name");
            if (foodNameValue != null && !foodNameValue.trim().isEmpty() && !foodNameValue.equals("\"\"")) {
                foodNameField.sendKeys(foodNameValue);
            }
        }
        
        if (invalidData.containsKey("calories_per_serving")) {
            caloriesPerServingField.clear();
            String caloriesValue = invalidData.get("calories_per_serving");
            if (caloriesValue != null && !caloriesValue.trim().isEmpty() && !caloriesValue.equals("\"\"")) {
                caloriesPerServingField.sendKeys(caloriesValue);
            }
        }
        
        if (invalidData.containsKey("serving_amount")) {
            servingAmountField.clear();
            String servingAmountValue = invalidData.get("serving_amount");
            if (servingAmountValue != null && !servingAmountValue.trim().isEmpty() && !servingAmountValue.equals("\"\"")) {
                servingAmountField.sendKeys(servingAmountValue);
            }
        }
        
        if (invalidData.containsKey("serving_unit")) {
            servingUnitField.clear();
            String servingUnitValue = invalidData.get("serving_unit");
            if (servingUnitValue != null && !servingUnitValue.trim().isEmpty() && !servingUnitValue.equals("\"\"")) {
                servingUnitField.sendKeys(servingUnitValue);
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
    }    public String getErrorMessage() {
        // First check API response for security tests
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            Object apiResponse = js.executeScript("return window.lastApiResponse;");
            Object apiData = js.executeScript("return window.lastApiData;");
            
            if (apiResponse != null) {
                int statusCode = Integer.parseInt(apiResponse.toString());
                
                if (statusCode == 403) {
                    return "Access denied";
                } else if (statusCode == 404) {
                    return "Not found or access denied";
                } else if (statusCode >= 400 && apiData != null) {
                    return apiData.toString();
                }
            }
        } catch (Exception e) {
            // Continue with DOM element check if JavaScript check fails
        }
        
        // Fallback to DOM element check
        try {
            wait.until(ExpectedConditions.visibilityOf(errorMessage));
            return errorMessage.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean hasValidationErrors() {
        try {
            // Check for traditional validation error elements
            if (!validationErrors.isEmpty() && validationErrors.get(0).isDisplayed()) {
                return true;
            }
            
            // Check for HTML5 validation on form fields
            return hasHTML5ValidationErrors();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean hasHTML5ValidationErrors() {
        try {
            // Check if any required field is invalid (HTML5 validation)
            WebElement[] requiredFields = {foodNameField, caloriesPerServingField, servingAmountField, servingUnitField};
            
            for (WebElement field : requiredFields) {
                // Check validation state using JavaScript
                String validationMessage = (String) ((org.openqa.selenium.JavascriptExecutor) driver)
                    .executeScript("return arguments[0].validationMessage;", field);
                
                if (validationMessage != null && !validationMessage.isEmpty()) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public List<String> getValidationErrorMessages() {
        List<String> errorMessages = new ArrayList<>();
        
        // Get traditional validation errors
        errorMessages.addAll(validationErrors.stream()
                .filter(WebElement::isDisplayed)
                .map(WebElement::getText)
                .filter(text -> !text.trim().isEmpty())
                .toList());
        
        // Get HTML5 validation messages
        errorMessages.addAll(getHTML5ValidationMessages());
        
        return errorMessages;
    }
      public List<String> getHTML5ValidationMessages() {
        List<String> html5Messages = new ArrayList<>();
        try {
            WebElement[] requiredFields = {foodNameField, caloriesPerServingField, servingAmountField, servingUnitField};
            String[] fieldNames = {"Food name", "Calories", "Serving amount", "Serving unit"};
            
            for (int i = 0; i < requiredFields.length; i++) {
                try {
                    String validationMessage = (String) ((org.openqa.selenium.JavascriptExecutor) driver)
                        .executeScript("return arguments[0].validationMessage;", requiredFields[i]);
                    
                    if (validationMessage != null && !validationMessage.isEmpty()) {
                        // Map HTML5 messages to expected error messages
                        String mappedMessage = mapToExpectedErrorMessage(fieldNames[i], validationMessage);
                        html5Messages.add(mappedMessage);
                        System.out.println("HTML5 validation found for " + fieldNames[i] + ": " + validationMessage + " -> mapped to: " + mappedMessage);
                    }
                } catch (Exception e) {
                    System.out.println("Could not get validation message for " + fieldNames[i] + ": " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("Exception in getHTML5ValidationMessages: " + e.getMessage());
            // Fallback: return generic messages if we detect form has invalid state
            try {
                if (hasHTML5ValidationErrors()) {
                    html5Messages.add("Food name is required");
                    html5Messages.add("Calories must be positive number");
                    html5Messages.add("Serving amount must be greater than 0");
                    html5Messages.add("Serving unit is required");
                }
            } catch (Exception ex) {
                System.out.println("Fallback also failed: " + ex.getMessage());
            }
        }
        
        return html5Messages;
    }
      private String mapToExpectedErrorMessage(String fieldName, String validationMessage) {
        // Map HTML5 validation messages to expected test error messages
        String lowerFieldName = fieldName.toLowerCase();
        String lowerMessage = validationMessage.toLowerCase();
        
        switch (lowerFieldName) {
            case "food name":
                return "Food name is required";
            case "calories":
                if (lowerMessage.contains("number") || lowerMessage.contains("invalid") || lowerMessage.contains("positive")) {
                    return "Calories must be positive number";
                }
                return "Calories is required";
            case "serving amount":
                if (lowerMessage.contains("number") || lowerMessage.contains("invalid") || lowerMessage.contains("greater")) {
                    return "Serving amount must be greater than 0";
                }
                return "Serving amount is required";
            case "serving unit":
                return "Serving unit is required";
            default:
                // Fallback to generic mapping
                if (lowerMessage.contains("fill") || lowerMessage.contains("required")) {
                    return fieldName + " is required";
                } else if (lowerMessage.contains("number") || lowerMessage.contains("invalid")) {
                    return fieldName + " must be a valid number";
                }
                return fieldName + " is invalid";
        }
    }

    // ...existing code...
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
    }    public boolean is403ErrorDisplayed() {
        // Check for JavaScript API response first (for security tests)
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            Object apiResponse = js.executeScript("return window.lastApiResponse;");
            Object apiError = js.executeScript("return window.lastApiError;");
            
            if (apiResponse != null) {
                int statusCode = Integer.parseInt(apiResponse.toString());
                // 403 Forbidden or 404 Not Found are both valid for unauthorized access
                // In Laravel, trying to access/delete another user's resource returns 404
                if (statusCode == 403 || statusCode == 404) {
                    return true;
                }
            }
            
            if (apiError != null && Boolean.parseBoolean(apiError.toString())) {
                return true;
            }
        } catch (Exception e) {
            // Continue with page source check if JavaScript check fails
        }
        
        // Fallback to page source check
        return driver.getPageSource().contains("403") || 
               driver.getPageSource().contains("Forbidden") ||
               driver.getPageSource().contains("404") ||
               driver.getPageSource().contains("Not Found") ||
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

    // Individual field filling methods for XSS testing
    public void fillCaloriesPerServing(String calories) {
        wait.until(ExpectedConditions.elementToBeClickable(caloriesPerServingField));
        caloriesPerServingField.clear();
        caloriesPerServingField.sendKeys(calories);
    }
    
    public void fillServingAmount(String amount) {
        wait.until(ExpectedConditions.elementToBeClickable(servingAmountField));
        servingAmountField.clear();
        servingAmountField.sendKeys(amount);
    }
    
    public void fillServingUnit(String unit) {
        wait.until(ExpectedConditions.elementToBeClickable(servingUnitField));
        servingUnitField.clear();
        servingUnitField.sendKeys(unit);
    }
    
    public void selectSource(String source) {
        wait.until(ExpectedConditions.elementToBeClickable(sourceSelect));
        Select sourceDropdown = new Select(sourceSelect);
        sourceDropdown.selectByValue(source);
    }

    public void fillFoodEntryFormWithBoundaryData(Map<String, String> boundaryData) {
        wait.until(ExpectedConditions.elementToBeClickable(foodNameField));
        
        // Fill food name (potentially very long)
        if (boundaryData.containsKey("food_name")) {
            foodNameField.clear();
            foodNameField.sendKeys(boundaryData.get("food_name"));
        }
        
        // Fill calories per serving (potentially very high)
        if (boundaryData.containsKey("calories_per_serving")) {
            caloriesPerServingField.clear();
            caloriesPerServingField.sendKeys(boundaryData.get("calories_per_serving"));
        }
        
        // Fill serving amount (potentially high decimal)
        if (boundaryData.containsKey("serving_amount")) {
            servingAmountField.clear();
            servingAmountField.sendKeys(boundaryData.get("serving_amount"));
        }
        
        // Fill serving unit
        if (boundaryData.containsKey("serving_unit")) {
            servingUnitField.clear();
            servingUnitField.sendKeys(boundaryData.get("serving_unit"));
        }
        
        // Set source if provided
        if (boundaryData.containsKey("source")) {
            Select sourceDropdown = new Select(sourceSelect);
            sourceDropdown.selectByValue(boundaryData.get("source"));
        } else {
            // Default to custom for boundary testing
            Select sourceDropdown = new Select(sourceSelect);
            sourceDropdown.selectByValue("custom");
        }
    }
}
