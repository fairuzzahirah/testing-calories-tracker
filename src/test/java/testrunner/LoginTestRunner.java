package testrunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/login.feature", // Specific to login feature
        glue = {"stepdefinition"},               // Location of step definitions
        plugin = {"pretty", "html:target/cucumber-login-report.html", "json:target/cucumber-login-report.json"},
        monochrome = true
)
public class LoginTestRunner {
}
