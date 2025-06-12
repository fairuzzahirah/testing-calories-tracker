package stepdefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import util.SharedDriver;

public class Hooks {
    WebDriver driver;

    @Before
    public void setUp() {
        driver = SharedDriver.getDriver(); // nyalain driver sekali
    }

    @After(order = 1)
    public void tearDown() {
        // Jangan tutup driver otomatis, biar bisa reuse di scenario lain kalau perlu
        // Tapi kalau mau clean up di akhir semua, bisa aktifin baris di bawah ini
        // SharedDriver.closeDriver();
    }
}
