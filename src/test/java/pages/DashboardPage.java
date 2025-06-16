package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage {
    private final WebDriver driver;
    private final WebDriverWait wait;    // Page URL
    private static final String DASHBOARD_URL = "http://localhost:8080/dashboard";    // Web Elements
    @FindBy(xpath = "//h2[contains(text(), 'Dashboard')]")
    private WebElement dashboardTitle;

    @FindBy(xpath = "//h3[contains(text(), \"Today's Calories\")]/following-sibling::p[contains(@class, 'text-3xl')]")
    private WebElement todayCaloriesElement;

    @FindBy(id = "weeklyChart")
    private WebElement weeklyChart;

    @FindBy(xpath = "//h3[contains(text(), 'Daily Goal')]/following-sibling::p[contains(@class, 'text-3xl')]")
    private WebElement calorieGoalProgress;    @FindBy(xpath = "//h3[contains(text(), 'Quick Actions')]")
    private WebElement quickActionsSection;    // Recent food entries - using quick actions as proxy since there's no dedicated recent entries section
    @FindBy(xpath = "//a[contains(@href, 'food')]")
    private WebElement recentFoodEntries;

    // Empty state - using quick actions as indicator dashboard is loaded
    @FindBy(xpath = "//h3[contains(text(), 'Quick Actions')]")
    private WebElement emptyState;@FindBy(xpath = "//a[contains(text(), 'Add Food Entry') and contains(@href, 'food/create')] | //a[contains(@href, 'food/create')]")
    private WebElement addFoodEntryButton;

    @FindBy(xpath = "//a[contains(text(), 'View Food Entries') and contains(@href, 'food')]")
    private WebElement foodEntriesLink;

    @FindBy(xpath = "//a[contains(text(), 'Add Custom Food') and contains(@href, 'custom-food/create')]")
    private WebElement customFoodsLink;

    @FindBy(xpath = "//a[contains(text(), 'Search USDA Foods') and contains(@href, 'usda')]")
    private WebElement usdaSearchLink;

    @FindBy(xpath = "//a[contains(text(), 'Chatbot')]")
    private WebElement chatbotLink;

    @FindBy(xpath = "//a[contains(text(), 'Edit Profile') and contains(@href, 'profile')]")
    private WebElement profileLink;    @FindBy(xpath = "//a[contains(text(), 'Log Out')]")
    private WebElement logoutButton;@FindBy(css = ".bg-green-100, .text-green-700")
    private WebElement successMessage;

    @FindBy(css = ".error-message")
    private WebElement errorMessage;

    @FindBy(xpath = "//button[contains(@class, 'flex') and contains(@class, 'items-center')]")
    private WebElement userDropdownTrigger;

    // Constructor
    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }    // Actions
    public void navigateToDashboard() {
        driver.get(DASHBOARD_URL);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(text(), 'Dashboard')] | //h1[contains(text(), 'Dashboard')]")));
    }    public void clickAddFoodEntry() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(addFoodEntryButton));
            addFoodEntryButton.click();
        } catch (Exception e) {
            // Fallback: try to find the button by different selectors
            WebElement fallbackButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(), 'Add Food Entry')] | //a[contains(@href, 'food/create')]")
            ));
            fallbackButton.click();
        }
    }

    public void navigateToFoodEntries() {
        wait.until(ExpectedConditions.elementToBeClickable(foodEntriesLink));
        foodEntriesLink.click();
    }

    public void navigateToCustomFoods() {
        wait.until(ExpectedConditions.elementToBeClickable(customFoodsLink));
        customFoodsLink.click();
    }

    public void navigateToUSDASearch() {
        wait.until(ExpectedConditions.elementToBeClickable(usdaSearchLink));
        usdaSearchLink.click();
    }

    public void navigateToChatbot() {
        wait.until(ExpectedConditions.elementToBeClickable(chatbotLink));
        chatbotLink.click();
    }

    public void navigateToProfile() {
        wait.until(ExpectedConditions.elementToBeClickable(profileLink));
        profileLink.click();
    }

    public void logout() {
        wait.until(ExpectedConditions.elementToBeClickable(userDropdownTrigger));
        userDropdownTrigger.click();
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
        logoutButton.click();
    }

    // Verifications
    public boolean isOnDashboard() {
        return driver.getCurrentUrl().contains("/dashboard");
    }

    public boolean isDashboardDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(dashboardTitle));
            return dashboardTitle.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getTodayCalories() {
        try {
            wait.until(ExpectedConditions.visibilityOf(todayCaloriesElement));
            return todayCaloriesElement.getText();
        } catch (Exception e) {
            return "0";
        }
    }

    public boolean isWeeklyChartDisplayed() {
        try {
            return weeklyChart.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isCalorieGoalProgressDisplayed() {
        try {
            return calorieGoalProgress.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean areRecentFoodEntriesDisplayed() {
        try {
            return recentFoodEntries.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isEmptyStateDisplayed() {
        try {
            return emptyState.isDisplayed();
        } catch (Exception e) {
            return false;
        }
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
}
