package testrunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"stepdefinition", "hooks"},
    tags = "@TC-018",
    plugin = {
        "pretty",
        "html:target/cucumber-reports/TC018-report",
        "json:target/cucumber-reports/TC018-report.json",
        "junit:target/cucumber-reports/TC018-report.xml"
    },
    monochrome = true
)
public class TC018Runner {
}
