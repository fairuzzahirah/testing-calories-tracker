package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FoodEntryPage {
    WebDriver driver;

    public FoodEntryPage(WebDriver driver) {
        this.driver = driver;
    }

    public void goTo() {
        driver.get("http://localhost:8080/food");
    }

    public void clickAddFoodEntryButton() {
        driver.findElement(By.xpath("//a[contains(@href, '/food/create')]")).click();
    }

    public void enterFoodName(String foodName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("food_name")));
        driver.findElement(By.id("food_name")).sendKeys(foodName);
    }

    public void enterCaloriesPerServing(String calories) { driver.findElement(By.id("calories_per_serving")).sendKeys(calories);}

    public void enterServingAmount(String amount) {
        driver.findElement(By.id("serving_amount")).sendKeys(amount);
    }

    public void enterServingUnit(String unit) {
        driver.findElement(By.id("serving_unit")).sendKeys(unit);
    }

    public void clickSubmit() {
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    public boolean isSuccessMessageShown() {
        String pageSource = driver.getPageSource().toLowerCase();
        return pageSource.contains("success") || pageSource.contains("berhasil menambahkan");
    }

    public boolean isValidationErrorShown() {
        String pageSource = driver.getPageSource().toLowerCase();
        return pageSource.contains("required") || pageSource.contains("harus diisi");
    }
}
