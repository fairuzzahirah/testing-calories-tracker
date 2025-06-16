package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;
    private static final String CONFIG_FILE = "src/test/resources/config.properties";

    static {
        loadProperties();
    }

    private static void loadProperties() {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream(CONFIG_FILE)) {
            properties.load(fis);
        } catch (IOException e) {
            // If config file doesn't exist, use default values
            setDefaultProperties();
        }
    }    private static void setDefaultProperties() {
        properties.setProperty("base.url", "http://localhost:8080");
        properties.setProperty("backend.url", "http://localhost:8000");
        properties.setProperty("timeout", "10");
        properties.setProperty("demo.email", "demo@example.com");
        properties.setProperty("demo.password", "password123");
        properties.setProperty("browser", "chrome");
        properties.setProperty("headless", "false");
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static String getBaseUrl() {
        return getProperty("base.url");
    }

    public static String getBackendUrl() {
        return getProperty("backend.url");
    }

    public static int getTimeout() {
        return Integer.parseInt(getProperty("timeout"));
    }

    public static String getDemoEmail() {
        return getProperty("demo.email");
    }

    public static String getDemoPassword() {
        return getProperty("demo.password");
    }

    public static String getBrowser() {
        return getProperty("browser");
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(getProperty("headless"));
    }
}
