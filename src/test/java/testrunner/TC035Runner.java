package testrunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features/custom-food-management.feature",
    glue = {"stepdefinition", "hooks"},
    tags = "@TC-035",
    plugin = {
        "pretty",
        "html:target/cucumber-reports/TC035-html-report",
        "json:target/cucumber-reports/TC035-json-report.json",
        "junit:target/cucumber-reports/TC035-junit-report.xml"
    },
    monochrome = true
)
public class TC035Runner {
    // This runner executes TC-035: Delete custom food that is being used in food entries
}
