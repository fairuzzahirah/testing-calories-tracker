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
        tags = "@TC-001 or @TC-002 or @TC-003 or @TC-004 or @TC-005 or @TC-006 or @TC-007 or @TC-008 or @TC-009 or @TC-010 or @TC-011 or @TC-012 or @TC-013 or @TC-014 or @TC-015 or @TC-016 or @TC-017 or @TC-018 or @TC-019 or @TC-020 or @TC-021 or @TC-022 or @TC-023 or @TC-024 or @TC-025",
        plugin = {
                "pretty",
                "html:target/cucumber-reports/all-test-cases/html",
                "json:target/cucumber-reports/all-test-cases/cucumber.json",
                "junit:target/cucumber-reports/all-test-cases/cucumber.xml"
        },
        monochrome = true,
        publish = false
)
public class AllTC001to025Runner {
    
    @AfterClass
    public static void tearDown() {
        Hooks.quitDriver();
    }
}
