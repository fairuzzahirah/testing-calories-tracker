package testrunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"stepdefinition", "hooks"},
    tags = "@TC-020",
    plugin = {
        "pretty",
        "html:target/cucumber-reports/TC020-report",
        "json:target/cucumber-reports/TC020-report.json",
        "junit:target/cucumber-reports/TC020-report.xml"
    },
    monochrome = true
)
public class TC020Runner {
}
