# Bug Report Template

## Bug Report Information
- **Bug ID**: [Auto-generated or manual ID]
- **Date Reported**: [Date]
- **Reporter**: [Tester Name]
- **Test Environment**: [Environment details]

## Bug Classification
- **Severity**: [Critical/High/Medium/Low]
- **Priority**: [P1/P2/P3/P4]
- **Bug Type**: [Functional/UI/Performance/Security/Usability]
- **Module**: [Register/Login/Dashboard/etc.]

## Bug Summary
**Title**: [Brief description of the bug]

## Test Case Details
- **Test Case ID**: [TC_REG_001, TC_LOGIN_002, etc.]
- **Test Scenario**: [Scenario being tested]
- **Test Data Used**: [Input data that caused the bug]

## Bug Description
### Steps to Reproduce:
1. [Step 1]
2. [Step 2]
3. [Step 3]
...

### Expected Result:
[What should happen]

### Actual Result:
[What actually happened]

## Environment Information
- **OS**: [Windows/Linux/Mac]
- **Browser**: [Chrome/Firefox/Safari + version]
- **Application Version**: [Version number]
- **Database**: [Database version if applicable]

## Evidence
- **Screenshots**: [Attached]
- **Error Messages**: [Copy exact error messages]
- **Browser Console Logs**: [If applicable]
- **Network Logs**: [If applicable]

## Impact Analysis
- **Business Impact**: [How does this affect business operations]
- **User Impact**: [How does this affect user experience]
- **Workaround**: [Is there a temporary solution]

## Additional Information
- **Related Bugs**: [Link to related bug reports]
- **Test Data**: [Any specific test data needed to reproduce]
- **Notes**: [Any additional observations]

---

## Bug Report Examples

### Example 1: Registration Bug

**Bug ID**: BUG_REG_001
**Date Reported**: 2025-06-15
**Reporter**: Test Team
**Severity**: High
**Priority**: P2
**Module**: Registration

**Title**: Email validation accepts invalid email format with double dots

**Test Case ID**: TC_REG_BOUNDARY_003

**Steps to Reproduce**:
1. Navigate to registration page
2. Enter name: "Test User"
3. Enter email: "user..name@domain.com"
4. Fill all other required fields with valid data
5. Click Register button

**Expected Result**: 
Email validation should reject email with consecutive dots and show error message

**Actual Result**: 
Registration succeeds with invalid email format

**Environment**: Windows 11, Chrome 120.0, Application v1.0

**Impact**: Medium - Users may register with invalid emails affecting communication

---

### Example 2: Login Security Bug

**Bug ID**: BUG_LOGIN_001
**Date Reported**: 2025-06-15
**Reporter**: Test Team
**Severity**: Critical
**Priority**: P1
**Module**: Login

**Title**: SQL Injection possible in login form

**Test Case ID**: TC_LOGIN_SEC_001

**Steps to Reproduce**:
1. Navigate to login page
2. Enter email: "admin'; DROP TABLE users; --"
3. Enter password: "anything"
4. Click Login button

**Expected Result**: 
Input should be sanitized and login should fail with authentication error

**Actual Result**: 
Application may be vulnerable to SQL injection (needs verification)

**Environment**: Windows 11, Chrome 120.0, Application v1.0

**Impact**: Critical - Potential data breach and system compromise

---

### Example 3: UI Bug

**Bug ID**: BUG_REG_002
**Date Reported**: 2025-06-15
**Reporter**: Test Team
**Severity**: Low
**Priority**: P3
**Module**: Registration

**Title**: Age field accepts negative values

**Test Case ID**: TC_REG_BOUNDARY_002

**Steps to Reproduce**:
1. Navigate to registration page
2. Fill all fields with valid data
3. Enter age: "-25"
4. Click Register button

**Expected Result**: 
Age field should show validation error for negative values

**Actual Result**: 
Registration proceeds with negative age value

**Environment**: Windows 11, Chrome 120.0, Application v1.0

**Impact**: Low - Data integrity issue but doesn't affect core functionality
