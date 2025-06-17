package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class USDASearchPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Page URL
    private static final String USDA_SEARCH_URL = "http://localhost:8080/usda-search";    // Web Elements - Search Form
    @FindBy(id = "query")
    private WebElement searchQueryField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement searchButton;    // Web Elements - Search Results
    @FindBy(css = "table.min-w-full")
    private WebElement searchResults;

    @FindBy(css = "tbody tr")
    private List<WebElement> foodItems;

    @FindBy(xpath = "//tbody//tr//td[1]")
    private List<WebElement> foodNames;

    @FindBy(xpath = "//tbody//tr//td[3]")
    private List<WebElement> caloriesInfo;    @FindBy(xpath = "//tbody//tr//td[4]")
    private List<WebElement> servingOptions;    @FindBy(xpath = "//td[5]//button")
    private List<WebElement> addToLogButtons;    // Web Elements - Add to Log Form
    @FindBy(id = "addFoodModal")
    private WebElement addFoodModal;

    @FindBy(id = "serving_amount")
    private WebElement servingAmountField;

    @FindBy(id = "consumed_at")
    private WebElement consumedAtField;

    @FindBy(xpath = "//button[@type='submit' and contains(text(), 'Add to Food Log')]")
    private WebElement addToFoodLogButton;    // Messages and Alerts
    @FindBy(css = ".bg-green-100")
    private WebElement successMessage;

    @FindBy(css = ".alert-danger, .invalid-feedback")
    private WebElement errorMessage;

    @FindBy(css = ".validation-error, .text-red-600, .text-red-500, .error-message")
    private List<WebElement> validationErrors;

    // Additional selector for USDA specific validation messages
    @FindBy(css = ".text-red-600, .text-red-500, .text-danger, [style*='color: red'], [class*='text-red']")
    private List<WebElement> usdaValidationMessages;    @FindBy(css = ".api-error-message, .error-message, .alert-danger, .text-red-600, .bg-red-100, [data-testid='api-error']")
    private WebElement apiErrorMessage;

    @FindBy(css = ".no-results, .no-results-message, .text-gray-500")
    private WebElement noResultsMessage;

    // Constructor
    public USDASearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }    // Navigation Actions
    public void navigateToUSDASearch() {
        driver.get(USDA_SEARCH_URL);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("query")));
    }

    // Search Actions
    public void enterSearchQuery(String query) {
        wait.until(ExpectedConditions.elementToBeClickable(searchQueryField));
        searchQueryField.clear();
        searchQueryField.sendKeys(query);
    }

    public void clickSearchButton() {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        searchButton.click();
    }

    public void searchForFood(String query) {
        enterSearchQuery(query);
        clickSearchButton();
    }

    public void submitEmptySearch() {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        searchButton.click();
    }

    public void searchWithShortQuery(String shortQuery) {
        enterSearchQuery(shortQuery);
        clickSearchButton();
    }    // Result Actions
    public void selectFoodItem(int index) {
        wait.until(ExpectedConditions.elementToBeClickable(addToLogButtons.get(index)));
        addToLogButtons.get(index).click();
        
        // Wait for modal to appear
        wait.until(ExpectedConditions.visibilityOf(addFoodModal));
    }

    public void fillServingDetails(String servingAmount, String consumedAt) {
        // Wait for modal to be visible and fields to be clickable
        wait.until(ExpectedConditions.visibilityOf(addFoodModal));
        wait.until(ExpectedConditions.elementToBeClickable(servingAmountField));
        
        servingAmountField.clear();
        servingAmountField.sendKeys(servingAmount);
        
        if (consumedAt != null && !consumedAt.isEmpty()) {
            consumedAtField.clear();
            consumedAtField.sendKeys(consumedAt);
        }
    }

    public void fillInvalidServingAmount(String invalidAmount) {
        wait.until(ExpectedConditions.elementToBeClickable(servingAmountField));
        servingAmountField.clear();
        if (!invalidAmount.isEmpty()) {
            servingAmountField.sendKeys(invalidAmount);
        }
    }

    public void leaveServingAmountEmpty() {
        wait.until(ExpectedConditions.elementToBeClickable(servingAmountField));
        servingAmountField.clear();
    }    public void clickAddToFoodLog() {
        // Wait for modal to be visible and button to be clickable
        wait.until(ExpectedConditions.visibilityOf(addFoodModal));
        wait.until(ExpectedConditions.elementToBeClickable(addToFoodLogButton));
        addToFoodLogButton.click();
    }

    // Verifications
    public boolean isOnUSDASearchPage() {
        return driver.getCurrentUrl().contains("/usda-search");
    }    public boolean areSearchResultsDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(searchResults));
            return searchResults.isDisplayed() && !foodItems.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isFoodNameDisplayed() {
        try {
            return !foodNames.isEmpty() && foodNames.get(0).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isCaloriesInfoDisplayed() {
        try {
            return !caloriesInfo.isEmpty() && caloriesInfo.get(0).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean areServingOptionsDisplayed() {
        try {
            return !servingOptions.isEmpty() && servingOptions.get(0).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }    public boolean areAddToLogButtonsDisplayed() {
        try {
            return !addToLogButtons.isEmpty() && addToLogButtons.get(0).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }    public String getSuccessMessage() {
        try {
            System.out.println("Looking for success message...");
            System.out.println("Current URL: " + driver.getCurrentUrl());
            
            // Look for the correct Laravel success message
            List<WebElement> greenSuccess = driver.findElements(By.cssSelector(".bg-green-100"));
            System.out.println("Green success elements: " + greenSuccess.size());
            
            if (!greenSuccess.isEmpty()) {
                String message = greenSuccess.get(0).getText();
                System.out.println("Success message found: " + message);
                return message;
            }
            
            return "";
        } catch (Exception e) {
            System.out.println("Error getting success message: " + e.getMessage());
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
    }    public String getValidationError() {
        try {
            // First check for HTML5 validation message
            String html5Message = getHTML5ValidationMessage();
            if (!html5Message.isEmpty()) {
                return html5Message;
            }
            
            // Check for USDA specific validation messages (red text)
            if (!usdaValidationMessages.isEmpty()) {
                for (WebElement validationElement : usdaValidationMessages) {
                    if (validationElement.isDisplayed() && !validationElement.getText().trim().isEmpty()) {
                        return validationElement.getText().trim();
                    }
                }
            }
            
            // Fallback to regular validation errors
            if (!validationErrors.isEmpty()) {
                for (WebElement validationElement : validationErrors) {
                    if (validationElement.isDisplayed() && !validationElement.getText().trim().isEmpty()) {
                        return validationElement.getText().trim();
                    }
                }
            }
            
            return "";
        } catch (Exception e) {
            return "";
        }
    }

    public String getHTML5ValidationMessage() {
        try {
            // HTML5 validation message can be retrieved from the search field
            String validationMessage = (String) ((org.openqa.selenium.JavascriptExecutor) driver)
                    .executeScript("return arguments[0].validationMessage;", searchQueryField);
            return validationMessage != null ? validationMessage : "";
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
    }    public String getApiErrorMessage() {
        try {
            // Look for various API error messages
            List<WebElement> errorElements = driver.findElements(By.cssSelector(
                ".api-error-message, .error-message, .alert-danger, .text-red-600, .bg-red-100, [data-testid='api-error'], .alert-error"
            ));
            
            for (WebElement element : errorElements) {
                if (element.isDisplayed() && !element.getText().trim().isEmpty()) {
                    String errorText = element.getText().trim();
                    // Check if it looks like an API error message
                    if (errorText.toLowerCase().contains("service") || 
                        errorText.toLowerCase().contains("unavailable") ||
                        errorText.toLowerCase().contains("temporarily") ||
                        errorText.toLowerCase().contains("try again") ||
                        errorText.toLowerCase().contains("timeout") ||
                        errorText.toLowerCase().contains("connection")) {
                        return errorText;
                    }
                }
            }
            
            // Also check if there are no results which might indicate API issues
            List<WebElement> noResultsElements = driver.findElements(By.cssSelector(
                ".no-results, .no-results-message, .text-gray-500"
            ));
            
            for (WebElement element : noResultsElements) {
                if (element.isDisplayed() && !element.getText().trim().isEmpty()) {
                    String noResultsText = element.getText().trim();
                    if (noResultsText.toLowerCase().contains("service") ||
                        noResultsText.toLowerCase().contains("unavailable")) {
                        return noResultsText;
                    }
                }
            }
            
            return "";
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isApiErrorDisplayed() {
        try {
            String apiError = getApiErrorMessage();
            return !apiError.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isRedirectedToFoodEntries() {
        wait.until(ExpectedConditions.urlContains("/food"));
        return driver.getCurrentUrl().contains("/food");
    }

    public int getSearchResultsCount() {
        try {
            return foodItems.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public boolean isAddFoodModalDisplayed() {
        try {
            return addFoodModal.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
