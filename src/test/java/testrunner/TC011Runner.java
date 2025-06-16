package testrunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"stepdefinition", "hooks"},
    tags = "@TC-011",
    plugin = {
        "pretty",
        "html:target/cucumber-reports/TC011-report",
        "json:target/cucumber-reports/TC011-report.json",
        "junit:target/cucumber-reports/TC011-report.xml"
    },
    monochrome = true
)
public class TC011Runner {
    // Test Runner for TC-011: Add new custom food
}
