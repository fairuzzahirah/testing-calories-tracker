# Calories Tracker Automation Testing Framework

A comprehensive Selenium-based automation testing framework for the Calories Tracker web application using Java, Cucumber BDD, and Maven.

## 🚀 Project Overview

This testing framework provides end-to-end automation testing for a full-stack calories tracking application, covering authentication, profile management, food entry management, custom food creation, USDA API integration, and AI chatbot functionality.

## 🛠️ Technology Stack

- **Programming Language**: Java 21
- **Testing Framework**: Selenium WebDriver 4.29.0
- **BDD Framework**: Cucumber 7.18.0
- **Build Tool**: Maven 3.x
- **Browser Support**: Chrome (ChromeDriver auto-managed)
- **Test Runner**: JUnit 4.13.2
- **Reporting**: Cucumber HTML Reports, JSON Reports

## 📋 Test Coverage

### Authentication (TC001-TC009)
- User registration and validation
- Login functionality and session management
- Password security and error handling

### Profile Management (TC010-TC013)
- Profile creation and updates
- Data validation and error handling
- User preferences management

### Food Entry Management (TC014-TC025)
- Add, edit, delete food entries
- Calorie calculations and validations
- Date and time handling
- Input validation and error scenarios

### Custom Food Management (TC029-TC033)
- Create custom food items
- Nutritional information validation
- Custom food database management
- Error handling and edge cases

### USDA Food Search (TC034-TC039)
- USDA API integration testing
- Food search functionality
- API error handling and fallback scenarios
- Boundary testing for search parameters

### AI Chatbot (TC040-TC045)
- Chatbot functionality testing
- Security testing (XSS prevention)
- Error handling and API failures
- Rate limiting validation

## 🗂️ Project Structure

```
testing-calories-tracker/
├── src/
│   └── test/
│       ├── java/
│       │   ├── hooks/
│       │   │   └── Hooks.java              # Test setup and teardown
│       │   ├── pages/                      # Page Object Model
│       │   │   ├── AuthenticationPage.java
│       │   │   ├── ChatbotPage.java
│       │   │   ├── CustomFoodPage.java
│       │   │   ├── DashboardPage.java
│       │   │   ├── FoodEntryPage.java
│       │   │   ├── ProfilePage.java
│       │   │   └── USDASearchPage.java
│       │   ├── stepdefinition/             # Cucumber Step Definitions
│       │   │   ├── AuthenticationSteps.java
│       │   │   ├── ChatbotSteps.java
│       │   │   ├── CustomFoodSteps.java
│       │   │   ├── DashboardSteps.java
│       │   │   ├── FoodEntrySteps.java
│       │   │   ├── ProfileSteps.java
│       │   │   └── USDASearchSteps.java
│       │   ├── testrunner/                 # Test Runners
│       │   │   ├── SequentialTestRunner.java    # All 44 test cases
│       │   │   ├── ProvenTestsRunner.java       # Verified passing tests
│       │   │   ├── AllTestsRunner.java
│       │   │   ├── NegativeTestsRunner.java
│       │   │   └── TC001Runner.java - TC045Runner.java
│       │   └── util/
│       │       └── TestContext.java        # Shared test utilities
│       └── resources/
│           └── features/                   # Cucumber Feature Files
│               ├── authentication.feature
│               ├── ai-chatbot.feature
│               ├── custom-food-management.feature
│               ├── dashboard-analytics.feature
│               ├── food-entry-management.feature
│               ├── profile-management.feature
│               └── usda-food-search.feature
├── target/                                 # Build output and reports
├── test-cases/
│   └── Test_Case_Management.csv           # Test case tracking
├── bug-reports/
│   └── bug_tracker.csv                    # Bug tracking
├── pom.xml                               # Maven configuration
├── TEST_RUNNER_DOCUMENTATION.md         # Detailed runner guide
└── README.md                            # This file
```

## 🚀 Getting Started

### Prerequisites

- **Java 21** or higher
- **Maven 3.6+**
- **Google Chrome** browser
- **Git** for version control

### Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd testing-calories-tracker
   ```

2. **Install dependencies**
   ```bash
   mvn clean install
   ```

3. **Verify setup**
   ```bash
   mvn compile
   ```

### Environment Setup

Ensure the Calories Tracker application is running on `http://localhost:8080` before executing tests.

## 🎯 Running Tests

