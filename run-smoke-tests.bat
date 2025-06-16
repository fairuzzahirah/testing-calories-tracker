@echo off
echo ========================================
echo    Calories Tracker - Smoke Tests
echo ========================================

echo Starting smoke tests...
mvn test -Dtest=SmokeTestRunner -Dheadless=false

if %ERRORLEVEL% == 0 (
    echo.
    echo ========================================
    echo    SMOKE TESTS COMPLETED SUCCESSFULLY
    echo ========================================
    echo Report available at: target\cucumber-reports\index.html
) else (
    echo.
    echo ========================================
    echo    SMOKE TESTS FAILED
    echo ========================================
    echo Check logs above for details
)

pause
