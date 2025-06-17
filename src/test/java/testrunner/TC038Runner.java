package testrunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/usda-food-search.feature",
        glue = {"stepdefinition", "hooks"},
        tags = "@TC-038",
        plugin = {
                "pretty",
                "html:target/cucumber-html-report-tc038",
                "json:target/cucumber-json-report-tc038.json"
        },
        monochrome = true
)
public class TC038Runner {
}
