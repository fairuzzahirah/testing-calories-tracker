package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FoodEntryPage {
    WebDriver driver;

    public FoodEntryPage(WebDriver driver) {
        this.driver = driver;
    }

    public void goTo() {
        driver.get("http://127.0.0.1:8080/food");
    }

    public void clickAddFoodEntryButton() {
        driver.findElement(By.linkText("Add Food Entry")).click();
    }

    public void enterFoodName(String foodName) {
        driver.findElement(By.id("food_name")).sendKeys(foodName);
    }

    public void enterCaloriesPerServing(String calories) {
        driver.findElement(By.id("calories_per_serving")).sendKeys(calories);
    }

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
