# Sequential Test Runner for Calories Tracker Testing
# Runs test cases from TC-001 to TC-051 in order

Write-Host "=== Sequential Test Runner for Calories Tracker ===" -ForegroundColor Green
Write-Host "Running test cases from TC-001 to TC-051 in sequential order..." -ForegroundColor Yellow

# Change to the correct directory
Set-Location "c:\Code love life\kuliah\Project\INTERO2\testing-calories-tracker"

# Test case numbers to run sequentially
$testCases = @(
    "TC-001", "TC-002", "TC-003", "TC-004", "TC-005", "TC-006", "TC-007", "TC-008", "TC-009", "TC-010",
    "TC-011", "TC-012", "TC-013", "TC-014", "TC-015", "TC-016", "TC-017", "TC-018", "TC-019", "TC-020",
    "TC-021", "TC-022", "TC-023", "TC-024", "TC-029", "TC-030", "TC-031", "TC-032", "TC-033", "TC-034",
    "TC-035", "TC-036", "TC-037", "TC-038", "TC-039", "TC-039b", "TC-039c", "TC-040", "TC-041", "TC-042",
    "TC-043", "TC-044", "TC-045", "TC-046", "TC-047", "TC-048", "TC-049", "TC-050", "TC-051"
)

$totalTests = $testCases.Count
$passedTests = 0
$failedTests = 0
$skippedTests = 0

Write-Host "Total test cases to run: $totalTests" -ForegroundColor Cyan

foreach ($i in 0..($testCases.Count - 1)) {
    $testCase = $testCases[$i]
    $currentTest = $i + 1
    
    Write-Host "`n[$currentTest/$totalTests] Running $testCase..." -ForegroundColor Yellow
    Write-Host "=" * 50 -ForegroundColor DarkGray
    
    try {
        # Run individual test with Maven
        $result = mvn test -Dtest=AllTestRunner -Dcucumber.filter.tags="@$testCase" -q
        
        if ($LASTEXITCODE -eq 0) {
            Write-Host "✓ $testCase PASSED" -ForegroundColor Green
            $passedTests++
        } else {
            Write-Host "✗ $testCase FAILED" -ForegroundColor Red
            $failedTests++
            
            # Show last few lines of output for debugging
            Write-Host "Last output:" -ForegroundColor Yellow
            $result | Select-Object -Last 5 | ForEach-Object { Write-Host "  $_" -ForegroundColor DarkYellow }
        }
    }
    catch {
        Write-Host "✗ $testCase ERROR: $($_.Exception.Message)" -ForegroundColor Red
        $failedTests++
    }
    
    # Brief pause between tests to allow cleanup
    Start-Sleep -Seconds 2
}

Write-Host "`n" + "=" * 60 -ForegroundColor Green
Write-Host "SEQUENTIAL TEST EXECUTION COMPLETED" -ForegroundColor Green
Write-Host "=" * 60 -ForegroundColor Green
Write-Host "Total Tests: $totalTests" -ForegroundColor Cyan
Write-Host "Passed: $passedTests" -ForegroundColor Green
Write-Host "Failed: $failedTests" -ForegroundColor Red
Write-Host "Skipped: $skippedTests" -ForegroundColor Yellow

if ($failedTests -eq 0) {
    Write-Host "`n🎉 ALL TESTS PASSED! 🎉" -ForegroundColor Green
} else {
    Write-Host "`n⚠️  $failedTests test(s) failed. Check the output above for details." -ForegroundColor Yellow
}

Write-Host "`nTest reports can be found in:" -ForegroundColor Cyan
Write-Host "  - target/cucumber-reports/" -ForegroundColor Gray
Write-Host "  - target/surefire-reports/" -ForegroundColor Gray
