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
        tags = "@TC-003",
        plugin = {
                "pretty",
                "html:target/cucumber-reports/TC-003/html",
                "json:target/cucumber-reports/TC-003/cucumber.json",
                "junit:target/cucumber-reports/TC-003/cucumber.xml"
        },
        monochrome = true,
        publish = false
)
public class TC003Runner {
    
    @AfterClass
    public static void tearDown() {
        Hooks.quitDriver();
    }
}
