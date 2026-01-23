package pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.ExplicitWaits;

import java.util.List;
import java.util.Random;

public class AddRemoveCartPage extends BasePage {

    private static final Logger log = LogManager.getLogger(AddRemoveCartPage.class);
    private ExplicitWaits waits;
    private Random random = new Random();

    public AddRemoveCartPage(WebDriver driver) {
        super(driver);
        this.waits = new ExplicitWaits(driver);
    }

    @FindBy(css = "button.btn_inventory")
    private List<WebElement> btnAddToCart;

    @FindBy(css = ".shopping_cart_badge")
    private WebElement cartBadge;

    public void addRandomProductToCart() {
        int index = random.nextInt(btnAddToCart.size());
        waits.waitForElementToBeClickable(btnAddToCart.get(index));
        btnAddToCart.get(index).click();
        log.info("Added product to cart at index: {}", index);
    }

    public void addMultipleProducts(int count) {
        int added = 0;
        while (added < count) {
            int index = random.nextInt(btnAddToCart.size());
            WebElement button = btnAddToCart.get(index);

            if (button.getText().equalsIgnoreCase("Add to cart")) {
                waits.waitForElementToBeClickable(button);
                button.click();
                log.info("Added product to cart at index: {}", index);
                added++;
            }
        }
    }

    public void removeRandomProductFromCart() {
        for (WebElement button : btnAddToCart) {
            if (button.getText().equalsIgnoreCase("Remove")) {
                waits.waitForElementToBeClickable(button);
                button.click();
                log.info("Removed a product from cart");
                break;
            }
        }
    }

    public void removeMultipleProducts(int count) {
        int removed = 0;
        for (WebElement button : btnAddToCart) {
            if (removed >= count) break;
            if (button.getText().equalsIgnoreCase("Remove")) {
                waits.waitForElementToBeClickable(button);
                button.click();
                removed++;
                log.info("Removed a product from cart");
            }
        }
    }

    public int getCartItemCount() {
        try {
            int count = Integer.parseInt(cartBadge.getText());
            log.info("Current cart count: {}", count);
            return count;
        } catch (Exception e) {
            log.info("Cart is empty");
            return 0;
        }
    }
}
