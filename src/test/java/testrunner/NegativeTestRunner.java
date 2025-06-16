package testrunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.junit.AfterClass;
import stepdefinition.Hooks;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepdefinition"},
        tags = "@negative",        plugin = {
                "pretty",
                "html:target/cucumber-reports/negative/html",
                "json:target/cucumber-reports/negative/cucumber.json",
                "junit:target/cucumber-reports/negative/cucumber.xml"
        },
        monochrome = true,
        publish = true
)
public class NegativeTestRunner {
    
    @AfterClass
    public static void tearDown() {
        Hooks.quitDriver();
    }
}
