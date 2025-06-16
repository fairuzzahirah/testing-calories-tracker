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
        tags = "@positive",        plugin = {
                "pretty",
                "html:target/cucumber-reports/positive/html",
                "json:target/cucumber-reports/positive/cucumber.json",
                "junit:target/cucumber-reports/positive/cucumber.xml"
        },
        monochrome = true,
        publish = true
)
public class PositiveTestRunner {
    
    @AfterClass
    public static void tearDown() {
        Hooks.quitDriver();
    }
}
