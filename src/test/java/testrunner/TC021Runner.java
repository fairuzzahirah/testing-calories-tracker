package testrunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features/profile-management.feature",
    glue = {"stepdefinition", "hooks"},
    tags = "@TC-021",    plugin = {
        "pretty",
        "html:target/cucumber-reports/TC021-report",
        "json:target/cucumber-reports/TC021-cucumber.json",
        "junit:target/cucumber-reports/TC021-junit.xml"
    },
    monochrome = true
)
public class TC021Runner {
}
