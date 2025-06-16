package stepdefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import util.DriverManager;
import util.ConfigReader;

public class Hooks {

    private WebDriver driver;    @Before
    public void setUp(Scenario scenario) {
        System.out.println("Starting scenario: " + scenario.getName());
        
        try {
            // Initialize WebDriver
            driver = DriverManager.getDriver();
            
            // Clear any existing cookies/session data
            try {
                driver.manage().deleteAllCookies();
            } catch (Exception e) {
                System.err.println("Failed to delete cookies during setup: " + e.getMessage());
            }
            
            // Set window size for consistent testing
            driver.manage().window().maximize();
            
            // Navigate to base URL to ensure application is accessible
            String baseUrl = ConfigReader.getBaseUrl();
            System.out.println("Navigating to: " + baseUrl);
            driver.get(baseUrl);
            
            // Wait a moment for page to load
            Thread.sleep(1000);
            
            System.out.println("Setup completed for scenario: " + scenario.getName());
        } catch (Exception e) {
            System.err.println("Error during scenario setup: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to setup test scenario", e);
        }
    }    @After
    public void tearDown(Scenario scenario) {
        System.out.println("Cleaning up after scenario: " + scenario.getName());
        
        if (scenario.isFailed()) {
            System.out.println("Scenario failed: " + scenario.getName());
            
            // Take screenshot on failure
            if (driver != null) {
                try {
                    TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
                    byte[] screenshot = takesScreenshot.getScreenshotAs(OutputType.BYTES);
                    scenario.attach(screenshot, "image/png", "Screenshot");
                    System.out.println("Screenshot attached for failed scenario");
                } catch (Exception e) {
                    System.err.println("Failed to take screenshot: " + e.getMessage());
                }
            }
        } else {
            System.out.println("Scenario passed: " + scenario.getName());
        }
        
        // Clear cookies and session data after each scenario
        if (driver != null) {
            try {
                driver.manage().deleteAllCookies();
            } catch (Exception e) {
                System.err.println("Failed to delete cookies: " + e.getMessage());
            }
            
            // Navigate to login page to clear any authenticated state
            try {
                driver.get(ConfigReader.getBaseUrl() + "/login");
            } catch (Exception e) {
                System.err.println("Failed to navigate to login page during cleanup: " + e.getMessage());
                // If navigation fails, the driver might be in a bad state
                // Don't quit here as it will be handled by DriverManager
            }
        }
        
        System.out.println("Cleanup completed for scenario: " + scenario.getName());
    }

    @After("@smoke")
    public void afterSmokeTest(Scenario scenario) {
        if (scenario.isFailed()) {
            System.err.println("CRITICAL: Smoke test failed - " + scenario.getName());
            // Additional logging or notification for smoke test failures
        }
    }

    @After("@security")
    public void afterSecurityTest(Scenario scenario) {
        if (scenario.isFailed()) {
            System.err.println("SECURITY ISSUE: Security test failed - " + scenario.getName());
            // Additional security-specific cleanup or logging
        }
    }

    @After("@api")
    public void afterApiTest(Scenario scenario) {
        // Additional cleanup specific to API tests
        System.out.println("API test completed: " + scenario.getName());
    }

    @After("@performance")
    public void afterPerformanceTest(Scenario scenario) {
        // Additional cleanup specific to performance tests
        System.out.println("Performance test completed: " + scenario.getName());
    }

    // Static method to quit driver at the end of all tests
    public static void quitDriver() {
        DriverManager.quitDriver();
        System.out.println("WebDriver quit successfully");
    }
}
