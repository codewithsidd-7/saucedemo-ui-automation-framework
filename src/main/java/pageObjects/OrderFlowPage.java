package pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ExplicitWaits;
import utils.FetchProperties;

import java.util.Arrays;
import java.util.List;

public class OrderFlowPage extends BasePage {

    private static final Logger log = LogManager.getLogger(OrderFlowPage.class);
    private ExplicitWaits explicitWaits;

    public OrderFlowPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.explicitWaits = new ExplicitWaits(driver);
    }

    @FindBy(className = "inventory_container")
    private WebElement inventoryContainer;

    @FindBy(css = ".inventory_item")
    private List<WebElement> allProducts;

    @FindBy(id = "shopping_cart_container")
    private WebElement btnCartIcon;

    @FindBy(id = "checkout")
    private WebElement btnCheckout;

    @FindBy(id = "first-name")
    private WebElement inputFirstName;

    @FindBy(id = "last-name")
    private WebElement inputLastName;

    @FindBy(id = "postal-code")
    private WebElement inputPostalCode;

    @FindBy(id = "continue")
    private WebElement btnContinue;

    @FindBy(id = "finish")
    private WebElement btnFinish;

    @FindBy(css = "h3[data-test='error']")
    private WebElement checkoutErrorMsg;

    @FindBy(className = "complete-header")
    private WebElement orderSuccessMsg;

    public void addProductsFromConfig() {
        List<String> productNames =
                Arrays.asList(FetchProperties.get("order_flow_products").split(","));
        explicitWaits.waitForElementToBeVisible(inventoryContainer);

        for (WebElement productCard : allProducts) {
            String productName = productCard
                    .findElement(By.className("inventory_item_name"))
                    .getText()
                    .trim();

            if (productNames.stream().anyMatch(p -> p.equalsIgnoreCase(productName))) {
                WebElement addBtn = productCard.findElement(By.cssSelector("button.btn_inventory"));
                explicitWaits.waitForElementToBeClickable(addBtn);
                addBtn.click();
                log.info("Added product to cart: {}", productName);
            }
        }
    }

    public void goToCart() {
        explicitWaits.waitForElementToBeClickable(btnCartIcon);
        btnCartIcon.click();
        log.info("Navigated to cart");
    }

    public void clickCheckout() {
        explicitWaits.waitForElementToBeClickable(btnCheckout);
        btnCheckout.click();
        log.info("Clicked on checkout");
    }

    public void enterUserInfo(String firstName, String lastName, String postalCode) {
        explicitWaits.waitForElementToBeVisible(inputFirstName);

        inputFirstName.clear();
        inputLastName.clear();
        inputPostalCode.clear();

        if (firstName != null) inputFirstName.sendKeys(firstName);
        if (lastName != null) inputLastName.sendKeys(lastName);
        if (postalCode != null) inputPostalCode.sendKeys(postalCode);

        log.info("Entered user info: firstName='{}', lastName='{}', postalCode='{}'",
                firstName, lastName, postalCode);
    }

    public void clickContinue() {
        explicitWaits.waitForElementToBeClickable(btnContinue);
        btnContinue.click();
        log.info("Clicked continue on checkout page");
    }

    public void clickFinish() {
        explicitWaits.waitForElementToBeClickable(btnFinish);
        btnFinish.click();
        log.info("Clicked finish to complete order");
    }

    public String getCheckoutErrorMessage() {
        explicitWaits.waitForElementToBeVisible(checkoutErrorMsg);
        String error = checkoutErrorMsg.getText();
        log.info("Checkout error message displayed: {}", error);
        return error;
    }

    public boolean isOrderPlacedSuccessfully() {
        explicitWaits.waitForElementToBeVisible(orderSuccessMsg);
        boolean success = orderSuccessMsg.getText().equalsIgnoreCase("Thank you for your order!");
        log.info("Order success status: {}", success);
        return success;
    }
}
