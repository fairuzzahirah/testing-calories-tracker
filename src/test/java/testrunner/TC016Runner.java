package testrunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features/ai-chatbot.feature",
    glue = {"stepdefinition", "hooks"},
    tags = "@TC-016",    plugin = {
        "pretty",
        "html:target/cucumber-reports/TC016-html-report",
        "json:target/cucumber-reports/TC016-json-report.json",
        "junit:target/cucumber-reports/TC016-junit-report.xml"
    },
    monochrome = true
)
public class TC016Runner {
    // This runner executes TC-016: Get food recommendations from AI chatbot
}
