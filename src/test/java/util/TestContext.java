package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.HashMap;
import java.util.Map;

public class TestContext {
    private WebDriver driver;
    private final Map<String, Object> testData;
    private static final String DEFAULT_BROWSER = "chrome";
    private static final int DEFAULT_TIMEOUT = 10;    public TestContext() {
        this.testData = new HashMap<>();
        initializeDriver();
    }

    private void initializeDriver() {
        initializeDriver(DEFAULT_BROWSER);
    }

    private void initializeDriver(String browserName) {
        try {
            switch (browserName.toLowerCase()) {                case "chrome":
                    // Clear cache and force fresh driver download
                    WebDriverManager.chromedriver().clearDriverCache().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--disable-dev-shm-usage");
                    chromeOptions.addArguments("--no-sandbox");
                    chromeOptions.addArguments("--disable-gpu");
                    chromeOptions.addArguments("--window-size=1920,1080");
                    chromeOptions.addArguments("--remote-allow-origins=*");
                    // Add headless mode for CI/CD environments
                    if (System.getProperty("headless", "false").equals("true")) {
                        chromeOptions.addArguments("--headless");
                    }
                    driver = new ChromeDriver(chromeOptions);
                    break;
                    
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    if (System.getProperty("headless", "false").equals("true")) {
                        firefoxOptions.addArguments("--headless");
                    }
                    driver = new FirefoxDriver(firefoxOptions);
                    break;
                    
                default:
                    throw new IllegalArgumentException("Browser not supported: " + browserName);
            }
              // Set implicit wait
            driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(DEFAULT_TIMEOUT));
            driver.manage().window().maximize();
            
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize WebDriver: " + e.getMessage(), e);
        }
    }

    public WebDriver getDriver() {
        if (driver == null) {
            initializeDriver();
        }
        return driver;
    }

    public void setTestData(String key, Object value) {
        testData.put(key, value);
    }

    public Object getTestData(String key) {
        return testData.get(key);
    }

    public String getTestDataAsString(String key) {
        Object value = testData.get(key);
        return value != null ? value.toString() : null;
    }

    public void clearTestData() {
        testData.clear();
    }

    public void closeDriver() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                System.err.println("Error closing driver: " + e.getMessage());
            } finally {
                driver = null;
            }
        }
    }

    public void navigateToUrl(String url) {
        getDriver().get(url);
    }

    public String getCurrentUrl() {
        return getDriver().getCurrentUrl();
    }

    public String getPageTitle() {
        return getDriver().getTitle();
    }

    public void refreshPage() {
        getDriver().navigate().refresh();
    }

    public void navigateBack() {
        getDriver().navigate().back();
    }

    public void navigateForward() {
        getDriver().navigate().forward();
    }

    // Method to take screenshot for reporting
    public byte[] takeScreenshot() {
        try {
            if (driver != null) {
                return ((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(org.openqa.selenium.OutputType.BYTES);
            }
        } catch (Exception e) {
            System.err.println("Failed to take screenshot: " + e.getMessage());
        }
        return new byte[0];
    }

    // Method to wait for a specific amount of time
    public void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Method to check if running in CI environment
    public boolean isRunningInCI() {
        return System.getenv("CI") != null || System.getProperty("ci", "false").equals("true");
    }    // Method to get base URL for testing
    public String getBaseUrl() {
        return System.getProperty("base.url", "http://localhost:8080");
    }
}
