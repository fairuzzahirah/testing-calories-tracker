package testrunner;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import stepdefinition.Hooks;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        // Quick validation of core negative scenarios
        TC014Runner.class,  // Empty food name validation
        TC015Runner.class,  // Invalid calories input
        TC029Runner.class,  // Empty custom food name
        TC030Runner.class,  // Invalid custom food nutrition
        TC034Runner.class,  // Empty USDA search query
        TC035Runner.class,  // Invalid USDA search characters
        TC040Runner.class,  // Empty chatbot query
        TC044Runner.class   // XSS security test
})
public class QuickValidationRunner {
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("=== Starting Quick Validation Test Suite ===");
        System.out.println("Running 8 core negative and security test cases");
        System.out.println("Estimated duration: 3-5 minutes");
    }
    
    @AfterClass
    public static void tearDown() {
        Hooks.quitDriver();
        System.out.println("=== Quick Validation Test Suite Completed ===");
        System.out.println("Core validation tests executed successfully");
    }
}
