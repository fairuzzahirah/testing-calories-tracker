# Test Execution Scripts

## PowerShell Scripts for Windows

# Run Register Tests Only
Write-Host "Running Register Tests..." -ForegroundColor Green
mvn clean test -Dtest=RegisterTestRunner
if ($LASTEXITCODE -eq 0) {
    Write-Host "Register tests completed successfully!" -ForegroundColor Green
} else {
    Write-Host "Register tests failed!" -ForegroundColor Red
}

# Run Login Tests Only  
Write-Host "Running Login Tests..." -ForegroundColor Green
mvn clean test -Dtest=LoginTestRunner
if ($LASTEXITCODE -eq 0) {
    Write-Host "Login tests completed successfully!" -ForegroundColor Green
} else {
    Write-Host "Login tests failed!" -ForegroundColor Red
}

# Run Smoke Tests Only
Write-Host "Running Smoke Tests..." -ForegroundColor Green
mvn clean test -Dtest=SmokeTestRunner
if ($LASTEXITCODE -eq 0) {
    Write-Host "Smoke tests completed successfully!" -ForegroundColor Green
} else {
    Write-Host "Smoke tests failed!" -ForegroundColor Red
}

# Run All Tests
Write-Host "Running All Tests..." -ForegroundColor Green
mvn clean test -Dtest=AllTestsRunner
if ($LASTEXITCODE -eq 0) {
    Write-Host "All tests completed successfully!" -ForegroundColor Green
    Write-Host "Opening test reports..." -ForegroundColor Yellow
    Start-Process "target\cucumber-reports\all-tests\index.html"
} else {
    Write-Host "Some tests failed!" -ForegroundColor Red
    Write-Host "Opening test reports for analysis..." -ForegroundColor Yellow
    Start-Process "target\cucumber-reports\all-tests\index.html"
}

## Maven Commands

# Run specific test runner
mvn clean test -Dtest=RegisterTestRunner
mvn clean test -Dtest=LoginTestRunner
mvn clean test -Dtest=SmokeTestRunner
mvn clean test -Dtest=AllTestsRunner

# Run tests with specific tags
mvn clean test -Dcucumber.filter.tags="@smoke"
mvn clean test -Dcucumber.filter.tags="@positive"
mvn clean test -Dcucumber.filter.tags="@negative"
mvn clean test -Dcucumber.filter.tags="@security"

# Run tests with browser selection
mvn clean test -Dbrowser=chrome -Dtest=AllTestsRunner
mvn clean test -Dbrowser=firefox -Dtest=AllTestsRunner

# Run tests in headless mode (for CI/CD)
mvn clean test -Dheadless=true -Dtest=AllTestsRunner

# Generate detailed reports
mvn clean test -Dtest=AllTestsRunner
mvn site

## Test Report Locations

After running tests, reports will be generated in:
- target/cucumber-reports/all-tests/index.html (Main HTML Report)
- target/cucumber-reports/AllTests.json (JSON Report)
- target/cucumber-reports/AllTests.xml (JUnit XML Report)
- target/cucumber-reports/timeline/index.html (Timeline Report)

## CI/CD Integration Commands

# For Jenkins/GitHub Actions
mvn clean test -Dheadless=true -Dci=true -Dtest=AllTestsRunner

# Generate reports for CI
mvn clean test site

# Archive test results
mvn clean test -Dtest=AllTestsRunner
tar -czf test-reports.tar.gz target/cucumber-reports/
