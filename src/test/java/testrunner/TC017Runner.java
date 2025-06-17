package testrunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features/ai-chatbot.feature",
    glue = {"stepdefinition", "hooks"},
    tags = "@TC-017",    plugin = {
        "pretty",
        "html:target/cucumber-reports/TC017-html-report",
        "json:target/cucumber-reports/TC017-json-report.json",
        "junit:target/cucumber-reports/TC017-junit-report.xml"
    },
    monochrome = true
)
public class TC017Runner {
    // This runner executes TC-017: View chat history
}
