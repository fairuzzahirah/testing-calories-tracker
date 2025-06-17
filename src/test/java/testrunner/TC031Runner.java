package testrunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features/food-entry-management.feature",
    glue = {"stepdefinition", "hooks"},
    tags = "@TC-031",
    plugin = {
        "pretty"
    },
    monochrome = true
)
public class TC031Runner {
    // This class remains empty. It serves as an entry point for JUnit to run Cucumber scenarios.
}
