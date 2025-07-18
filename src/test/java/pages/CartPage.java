package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage{

    private final By CART_TITLE = By.xpath("//span[text()='Your Cart']"); // название страницы
    private final By ELEMENT_BIKE = By.xpath("//div[text()='Sauce Labs Bike Light']"); // фикс конкр. товара
    private final By REMOVE_BIKE = By.cssSelector("#remove-sauce-labs-bike-light"); // фикс. кнопки удаления кнркт. товара
    private final By CHECK_SIZE = By.cssSelector("[data-test=shopping-cart-badge]");
    private final By PRICE_PRODUCT = By.cssSelector(".inventory_item_price");
    private final By BUTTON_CONTINUE_SHOPPING = By.cssSelector("#continue-shopping");

    public CartPage(WebDriver driver) {
        super(driver);
    }
// метод открытия страницы корзины
    public void open(){
        driver.get(BASE_URL + "cart.html");
    }
// метод получения текста по локатору, для опредления нужной страницы
    public String getCartTitle() {
        return driver.findElement(CART_TITLE).getText();
    }
    // Метод получения текста по локатору для определения товара на странице
    public String getElementText() {
        return driver.findElement(ELEMENT_BIKE).getText();
    }
    // Метод для удаления
    public void removeBike(){
        driver.findElement(REMOVE_BIKE).click();
    }
    public boolean checkSizeCart() {
        return !driver.findElements(CHECK_SIZE).isEmpty();
    }
    public String getPriceProduct() {
        return driver.findElement(PRICE_PRODUCT).getText();
    }
    public boolean getButtonContinue() {
        return driver.findElement(BUTTON_CONTINUE_SHOPPING).isDisplayed();
    }
}
