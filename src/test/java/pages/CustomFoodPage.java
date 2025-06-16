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
import java.util.List;
import java.util.Map;

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
    }

    public void fillCustomFoodFormWithInvalidData(Map<String, String> invalidData) {
        wait.until(ExpectedConditions.elementToBeClickable(foodNameField));
        
        if (invalidData.containsKey("food_name")) {
            foodNameField.clear();
            if (!invalidData.get("food_name").isEmpty()) {
                foodNameField.sendKeys(invalidData.get("food_name"));
            }
        }
        
        if (invalidData.containsKey("calories")) {
            caloriesField.clear();
            if (!invalidData.get("calories").isEmpty()) {
                caloriesField.sendKeys(invalidData.get("calories"));
            }
        }
        
        if (invalidData.containsKey("serving_unit")) {
            servingUnitField.clear();
            if (!invalidData.get("serving_unit").isEmpty()) {
                servingUnitField.sendKeys(invalidData.get("serving_unit"));
            }
        }
        
        if (invalidData.containsKey("category")) {
            // Leave category empty or select invalid option
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
    }

    public void clickDeleteButtonForFood(int index) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(deleteButtons.get(index)));
            deleteButtons.get(index).click();
        } catch (Exception e) {
            // Fallback: find delete button by row index
            WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//button[@type='submit' and contains(@class, 'text-red-600')])[" + (index + 1) + "]")
            ));
            deleteButton.click();
        }
    }    public void confirmDeletion() {
        try {
            // Wait for alert and accept
            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
        } catch (Exception e) {
            // If no alert appears, the deletion might be direct
            System.out.println("No confirmation dialog appeared, deletion might be direct");
        }
    }

    public void tryToDeleteFoodInUse() {
        // Simulate attempting to delete a custom food that's referenced in food entries
        clickDeleteButtonForFood(0);
    }    public void tryToAccessAnotherUserCustomFood(String otherUserFoodId) {
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

    public boolean isCustomFoodDisplayed(String foodName) {
        try {
            WebElement food = driver.findElement(By.xpath("//td[contains(text(), '" + foodName + "')]"));
            return food.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean is403ErrorDisplayed() {
        return driver.getPageSource().contains("403") || 
               driver.getPageSource().contains("Forbidden") ||
               getErrorMessage().contains("Access denied");
    }

    public int getCustomFoodsCount() {
        try {
            return customFoodRows.size();
        } catch (Exception e) {
            return 0;
        }
    }
}
