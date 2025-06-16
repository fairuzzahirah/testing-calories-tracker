# Calories Tracker Automated Testing Guide

## Overview
This comprehensive test suite covers all major functionalities of the Calories Tracker application using Java, Selenium WebDriver, and Cucumber BDD framework.

## Test Coverage
Based on the **53 test cases** from `CALORIES_TRACKER_TEST_CASES.tsv`:

### ✅ **Implemented Features**
1. **Authentication** (TC-001 to TC-006, TC-024)
   - Login with valid/invalid credentials
   - User registration
   - Logout functionality

2. **Food Entry Management** (TC-007 to TC-010, TC-029 to TC-032)
   - Add, edit, delete food entries
   - Validation and security testing

3. **Custom Food Management** (TC-011 to TC-013, TC-033 to TC-036)
   - Manage custom foods
   - Duplicate name validation
   - Access control testing

4. **USDA Food Search** (TC-014 to TC-015, TC-037 to TC-040)
   - Search USDA database
   - Add USDA foods to log
   - API error handling

5. **AI Chatbot** (TC-016 to TC-018, TC-041 to TC-045)
   - Get food recommendations
   - Chat history management
   - Rate limiting and security

6. **Dashboard Analytics** (TC-019 to TC-020, TC-046 to TC-048)
   - View analytics and progress
   - Handle empty states
   - Error handling for data issues

7. **Profile Management** (TC-021 to TC-023, TC-049 to TC-053)
   - Update profile information
   - Password management
   - Account deletion

## Project Structure
```
testing-calories-tracker/
├── src/test/java/
│   ├── pages/                    # Page Object Model classes
│   │   ├── LoginPage.java
│   │   ├── RegisterPage.java
│   │   ├── DashboardPage.java
│   │   ├── FoodEntryPage.java
│   │   ├── CustomFoodPage.java
│   │   ├── USDASearchPage.java
│   │   ├── ChatbotPage.java
│   │   └── ProfilePage.java
│   ├── stepdefinition/           # Cucumber step definitions
│   │   ├── AuthenticationSteps.java
│   │   ├── FoodEntrySteps.java
│   │   ├── CustomFoodSteps.java
│   │   ├── USDASearchSteps.java
│   │   ├── ChatbotSteps.java
│   │   ├── DashboardSteps.java
│   │   ├── ProfileSteps.java
│   │   └── Hooks.java
│   ├── testrunner/               # Test runners
│   │   ├── AllTestRunner.java
│   │   ├── SmokeTestRunner.java
│   │   ├── PositiveTestRunner.java
│   │   ├── NegativeTestRunner.java
│   │   └── SecurityTestRunner.java
│   └── util/                     # Utility classes
│       ├── DriverManager.java
│       ├── ConfigReader.java
│       └── TestDataGenerator.java
├── src/test/resources/
│   ├── features/                 # Gherkin feature files
│   │   ├── authentication.feature
│   │   ├── food-entry-management.feature
│   │   ├── custom-food-management.feature
│   │   ├── usda-food-search.feature
│   │   ├── ai-chatbot.feature
│   │   ├── dashboard-analytics.feature
│   │   └── profile-management.feature
│   └── config.properties         # Configuration file
├── Run-CaloriesTrackerTests.ps1  # PowerShell execution script
└── *.bat                         # Batch execution scripts
```

## Prerequisites
1. **Java 21** installed
2. **Maven** installed
3. **Chrome/Firefox/Edge** browser
4. **Calories Tracker application** running:
   - Frontend: http://localhost:8001
   - Backend: http://localhost:8000

## Quick Start

### 1. Using PowerShell (Recommended)
```powershell
# Run all tests
.\Run-CaloriesTrackerTests.ps1

# Run specific test types
.\Run-CaloriesTrackerTests.ps1 -TestType smoke
.\Run-CaloriesTrackerTests.ps1 -TestType negative
.\Run-CaloriesTrackerTests.ps1 -TestType security

# Run in headless mode
.\Run-CaloriesTrackerTests.ps1 -TestType smoke -Headless $true

# Run with different browser
.\Run-CaloriesTrackerTests.ps1 -Browser firefox
```

### 2. Using Batch Files
```batch
run-smoke-tests.bat     # Run smoke tests only
run-negative-tests.bat  # Run negative test scenarios
run-all-tests.bat       # Run complete test suite
```

