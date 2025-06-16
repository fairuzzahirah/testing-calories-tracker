# Calories Tracker Testing Framework

## Overview
Comprehensive testing framework untuk aplikasi Calories Tracker menggunakan Java, Selenium WebDriver, Cucumber (BDD), dan Maven. Framework ini mengimplementasikan Page Object Model (POM) dan menggunakan teknik Boundary Value Analysis, Equivalence Partitioning, dan Negative Testing.

## Tech Stack
- **Java 21**: Programming language
- **Selenium WebDriver 4.29.0**: Web automation
- **Cucumber 7.11.2**: BDD framework
- **JUnit 4.13.2**: Test runner
- **Maven**: Build and dependency management
- **WebDriverManager 5.3.2**: Automatic driver management

## Project Structure
```
testing-calories-tracker/
├── src/
│   └── test/
│       ├── java/
│       │   ├── pages/                 # Page Object Model classes
│       │   │   ├── RegisterPage.java
│       │   │   └── LoginPage.java
│       │   ├── stepdefinition/        # Cucumber step definitions
│       │   │   ├── RegisterStepDefinitions.java
│       │   │   ├── LoginStepDefinitions.java
│       │   │   └── Hooks.java
│       │   ├── testrunner/            # Test runners
│       │   │   ├── AllTestsRunner.java
│       │   │   ├── RegisterTestRunner.java
│       │   │   ├── LoginTestRunner.java
│       │   │   └── SmokeTestRunner.java
│       │   └── util/                  # Utility classes
│       │       └── TestContext.java
│       └── resources/
│           └── features/              # Gherkin feature files
│               ├── register.feature
│               └── login.feature
├── test-cases/                        # Test case documentation
├── bug-reports/                       # Bug report templates
├── target/                           # Generated reports
├── pom.xml                           # Maven configuration
└── run-auth-tests.ps1               # PowerShell execution script
```

## Features Tested

### Register Module
- **Positive Testing**: Valid registration scenarios
- **Boundary Value Analysis**: Age (13-100), Height (100-250cm), Weight (30-300kg)
- **Equivalence Partitioning**: Valid/invalid email formats, password combinations
- **Negative Testing**: Empty fields, invalid data, duplicate emails
- **Security Testing**: XSS, SQL injection prevention

### Login Module
- **Authentication Testing**: Valid/invalid credentials
- **Security Testing**: Brute force protection, input sanitization
- **Usability Testing**: Remember me functionality, navigation
- **Performance Testing**: Response time validation
- **Accessibility Testing**: Form accessibility features

## Test Execution

### Prerequisites
1. Java 21 installed
2. Maven installed
3. Chrome/Firefox browser installed
4. Calories Tracker application running on `http://localhost:8000`

### Running Tests

#### PowerShell Script (Recommended)
```powershell
# Run all tests
./run-auth-tests.ps1 -TestType all

# Run specific test suites
./run-auth-tests.ps1 -TestType register
./run-auth-tests.ps1 -TestType login
./run-auth-tests.ps1 -TestType smoke

# Run with different browsers
./run-auth-tests.ps1 -TestType all -Browser firefox

# Run in headless mode
./run-auth-tests.ps1 -TestType all -Headless

# Generate additional reports
./run-auth-tests.ps1 -TestType all -GenerateReport
```

#### Maven Commands
```bash
# Run all tests
mvn clean test -Dtest=AllTestsRunner

# Run specific test runners
mvn clean test -Dtest=RegisterTestRunner
mvn clean test -Dtest=LoginTestRunner
mvn clean test -Dtest=SmokeTestRunner

# Run tests with specific tags
mvn clean test -Dcucumber.filter.tags="@smoke"
mvn clean test -Dcucumber.filter.tags="@positive"
mvn clean test -Dcucumber.filter.tags="@negative"
mvn clean test -Dcucumber.filter.tags="@security"

# Run with different browsers
mvn clean test -Dbrowser=firefox -Dtest=AllTestsRunner

# Run in headless mode
mvn clean test -Dheadless=true -Dtest=AllTestsRunner
```

## Test Reports

### Generated Reports
After test execution, reports are generated in:
- `target/cucumber-reports/all-tests/index.html` - Main HTML report
- `target/cucumber-reports/AllTests.json` - JSON report
- `target/cucumber-reports/AllTests.xml` - JUnit XML report
- `target/cucumber-reports/timeline/index.html` - Timeline report

