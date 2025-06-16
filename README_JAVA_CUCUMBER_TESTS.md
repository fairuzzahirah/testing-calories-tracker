# Calories Tracker - Java Cucumber Test Suite

## Overview
This test suite provides comprehensive automated testing for the Calories Tracker application using Java, Selenium WebDriver, and Cucumber BDD framework.

## Test Coverage
The test suite covers all major features based on the comprehensive test cases:

### Features Tested:
1. **Authentication** (Login/Register/Logout)
2. **Food Entry Management** (CRUD operations)
3. **Custom Food Management** (CRUD operations)
4. **USDA Food Search** (Search and add to entries)
5. **AI Chatbot** (Interactions and chat history)
6. **Dashboard Analytics** (Data display and error handling)
7. **Profile Management** (Update profile and password)

### Test Types:
- **Positive Tests** (@positive): Happy path scenarios
- **Negative Tests** (@negative): Error handling and validation
- **Smoke Tests** (@smoke): Critical functionality
- **Security Tests** (@security): XSS, unauthorized access
- **API Tests** (@api): Backend API integration
- **Performance Tests** (@performance): Response time validation

## Project Structure
```
src/test/
├── java/
│   ├── hooks/
│   │   └── TestHooks.java              # Setup/Teardown hooks
│   ├── pages/
│   │   ├── LoginPage.java              # Login page objects
│   │   ├── RegisterPage.java           # Registration page objects
│   │   ├── DashboardPage.java          # Dashboard page objects
│   │   └── FoodEntryPage.java          # Food entry page objects
│   ├── stepdefinition/
│   │   ├── AuthenticationSteps.java    # Auth step definitions
│   │   └── FoodEntrySteps.java         # Food entry step definitions
│   ├── testrunner/
│   │   ├── AllTestRunner.java          # Run all tests
│   │   ├── SmokeTestRunner.java        # Run smoke tests only
│   │   ├── PositiveTestRunner.java     # Run positive tests only
│   │   └── NegativeTestRunner.java     # Run negative tests only
│   └── util/
│       ├── DriverManager.java          # WebDriver management
│       ├── ConfigReader.java           # Configuration reader
│       └── TestDataGenerator.java      # Test data utilities
└── resources/
    ├── features/
    │   ├── authentication.feature      # Auth scenarios
    │   ├── food-entry-management.feature
    │   ├── custom-food-management.feature
    │   ├── usda-food-search.feature
    │   ├── ai-chatbot.feature
    │   ├── dashboard-analytics.feature
    │   └── profile-management.feature
    └── config.properties               # Test configuration
```

## Prerequisites
1. **Java 21** installed
2. **Maven** installed
3. **Chrome/Firefox/Edge** browser installed
4. **Calories Tracker Application** running on:
   - Frontend: http://localhost:8001
   - Backend: http://localhost:8000

## Test Execution

### Run All Tests
```bash
mvn test -Dtest=AllTestRunner
```

### Run Smoke Tests Only
```bash
mvn test -Dtest=SmokeTestRunner
```

### Run Positive Tests Only
```bash
mvn test -Dtest=PositiveTestRunner
```

### Run Negative Tests Only
```bash
mvn test -Dtest=NegativeTestRunner
```

### Run with Different Browser
```bash
mvn test -Dtest=SmokeTestRunner -Dbrowser=firefox
```

### Run in Headless Mode
```bash
mvn test -Dtest=AllTestRunner -Dheadless=true
```

### Run Specific Feature
```bash
mvn test -Dtest=AllTestRunner -Dcucumber.options="--tags @authentication"
```

## Test Tags
- `@smoke` - Critical functionality tests
- `@positive` - Happy path scenarios
- `@negative` - Error handling tests
- `@security` - Security-related tests
- `@api` - API integration tests
- `@performance` - Performance validation tests

## Test Reports
After test execution, reports are generated in:
- **HTML Report**: `target/cucumber-reports/index.html`
- **JSON Report**: `target/cucumber-reports/Cucumber.json`
- **XML Report**: `target/cucumber-reports/Cucumber.xml`

## Configuration
Modify `src/test/resources/config.properties` to change:
- Application URLs
- Browser settings
- Timeouts
- Test credentials

## Test Data
The suite uses:
- **Demo User**: demo@example.com / password123
- **Generated Data**: Random test data for new users
- **Test Scenarios**: Based on comprehensive test case document

## CI/CD Integration
The test suite can be integrated with CI/CD pipelines:

### Jenkins
```groovy
pipeline {
    agent any
    stages {
        stage('Test') {
            steps {
                sh 'mvn test -Dtest=SmokeTestRunner'
            }
        }
    }
    post {
        always {
            publishHTML([
                allowMissing: false,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'target/cucumber-reports',
                reportFiles: 'index.html',
                reportName: 'Cucumber Report'
            ])
        }
    }
}
```

### GitHub Actions
```yaml
name: Automated Tests
on: [push, pull_request]
jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - uses: actions/setup-java@v2
        with:
          java-version: '21'
      - run: mvn test -Dtest=SmokeTestRunner -Dheadless=true
```

## Troubleshooting

### Common Issues:
1. **WebDriver Issues**: Ensure WebDriverManager can download drivers
2. **Application Not Running**: Start the application before running tests
3. **Port Conflicts**: Verify application is running on correct ports
4. **Browser Compatibility**: Update browser to latest version

### Debug Mode:
```bash
mvn test -Dtest=SmokeTestRunner -Dheadless=false -X
```

## Test Maintenance
- Update page objects when UI changes
- Add new scenarios when new features are added
- Update test data when business rules change
- Review and update selectors regularly

## Contributing
When adding new tests:
1. Follow existing naming conventions
2. Use appropriate tags
3. Add meaningful assertions
4. Update documentation
5. Ensure tests are independent and can run in any order
