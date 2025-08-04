package tests;


import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {

    @Test (priority = 1)
    public void checkLoginWithoutPassword() {
        loginPage.open();
        loginPage.login("standard_user", "");
        assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Password is required",
                "Сообщение не соответствует");
    }

    @Test(priority = 3)
    public void checkLoginWithoutLogin() {
        loginPage.open();
        loginPage.login("", "secret_sauce");
        assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Username is required",
                "Сообщение не соответствует");
    }

    @Test (priority = 4)
    public void checkLoginWithoutNegativeValue() {
        loginPage.open();
        loginPage.login("standawa", "123456");
        assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Username and password do not match any user in this service",
                "Сообщение не соответствует");
    }

    @Test (priority = 2)
    public void checkLogin() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertTrue(productsPage.isPageOpened());
    }
}
