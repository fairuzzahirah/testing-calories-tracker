package testrunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features/profile-management.feature",
    glue = {"stepdefinition", "hooks"},
    tags = "@TC-023",
    plugin = {
        "pretty",
        "html:target/cucumber-reports/TC023-html",
        "json:target/cucumber-reports/TC023-json/cucumber.json",
        "junit:target/cucumber-reports/TC023-junit/cucumber.xml"
    },
    monochrome = true
)
public class TC023Runner {
}