### Quick Start - Proven Tests (Recommended)
Run only the verified passing test cases:
```bash
mvn test -Dtest=ProvenTestsRunner
```

### Complete Test Suite
Run all 44 test cases sequentially:
```bash
mvn test -Dtest=SequentialTestRunner
```

### Feature-Specific Testing
```bash
# Food Entry Management tests
mvn test -Dtest=FoodEntryNegativeRunner

# Custom Food Management tests
mvn test -Dtest=CustomFoodNegativeRunner

# USDA Search tests
mvn test -Dtest=USDANegativeRunner

# AI Chatbot tests
mvn test -Dtest=ChatbotNegativeRunner

# All negative tests
mvn test -Dtest=NegativeTestsRunner
```

### Individual Test Cases
```bash
# Run specific test case
mvn test -Dtest=TC044Runner  # XSS Security test
mvn test -Dtest=TC045Runner  # Rate limiting test
mvn test -Dtest=TC039Runner  # USDA API error handling
```

## 📊 Test Reports

After test execution, reports are generated in:
- **HTML Reports**: `target/cucumber-html-report-*/`
- **JSON Reports**: `target/cucumber-json-report-*.json`
- **JUnit Reports**: `target/cucumber-junit-report-*.xml`

## 🧪 Test Categories

### ✅ Proven Test Cases (23 tests)
These tests have been thoroughly validated and consistently pass:
- **TC014-TC017**: Food Entry validation and error handling
- **TC029-TC033**: Custom Food validation and management
- **TC034-TC039, TC039b, TC039c**: USDA search and API handling
- **TC040-TC045**: Chatbot functionality and security

### 🔍 Test Types Covered
- **Functional Testing**: Core application features
- **Negative Testing**: Invalid inputs and error scenarios
- **Boundary Testing**: Edge cases and limits
- **Security Testing**: XSS prevention and input sanitization
- **API Testing**: External service integration
- **UI Validation**: Form validation and user experience

## 🔧 Configuration

### Browser Configuration
- Default browser: Chrome (auto-managed by WebDriverManager)
- Headless mode: Configurable via system properties
- Window size: Maximized by default

### Test Data
- Demo user: `demo@example.com`
- Test data is managed through feature files and step definitions
- Dynamic test data generation for edge cases

### Timeouts
- Implicit wait: 10 seconds
- Explicit wait: 30 seconds
- Page load timeout: 60 seconds

## 🐛 Debugging and Troubleshooting

### Common Issues

1. **ChromeDriver Issues**
   - Ensure Chrome browser is updated
   - WebDriverManager handles driver management automatically

2. **Application Not Running**
   - Verify the application is accessible at `http://localhost:8080`
   - Check backend services are running

3. **Test Failures**
   - Check console output for detailed error messages
   - Review HTML reports for step-by-step execution details

### Debug Mode
Add system property for verbose logging:
```bash
mvn test -Dtest=TC044Runner -Dcucumber.options="--plugin pretty"
```

## 📈 Test Statistics

- **Total Test Cases**: 44
- **Proven Stable Tests**: 23
- **Coverage Areas**: 6 major features
- **Success Rate**: 100% for proven tests
- **Execution Time**: ~2-3 minutes per test case

## 🤝 Contributing

1. Follow the existing Page Object Model pattern
2. Use descriptive test names and scenarios
3. Add appropriate wait strategies
4. Include debug logging for complex scenarios
5. Update documentation for new test cases

## 📝 Test Case Management

Test cases are tracked in `test-cases/Test_Case_Management.csv` with:
- Test Case ID
- Feature Area
- Test Type (Positive/Negative/Boundary/Security)
- Priority Level
- Status (Pass/Fail/Blocked)

## 🔒 Security Testing

The framework includes comprehensive security testing:
- **XSS Prevention**: Input sanitization validation
- **SQL Injection**: Database security testing
- **Authentication**: Session management and security
- **Authorization**: Access control testing

## 📞 Support

For issues or questions:
1. Check the troubleshooting section
2. Review test execution logs
3. Consult `TEST_RUNNER_DOCUMENTATION.md` for detailed runner information

## 🏆 Best Practices

- Use Page Object Model for maintainability
- Implement explicit waits over implicit waits
- Use descriptive assertion messages
- Keep test data separate from test logic
- Regular cleanup of browser sessions
- Parallel execution ready (configure in pom.xml)

---

**Framework Version**: 1.0  
**Last Updated**: June 2025  
**Maintained by**: QA Automation Team
