package utils;

import org.openqa.selenium.*;
import java.io.*;
import java.nio.file.*;
public class ScreenshotUtil {
    public static String takeScreenshot(WebDriver driver, String name) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir") + "/screenshots/" + name +
                ".png";
        try {
            Files.createDirectories(Paths.get(System.getProperty("user.dir") +
                    "/screenshots/"));
            Files.copy(source.toPath(), Paths.get(destination));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destination;
    }
}