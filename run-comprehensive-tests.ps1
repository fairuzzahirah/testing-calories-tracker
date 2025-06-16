# Calories Tracker - Comprehensive Test Execution Script
# PowerShell script for running various test suites

param(
    [string]$TestSuite = "smoke",
    [string]$Browser = "chrome",
    [bool]$Headless = $false,
    [string]$Tags = "",
    [bool]$GenerateReport = $true
)

Write-Host "========================================" -ForegroundColor Cyan
Write-Host "    Calories Tracker Test Execution" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan

# Validate parameters
$validSuites = @("smoke", "all", "positive", "negative", "auth", "food", "custom", "usda", "chatbot", "dashboard", "profile")
if ($TestSuite -notin $validSuites) {
    Write-Host "Invalid test suite: $TestSuite" -ForegroundColor Red
    Write-Host "Valid options: $($validSuites -join ', ')" -ForegroundColor Yellow
    exit 1
}

# Check if application is running
Write-Host "Checking if application is running..." -ForegroundColor Yellow

try {
    $frontendCheck = Invoke-WebRequest -Uri "http://localhost:8001" -TimeoutSec 5 -UseBasicParsing
    Write-Host "✓ Frontend is running on port 8001" -ForegroundColor Green
} catch {
    Write-Host "✗ Frontend not accessible on port 8001" -ForegroundColor Red
    Write-Host "Please start the frontend application first" -ForegroundColor Yellow
    exit 1
}

try {
    $backendCheck = Invoke-WebRequest -Uri "http://localhost:8000/api/health" -TimeoutSec 5 -UseBasicParsing
    Write-Host "✓ Backend is running on port 8000" -ForegroundColor Green
} catch {
    Write-Host "✗ Backend not accessible on port 8000" -ForegroundColor Red
    Write-Host "Please start the backend application first" -ForegroundColor Yellow
    exit 1
}

# Determine test runner and tags
$testRunner = ""
$cucumberTags = ""

switch ($TestSuite) {
    "smoke" { 
        $testRunner = "SmokeTestRunner"
        $cucumberTags = "@smoke"
    }
    "all" { 
        $testRunner = "AllTestRunner" 
    }
    "positive" { 
        $testRunner = "PositiveTestRunner"
        $cucumberTags = "@positive"
    }
    "negative" { 
        $testRunner = "NegativeTestRunner"
        $cucumberTags = "@negative"
    }
    "auth" { 
        $testRunner = "AllTestRunner"
        $cucumberTags = "@authentication"
    }
    "food" { 
        $testRunner = "AllTestRunner"
        $cucumberTags = "@food-entry"
    }
    "custom" { 
        $testRunner = "AllTestRunner"
        $cucumberTags = "@custom-food"
    }
    "usda" { 
        $testRunner = "AllTestRunner"
        $cucumberTags = "@usda"
    }
    "chatbot" { 
        $testRunner = "AllTestRunner"
        $cucumberTags = "@chatbot"
    }
    "dashboard" { 
        $testRunner = "AllTestRunner"
        $cucumberTags = "@dashboard"
    }
    "profile" { 
        $testRunner = "AllTestRunner"
        $cucumberTags = "@profile"
    }
}

# Override with custom tags if provided
if ($Tags -ne "") {
    $cucumberTags = $Tags
}

# Build Maven command
$mavenCmd = "mvn test -Dtest=$testRunner"
$mavenCmd += " -Dbrowser=$Browser"
$mavenCmd += " -Dheadless=$Headless"

if ($cucumberTags -ne "") {
    $mavenCmd += " -Dcucumber.options=`"--tags $cucumberTags`""
}

Write-Host "Configuration:" -ForegroundColor Cyan
Write-Host "  Test Suite: $TestSuite" -ForegroundColor White
Write-Host "  Test Runner: $testRunner" -ForegroundColor White
Write-Host "  Browser: $Browser" -ForegroundColor White
Write-Host "  Headless: $Headless" -ForegroundColor White
Write-Host "  Tags: $cucumberTags" -ForegroundColor White
Write-Host ""

Write-Host "Executing command:" -ForegroundColor Cyan
Write-Host "  $mavenCmd" -ForegroundColor White
Write-Host ""

# Execute tests
$startTime = Get-Date
Write-Host "Starting tests at: $($startTime.ToString('yyyy-MM-dd HH:mm:ss'))" -ForegroundColor Yellow
Write-Host "========================================" -ForegroundColor Cyan

# Run the Maven command
$process = Start-Process -FilePath "mvn" -ArgumentList $mavenCmd.Split(' ', 2)[1] -Wait -PassThru -NoNewWindow

$endTime = Get-Date
$duration = $endTime - $startTime

Write-Host ""
Write-Host "========================================" -ForegroundColor Cyan
Write-Host "Test execution completed at: $($endTime.ToString('yyyy-MM-dd HH:mm:ss'))" -ForegroundColor Yellow
Write-Host "Total duration: $($duration.ToString('hh\:mm\:ss'))" -ForegroundColor Yellow

if ($process.ExitCode -eq 0) {
    Write-Host ""
    Write-Host "✓ TESTS PASSED SUCCESSFULLY" -ForegroundColor Green
    Write-Host "========================================" -ForegroundColor Green
    
    if ($GenerateReport) {
        $reportPath = Join-Path $PSScriptRoot "target\cucumber-reports\index.html"
        if (Test-Path $reportPath) {
            Write-Host "📊 Test report generated:" -ForegroundColor Cyan
            Write-Host "   $reportPath" -ForegroundColor White
            
            # Ask if user wants to open the report
            $openReport = Read-Host "Do you want to open the test report? (y/n)"
            if ($openReport -eq "y" -or $openReport -eq "Y") {
                Start-Process $reportPath
            }
        }
    }
} else {
    Write-Host ""
    Write-Host "✗ TESTS FAILED" -ForegroundColor Red
    Write-Host "========================================" -ForegroundColor Red
    Write-Host "Exit code: $($process.ExitCode)" -ForegroundColor Red
    
    if ($GenerateReport) {
        $reportPath = Join-Path $PSScriptRoot "target\cucumber-reports\index.html"
        if (Test-Path $reportPath) {
            Write-Host "📊 Test report (with failures) available at:" -ForegroundColor Yellow
            Write-Host "   $reportPath" -ForegroundColor White
        }
    }
}

Write-Host ""
Write-Host "Test execution summary:" -ForegroundColor Cyan
Write-Host "  Suite: $TestSuite" -ForegroundColor White
Write-Host "  Duration: $($duration.ToString('hh\:mm\:ss'))" -ForegroundColor White
Write-Host "  Status: $(if ($process.ExitCode -eq 0) { 'PASSED' } else { 'FAILED' })" -ForegroundColor $(if ($process.ExitCode -eq 0) { 'Green' } else { 'Red' })

# Pause if running interactively
if ($Host.Name -eq "ConsoleHost") {
    Write-Host ""
    Write-Host "Press any key to exit..." -ForegroundColor Gray
    $null = $Host.UI.RawUI.ReadKey("NoEcho,IncludeKeyDown")
}
