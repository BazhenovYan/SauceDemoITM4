package tests;

import jdk.jfr.Description;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CartTest extends BaseTest {

    @Test(testName = "Проверка отображения страницы корзины")
    public void checkOpenCart() {
        loginPage.open();
        loginPage.login("standard_user","secret_sauce");
        cartPage.open();
        cartPage.getCartTitle();
    }

    @Test(testName = "Проверка присутствия кнопки 'Continue shopping'")
    public void checkButtonContinue() {
        loginPage.open();
        loginPage.login("standard_user","secret_sauce");
        cartPage.open();
        cartPage.getCartTitle();
        assertTrue(cartPage.getButtonContinue());
    }

    @Test(testName = "Проверка добавления товара в корзину")
    @Description("Позитивное тестирование")
    public void addProductIntoCart() {
        loginPage.open();
        loginPage.login("standard_user","secret_sauce");
        productsPage.addToCartBike();
        cartPage.open();
        cartPage.getCartTitle();
        cartPage.getElementText();
    }

    @Test(testName = "Проверка удаления товара")
    @Description("Позитивное тестирование")
    public void deleteProduct() {
        loginPage.open();
        loginPage.login("standard_user","secret_sauce");
        productsPage.addToCartBike();
        cartPage.open();
        cartPage.getCartTitle();
        cartPage.removeBike();
        softAssert.assertFalse(cartPage.checkSizeCart(), "Товар не был удалён из корзины");
    }

    @Test(testName = "Проверка цены добавленного товара")
    @Description("Позитивное тестирование")
    public void checkPriceProduct() {
        loginPage.open();
        loginPage.login("standard_user","secret_sauce");
        productsPage.addToCartBike();
        cartPage.open();
        cartPage.getCartTitle();
        assertEquals(cartPage.getPriceProduct(), "$9.99");
    }
}
