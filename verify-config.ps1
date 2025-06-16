# Verify Configuration Script for Calories Tracker Test Suite
# This script verifies that all configurations are set correctly for port 8080

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

# Check page object URLs
Write-Host "2. Checking Page Object URLs..." -ForegroundColor Yellow
$pageFiles = @(
    "src\test\java\pages\LoginPage.java",
    "src\test\java\pages\RegisterPage.java", 
    "src\test\java\pages\DashboardPage.java",
    "src\test\java\pages\FoodEntryPage.java",
    "src\test\java\pages\CustomFoodPage.java",
    "src\test\java\pages\USDASearchPage.java",
    "src\test\java\pages\ChatbotPage.java",
    "src\test\java\pages\ProfilePage.java"
)

$allCorrect = $true
foreach ($file in $pageFiles) {
    if (Test-Path $file) {
        $content = Get-Content $file
        $urls8001 = $content | Select-String "8001"
        $urls8080 = $content | Select-String "8080"
        
        $fileName = Split-Path $file -Leaf
        if ($urls8001) {
            Write-Host "   ✗ $fileName still contains port 8001!" -ForegroundColor Red
            $allCorrect = $false
        } elseif ($urls8080) {
            Write-Host "   ✓ $fileName correctly uses port 8080" -ForegroundColor Green
        } else {
            Write-Host "   ? $fileName no port found (may use config)" -ForegroundColor Yellow
        }
    } else {
        Write-Host "   ✗ $file not found!" -ForegroundColor Red
        $allCorrect = $false
    }
}

Write-Host ""

# Check if Maven is available
Write-Host "3. Checking Maven availability..." -ForegroundColor Yellow
$mavenCheck = Get-Command mvn -ErrorAction SilentlyContinue
if ($mavenCheck) {
    Write-Host "   ✓ Maven is available" -ForegroundColor Green
} else {
    Write-Host "   ✗ Maven not found in PATH!" -ForegroundColor Red
    Write-Host "   Please install Maven or add it to your PATH" -ForegroundColor Yellow
}

Write-Host ""

# Check if Java is available
Write-Host "4. Checking Java availability..." -ForegroundColor Yellow
$javaCheck = Get-Command java -ErrorAction SilentlyContinue
if ($javaCheck) {
    Write-Host "   ✓ Java is available" -ForegroundColor Green
} else {
    Write-Host "   ✗ Java not found in PATH!" -ForegroundColor Red
    Write-Host "   Please install Java or add it to your PATH" -ForegroundColor Yellow
}

Write-Host ""

# Summary
Write-Host "5. Summary" -ForegroundColor Yellow
if ($allCorrect) {
    Write-Host "   ✓ All page objects are configured for port 8080" -ForegroundColor Green
    Write-Host "   ✓ Configuration verification completed successfully!" -ForegroundColor Green
    Write-Host ""
    Write-Host "You can now run the test suite with:" -ForegroundColor Cyan
    Write-Host "   mvn test -Dtest=SmokeTestRunner" -ForegroundColor White
    Write-Host "   or" -ForegroundColor Cyan
    Write-Host "   .\run-smoke-tests.bat" -ForegroundColor White
} else {
    Write-Host "   ✗ Some configurations need to be fixed!" -ForegroundColor Red
    Write-Host "   Please check the errors above" -ForegroundColor Yellow
}

Write-Host ""
Write-Host "================================================" -ForegroundColor Cyan
