package testrunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.junit.AfterClass;
import stepdefinition.Hooks;

/**
 * Test Runner untuk menjalankan Authentication Test Cases (TC-001 to TC-006, TC-024)
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/authentication.feature",
        glue = {"stepdefinition"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/authentication/html",
                "json:target/cucumber-reports/authentication/cucumber.json",
                "junit:target/cucumber-reports/authentication/cucumber.xml"
        },
        monochrome = true,
        publish = true
)
public class AuthenticationTestRunner {
    
    @AfterClass
    public static void tearDown() {
        Hooks.quitDriver();
    }
}
