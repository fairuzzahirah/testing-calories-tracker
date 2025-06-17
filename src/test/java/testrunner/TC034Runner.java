package testrunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features/custom-food-management.feature",
    glue = {"stepdefinition", "hooks"},
    tags = "@TC-034",
    plugin = {
        "pretty",
        "html:target/cucumber-reports/TC034-html-report",
        "json:target/cucumber-reports/TC034-json-report.json",
        "junit:target/cucumber-reports/TC034-junit-report.xml"
    },
    monochrome = true
)
public class TC034Runner {
    // This runner executes TC-034: Edit custom food belonging to another user
}
