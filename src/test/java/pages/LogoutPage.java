package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LogoutPage {
    WebDriver driver;

    public LogoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void goToDashboard() {
        driver.get("http://localhost:8080/dashboard");
    }

    public void clickDropdown() {
        WebElement dropdown = driver.findElement(By.cssSelector("button.inline-flex.items-center"));
        dropdown.click();
    }

    public void clickLogout() {
        WebElement logoutLink = driver.findElement(By.xpath("//a[contains(text(), 'Log Out')]"));
        logoutLink.click();
    }
}
