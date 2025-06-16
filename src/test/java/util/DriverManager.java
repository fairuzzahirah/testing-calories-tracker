package util;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {
    private static WebDriver driver;
    private static final String BROWSER = System.getProperty("browser", "chrome");
    private static final String HEADLESS = System.getProperty("headless", "false");    public static WebDriver getDriver() {
        if (driver == null || !isDriverAlive()) {
            initializeDriver();
        }
        return driver;
    }

    private static boolean isDriverAlive() {
        try {
            if (driver != null) {
                driver.getTitle();
                return true;
            }
        } catch (Exception e) {
            System.out.println("WebDriver session is not alive: " + e.getMessage());
            driver = null;
        }
        return false;
    }    private static void initializeDriver() {
        // Quit existing driver if it exists
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                System.out.println("Error quitting existing driver: " + e.getMessage());
            }
            driver = null;
        }

        System.out.println("Initializing WebDriver with browser: " + BROWSER);
        
        switch (BROWSER.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                if ("true".equalsIgnoreCase(HEADLESS)) {
                    chromeOptions.addArguments("--headless");
                }
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.addArguments("--disable-gpu");
                chromeOptions.addArguments("--window-size=1920,1080");
                chromeOptions.addArguments("--remote-allow-origins=*");
                chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
                driver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;

            default:
                throw new IllegalArgumentException("Browser not supported: " + BROWSER);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        
        System.out.println("WebDriver initialized successfully");
    }    public static void quitDriver() {
        if (driver != null) {
            try {
                driver.quit();
                System.out.println("WebDriver quit successfully");
            } catch (Exception e) {
                System.out.println("Error quitting driver: " + e.getMessage());
            } finally {
                driver = null;
            }
        }
    }

    public static void closeDriver() {
        if (driver != null) {
            try {
                driver.close();
            } catch (Exception e) {
                System.out.println("Error closing driver: " + e.getMessage());
            }
        }
    }
}
