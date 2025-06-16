package util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.List;

public class WaitUtils {
    
    private static final int DEFAULT_WAIT_TIMEOUT = 5; // Reduced from 10 seconds
    private static final int QUICK_WAIT_TIMEOUT = 2;   // For error messages
    private static final int SUCCESS_WAIT_TIMEOUT = 8; // For successful operations
      /**
     * Wait for ANY error message to appear (comprehensive error detection for negative tests)
     */
    public static boolean waitForErrorMessage(WebDriver driver, int timeoutSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
            
            // Comprehensive error message selectors - covers all possible error types
            List<String> errorSelectors = List.of(
                // Text color based errors (red text)
                "*[class*='text-red']",
                "*[class*='text-danger']", 
                "*[style*='color: red']",
                "*[style*='color:red']",
                "*[style*='color:#red']",
                "*[style*='color: #ff']",
                "*[style*='color:#ff']",
                
                // Class based errors
                "*[class*='error']",
                "*[class*='invalid']",
                "*[class*='danger']",
                "*[class*='alert-danger']",
                "*[class*='alert-error']",
                "*[class*='invalid-feedback']",
                "*[class*='field-error']",
                "*[class*='form-error']",
                "*[class*='validation-error']",
                "*[class*='is-invalid']",
                "*[class*='has-error']",
                
                // Bootstrap and common framework errors
                ".text-danger",
                ".alert-danger",
                ".invalid-feedback",
                ".field-with-errors",
                ".error-message",
                ".validation-message",
                
                // Specific error messages
                "[data-testid*='error']",
                "[data-error]",
                "[role='alert']",
                
                // Laravel specific errors
                "*[class*='laravel-error']",
                "*[class*='blade-error']"
            );
            
            // First, check for immediate visibility of any error
            for (String selector : errorSelectors) {
                try {
                    List<WebElement> elements = driver.findElements(By.cssSelector(selector));
                    for (WebElement element : elements) {
                        if (element.isDisplayed() && !element.getText().trim().isEmpty()) {
                            System.out.println("Found error with selector: " + selector + " - Text: " + element.getText());
                            return true;
                        }
                    }
                } catch (Exception e) {
                    // Continue to next selector
                }
            }
            
            // If no immediate errors found, wait for any to appear
            for (String selector : errorSelectors) {
                try {
                    WebElement errorElement = wait.until(
                        ExpectedConditions.presenceOfElementLocated(By.cssSelector(selector))
                    );
                    if (errorElement.isDisplayed() && !errorElement.getText().trim().isEmpty()) {
                        System.out.println("Found error after wait with selector: " + selector + " - Text: " + errorElement.getText());
                        return true;
                    }
                } catch (Exception e) {
                    // Continue to next selector
                }
            }
            
            return false;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Quick wait for error messages (for negative tests)
     */
    public static boolean waitForErrorMessageQuick(WebDriver driver) {
        return waitForErrorMessage(driver, QUICK_WAIT_TIMEOUT);
    }
    
    /**
     * Wait for successful dashboard redirect (for positive tests)
     */
    public static boolean waitForDashboardRedirect(WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(SUCCESS_WAIT_TIMEOUT));
            
            // Wait for URL to contain dashboard
            wait.until(ExpectedConditions.urlContains("dashboard"));
            return driver.getCurrentUrl().contains("dashboard");
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Wait for specific URL pattern (quick check)
     */
    public static boolean waitForUrlContains(WebDriver driver, String urlPart, int timeoutSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
            wait.until(ExpectedConditions.urlContains(urlPart));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Wait for element to be clickable with shorter timeout
     */
    public static WebElement waitForClickable(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIMEOUT));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    
    /**
     * Wait for element to be visible with shorter timeout
     */
    public static WebElement waitForVisible(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIMEOUT));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
    /**
     * Wait for page load completion
     */
    public static boolean waitForPageLoad(WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIMEOUT));
            return wait.until(webDriver -> 
                ((org.openqa.selenium.JavascriptExecutor) webDriver)
                    .executeScript("return document.readyState").equals("complete"));
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Check if any validation error is present (for immediate feedback)
     */
    public static boolean hasValidationError(WebDriver driver) {
        try {
            List<String> validationSelectors = List.of(
                "input:invalid",
                "[aria-invalid='true']",
                ".is-invalid",
                "[data-validation-error]"
            );
            
            for (String selector : validationSelectors) {
                List<WebElement> elements = driver.findElements(By.cssSelector(selector));
                if (!elements.isEmpty()) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Get validation error message from form field
     */
    public static String getValidationMessage(WebDriver driver, String fieldName) {
        try {
            // Try different approaches to get validation message
            WebElement field = driver.findElement(By.id(fieldName));
            
            // HTML5 validation message
            String validationMessage = (String) ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("return arguments[0].validationMessage;", field);
            
            if (validationMessage != null && !validationMessage.trim().isEmpty()) {
                return validationMessage;
            }
            
            // Check for nearby error elements
            try {
                WebElement errorElement = field.findElement(By.xpath("..//*[contains(@class,'error') or contains(@class,'invalid')]"));
                return errorElement.getText();
            } catch (Exception e) {
                // No error element found
            }
            
            return "";
        } catch (Exception e) {
            return "";
        }
    }
    
    /**
     * Check for ANY type of error on the page (immediate check)
     */
    public static boolean hasAnyError(WebDriver driver) {
        try {
            // 1. Check for HTML5 validation errors first
            List<WebElement> invalidInputs = driver.findElements(By.cssSelector("input:invalid, select:invalid, textarea:invalid"));
            if (!invalidInputs.isEmpty()) {
                System.out.println("Found HTML5 validation error on invalid input field");
                return true;
            }
            
            // 2. Check for elements with validation attributes
            List<WebElement> ariaInvalid = driver.findElements(By.cssSelector("[aria-invalid='true']"));
            if (!ariaInvalid.isEmpty()) {
                System.out.println("Found aria-invalid element");
                return true;
            }
            
            // 3. Check for any visible red text (common error indicator)
            List<WebElement> redElements = driver.findElements(By.xpath("//*[contains(@style,'color:red') or contains(@style,'color: red') or contains(@style,'color:#red') or contains(@style,'color: #ff')]"));
            for (WebElement element : redElements) {
                if (element.isDisplayed() && !element.getText().trim().isEmpty()) {
                    System.out.println("Found red text error: " + element.getText());
                    return true;
                }
            }
            
            // 4. Check for elements containing common error keywords in text
            String pageText = driver.getPageSource().toLowerCase();
            List<String> errorKeywords = List.of(
                "error", "invalid", "incorrect", "wrong", "failed", "denied",
                "required", "missing", "empty", "must", "cannot", "forbidden",
                "unauthorized", "not allowed", "please", "warning", "danger",
                "validation", "exception", "problem", "issue"
            );
            
            for (String keyword : errorKeywords) {
                if (pageText.contains(keyword)) {
                    // Find the actual element containing this keyword
                    try {
                        WebElement errorElement = driver.findElement(By.xpath("//*[contains(text(),'" + keyword + "')]"));
                        if (errorElement.isDisplayed()) {
                            System.out.println("Found error keyword '" + keyword + "' in text: " + errorElement.getText());
                            return true;
                        }
                    } catch (Exception e) {
                        // Continue searching
                    }
                }
            }
            
            // 5. Check for Laravel specific error structure
            try {
                WebElement laravelError = driver.findElement(By.xpath("//div[contains(@class,'error') or contains(@class,'invalid') or contains(@class,'danger')]"));
                if (laravelError.isDisplayed() && !laravelError.getText().trim().isEmpty()) {
                    System.out.println("Found Laravel error: " + laravelError.getText());
                    return true;
                }
            } catch (Exception e) {
                // No Laravel error found
            }
            
            return false;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Wait for ANY error to appear on page (comprehensive detection)
     */
    public static boolean waitForAnyError(WebDriver driver, int timeoutSeconds) {
        try {
            // First check immediate errors
            if (hasAnyError(driver)) {
                return true;
            }
            
            // Then wait for errors to appear
            long startTime = System.currentTimeMillis();
            long timeout = timeoutSeconds * 1000;
            
            while (System.currentTimeMillis() - startTime < timeout) {
                if (hasAnyError(driver)) {
                    return true;
                }
                
                // Also check if error messages appear dynamically
                if (waitForErrorMessage(driver, 1)) {
                    return true;
                }
                
                Thread.sleep(500); // Check every 500ms
            }
            
            return false;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Get any error message found on the page
     */
    public static String getAnyErrorMessage(WebDriver driver) {
        try {
            // Priority order for finding error messages
            List<String> selectors = List.of(
                "*[class*='text-red']",
                "*[class*='error']", 
                "*[class*='invalid']",
                "*[class*='danger']",
                "input:invalid",
                "[aria-invalid='true']"
            );
            
            for (String selector : selectors) {
                try {
                    List<WebElement> elements = driver.findElements(By.cssSelector(selector));
                    for (WebElement element : elements) {
                        if (element.isDisplayed()) {
                            String text = element.getText().trim();
                            if (!text.isEmpty()) {
                                return text;
                            }
                            
                            // Check for validation message attribute
                            String validationMessage = (String) ((org.openqa.selenium.JavascriptExecutor) driver)
                                .executeScript("return arguments[0].validationMessage;", element);
                            if (validationMessage != null && !validationMessage.trim().isEmpty()) {
                                return validationMessage;
                            }
                        }
                    }
                } catch (Exception e) {
                    // Continue to next selector
                }
            }
            
            return "";
        } catch (Exception e) {
            return "";
        }
    }
}
