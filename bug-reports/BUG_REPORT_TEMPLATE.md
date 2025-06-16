# 🐛 BUG REPORTING SYSTEM - CALORIES TRACKER

## Bug Report Template

### Bug ID: [BUG-YYYY-MM-DD-###]

**Bug Title:** [Brief descriptive title]

**Reporter:** [Name]
**Date Reported:** [YYYY-MM-DD]
**Test Environment:** [Browser/OS/Resolution]
**Severity:** [Critical/High/Medium/Low]
**Priority:** [P1/P2/P3/P4]
**Status:** [New/Open/In Progress/Fixed/Closed/Rejected]

---

### Bug Classification

**Type:** 
- [ ] Functional Bug
- [ ] UI/UX Bug
- [ ] Performance Bug
- [ ] Security Bug
- [ ] Integration Bug
- [ ] Data Bug
- [ ] Configuration Bug

**Category:**
- [ ] Authentication
- [ ] Dashboard
- [ ] Food Management
- [ ] Custom Foods
- [ ] USDA Integration
- [ ] AI Chatbot
- [ ] Profile Management
- [ ] API Endpoints
- [ ] Database Issues

---

### Test Environment Details

**Frontend:**
- URL: http://localhost:8001
- Browser: [Chrome/Firefox/Safari/Edge]
- Browser Version: [Version number]
- Resolution: [e.g., 1920x1080]
- Device: [Desktop/Mobile/Tablet]

**Backend:**
- API URL: http://localhost:8000/api
- Database: MySQL
- PHP Version: 8.2+
- Laravel Version: 11

**Test Data Used:**
- User Account: [test credentials used]
- Specific Test Data: [any custom data]

---

### Bug Description

**Summary:**
[One-line summary of the bug]

**Detailed Description:**
[Detailed explanation of what happened]

**Expected Behavior:**
[What should have happened]

**Actual Behavior:**
[What actually happened]

---

### Steps to Reproduce

**Preconditions:**
[Any setup required before testing]

**Test Steps:**
1. [Step 1]
2. [Step 2]
3. [Step 3]
...

**Test Data:**
[Any specific data used in reproduction]

---

### Evidence

**Screenshots:**
- [ ] Attached: [filename]
- [ ] Error screenshots: [filename]
- [ ] Expected vs Actual: [filename]

**Screen Recording:**
- [ ] Attached: [filename]

**Browser Console Logs:**
```
[Paste console logs here]
```

**Network Tab Errors:**
```
[Paste network errors here]
```

**Server Logs:**
```
[Paste relevant server logs here]
```

---

### Impact Assessment

**User Impact:**
[How does this affect users?]

**Business Impact:**
[How does this affect business operations?]

**Workaround Available:**
- [ ] Yes: [Describe workaround]
- [ ] No

**Frequency:**
- [ ] Always reproducible
- [ ] Intermittent
- [ ] Rare occurrence

---

### Testing Coverage

**Test Case Reference:**
[Link to test case that found this bug]

**Automation Status:**
- [ ] Manual testing only
- [ ] Automated test available
- [ ] Automated test needs update

**Regression Testing:**
- [ ] Affects existing functionality
- [ ] New feature bug only

---

### Root Cause Analysis

**Technical Analysis:**
[Technical details of the root cause]

**Code Location:**
[File paths and line numbers if known]

**Related Components:**
[Other parts of system that might be affected]

---

### Fix Information

**Developer Assigned:** [Name]
**Fix Description:** [How the bug was fixed]
**Code Changes:** [Files modified]
**Testing Required:** [Types of testing needed]

**Fix Verification:**
- [ ] Unit tests pass
- [ ] Integration tests pass
- [ ] Manual testing completed
- [ ] Regression testing completed

---

### Closure Information

**Resolution:**
- [ ] Fixed
- [ ] Won't Fix
- [ ] Duplicate
- [ ] Cannot Reproduce
- [ ] Working as Designed

**Verified By:** [Name]
**Verification Date:** [YYYY-MM-DD]
**Notes:** [Additional notes]

---

## Bug Severity Guidelines

### Critical (P1)
- Application crashes or becomes completely unusable
- Data loss or corruption
- Security vulnerabilities
- Complete feature failure for core functionality

### High (P2)
- Major feature not working as expected
- Significant usability issues
- Performance issues affecting user experience
- API endpoint failures

### Medium (P3)
- Minor feature issues
- UI cosmetic issues that affect usability
- Non-critical functionality issues
- Intermittent issues

### Low (P4)
- Cosmetic issues
- Minor text/spelling errors
- Enhancement requests
- Nice-to-have improvements

---

## Bug Tracking Workflow

1. **Bug Discovery** → Report with template
2. **Triage** → Assign severity and priority
3. **Investigation** → Reproduce and analyze
4. **Assignment** → Assign to developer
5. **Development** → Fix implementation
6. **Testing** → Verify fix
7. **Closure** → Mark as resolved

---

## Common Bug Categories for Calories Tracker

### Authentication Bugs
- Login/logout failures
- Session management issues
- Password reset problems
- Token expiration issues

### Dashboard Bugs
- Calorie calculation errors
- Chart rendering issues
- Data refresh problems
- Navigation failures

### Food Management Bugs
- CRUD operation failures
- Data validation errors
- Search functionality issues
- Pagination problems

### API Integration Bugs
- Request/response format errors
- Timeout issues
- Error handling problems
- Authentication failures

### UI/UX Bugs
- Responsive design issues
- Form validation problems
- Button/link failures
- Display formatting errors

---

## Testing Best Practices for Bug Reports

1. **Reproducibility:** Always try to reproduce the bug at least 3 times
2. **Documentation:** Include all relevant screenshots and logs
3. **Isolation:** Test in clean environment when possible
4. **Browser Testing:** Test across different browsers
5. **Device Testing:** Test on different screen sizes/devices
6. **Data Variation:** Test with different data sets
7. **Edge Cases:** Test boundary conditions
8. **User Flows:** Test complete user journeys

---

## Bug Report Quality Checklist

- [ ] Clear and descriptive title
- [ ] Detailed steps to reproduce
- [ ] Expected vs actual behavior clearly stated
- [ ] Screenshots/evidence attached
- [ ] Environment details provided
- [ ] Severity/priority assigned appropriately
- [ ] Impact assessment completed
- [ ] Test data/credentials provided
- [ ] Console logs included (if applicable)
- [ ] Cross-browser testing completed
