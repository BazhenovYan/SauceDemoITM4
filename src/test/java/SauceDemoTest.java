import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.HashMap;

public class SauceDemoTest {
    WebDriver driver;
    SoftAssert softAssert;

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("credentials_enable_service", false);
        chromePrefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        softAssert = new SoftAssert();
    }

    @Test
    public void buyTest() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        softAssert.assertTrue(driver.findElement(By.cssSelector(".title")).isDisplayed());
        // 2. добавление товара в корзину
        driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
        // 3. перейти в корзину
        driver.findElement(By.className("shopping_cart_link")).click();
        // 4. проверка стоимости товара и его имя в корзине
        String product_name = driver.findElement(By.cssSelector(".inventory_item_name")).getText();
        String price = driver.findElement(By.cssSelector(".inventory_item_price")).getText();
        softAssert.assertEquals(product_name, "Sauce Labs Bolt T-Shirt");
        softAssert.assertEquals(price, "$15.99");
        softAssert.assertAll();
    }

    @AfterMethod
    public void exit() {
        driver.quit();
    }
}