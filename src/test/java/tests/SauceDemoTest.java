package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class SauceDemoTest extends BaseTest {

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
}