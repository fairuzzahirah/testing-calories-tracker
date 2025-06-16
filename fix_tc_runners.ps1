# Script untuk memperbaiki format tag di semua TC Runner files
$basePath = "src\test\java\testrunner"

# Perbaiki TC-001 sampai TC-024 yang ada
$existingTCs = @("001", "002", "003", "004", "005", "006", "007", "008", "009", "010", 
                 "011", "012", "013", "014", "015", "016", "017", "018", "019", "020", 
                 "021", "022", "023", "024")

foreach ($tc in $existingTCs) {
    $fileName = "TC${tc}Runner.java"
    $filePath = Join-Path $basePath $fileName
    
    if (Test-Path $filePath) {
        $content = Get-Content $filePath -Raw
        $oldTag = "tags = `"@TC${tc}`","
        $newTag = "tags = `"@TC-${tc}`","
        $content = $content -replace [regex]::Escape($oldTag), $newTag
        Set-Content -Path $filePath -Value $content -Encoding UTF8
        Write-Host "Updated: $fileName"
    }
}

Write-Host "All TC Runner files have been updated with correct tag format!"
