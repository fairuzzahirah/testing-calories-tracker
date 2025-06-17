package testrunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/ai-chatbot.feature",
        glue = {"stepdefinition", "hooks"},
        tags = "@TC-045",
        plugin = {
                "pretty",
                "html:target/cucumber-html-report-tc045",
                "json:target/cucumber-json-report-tc045.json"
        },
        monochrome = true
)
public class TC045Runner {
}
