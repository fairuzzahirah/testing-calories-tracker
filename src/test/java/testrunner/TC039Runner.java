package testrunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/usda-food-search.feature",
        glue = {"stepdefinition", "hooks"},
        tags = "@TC-039",
        plugin = {
                "pretty",
                "html:target/cucumber-html-report-tc039",
                "json:target/cucumber-json-report-tc039.json"
        },
        monochrome = true
)
public class TC039Runner {
}
