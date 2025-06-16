#!/usr/bin/env pwsh
# Essential Test Runner - Simple and Clean
# Runs only Login and Register essential tests (10 total)

Write-Host "=========================================" -ForegroundColor Green
Write-Host "   ESSENTIAL TEST SUITE EXECUTION" -ForegroundColor Green  
Write-Host "=========================================" -ForegroundColor Green
Write-Host ""

# Set location
Set-Location "c:\Code love life\kuliah\Project\INTERO2\testing-calories-tracker"

# Function to run test
function Run-Test {
    param(
        [string]$TestName,
        [string]$TestRunner
    )
    
    Write-Host "🚀 Running $TestName..." -ForegroundColor Cyan
    Write-Host ""
    
    try {
        mvn test -Dtest=$TestRunner -q
        if ($LASTEXITCODE -eq 0) {
            Write-Host "✅ $TestName PASSED" -ForegroundColor Green
        } else {
            Write-Host "❌ $TestName FAILED" -ForegroundColor Red
        }
    } catch {
        Write-Host "❌ Error running $TestName" -ForegroundColor Red
    }
    
    Write-Host ""
}

# Menu
Write-Host "Select test to run:" -ForegroundColor Yellow
Write-Host "1. All Essential Tests (10 tests)" -ForegroundColor White
Write-Host "2. Register Essential Tests (5 tests)" -ForegroundColor White  
Write-Host "3. Login Essential Tests (5 tests)" -ForegroundColor White
Write-Host "4. Exit" -ForegroundColor White
Write-Host ""

$choice = Read-Host "Enter your choice (1-4)"

switch ($choice) {
    "1" {
        Write-Host "Running ALL Essential Tests..." -ForegroundColor Yellow
        Run-Test "All Essential Tests" "AllEssentialTestRunner"
    }
    "2" {
        Write-Host "Running Register Essential Tests..." -ForegroundColor Yellow
        Run-Test "Register Essential Tests" "RegisterEssentialTestRunner"
    }
    "3" {
        Write-Host "Running Login Essential Tests..." -ForegroundColor Yellow  
        Run-Test "Login Essential Tests" "LoginEssentialTestRunner"
    }
    "4" {
        Write-Host "Exiting..." -ForegroundColor Yellow
        exit
    }
    default {
        Write-Host "Invalid choice. Exiting..." -ForegroundColor Red
        exit
    }
}

Write-Host ""
Write-Host "=========================================" -ForegroundColor Green
Write-Host "          TEST EXECUTION COMPLETE" -ForegroundColor Green
Write-Host "=========================================" -ForegroundColor Green
