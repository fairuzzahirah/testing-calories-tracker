package testrunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/custom-food-management.feature",
        glue = {"stepdefinition", "hooks"},
        tags = "@TC-036",
        plugin = {
                "pretty",
                "html:target/cucumber-html-report-tc036",
                "json:target/cucumber-json-report-tc036.json"
        },
        monochrome = true
)
public class TC036Runner {
}
