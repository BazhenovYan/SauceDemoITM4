package pages;

import org.openqa.selenium.WebDriver;

// Общая логика страницы
public class BasePage {

    public final String BASE_URL = "https://www.saucedemo.com/";
    WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }
}
