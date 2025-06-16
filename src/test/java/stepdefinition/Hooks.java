package stepdefinition;

import io.cucumber.java.*;
import org.openqa.selenium.WebDriver;
import utils.ExtentReportManager;
import utils.ScreenshotUtil;
import com.aventstack.extentreports.*;

public class Hooks {
    WebDriver driver;

    // Tambahan untuk Extent Report
    private static ExtentReports extent = ExtentReportManager.getInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Before
    public void setUp(Scenario scenario) {
        driver = SharedDriver.getDriver(); // nyalain driver sekali

        // Inisialisasi Extent Report untuk setiap scenario
        ExtentTest extentTest = extent.createTest(scenario.getName());
        test.set(extentTest);
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        if (scenario.isFailed()) {
            // Ambil screenshot saat gagal dan tambahkan ke report
            String screenshotPath = ScreenshotUtil.takeScreenshot(driver, scenario.getName().replace(" ", "_"));
            test.get().fail("Step failed")
                    .addScreenCaptureFromPath(screenshotPath);
        }
    }

    @After
    public void flushReport() {
        extent.flush(); // Tutup dan simpan report HTML
    }

    @AfterAll
    public static void afterAllTests() {
        SharedDriver.closeDriver();
    }
}
