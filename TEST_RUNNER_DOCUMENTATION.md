# Test Runner Documentation

## Available Test Runners

### 1. SequentialTestRunner.java
**Purpose**: Run ALL test cases sequentially from TC001 to TC045
**Total Test Cases**: 44
**Categories**:
- Authentication: TC001-TC009 (9 tests)
- Profile Management: TC010-TC013 (4 tests)  
- Food Entry Management: TC014-TC025 (12 tests)
- Custom Food Management: TC029-TC033 (5 tests)
- USDA Food Search: TC034-TC039, TC039b, TC039c (8 tests)
- AI Chatbot: TC040-TC045 (6 tests)

**Command**: `mvn test -Dtest=SequentialTestRunner`

### 2. ProvenTestsRunner.java
**Purpose**: Run ONLY the proven PASSED test cases (negative/boundary/security)
**Total Test Cases**: 23
**Categories**:
- Food Entry Management: TC014-TC017 (4 tests)
- Custom Food Management: TC029-TC033 (5 tests)
- USDA Food Search: TC034-TC039, TC039b, TC039c (8 tests)
- AI Chatbot: TC040-TC045 (6 tests)

**Command**: `mvn test -Dtest=ProvenTestsRunner`

### 3. AllTestsRunner.java
**Purpose**: Run specific test cases using Cucumber tags
**Features**: Uses Cucumber tags for flexible test selection
**Command**: `mvn test -Dtest=AllTestsRunner`

### 4. Feature-Specific Runners
- **FoodEntryNegativeRunner.java**: Food entry negative tests only
- **CustomFoodNegativeRunner.java**: Custom food negative tests only
- **USDANegativeRunner.java**: USDA search negative/boundary tests only
- **ChatbotNegativeRunner.java**: Chatbot negative/security tests only
- **NegativeTestsRunner.java**: All negative tests across features

### 5. Individual Test Case Runners
Available for each test case: TC001Runner.java through TC045Runner.java

## Recommended Usage

### For Complete Testing
```bash
mvn test -Dtest=SequentialTestRunner
```

### For Quick Validation (Proven Tests Only)
```bash
mvn test -Dtest=ProvenTestsRunner
```

### For Specific Feature Testing
```bash
mvn test -Dtest=FoodEntryNegativeRunner
mvn test -Dtest=ChatbotNegativeRunner
```

### For Individual Test Case
```bash
mvn test -Dtest=TC044Runner
mvn test -Dtest=TC045Runner
```

## Test Coverage Summary

### Proven Test Cases (PASSED)
- ✅ TC014-TC017: Food Entry validation, error handling
- ✅ TC029-TC033: Custom Food validation, error handling  
- ✅ TC034-TC039, TC039b, TC039c: USDA search validation, API error handling
- ✅ TC040-TC045: Chatbot functionality, security (XSS), rate limiting validation

### Complete Test Suite (All TC001-TC045)
- Authentication flows and validation
- Profile management and validation
- Complete food entry management lifecycle
- Custom food creation and management
- USDA API integration and error handling
- AI Chatbot security and functionality testing

## Notes
- All proven test cases have been validated and confirmed to PASS
- Security testing includes XSS validation and input sanitization
- Error handling covers API failures, validation errors, and edge cases
- Boundary testing covers input limits and edge conditions
