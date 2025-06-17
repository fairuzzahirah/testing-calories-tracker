package testrunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features/usda-food-search.feature",
    glue = {"stepdefinition", "hooks"},
    tags = "@TC-015",    plugin = {
        "pretty",
        "html:target/cucumber-reports/TC015-html-report",
        "json:target/cucumber-reports/TC015-json-report.json",
        "junit:target/cucumber-reports/TC015-junit-report.xml"
    },
    monochrome = true
)
public class TC015Runner {
    // This runner executes TC-015: Add USDA food to food entries
}
