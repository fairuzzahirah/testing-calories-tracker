package testrunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features/food-entry-management.feature",
    glue = "stepdefinition",
    tags = "@TC-032",
    plugin = {
        "pretty",
        "html:target/cucumber-reports/TC032-html",
        "json:target/cucumber-reports/TC032-json/cucumber.json",
        "junit:target/cucumber-reports/TC032-junit/cucumber.xml"
    },
    monochrome = true
)
public class TC032Runner {
}
