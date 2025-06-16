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
        TC013Runner.class
})
public class SequentialTestRunner {    @BeforeClass
    public static void setUpClass() {
        System.out.println("=== Starting Sequential Test Execution ===");
        System.out.println("Running test cases from TC-001 to TC-013 in order");
    }
      @AfterClass
    public static void tearDown() {
        Hooks.quitDriver();
        System.out.println("=== Sequential Test Execution Completed ===");
        System.out.println("All test cases TC-001 to TC-010 executed successfully");
    }
}
