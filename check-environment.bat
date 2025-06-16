@echo off
echo ================================================
echo Calories Tracker Test Environment Check
echo ================================================
echo.

echo Checking Java installation...
java -version
if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Java not found! Please install Java 21 or higher.
    echo Download from: https://adoptium.net/
    pause
    exit /b 1
)
echo ✓ Java is installed
echo.

echo Checking Maven installation...
mvn -version
if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Maven not found! Please install Maven.
    echo Download from: https://maven.apache.org/download.cgi
    pause
    exit /b 1
)
echo ✓ Maven is installed
echo.

echo Checking if project dependencies are downloaded...
if not exist "target" (
    echo Downloading dependencies for the first time...
    mvn dependency:resolve
)
echo ✓ Dependencies ready
echo.

echo Checking application servers...
echo Testing frontend server (http://localhost:8001)...
powershell -Command "try { Invoke-WebRequest -Uri 'http://localhost:8001' -Method Head -TimeoutSec 5 -ErrorAction Stop; Write-Host '✓ Frontend server is running' -ForegroundColor Green } catch { Write-Host '✗ Frontend server is NOT running' -ForegroundColor Red; Write-Host 'Please start frontend server first: npm run dev or php artisan serve --port=8001' -ForegroundColor Yellow }"

echo Testing backend server (http://localhost:8000)...
powershell -Command "try { Invoke-WebRequest -Uri 'http://localhost:8000' -Method Head -TimeoutSec 5 -ErrorAction Stop; Write-Host '✓ Backend server is running' -ForegroundColor Green } catch { Write-Host '✗ Backend server is NOT running' -ForegroundColor Red; Write-Host 'Please start backend server first: php artisan serve or npm run start' -ForegroundColor Yellow }"

echo.
echo ================================================
echo Environment Check Complete
echo ================================================
echo.
echo If both servers are running, you can proceed with:
echo   1. run-smoke-tests.bat
echo   2. run-all-tests.bat
echo   3. Or use PowerShell: .\Run-CaloriesTrackerTests.ps1
echo.
pause
