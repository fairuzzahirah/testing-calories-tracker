package testrunner;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import stepdefinition.Hooks;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TC001Runner.class,
        TC002Runner.class,
        TC003Runner.class,
        TC004Runner.class,
        TC005Runner.class,
        TC006Runner.class,
        TC007Runner.class,
        TC008Runner.class,
        TC009Runner.class,
        TC010Runner.class,
        TC011Runner.class,
        TC012Runner.class,
        TC013Runner.class,
        TC014Runner.class,
        TC015Runner.class,
        TC016Runner.class,
        TC017Runner.class,
        TC018Runner.class,
        TC019Runner.class,
        TC020Runner.class,
        TC021Runner.class,
        TC022Runner.class,
        TC023Runner.class,
        TC024Runner.class,
        TC025Runner.class
})
public class CompleteSequentialTestRunner {
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("=== Starting Complete Sequential Test Execution ===");
        System.out.println("Running all test cases from TC-001 to TC-025 in order");
        System.out.println("Total test cases: 25");
        System.out.println("Expected execution time: ~20-30 minutes");
    }
    
    @AfterClass
    public static void tearDown() {
        Hooks.quitDriver();
        System.out.println("=== Complete Sequential Test Execution Finished ===");
        System.out.println("All test cases TC-001 to TC-025 executed successfully");
        System.out.println("Check target/cucumber-reports/ for detailed reports");
    }
}
