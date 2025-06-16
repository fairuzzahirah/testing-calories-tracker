@echo off
echo ========================================
echo  Calories Tracker - Negative Tests
echo ========================================

echo Starting negative/error handling tests...
mvn test -Dtest=NegativeTestRunner -Dheadless=false

if %ERRORLEVEL% == 0 (
    echo.
    echo ========================================
    echo   NEGATIVE TESTS COMPLETED SUCCESSFULLY
    echo ========================================
    echo Report available at: target\cucumber-reports\index.html
) else (
    echo.
    echo ========================================
    echo    NEGATIVE TESTS FAILED
    echo ========================================
    echo Check logs above for details
)

pause
