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
        plugin = {
                "pretty",
                "html:target/cucumber-reports/all/html",
                "json:target/cucumber-reports/all/cucumber.json",
                "junit:target/cucumber-reports/all/cucumber.xml"
        },
        monochrome = true,
        publish = true
)
public class AllTestRunner {
    
    @AfterClass
    public static void tearDown() {
        Hooks.quitDriver();
    }
}
