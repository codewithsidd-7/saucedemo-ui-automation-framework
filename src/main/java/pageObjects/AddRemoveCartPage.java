package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.ExplicitWaits;

import java.util.List;
import java.util.Random;

public class AddRemoveCartPage extends BasePage {

    private ExplicitWaits waits;
    private Random random = new Random();

    public AddRemoveCartPage(WebDriver driver) {
        super(driver);
        this.waits = new ExplicitWaits(driver);
    }

    // Locators
    @FindBy(css = "button.btn_inventory")
    private List<WebElement> btnAddToCart;

    @FindBy(css = ".shopping_cart_badge")
    private WebElement cartBadge;

    // Action Methods

    public void addRandomProductToCart() {
        int index = random.nextInt(btnAddToCart.size());
        waits.waitForElementToBeClickable(btnAddToCart.get(index));
        btnAddToCart.get(index).click();
    }

    public void addMultipleProducts(int count) {
        int added = 0;
        while (added < count) {
            int index = random.nextInt(btnAddToCart.size());
            WebElement button = btnAddToCart.get(index);

            if (button.getText().equalsIgnoreCase("Add to cart")) {
                waits.waitForElementToBeClickable(button);
                button.click();
                added++;
            }
            // if button already says "Remove", skip
        }
    }


    public void removeRandomProductFromCart() {
        for (WebElement button : btnAddToCart) {
            if (button.getText().equalsIgnoreCase("Remove")) {
                waits.waitForElementToBeClickable(button);
                button.click();
                break; // remove only one product
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
            }
        }
    }

    public int getCartItemCount() {
        try {
            return Integer.parseInt(cartBadge.getText());
        } catch (Exception e) {
            return 0;
        }
    }
}
