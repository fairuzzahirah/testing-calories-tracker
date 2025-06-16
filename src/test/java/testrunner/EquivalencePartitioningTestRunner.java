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
        tags = "@equivalence",
        plugin = {
                "pretty",
                "html:target/cucumber-reports/equivalence-partitioning/html",
                "json:target/cucumber-reports/equivalence-partitioning/cucumber.json",
                "junit:target/cucumber-reports/equivalence-partitioning/cucumber.xml"
        },
        monochrome = true,
        publish = false
)
public class EquivalencePartitioningTestRunner {
    
    @AfterClass
    public static void tearDown() {
        Hooks.quitDriver();
    }
}
