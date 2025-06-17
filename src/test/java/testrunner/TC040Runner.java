package testrunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features/usda-food-search.feature",
    glue = {"stepdefinition", "hooks"},
    tags = "@TC-040",
    plugin = {
        "pretty",
        "html:target/cucumber-reports/TC040",
        "json:target/cucumber-reports/TC040.json"
    }
)
public class TC040Runner {
}
