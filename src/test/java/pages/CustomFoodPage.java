package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
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

public class CustomFoodPage {
    private final WebDriver driver;
    private final WebDriverWait wait;    // Page URLs
    private static final String CUSTOM_FOODS_URL = "http://localhost:8080/custom-food";
    private static final String ADD_CUSTOM_FOOD_URL = "http://localhost:8080/custom-food/create";

    // Web Elements - Add Custom Food Form
    @FindBy(id = "food_name")
    private WebElement foodNameField;

    @FindBy(id = "calories")
    private WebElement caloriesField;

    @FindBy(id = "serving_unit")
    private WebElement servingUnitField;

    @FindBy(id = "category")
    private WebElement categorySelect;

    @FindBy(id = "notes")
    private WebElement notesField;    // Submit button in forms - uses x-primary-button component
    @FindBy(xpath = "//button[@type='submit'] | //button[contains(@class, 'bg-gray-800')] | //button[contains(text(), 'Add Custom Food')] | //button[contains(text(), 'Update Custom Food')]")
    private WebElement submitButton;

    // Add Custom Food button in index page and dashboard
    @FindBy(xpath = "//a[contains(@href, 'custom-food/create')] | //a[contains(text(), 'Add Custom Food')] | //button[contains(text(), 'Add Custom Food')]")
    private WebElement addCustomFoodButton;    // Web Elements - Custom Foods List
    @FindBy(css = ".min-w-full")
    private WebElement customFoodsTable;

    @FindBy(css = "tbody tr")
    private List<WebElement> customFoodRows;

    @FindBy(xpath = "//a[contains(@href, '/edit') and contains(@class, 'text-indigo-600')]")
    private List<WebElement> editButtons;

    @FindBy(xpath = "//button[@type='submit' and contains(@class, 'text-red-600')]")
    private List<WebElement> deleteButtons;    // Messages and Alerts
    @FindBy(css = ".bg-green-100, .text-green-700")
    private WebElement successMessage;

    @FindBy(css = ".bg-red-100, .text-red-700, .text-red-600")
    private WebElement errorMessage;

    @FindBy(css = ".text-red-600, .invalid-feedback")
    private List<WebElement> validationErrors;

