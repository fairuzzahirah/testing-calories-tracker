package testrunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/ai-chatbot.feature",
        glue = {"stepdefinition", "hooks"},
        tags = "@TC-043",
        plugin = {
                "pretty",
                "html:target/cucumber-html-report-tc043",
                "json:target/cucumber-json-report-tc043.json"
        },
        monochrome = true
)
public class TC043Runner {
}
