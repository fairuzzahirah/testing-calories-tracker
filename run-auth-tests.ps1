param(
    [string]$TestType = "all",
    [string]$Browser = "chrome",
    [switch]$Headless,
    [switch]$GenerateReport
)

Write-Host "============================================" -ForegroundColor Cyan
Write-Host "    Calories Tracker Testing Framework     " -ForegroundColor Cyan
Write-Host "============================================" -ForegroundColor Cyan
Write-Host ""

# Set browser system property
$env:BROWSER = $Browser

# Set headless mode if specified
if ($Headless) {
    $env:HEADLESS = "true"
    Write-Host "Running in headless mode..." -ForegroundColor Yellow
}

Write-Host "Browser: $Browser" -ForegroundColor Green
Write-Host "Test Type: $TestType" -ForegroundColor Green
Write-Host ""

# Function to run tests and check results
function Run-TestSuite {
    param(
        [string]$TestRunner,
        [string]$TestName
    )
    
    Write-Host "Running $TestName..." -ForegroundColor Green
    Write-Host "Command: mvn clean test -Dtest=$TestRunner" -ForegroundColor Gray
    
    mvn clean test -Dtest=$TestRunner
    
    if ($LASTEXITCODE -eq 0) {
        Write-Host "✓ $TestName completed successfully!" -ForegroundColor Green
        return $true
    } else {
        Write-Host "✗ $TestName failed!" -ForegroundColor Red
        return $false
    }
}

# Function to open test reports
function Open-TestReports {
    param([string]$ReportPath)
    
    if (Test-Path $ReportPath) {
        Write-Host "Opening test reports..." -ForegroundColor Yellow
        Start-Process $ReportPath
    } else {
        Write-Host "Test reports not found at: $ReportPath" -ForegroundColor Red
    }
}

# Main test execution logic
$allTestsSuccess = $true

switch ($TestType.ToLower()) {
    "register" {
        $success = Run-TestSuite "RegisterTestRunner" "Register Tests"
        $allTestsSuccess = $allTestsSuccess -and $success
        Open-TestReports "target\cucumber-reports\index.html"
    }
    
    "login" {
        $success = Run-TestSuite "LoginTestRunner" "Login Tests"
        $allTestsSuccess = $allTestsSuccess -and $success
        Open-TestReports "target\cucumber-reports\login\index.html"
    }
    
    "smoke" {
        $success = Run-TestSuite "SmokeTestRunner" "Smoke Tests"
        $allTestsSuccess = $allTestsSuccess -and $success
        Open-TestReports "target\cucumber-reports\smoke\index.html"
    }
    
    "all" {
        # Run smoke tests first
        $smokeSuccess = Run-TestSuite "SmokeTestRunner" "Smoke Tests"
        
        if ($smokeSuccess) {
            Write-Host "Smoke tests passed. Running full test suite..." -ForegroundColor Green
            $allSuccess = Run-TestSuite "AllTestsRunner" "All Tests"
            $allTestsSuccess = $allTestsSuccess -and $allSuccess
        } else {
            Write-Host "Smoke tests failed. Skipping full test suite." -ForegroundColor Red
            $allTestsSuccess = $false
        }
        
        Open-TestReports "target\cucumber-reports\all-tests\index.html"
    }
    
    "security" {
        Write-Host "Running Security Tests..." -ForegroundColor Green
        mvn clean test -Dcucumber.filter.tags="@security"
        
        if ($LASTEXITCODE -eq 0) {
            Write-Host "✓ Security tests completed successfully!" -ForegroundColor Green
        } else {
            Write-Host "✗ Security tests failed!" -ForegroundColor Red
            $allTestsSuccess = $false
        }
    }
    
    default {
        Write-Host "Invalid test type: $TestType" -ForegroundColor Red
        Write-Host "Valid options: register, login, smoke, all, security" -ForegroundColor Yellow
        exit 1
    }
}

Write-Host ""
Write-Host "============================================" -ForegroundColor Cyan

if ($allTestsSuccess) {
    Write-Host "✓ ALL TESTS COMPLETED SUCCESSFULLY!" -ForegroundColor Green
    exit 0
} else {
    Write-Host "✗ SOME TESTS FAILED!" -ForegroundColor Red
    Write-Host "Please check the test reports for details." -ForegroundColor Yellow
    exit 1
}

# Generate additional reports if requested
if ($GenerateReport) {
    Write-Host ""
    Write-Host "Generating additional reports..." -ForegroundColor Yellow
    mvn site
    Write-Host "Maven site reports generated." -ForegroundColor Green
}