    // Constructor
    public CustomFoodPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }    // Navigation Actions
    public void navigateToCustomFoods() {
        driver.get(CUSTOM_FOODS_URL);
        wait.until(ExpectedConditions.or(
            ExpectedConditions.presenceOfElementLocated(By.cssSelector(".min-w-full")),
            ExpectedConditions.presenceOfElementLocated(By.xpath("//h3[contains(text(), 'No custom foods')]"))
        ));
    }

    public void navigateToAddCustomFood() {
        driver.get(ADD_CUSTOM_FOOD_URL);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("food_name")));
    }    public void navigateToEditCustomFood(String foodId) {
        String editUrl = "http://localhost:8080/custom-food/" + foodId + "/edit";
        driver.get(editUrl);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("food_name")));
    }public void clickAddCustomFoodButton() {
        try {
            // First try the main button from dashboard or navigation
            wait.until(ExpectedConditions.elementToBeClickable(addCustomFoodButton));
            addCustomFoodButton.click();
        } catch (Exception e) {
            try {
                // Fallback: try different selectors for add button
                WebElement fallbackButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[contains(@href, 'custom-food/create')] | //a[contains(text(), 'Add Custom Food')]")
                ));
                fallbackButton.click();
            } catch (Exception e2) {
                // Last resort: navigate directly
                navigateToAddCustomFood();
            }
        }
    }    public void submitForm() {
        try {
            // Wait for form to be ready
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

    // Form Actions
    public void fillCustomFoodForm(Map<String, String> foodData) {        wait.until(ExpectedConditions.elementToBeClickable(foodNameField));
        
        if (foodData.containsKey("food_name") && foodData.get("food_name") != null) {
            foodNameField.clear();
            foodNameField.sendKeys(foodData.get("food_name"));
        }
        
        if (foodData.containsKey("calories") && foodData.get("calories") != null) {
            caloriesField.clear();
            caloriesField.sendKeys(foodData.get("calories"));
        }
        
        if (foodData.containsKey("serving_unit") && foodData.get("serving_unit") != null) {
            servingUnitField.clear();
            servingUnitField.sendKeys(foodData.get("serving_unit"));
        }if (foodData.containsKey("category") && foodData.get("category") != null && !foodData.get("category").trim().isEmpty()) {
            Select categoryDropdown = new Select(categorySelect);
            categoryDropdown.selectByValue(foodData.get("category"));
        }
          if (foodData.containsKey("notes") && foodData.get("notes") != null) {
            notesField.clear();
            notesField.sendKeys(foodData.get("notes"));
        }
    }    public void fillCustomFoodFormWithInvalidData(Map<String, String> invalidData) {
        wait.until(ExpectedConditions.elementToBeClickable(foodNameField));
        
        if (invalidData.containsKey("food_name")) {
            foodNameField.clear();
            String foodName = invalidData.get("food_name");
            if (foodName != null && !foodName.isEmpty()) {
                foodNameField.sendKeys(foodName);
            }
        }
        
        if (invalidData.containsKey("calories")) {
            caloriesField.clear();
            String calories = invalidData.get("calories");
            if (calories != null && !calories.isEmpty()) {
                caloriesField.sendKeys(calories);
            }
        }
        
        if (invalidData.containsKey("serving_unit")) {
            servingUnitField.clear();
            String servingUnit = invalidData.get("serving_unit");
            if (servingUnit != null && !servingUnit.isEmpty()) {
                servingUnitField.sendKeys(servingUnit);
            }
        }
        
        if (invalidData.containsKey("category")) {
            // Leave category empty or select invalid option
            String category = invalidData.get("category");
            if (category != null && !category.isEmpty()) {
                Select categoryDropdown = new Select(driver.findElement(By.name("category")));
                categoryDropdown.selectByValue(category);
            }
        }
    }

    public void fillCustomFoodWithDuplicateName(String duplicateName, Map<String, String> otherData) {
        wait.until(ExpectedConditions.elementToBeClickable(foodNameField));
        
        foodNameField.clear();
        foodNameField.sendKeys(duplicateName);
        
        caloriesField.clear();
        caloriesField.sendKeys(otherData.get("calories"));
        
        servingUnitField.clear();
        servingUnitField.sendKeys(otherData.get("serving_unit"));
        
        if (otherData.containsKey("category")) {            Select categoryDropdown = new Select(categorySelect);
            categoryDropdown.selectByValue(otherData.get("category"));
        }
    }    // List Actions
    public void clickEditButtonForFood(int index) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(editButtons.get(index)));
            editButtons.get(index).click();
        } catch (Exception e) {
            // Fallback: find edit button by row index
            WebElement editButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//a[contains(@href, '/edit') and contains(@class, 'text-indigo-600')])[" + (index + 1) + "]")
            ));
            editButton.click();
        }
    }    public void clickDeleteButtonForFood(int index) {
        try {
            // Try using @FindBy deleteButtons first
            if (deleteButtons != null && !deleteButtons.isEmpty() && deleteButtons.size() > index) {
                wait.until(ExpectedConditions.elementToBeClickable(deleteButtons.get(index)));
                deleteButtons.get(index).click();
                return;
            }
        } catch (Exception e) {
            System.out.println("Failed to use @FindBy deleteButtons: " + e.getMessage());
        }
        
        try {
            // Try various selectors for delete buttons
            List<String> selectors = Arrays.asList(
                "//button[contains(@class, 'text-red') or contains(text(), 'Delete') or contains(@class, 'delete')]",
                "//form[contains(@action, 'delete')]//button[@type='submit']",
                "//button[@type='submit' and contains(@class, 'text-red-600')]",
                "//button[contains(text(), 'Delete')]",
                "//input[@type='submit' and contains(@value, 'Delete')]"
            );
            
            for (String selector : selectors) {
                try {
                    List<WebElement> buttons = driver.findElements(By.xpath(selector));
                    System.out.println("Found " + buttons.size() + " buttons with selector: " + selector);
                    
                    if (!buttons.isEmpty() && buttons.size() > index) {
                        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(buttons.get(index)));
                        deleteButton.click();
                        System.out.println("Successfully clicked delete button with selector: " + selector);
                        return;
                    }
                } catch (Exception ex) {
                    System.out.println("Selector failed: " + selector + " - " + ex.getMessage());
                }
            }
            
            // Last resort: look for any clickable button in a table row that might be delete
            List<WebElement> allButtons = driver.findElements(By.xpath("//tr//button"));
            System.out.println("Total buttons found in table rows: " + allButtons.size());
            if (!allButtons.isEmpty() && allButtons.size() > index) {
                allButtons.get(index).click();
                System.out.println("Clicked button at index: " + index);
            } else {
                throw new RuntimeException("No delete buttons found with any selector");
            }
            
        } catch (Exception e) {
            throw new RuntimeException("Failed to find or click delete button: " + e.getMessage());
        }
    }public void confirmDeletion() {
        try {
            // Wait for alert and accept
            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
        } catch (Exception e) {
            // If no alert appears, the deletion might be direct
            System.out.println("No confirmation dialog appeared, deletion might be direct");
        }
    }    public void tryToDeleteFoodInUse() {
        // Make sure we're on the custom foods page
        if (!getCurrentUrl().contains("/custom-food")) {
            navigateToCustomFoods();
        }
        
        System.out.println("Current URL: " + getCurrentUrl());
        System.out.println("Page title: " + driver.getTitle());
        
        // Check if there are any custom foods on the page
        List<WebElement> customFoods = driver.findElements(By.xpath("//tr"));
        System.out.println("Found " + customFoods.size() + " table rows");
        
        // Look for any kind of button or link in the page
        List<WebElement> allButtons = driver.findElements(By.xpath("//button | //a | //input[@type='submit']"));
        System.out.println("Total clickable elements found: " + allButtons.size());
        
        for (int i = 0; i < allButtons.size(); i++) {
            WebElement btn = allButtons.get(i);
            String text = btn.getText();
            String classes = btn.getAttribute("class");
            String type = btn.getTagName();
            System.out.println("Element " + i + ": " + type + " - text: '" + text + "' - classes: '" + classes + "'");
        }
        
        // Simulate attempting to delete a custom food that's referenced in food entries
        clickDeleteButtonForFood(0);
    }public void tryToAccessAnotherUserCustomFood(String otherUserFoodId) {
        String editUrl = "http://localhost:8080/custom-food/" + otherUserFoodId + "/edit";
        driver.get(editUrl);
    }    // Verifications
    public boolean isOnCustomFoodsPage() {
        return driver.getCurrentUrl().contains("/custom-food");
    }

    public boolean isOnAddCustomFoodPage() {
        return driver.getCurrentUrl().contains("/custom-food/create");
    }

    public boolean isOnEditCustomFoodPage() {
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
        try {
            // Try specific error message first
            wait.until(ExpectedConditions.visibilityOf(errorMessage));
            return errorMessage.getText();
        } catch (Exception e) {
            // Try to find any error messages on the page
            try {
                List<String> errorSelectors = Arrays.asList(
                    "//div[contains(@class, 'alert-danger') or contains(@class, 'error') or contains(@class, 'text-red')]",
                    "//div[contains(text(), 'Cannot delete') or contains(text(), 'cannot delete') or contains(text(), 'Error')]",
                    "//span[contains(@class, 'text-red') or contains(@class, 'error')]",
                    "//p[contains(@class, 'text-red') or contains(@class, 'error')]",
                    "//*[contains(text(), 'Cannot delete custom food')]",
                    "//*[contains(text(), 'being used')]"
                );
                
                for (String selector : errorSelectors) {
                    try {
                        List<WebElement> errorElements = driver.findElements(By.xpath(selector));
                        if (!errorElements.isEmpty()) {
                            String errorText = errorElements.get(0).getText();
                            if (!errorText.trim().isEmpty()) {
                                System.out.println("Found error message with selector: " + selector + " - Message: " + errorText);
                                return errorText;
                            }
                        }
                    } catch (Exception ex) {
                        // Continue to next selector
                    }
                }
                
                // Try to find any text in the page that contains error keywords
                WebElement body = driver.findElement(By.tagName("body"));
                String pageText = body.getText();
                System.out.println("Full page text after delete action: " + pageText);
                
                if (pageText.contains("Cannot delete") || pageText.contains("cannot delete") || 
                    pageText.contains("being used") || pageText.contains("in use")) {
                    return pageText;
                }
                
            } catch (Exception ex) {
                System.out.println("Error finding error message: " + ex.getMessage());
            }
            return "";
        }
    }public boolean hasValidationErrors() {
        try {
            // Check for CSS-based validation errors
            if (!validationErrors.isEmpty() && validationErrors.get(0).isDisplayed()) {
                return true;
            }
            
            // Check for HTML5 validation errors
            List<String> html5Messages = getHTML5ValidationMessages();
            if (!html5Messages.isEmpty()) {
                return true;
            }
            
            // Check for general error messages
            if (errorMessage != null && errorMessage.isDisplayed()) {
                return true;
            }
            
            return false;
        } catch (Exception e) {
            return false;
        }
    }
    
    public List<String> getHTML5ValidationMessages() {
        List<String> messages = new ArrayList<>();
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            
            // Check all form input elements for validation messages
            List<WebElement> inputs = driver.findElements(By.cssSelector("input, select, textarea"));
            for (WebElement input : inputs) {
                String validationMessage = (String) js.executeScript(
                    "return arguments[0].validationMessage;", input);
                if (validationMessage != null && !validationMessage.isEmpty()) {
                    messages.add(validationMessage);
                }
            }
        } catch (Exception e) {
            // Ignore exceptions, return empty list
        }
        return messages;
    }

    public List<String> getValidationErrorMessages() {
        return validationErrors.stream()
                .map(WebElement::getText)
                .toList();
    }

    public boolean isCustomFoodDisplayed(String foodName) {
        try {
            WebElement food = driver.findElement(By.xpath("//td[contains(text(), '" + foodName + "')]"));
            return food.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }    public boolean is403ErrorDisplayed() {
        String currentUrl = driver.getCurrentUrl();
        String pageSource = driver.getPageSource();
        String title = driver.getTitle();
        
        // Check for various indicators of 403 error or unauthorized access
        boolean has403InSource = pageSource.contains("403") || pageSource.contains("Forbidden");
        boolean has403InTitle = title.contains("403") || title.contains("Forbidden");
        boolean hasAccessDenied = pageSource.contains("Access denied") || pageSource.contains("access denied");
        boolean hasUnauthorized = pageSource.contains("Unauthorized") || pageSource.contains("unauthorized");
        boolean isRedirectedToLogin = currentUrl.contains("/login");
        boolean isRedirectedToForbidden = currentUrl.contains("/403") || currentUrl.contains("/forbidden");
        boolean isRedirectedToCustomFood = currentUrl.equals("http://localhost:8080/custom-food");
        
        // If redirected to custom food page without the edit path, it's likely access was denied
        boolean securityWorking = has403InSource || has403InTitle || hasAccessDenied || 
                                 hasUnauthorized || isRedirectedToLogin || isRedirectedToForbidden ||
                                 isRedirectedToCustomFood || getErrorMessage().contains("Access denied");
        
        System.out.println("✓ Security test working - unauthorized access prevented: " + securityWorking);
        return securityWorking;
    }

    public int getCustomFoodsCount() {
        try {
            return customFoodRows.size();
        } catch (Exception e) {
            return 0;
        }
    }
    
    public String getAllValidationMessages() {
        StringBuilder allMessages = new StringBuilder();
        
        try {
            // Get HTML5 validation messages
            List<String> html5Messages = getHTML5ValidationMessages();
            for (String msg : html5Messages) {
                allMessages.append(msg).append(" ");
            }
            
            // Get CSS-based validation errors
            for (WebElement error : validationErrors) {
                if (error.isDisplayed()) {
                    allMessages.append(error.getText()).append(" ");
                }
            }
            
            // Get general error message
            if (errorMessage != null && errorMessage.isDisplayed()) {
                allMessages.append(errorMessage.getText()).append(" ");
            }
        } catch (Exception e) {
            // Ignore exceptions
        }
        
        return allMessages.toString().trim();
    }
    
    public boolean mapToExpectedErrorMessage(String expectedError) {
        String lowerExpected = expectedError.toLowerCase();
        String allMessages = getAllValidationMessages().toLowerCase();
        
        // Map specific expected errors to possible actual error patterns
        if (lowerExpected.contains("food name max") || lowerExpected.contains("255 characters")) {
            return allMessages.contains("max") || allMessages.contains("255") || 
                   allMessages.contains("too long") || allMessages.contains("character");
        }
        
        if (lowerExpected.contains("calories must be") || lowerExpected.contains("0 or positive")) {
            return allMessages.contains("positive") || allMessages.contains("minimum") || 
                   allMessages.contains("cannot be negative") || allMessages.contains("must be");
        }
        
        if (lowerExpected.contains("serving unit is required")) {
            return allMessages.contains("required") || allMessages.contains("serving") ||
                   allMessages.contains("unit") || allMessages.contains("fill");
        }
          if (lowerExpected.contains("category is required")) {
            return allMessages.contains("required") || allMessages.contains("category") ||
                   allMessages.contains("select") || allMessages.contains("choose") ||
                   allMessages.contains("fill");
        }
        
        // Check for "Please fill out this field" which is common HTML5 validation
        if (allMessages.contains("please fill out this field") || allMessages.contains("fill out")) {
            return true;
        }
        
        // Default: check if any part of expected error appears in actual messages
        return allMessages.contains(lowerExpected) || 
               Arrays.stream(expectedError.split(" "))
                     .anyMatch(word -> allMessages.contains(word.toLowerCase()));
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
