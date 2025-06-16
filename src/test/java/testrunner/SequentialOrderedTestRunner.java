package testrunner;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.Test;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

/**
 * Sequential Test Runner - Executes test cases one by one in order
 * TC-001 through TC-051
 */
public class SequentialOrderedTestRunner {
    
    private static final String[] TEST_CASES = {
        "TC-001", "TC-002", "TC-003", "TC-004", "TC-005", "TC-006", "TC-007", 
        "TC-008", "TC-009", "TC-010", "TC-011", "TC-012", "TC-013", "TC-014", 
        "TC-015", "TC-016", "TC-017", "TC-018", "TC-019", "TC-020", "TC-021", 
        "TC-022", "TC-023", "TC-024", "TC-029", "TC-030", "TC-031", "TC-032", 
        "TC-033", "TC-034", "TC-035", "TC-036", "TC-037", "TC-038", "TC-039", 
        "TC-039b", "TC-039c", "TC-040", "TC-041", "TC-042", "TC-043", "TC-044", 
        "TC-045", "TC-046", "TC-047", "TC-048", "TC-049", "TC-050", "TC-051"
    };
    
    public static void main(String[] args) {
        System.out.println("===========================================");
        System.out.println("    SEQUENTIAL TEST EXECUTION STARTING");
        System.out.println("===========================================");
        
        int totalTests = TEST_CASES.length;
        int passedTests = 0;
        int failedTests = 0;
        
        for (int i = 0; i < TEST_CASES.length; i++) {
            String testCase = TEST_CASES[i];
            System.out.println();
            System.out.println(">>> Executing " + testCase + " (" + (i+1) + "/" + totalTests + ")");
            System.out.println("===========================================");
            
            try {
                // Create individual test runner for this TC
                String command = "mvn test -Dcucumber.filter.tags=\"@" + testCase + "\"";
                
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", command);
                pb.inheritIO();
                Process process = pb.start();
                int exitCode = process.waitFor();
                
                if (exitCode == 0) {
                    System.out.println("✓ " + testCase + " PASSED");
                    passedTests++;
                } else {
                    System.out.println("✗ " + testCase + " FAILED");
                    failedTests++;
                }
                
            } catch (Exception e) {
                System.err.println("Error executing " + testCase + ": " + e.getMessage());
                failedTests++;
            }
            
            System.out.println("===========================================");
        }
        
        System.out.println();
        System.out.println("===========================================");
        System.out.println("    SEQUENTIAL TEST EXECUTION COMPLETED");
        System.out.println("===========================================");
        System.out.println("Total Tests: " + totalTests);
        System.out.println("Passed: " + passedTests);
        System.out.println("Failed: " + failedTests);
        System.out.println("Success Rate: " + (passedTests * 100 / totalTests) + "%");
        System.out.println("===========================================");
    }
}