### Report Features
- Test scenario results with pass/fail status
- Screenshots for failed scenarios
- Execution timeline
- Step-by-step execution details
- Error messages and stack traces

## Testing Techniques Applied

### 1. Boundary Value Analysis
- **Age**: 13 (min), 18, 65, 100 (max), 0, 12 (invalid), 101, 150 (invalid)
- **Height**: 100 (min), 150, 180, 220, 250 (max), 0, 99 (invalid), 251, 300 (invalid)
- **Weight**: 30 (min), 50, 80, 150, 300 (max), 0, 29 (invalid), 301, 500 (invalid)

### 2. Equivalence Partitioning
#### Valid Classes:
- Email: Standard format, with dots, with plus signs, subdomains
- Password: 8+ characters with mixed case, numbers, symbols
- Dropdowns: All valid options for gender, goal, activity level

#### Invalid Classes:
- Email: Missing @, missing domain, double dots, invalid format
- Password: <8 characters, only numbers, only letters
- Fields: Empty values, out-of-range numbers

### 3. Negative Testing
- Empty required fields
- Invalid data formats
- SQL injection attempts
- XSS attack prevention
- Duplicate data handling
- Authentication failures

## Bug Reporting

### Bug Classification
- **Critical**: Security vulnerabilities, authentication bypass
- **High**: Functional failures, data corruption
- **Medium**: UI issues, validation problems
- **Low**: Cosmetic issues, minor usability problems

### Bug Report Template
Located in `bug-reports/bug-report-template.md` with:
- Bug classification (severity, priority, type)
- Reproduction steps
- Expected vs actual results
- Environment information
- Impact analysis
- Evidence (screenshots, logs)

## Test Case Management

### Test Case Categories
1. **@smoke**: Basic functionality tests
2. **@positive**: Happy path scenarios
3. **@negative**: Error handling tests
4. **@boundary**: Edge case testing
5. **@equivalence**: Class-based testing
6. **@security**: Security validation
7. **@usability**: User experience tests

### Test Documentation
- `test-cases/register-test-cases.md` - Register module test cases
- `test-cases/login-test-cases.md` - Login module test cases

## CI/CD Integration

### Jenkins Integration
```groovy
pipeline {
    agent any
    stages {
        stage('Test') {
            steps {
                bat 'mvn clean test -Dheadless=true -Dtest=AllTestsRunner'
            }
        }
        stage('Reports') {
            steps {
                publishHTML([
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'target/cucumber-reports/all-tests',
                    reportFiles: 'index.html',
                    reportName: 'Cucumber Reports'
                ])
            }
        }
    }
}
```

### GitHub Actions Integration
```yaml
name: Test Automation
on: [push, pull_request]
jobs:
  test:
    runs-on: windows-latest
    steps:
      - uses: actions/checkout@v2
      - uses: actions/setup-java@v2
        with:
          java-version: '21'
      - run: mvn clean test -Dheadless=true -Dtest=AllTestsRunner
      - uses: actions/upload-artifact@v2
        with:
          name: test-reports
          path: target/cucumber-reports/
```

## Best Practices

### Page Object Model
- Separate page classes for each application page
- Encapsulate page elements and actions
- Use PageFactory for element initialization
- Implement explicit waits for reliability

### Test Data Management
- Generate unique test data using timestamps
- Use parameterized tests for different data sets
- Separate test data from test logic
- Clean up test data after execution

### Error Handling
- Proper exception handling in page classes
- Screenshot capture for failed scenarios
- Detailed error messages in assertions
- Graceful cleanup in hooks

## Troubleshooting

### Common Issues
1. **WebDriver Issues**: Use WebDriverManager for automatic driver management
2. **Element Not Found**: Implement proper waits and element strategies
3. **Test Data Conflicts**: Use unique identifiers for test data
4. **Browser Compatibility**: Test with multiple browsers using parameters

### Debug Mode
Add `-Ddebug=true` to Maven command for verbose logging:
```bash
mvn clean test -Ddebug=true -Dtest=RegisterTestRunner
```

## Contributing

### Adding New Tests
1. Create feature file in `src/test/resources/features/`
2. Implement page object if needed in `src/test/java/pages/`
3. Create step definitions in `src/test/java/stepdefinition/`
4. Add test runner in `src/test/java/testrunner/`
5. Update documentation

### Code Standards
- Follow Java naming conventions
- Use meaningful method and variable names
- Add appropriate comments for complex logic
- Implement proper error handling
- Write clean, maintainable Gherkin scenarios

## Contact
For questions or issues, please contact the Test Automation Team.
