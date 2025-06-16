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
        tags = "@boundary",
        plugin = {
                "pretty",
                "html:target/cucumber-reports/boundary-value/html",
                "json:target/cucumber-reports/boundary-value/cucumber.json",
                "junit:target/cucumber-reports/boundary-value/cucumber.xml"
        },
        monochrome = true,
        publish = false
)
public class BoundaryValueTestRunner {
    
    @AfterClass
    public static void tearDown() {
        Hooks.quitDriver();
    }
}
