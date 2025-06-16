package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import util.DriverManager;

public class TestHooks {

    @Before
    public void setUp(Scenario scenario) {
        System.out.println("Starting scenario: " + scenario.getName());
        // Driver is initialized lazily in DriverManager.getDriver()
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            // Take screenshot on failure
            try {
                byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "Screenshot");
            } catch (Exception e) {
                System.out.println("Failed to take screenshot: " + e.getMessage());
            }
        }
        
        System.out.println("Finished scenario: " + scenario.getName() + " - Status: " + 
                          (scenario.isFailed() ? "FAILED" : "PASSED"));
        
        // Close the driver after each scenario to ensure clean state
        DriverManager.quitDriver();
    }
}