### 3. Using Maven Commands
```bash
# Run all tests
mvn clean test -Dtest=AllTestRunner

# Run smoke tests
mvn clean test -Dtest=SmokeTestRunner

# Run with specific browser
mvn clean test -Dtest=SmokeTestRunner -Dbrowser=firefox -Dheadless=true
```

## Test Tags and Organization

### 🏷️ **Available Tags**
- `@smoke` - Critical functionality tests
- `@positive` - Happy path scenarios
- `@negative` - Error handling and validation
- `@security` - Security and access control
- `@api` - API integration tests
- `@performance` - Performance and timeout tests

### 📊 **Test Categories**
| Category | Positive Tests | Negative Tests | Total |
|----------|---------------|---------------|-------|
| Authentication | 4 | 3 | 7 |
| Food Entry | 4 | 4 | 8 |
| Custom Food | 3 | 4 | 7 |
| USDA Search | 2 | 4 | 6 |
| AI Chatbot | 3 | 5 | 8 |
| Dashboard | 2 | 3 | 5 |
| Profile | 3 | 5 | 8 |
| **Total** | **21** | **28** | **49** |

## Test Reporting

### 📈 **Report Generation**
After test execution, reports are generated in:
- **HTML Report**: `target/cucumber-reports/{test-type}/index.html`
- **JSON Report**: `target/cucumber-reports/{test-type}/Cucumber.json`
- **JUnit XML**: `target/cucumber-reports/{test-type}/Cucumber.xml`

### 📸 **Screenshots**
- Automatically captured on test failures
- Attached to Cucumber reports
- Stored in test execution context

## Configuration

### ⚙️ **config.properties**
```properties
# Application URLs
base.url=http://localhost:8001
backend.url=http://localhost:8000

# Browser settings
browser=chrome
headless=false
timeout=10

# Test credentials
demo.email=demo@example.com
demo.password=password123
```

## Best Practices

### ✅ **Implemented Patterns**
1. **Page Object Model** - Organized, maintainable page classes
2. **BDD with Gherkin** - Human-readable test scenarios
3. **Data-Driven Testing** - Parameterized test scenarios
4. **Cross-Browser Support** - Chrome, Firefox, Edge compatibility
5. **Screenshot Capture** - Automatic failure documentation
6. **Parallel Execution Ready** - Configurable parallel runs

### 🔒 **Security Testing**
- XSS injection testing
- Access control validation
- Session management verification
- Input sanitization checks

### 🚀 **Performance Considerations**
- API timeout handling
- Database connection testing
- Loading state verification
- Responsive design validation

## Troubleshooting

### ❌ **Common Issues**
1. **Application not running**: Ensure both frontend and backend are started
2. **Driver issues**: WebDriverManager handles driver downloads automatically
3. **Port conflicts**: Check if ports 8001 and 8000 are available
4. **Test data**: Demo user should exist with email `demo@example.com`

### 🔧 **Debug Mode**
```bash
# Enable debug logging
mvn clean test -Dtest=SmokeTestRunner -Dmaven.surefire.debug=true

# Run single feature
mvn clean test -Dtest=AllTestRunner -Dcucumber.features="src/test/resources/features/authentication.feature"
```

## Continuous Integration

### 🔄 **CI/CD Integration**
The test suite is designed for CI/CD pipeline integration:
- Headless browser execution
- JUnit XML output for Jenkins/Azure DevOps
- JSON reports for custom dashboards
- Exit codes for pipeline decisions

### 📝 **Example Jenkins Pipeline**
```groovy
pipeline {
    agent any
    stages {
        stage('Test') {
            steps {
                bat 'mvn clean test -Dtest=SmokeTestRunner -Dheadless=true'
            }
            post {
                always {
                    publishHTML([
                        allowMissing: false,
                        alwaysLinkToLastBuild: true,
                        keepAll: true,
                        reportDir: 'target/cucumber-reports/smoke',
                        reportFiles: 'index.html',
                        reportName: 'Cucumber Report'
                    ])
                }
            }
        }
    }
}
```

## Support
For issues or questions about the test suite, check:
1. Test execution logs in `target/surefire-reports/`
2. Screenshot evidence in Cucumber reports
3. Application server logs
4. Browser console errors (captured in test context)

---
**✨ Test Suite Status**: Ready for Production Use  
**📅 Last Updated**: June 16, 2025  
**👥 Test Coverage**: 53 test cases implemented
