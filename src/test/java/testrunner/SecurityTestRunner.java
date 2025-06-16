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
        tags = "@security",        plugin = {
                "pretty",
                "html:target/cucumber-reports/security/html",
                "json:target/cucumber-reports/security/cucumber.json",
                "junit:target/cucumber-reports/security/cucumber.xml"
        },
        monochrome = true,
        publish = true
)
public class SecurityTestRunner {
    
    @AfterClass
    public static void tearDown() {
        Hooks.quitDriver();
    }
}
