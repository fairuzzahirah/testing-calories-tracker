package testrunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features/authentication.feature",
    glue = {"stepdefinition", "hooks"},
    tags = "@TC-025",
    plugin = {
        "pretty",
        "html:target/cucumber-reports/TC025-html",
        "json:target/cucumber-reports/TC025-json/cucumber.json",
        "junit:target/cucumber-reports/TC025-junit/cucumber.xml"
    },
    monochrome = true
)
public class TC025Runner {
}
