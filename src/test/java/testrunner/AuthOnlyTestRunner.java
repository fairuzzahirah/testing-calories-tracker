package testrunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/authentication.feature",
        glue = {"stepdefinition"},
        tags = "@smoke",
        plugin = {
                "pretty",
                "html:target/cucumber-reports/auth-only/html",
                "json:target/cucumber-reports/auth-only/cucumber.json",
                "junit:target/cucumber-reports/auth-only/cucumber.xml"
        },
        monochrome = true,
        publish = true
)
public class AuthOnlyTestRunner {
}
