package testrunner;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import stepdefinition.Hooks;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepdefinition"},
        tags = "@smoke",        plugin = {
                "pretty",
                "html:target/cucumber-reports/smoke/html",
                "json:target/cucumber-reports/smoke/cucumber.json",
                "junit:target/cucumber-reports/smoke/cucumber.xml"
        },
        monochrome = true,
        publish = true
)
public class SmokeTestRunner {
    
    @AfterClass
    public static void tearDown() {
        Hooks.quitDriver();
    }
}
