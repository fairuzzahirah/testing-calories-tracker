package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CustomFoodPage {
    WebDriver driver;

    public CustomFoodPage(WebDriver driver) {
        this.driver = driver;
    }

    public void goTo() {
        driver.get("http://localhost:8080/custom-food");
    }

    public void clickCustomFoodButton() {
        driver.findElement(By.xpath("//a[contains(@href, '/custom-food/create')]")).click();
    }

    public void enterFoodName(String foodName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("food_name")));
        driver.findElement(By.id("food_name")).sendKeys(foodName);
    }

    public void enterCalories(String calories) {
        driver.findElement(By.id("calories")).sendKeys(calories);
    }

    public void enterServingUnit(String unit) {
        driver.findElement(By.name("serving_unit")).sendKeys(unit);
    }

    public void selectCategory(String categoryText) {
        Select dropdown = new Select(driver.findElement(By.id("category")));
        dropdown.selectByVisibleText(categoryText);
    }

    public void enterNotes(String notes) {
        driver.findElement(By.name("notes")).sendKeys(notes);
    }

    public void clickSubmit() {
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }
    public boolean isSuccessMessageShown() {
        String pageSource = driver.getPageSource().toLowerCase();
        return pageSource.contains("success") || pageSource.contains("Custom food created successfully!");
    }

    public boolean isValidationErrorShown() {
        String pageSource = driver.getPageSource().toLowerCase();
        return pageSource.contains("required") || pageSource.contains("please fill out this field");
    }
}