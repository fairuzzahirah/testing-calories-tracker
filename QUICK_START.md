# Quick Start Commands

## 🚀 Essential Commands

### Setup and Verification
```bash
# Clean and install dependencies
mvn clean install

# Compile project
mvn compile

# Verify environment
mvn test -Dtest=TC044Runner
```

### Test Execution

#### Recommended (Proven Tests)
```bash
# Run 23 proven test cases (2-3 minutes)
mvn test -Dtest=ProvenTestsRunner
```

#### Complete Test Suite
```bash
# Run all 44 test cases (8-10 minutes)
mvn test -Dtest=SequentialTestRunner
```

#### Feature-Specific
```bash
# Food Entry tests
mvn test -Dtest=FoodEntryNegativeRunner

# Custom Food tests
mvn test -Dtest=CustomFoodNegativeRunner

# USDA Search tests
mvn test -Dtest=USDANegativeRunner

# Chatbot Security tests
mvn test -Dtest=ChatbotNegativeRunner

# All negative tests
mvn test -Dtest=NegativeTestsRunner
```

#### Individual Test Cases
```bash
# Security XSS test
mvn test -Dtest=TC044Runner

# Rate limiting test
mvn test -Dtest=TC045Runner

# USDA API error handling
mvn test -Dtest=TC039Runner

# Food entry validation
mvn test -Dtest=TC014Runner
```

### Report Generation
```bash
# Generate comprehensive reports
mvn test -Dtest=ProvenTestsRunner
# Reports available in: target/cucumber-html-report-proven-tests/
```

### Debugging
```bash
# Verbose output
mvn test -Dtest=TC044Runner -X

# With specific browser
mvn test -Dtest=TC044Runner -Dbrowser=chrome

# Headless mode
mvn test -Dtest=ProvenTestsRunner -Dheadless=true
```

### Cleanup
```bash
# Clean build artifacts
mvn clean

# Reset everything
mvn clean compile
```

## 📊 Expected Results

### Proven Tests (ProvenTestsRunner)
- **Expected**: All 23 tests PASS
- **Duration**: 2-3 minutes
- **Success Rate**: 100%

### Individual Security Test (TC044Runner)
- **Expected**: PASS - XSS prevention validated
- **Duration**: ~10 seconds
- **Output**: "Build SUCCESS"

### USDA API Test (TC039Runner)
- **Expected**: PASS - API error handling validated
- **Duration**: ~15 seconds
- **Output**: Fallback scenario verification

## 🔍 Quick Verification

After any test run, check:
1. Console shows "BUILD SUCCESS"
2. HTML report generated in `target/` folder
3. No Java exceptions in output
4. Browser closes automatically

## 🆘 Quick Troubleshooting

### If Tests Fail
```bash
# 1. Verify application is running
curl http://localhost:8080

# 2. Check Java version
java -version

# 3. Reinstall dependencies
mvn clean install

# 4. Run single test for debugging
mvn test -Dtest=TC044Runner -X
```

### If Browser Issues
```bash
# Update Maven dependencies
mvn dependency:resolve

# Check Chrome version (should be latest)
google-chrome --version
```

## 🎯 Most Used Commands

```bash
# Daily testing routine
mvn test -Dtest=ProvenTestsRunner

# Quick single test
mvn test -Dtest=TC044Runner

# Full regression
mvn test -Dtest=SequentialTestRunner

# Clean and test
mvn clean test -Dtest=ProvenTestsRunner
```
