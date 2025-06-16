# Verify Configuration Script for Calories Tracker Test Suite
Write-Host "================================================" -ForegroundColor Cyan
Write-Host "CALORIES TRACKER TEST SUITE - CONFIG VERIFICATION" -ForegroundColor Cyan
Write-Host "================================================" -ForegroundColor Cyan
Write-Host ""

# Check config.properties
Write-Host "1. Checking config.properties..." -ForegroundColor Yellow
$configFile = "src\test\resources\config.properties"
if (Test-Path $configFile) {
    $configContent = Get-Content $configFile
    $baseUrl = $configContent | Select-String "base.url="
    if ($baseUrl -like "*8080*") {
        Write-Host "   ✓ Base URL correctly set to port 8080" -ForegroundColor Green
        Write-Host "   $baseUrl" -ForegroundColor Gray
    } else {
        Write-Host "   ✗ Base URL not set to port 8080!" -ForegroundColor Red
        Write-Host "   $baseUrl" -ForegroundColor Gray
    }
} else {
    Write-Host "   ✗ Config file not found!" -ForegroundColor Red
}

Write-Host ""

# Check for any remaining 8001 URLs
Write-Host "2. Checking for remaining port 8001 references..." -ForegroundColor Yellow
$files8001 = Get-ChildItem -Path "src\test\java\pages\*.java" | ForEach-Object {
    $content = Get-Content $_.FullName
    if ($content | Select-String "8001") {
        $_.Name
    }
}

if ($files8001) {
    Write-Host "   ✗ Files still containing port 8001:" -ForegroundColor Red
    $files8001 | ForEach-Object { Write-Host "     - $_" -ForegroundColor Red }
} else {
    Write-Host "   ✓ No files contain port 8001" -ForegroundColor Green
}

Write-Host ""

# Check for 8080 URLs
Write-Host "3. Checking for port 8080 references..." -ForegroundColor Yellow
$files8080 = Get-ChildItem -Path "src\test\java\pages\*.java" | ForEach-Object {
    $content = Get-Content $_.FullName
    if ($content | Select-String "8080") {
        $_.Name
    }
}

if ($files8080) {
    Write-Host "   ✓ Files correctly using port 8080:" -ForegroundColor Green
    $files8080 | ForEach-Object { Write-Host "     - $_" -ForegroundColor Green }
} else {
    Write-Host "   ? No files explicitly use port 8080 (may use config)" -ForegroundColor Yellow
}

Write-Host ""
Write-Host "4. Summary" -ForegroundColor Yellow
if (-not $files8001) {
    Write-Host "   ✓ Configuration verification completed successfully!" -ForegroundColor Green
    Write-Host ""
    Write-Host "You can now run the test suite with:" -ForegroundColor Cyan
    Write-Host "   mvn test -Dtest=SmokeTestRunner" -ForegroundColor White
    Write-Host "   or" -ForegroundColor Cyan
    Write-Host "   .\run-smoke-tests.bat" -ForegroundColor White
} else {
    Write-Host "   ✗ Some files still need to be updated!" -ForegroundColor Red
}

Write-Host ""
Write-Host "================================================" -ForegroundColor Cyan
