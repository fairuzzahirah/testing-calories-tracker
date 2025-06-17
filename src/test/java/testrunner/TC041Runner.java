package testrunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/ai-chatbot.feature",
        glue = {"stepdefinition", "hooks"},
        tags = "@TC-041",
        plugin = {
                "pretty",
                "html:target/cucumber-html-report-tc041",
                "json:target/cucumber-json-report-tc041.json"
        },
        monochrome = true
)
public class TC041Runner {
}
