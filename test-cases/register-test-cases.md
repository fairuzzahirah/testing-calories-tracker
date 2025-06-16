# Register Feature Test Cases

## Test Case Management
This document contains comprehensive test cases for the Register functionality using Boundary Value Analysis, Equivalence Partitioning, and Negative Testing techniques.

## Test Categories

### 1. Equivalence Partitioning

#### Valid Equivalence Classes:
- **Name**: 1-255 characters, alphabetic and spaces
- **Email**: Valid email format (user@domain.com)
- **Password**: 8+ characters with mix of uppercase, lowercase, numbers, symbols
- **Age**: 13-100 years
- **Height**: 100-250 cm
- **Weight**: 30-300 kg
- **Gender**: male, female, other
- **Goal**: lose, maintain, gain
- **Activity Level**: sedentary, light, moderate, active, very_active

#### Invalid Equivalence Classes:
- **Name**: Empty, >255 characters, special characters
- **Email**: Invalid format, missing @, missing domain
- **Password**: <8 characters, only numbers, only letters
- **Age**: <13, >100, negative numbers
- **Height**: <100, >250, negative numbers
- **Weight**: <30, >300, negative numbers

### 2. Boundary Value Analysis

#### Age Boundaries:
- **Valid**: 13 (min), 18, 65, 100 (max)
- **Invalid**: 0, 12 (below min), 101, 150 (above max)

#### Height Boundaries:
- **Valid**: 100 (min), 150, 180, 220, 250 (max)
- **Invalid**: 0, 99 (below min), 251, 300 (above max)

#### Weight Boundaries:
- **Valid**: 30 (min), 50, 80, 150, 300 (max)
- **Invalid**: 0, 29 (below min), 301, 500 (above max)

### 3. Negative Test Cases

#### Security Testing:
- XSS injection attempts
- SQL injection attempts
- Script tag injection
- Malformed input data

#### Validation Testing:
- Empty required fields
- Invalid email formats
- Password mismatch
- Duplicate email registration
- Out-of-range numeric values

## Test Execution Strategy

### Test Priorities:
1. **Smoke Tests (@smoke)**: Basic happy path registration
2. **Positive Tests (@positive)**: Valid data scenarios
3. **Boundary Tests (@boundary)**: Edge case values
4. **Negative Tests (@negative)**: Error handling
5. **Security Tests (@security)**: Malicious input handling

### Test Data Management:
- Unique emails generated using timestamp
- Predefined test data sets for different scenarios
- Boundary value test data
- Invalid data sets for negative testing

## Bug Tracking Integration
Failed test cases will automatically generate bug reports with:
- Test scenario details
- Steps to reproduce
- Expected vs actual results
- Screenshots for visual bugs
- Environment information

## Test Metrics
- Test case coverage: 100% of identified equivalence classes
- Boundary value coverage: All critical boundaries tested
- Negative scenario coverage: All major error paths covered
- Code coverage: Page Object Model implementation
