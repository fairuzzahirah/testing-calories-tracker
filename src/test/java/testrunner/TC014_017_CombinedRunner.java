package testrunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = {
        "src/test/resources/features/usda-food-search.feature",
        "src/test/resources/features/ai-chatbot.feature"
    },
    glue = {"stepdefinition", "hooks"},
    tags = "@TC-014 or @TC-015 or @TC-016 or @TC-017",
    plugin = {
        "pretty",
        "html:target/cucumber-reports/TC014-017-combined-html-report",
        "json:target/cucumber-reports/TC014-017-combined-json-report.json",
        "junit:target/cucumber-reports/TC014-017-combined-junit-report.xml"
    },
    monochrome = true
)
public class TC014_017_CombinedRunner {
    // This runner executes all test cases TC-014 to TC-017:
    // TC-014: Search for food in USDA database
    // TC-015: Add USDA food to food entries  
    // TC-016: Get food recommendations from AI chatbot
    // TC-017: View chat history
}
