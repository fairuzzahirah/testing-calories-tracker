package testrunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/usda-food-search.feature",
        glue = {"stepdefinition", "hooks"},
        tags = "@TC-039b",
        plugin = {
                "pretty",
                "html:target/cucumber-html-report-tc039b",
                "json:target/cucumber-json-report-tc039b.json"
        },
        monochrome = true
)
public class TC039bRunner {
}
