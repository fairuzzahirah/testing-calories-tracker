# Script untuk generate TC Runner files yang sesuai dengan TC yang ada
$basePath = "src\test\java\testrunner"

# Buat direktori jika belum ada
if (!(Test-Path $basePath)) {
    New-Item -ItemType Directory -Path $basePath -Force
}

# Daftar TC yang benar-benar ada berdasarkan feature files
$existingTCs = @(
    "001", "002", "003", "004", "005", "006", "007", "008", "009", "010",
    "011", "012", "013", "014", "015", "016", "017", "018", "019", "020",
    "021", "022", "023", "024"
)

foreach ($tc in $existingTCs) {
    $tcNumber = "TC-${tc}"
    $className = "TC${tc}Runner"
    $fileName = "${className}.java"
    $filePath = Join-Path $basePath $fileName
    
    $content = @"
package testrunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.junit.AfterClass;
import stepdefinition.Hooks;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepdefinition"},
        tags = "@$tcNumber",
        plugin = {
                "pretty",
                "html:target/cucumber-reports/$tcNumber/html",
                "json:target/cucumber-reports/$tcNumber/cucumber.json",
                "junit:target/cucumber-reports/$tcNumber/cucumber.xml"
        },
        monochrome = true,
        publish = false
)
public class $className {
    
    @AfterClass
    public static void tearDown() {
        Hooks.quitDriver();
    }
}
"@
    
    # Write file with UTF-8 encoding without BOM
    [System.IO.File]::WriteAllText($filePath, $content, [System.Text.UTF8Encoding]::new($false))
    Write-Host "Created: $fileName"
}

Write-Host "All TC Runner files have been generated successfully!"
