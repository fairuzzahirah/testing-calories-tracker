package testrunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features/food-entry-management.feature",
    glue = {"stepdefinition", "hooks"},
    tags = "@TC-029",
    plugin = {
        "pretty",
        "html:target/cucumber-reports/TC029-html",
        "json:target/cucumber-reports/TC029-json/cucumber.json",
        "junit:target/cucumber-reports/TC029-junit/cucumber.xml"
    },
    monochrome = true
)
public class TC029Runner {
}
