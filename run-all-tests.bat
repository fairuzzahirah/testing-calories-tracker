@echo off
echo ========================================
echo   Calories Tracker - All Tests
echo ========================================

echo Starting comprehensive test suite...
mvn test -Dtest=AllTestRunner -Dheadless=false

if %ERRORLEVEL% == 0 (
    echo.
    echo ========================================
    echo    ALL TESTS COMPLETED SUCCESSFULLY
    echo ========================================
    echo Report available at: target\cucumber-reports\index.html
) else (
    echo.
    echo ========================================
    echo    SOME TESTS FAILED
    echo ========================================
    echo Check logs above for details
)

pause
