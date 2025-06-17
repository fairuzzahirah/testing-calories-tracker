package testrunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features/usda-food-search.feature",
    glue = {"stepdefinition", "hooks"},
    tags = "@TC-014",    plugin = {
        "pretty",
        "html:target/cucumber-reports/TC014-html-report",
        "json:target/cucumber-reports/TC014-json-report.json",
        "junit:target/cucumber-reports/TC014-junit-report.xml"
    },
    monochrome = true
)
public class TC014Runner {
    // This runner executes TC-014: Search for food in USDA database
}
