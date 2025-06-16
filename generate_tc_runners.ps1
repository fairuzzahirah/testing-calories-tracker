# Script untuk generate semua TC Runner files
$basePath = "src\test\java\testrunner"

# Buat direktori jika belum ada
if (!(Test-Path $basePath)) {
    New-Item -ItemType Directory -Path $basePath -Force
}

# Generate TC-001 sampai TC-051
for ($i = 1; $i -le 51; $i++) {
    $tcNumber = "TC{0:D3}" -f $i
    $className = "${tcNumber}Runner"
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
    
    # Write file
    Set-Content -Path $filePath -Value $content -Encoding UTF8
    Write-Host "Created: $fileName"
}

Write-Host "All TC Runner files have been generated successfully!"
