package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage{

    private final By TITLE = By.cssSelector(".title");
    private final By ADD_BIKE = By.cssSelector("#add-to-cart-sauce-labs-bike-light");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public void open(){
        driver.get(BASE_URL + "/inventory.html");
    }

    public boolean isPageOpened() {
        return driver.findElement((TITLE)).isDisplayed();
    }
    // Добавить товар bike
    public void addToCartBike() {
        driver.findElement(ADD_BIKE).click();
    }
}
