package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.List;

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

    @FindBy(css = ".validation-error")
    private List<WebElement> validationErrors;

    @FindBy(css = ".api-error-message")
    private WebElement apiErrorMessage;

    @FindBy(css = ".no-results")
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
    }

    public String getValidationError() {
        try {
            return validationErrors.isEmpty() ? "" : validationErrors.get(0).getText();
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

    public String getApiErrorMessage() {
        try {
            wait.until(ExpectedConditions.visibilityOf(apiErrorMessage));
            return apiErrorMessage.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isApiErrorDisplayed() {
        try {
            return apiErrorMessage.isDisplayed();
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
}
