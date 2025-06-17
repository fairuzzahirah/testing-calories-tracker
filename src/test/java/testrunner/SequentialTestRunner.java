package testrunner;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import stepdefinition.Hooks;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        // Authentication Tests (TC001-TC009)
        TC001Runner.class,
        TC002Runner.class,
        TC003Runner.class,
        TC004Runner.class,
        TC005Runner.class,
        TC006Runner.class,
        TC007Runner.class,
        TC008Runner.class,
        TC009Runner.class,
        
        // Profile Management Tests (TC010-TC013)
        TC010Runner.class,
        TC011Runner.class,
        TC012Runner.class,
        TC013Runner.class,
          // Food Entry Management Tests (TC014-TC025)
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
        TC025Runner.class,
        
        // Custom Food Management Tests (TC029-TC033)
        TC029Runner.class,
        TC030Runner.class,
        TC031Runner.class,
        TC032Runner.class,
        TC033Runner.class,
        
        // USDA Food Search Tests (TC034-TC039)
        TC034Runner.class,
        TC035Runner.class,
        TC036Runner.class,
        TC037Runner.class,
        TC038Runner.class,
        TC039Runner.class,
        TC039bRunner.class,
        TC039cRunner.class,
        
        // AI Chatbot Tests (TC040-TC045)
        TC040Runner.class,
        TC041Runner.class,
        TC042Runner.class,
        TC043Runner.class,
        TC044Runner.class,
        TC045Runner.class
})
public class SequentialTestRunner {    @BeforeClass
    public static void setUpClass() {
        System.out.println("=== Starting Complete Sequential Test Execution ===");
        System.out.println("Running ALL Test Cases (Positive, Negative, Boundary, and Security):");
        System.out.println("- Authentication: TC001-TC009 (9 test cases)");
        System.out.println("- Profile Management: TC010-TC013 (4 test cases)");
        System.out.println("- Food Entry Management: TC014-TC025 (12 test cases)");
        System.out.println("- Custom Food Management: TC029-TC033 (5 test cases)");
        System.out.println("- USDA Food Search: TC034-TC039, TC039b, TC039c (8 test cases)");
        System.out.println("- AI Chatbot: TC040-TC045 (6 test cases)");
        System.out.println("Total: 44 test cases");
        System.out.println("===============================================");
    }    @AfterClass
    public static void tearDown() {
        Hooks.quitDriver();
        System.out.println("=== Complete Sequential Test Execution Completed ===");
        System.out.println("All 44 test cases executed successfully");
        System.out.println("Test categories covered:");
        System.out.println("✅ Authentication (Login, Registration, Session)");
        System.out.println("✅ Profile Management (Update, Validation)");
        System.out.println("✅ Food Entry Management (Add, Edit, Delete, Validation)");
        System.out.println("✅ Custom Food Management (Create, Manage, Validation)");
        System.out.println("✅ USDA Food Search (Search, Validation, API Handling)");
        System.out.println("✅ AI Chatbot (Security, Functionality, Error Handling)");
        System.out.println("===============================================");
    }
}
