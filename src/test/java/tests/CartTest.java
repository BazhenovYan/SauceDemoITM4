package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class CartTest extends BaseTest {
    @Test(priority = 6, testName = "Проверка отображения страницы корзины без добавления товара",
            groups = {"regression"})
    public void checkPageYourCart() {

        driver.get("https://www.saucedemo.com/");

        // Залогиниться
        cartPage.openYourCartPage();
        loginPage.login("standard_user", "secret_sauce");

        // Перейти в корзину
        cartPage.openShoppingCart();
        // Проверить отображение страницы
        assertTrue(cartPage.isYourCartPage());
    }

    @Test(priority = 5, testName = "Проверка цены и названия товара после добавления в корзину",
            groups = {"smoke"})
    public void checkProductYourCart() {

        driver.get("https://www.saucedemo.com/");

        // Залогиниться
        loginPage.login("standard_user", "secret_sauce");
        // Получить ожидаемую имя и цену продукта
        cartPage.expectedPriceProduct();
        cartPage.expectedNameProduct();

        // Добавить товар в корзину
        cartPage.addProduct();

        // Перейти в корзину
        cartPage.openShoppingCart();

        // Проверить стоимость и имя товара в корзине
        softAssert.assertEquals(cartPage.getPriceProduct(),
                cartPage.expectedPriceProduct(),
                "Название товара не совпадает: ");
        softAssert.assertEquals(cartPage.getNameProduct(),
                cartPage.expectedNameProduct(),
                "Цена не совпадает: ");
        softAssert.assertAll();
    }

    @Test(priority = 1, invocationCount = 2,
            testName = "Проверка иконки корзины после добавления товара",
            groups = {"smoke"})
    public void checkCartBadge() {

        driver.get("https://www.saucedemo.com/");

        // Залогиниться
        loginPage.login("standard_user", "secret_sauce");

        // Добавить товар в корзину
        cartPage.addProduct();
        // Получить иконку корзины с количеством
        cartPage.getCartBadge();

        // Добавить товар в корзину
        cartPage.addProduct();

        // Перейти в корзину
        cartPage.openShoppingCart();

        // Проверить иконку в корзине
        softAssert.assertEquals(cartPage.getCartBadgeShopping(),
                cartPage.getCartBadge(),
                "Иконка корзины не совпадает: ");
        softAssert.assertAll();
    }

    @Test(priority = 3, testName = "Проверка отображения страницы при переходе по кнопке Checkout",
            groups = {"smoke"})
    public void checkButtonCheckout() {

        driver.get("https://www.saucedemo.com/");

        // Залогиниться
        cartPage.openCheckoutPage1();
        loginPage.login("standard_user", "secret_sauce");

        // Добавить товар в корзину
        cartPage.addProduct();

        // Перейти в корзину
        cartPage.openShoppingCart();

        // Перейти по кнопке Checkout
        cartPage.clickButtonCheckout();

        // Проверить отображение страницы
        assertTrue(cartPage.isCheckoutPage1());
    }

    @Test(priority = 4, testName = "Проверка удаления товара в корзине",
            groups = {"smoke"})
    public void checkButtonRemoveProductYourCart() {

        driver.get("https://www.saucedemo.com/");

        // Залогиниться
        loginPage.login("standard_user", "secret_sauce");
        // Получить ожидаемую имя и цену продукта
        cartPage.expectedNameProduct();
        cartPage.expectedPriceProduct();

        // Добавить товар в корзину
        cartPage.addProduct();

        // Перейти в корзину
        cartPage.openShoppingCart();

        // Удалить товар
        cartPage.removeButtonProduct();

        // Проверить удаление товара
        softAssert.assertFalse(cartPage.isCartBadge(), "Товар не был удалён из корзины");
        softAssert.assertAll();
    }
}