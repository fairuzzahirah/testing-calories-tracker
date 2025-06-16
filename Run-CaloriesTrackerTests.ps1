# PowerShell script to run comprehensive Calories Tracker tests
param(
    [string]$TestType = "all",
    [string]$Browser = "chrome",
    [bool]$Headless = $false,
    [bool]$GenerateReport = $true
)

Write-Host "============================================" -ForegroundColor Green
Write-Host "Calories Tracker Automated Test Suite" -ForegroundColor Green
Write-Host "============================================" -ForegroundColor Green

# Validate parameters
$validTestTypes = @("all", "smoke", "positive", "negative", "security")
if ($TestType -notin $validTestTypes) {
    Write-Host "Invalid test type: $TestType" -ForegroundColor Red
    Write-Host "Valid options: $($validTestTypes -join ', ')" -ForegroundColor Yellow
    exit 1
}

# Check if servers are running
Write-Host "Checking if application servers are running..." -ForegroundColor Yellow

$frontendUrl = "http://localhost:8001"
$backendUrl = "http://localhost:8000"

try {
    $frontendResponse = Invoke-WebRequest -Uri $frontendUrl -Method Head -TimeoutSec 5 -ErrorAction Stop
    Write-Host "✓ Frontend server is running on $frontendUrl" -ForegroundColor Green
} catch {
    Write-Host "✗ Frontend server is not running on $frontendUrl" -ForegroundColor Red
    Write-Host "Please start the frontend server first." -ForegroundColor Yellow
    exit 1
}

try {
    $backendResponse = Invoke-WebRequest -Uri "$backendUrl/api/health" -Method Get -TimeoutSec 5 -ErrorAction Stop
    Write-Host "✓ Backend server is running on $backendUrl" -ForegroundColor Green
} catch {
    Write-Host "✗ Backend server is not running on $backendUrl" -ForegroundColor Red
    Write-Host "Please start the backend server first." -ForegroundColor Yellow
    exit 1
}

# Set test runner based on test type
$testRunner = switch ($TestType) {
    "all" { "AllTestRunner" }
    "smoke" { "SmokeTestRunner" }
    "positive" { "PositiveTestRunner" }
    "negative" { "NegativeTestRunner" }
    "security" { "SecurityTestRunner" }
}

# Clean previous test results
$reportDir = "target\cucumber-reports\$TestType"
if (Test-Path $reportDir) {
    Write-Host "Cleaning previous test results..." -ForegroundColor Yellow
    Remove-Item $reportDir -Recurse -Force
}

# Build Maven command
$mavenCmd = "mvn clean test"
$mavenCmd += " -Dtest=$testRunner"
$mavenCmd += " -Dbrowser=$Browser"
$mavenCmd += " -Dheadless=$Headless"

Write-Host "Running $TestType tests..." -ForegroundColor Yellow
Write-Host "Command: $mavenCmd" -ForegroundColor Cyan

# Execute tests
$startTime = Get-Date
try {
    Invoke-Expression $mavenCmd
    $exitCode = $LASTEXITCODE
} catch {
    Write-Host "Error executing tests: $($_.Exception.Message)" -ForegroundColor Red
    exit 1
}

$endTime = Get-Date
$duration = $endTime - $startTime

Write-Host "============================================" -ForegroundColor Green
if ($exitCode -eq 0) {
    Write-Host "✓ $TestType Tests PASSED!" -ForegroundColor Green
} else {
    Write-Host "✗ $TestType Tests FAILED!" -ForegroundColor Red
}
Write-Host "Execution time: $($duration.ToString('mm\:ss'))" -ForegroundColor Cyan
Write-Host "============================================" -ForegroundColor Green

# Generate and open report
if ($GenerateReport -and (Test-Path "$reportDir\index.html")) {
    Write-Host "Opening test report..." -ForegroundColor Yellow
    Start-Process "$reportDir\index.html"
} elseif ($GenerateReport) {
    Write-Host "Test report not found at: $reportDir\index.html" -ForegroundColor Yellow
}

# Summary
Write-Host "`nTest Summary:" -ForegroundColor Cyan
Write-Host "- Test Type: $TestType" -ForegroundColor White
Write-Host "- Browser: $Browser" -ForegroundColor White
Write-Host "- Headless: $Headless" -ForegroundColor White
Write-Host "- Duration: $($duration.ToString('mm\:ss'))" -ForegroundColor White
Write-Host "- Status: $(if ($exitCode -eq 0) { 'PASSED' } else { 'FAILED' })" -ForegroundColor $(if ($exitCode -eq 0) { 'Green' } else { 'Red' })

exit $exitCode
