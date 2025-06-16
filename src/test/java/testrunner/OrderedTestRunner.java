package testrunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.junit.AfterClass;
import stepdefinition.Hooks;

/**
 * Test Runner untuk menjalankan semua test case secara berurutan
 * dari TC-001 sampai TC-053
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = {
                "src/test/resources/features/authentication.feature",
                "src/test/resources/features/food-entry-management.feature", 
                "src/test/resources/features/custom-food-management.feature",
                "src/test/resources/features/usda-food-search.feature",
                "src/test/resources/features/ai-chatbot.feature",
                "src/test/resources/features/dashboard-analytics.feature",
                "src/test/resources/features/profile-management.feature"
        },
        glue = {"stepdefinition"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/ordered/html",
                "json:target/cucumber-reports/ordered/cucumber.json",
                "junit:target/cucumber-reports/ordered/cucumber.xml"
        },
        monochrome = true,
        publish = true
)
public class OrderedTestRunner {
    
    @AfterClass
    public static void tearDown() {
        Hooks.quitDriver();
    }
}
