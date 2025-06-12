package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SharedDriver {
    private static WebDriver driver;

    private SharedDriver() {}

    public static WebDriver getDriver() {
        if (driver == null) {
            driver = new ChromeDriver(); // bisa juga FirefoxDriver atau lainnya
        }
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
