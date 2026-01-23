package tests;

import baseTest.BaseClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import pageObjects.OrderFlowPage;
import utils.FetchProperties;

public class OrderFlowTest extends BaseClass {

    private static final Logger log = LogManager.getLogger(OrderFlowTest.class);

    private LoginPage loginPage;
    private OrderFlowPage orderFlowPage;

    @BeforeMethod
    public void setUp() {
        driver.get(FetchProperties.get("base_url"));
        loginPage = new LoginPage(driver);
        loginPage.validLogin();
        orderFlowPage = new OrderFlowPage(driver);
    }

    @Test(priority = 1)
    public void shouldPlaceOrderSuccessfullyWithValidUserInfo() {
        log.info("Starting test: shouldPlaceOrderSuccessfullyWithValidUserInfo");
        orderFlowPage.addProductsFromConfig();
        orderFlowPage.goToCart();
        orderFlowPage.clickCheckout();

        orderFlowPage.enterUserInfo(
                FetchProperties.get("first_name"),
                FetchProperties.get("last_name"),
                FetchProperties.get("postal_code")
        );

        orderFlowPage.clickContinue();
        orderFlowPage.clickFinish();

        Assert.assertTrue(orderFlowPage.isOrderPlacedSuccessfully(),
                "Order was not completed successfully");
    }

    @Test(priority = 2)
    public void shouldShowErrorWhenFirstNameIsMissing() {
        log.info("Starting test: shouldShowErrorWhenFirstNameIsMissing");
        orderFlowPage.addProductsFromConfig();
        orderFlowPage.goToCart();
        orderFlowPage.clickCheckout();

        orderFlowPage.enterUserInfo(
                null,
                FetchProperties.get("last_name"),
                FetchProperties.get("postal_code")
        );

        orderFlowPage.clickContinue();
        Assert.assertEquals(orderFlowPage.getCheckoutErrorMessage(),
                "Error: First Name is required");
    }

    @Test(priority = 3)
    public void shouldShowErrorWhenLastNameIsMissing() {
        log.info("Starting test: shouldShowErrorWhenLastNameIsMissing");
        orderFlowPage.addProductsFromConfig();
        orderFlowPage.goToCart();
        orderFlowPage.clickCheckout();

        orderFlowPage.enterUserInfo(
                FetchProperties.get("first_name"),
                null,
                FetchProperties.get("postal_code")
        );

        orderFlowPage.clickContinue();
        Assert.assertEquals(orderFlowPage.getCheckoutErrorMessage(),
                "Error: Last Name is required");
    }

    @Test(priority = 4)
    public void shouldShowErrorWhenPostalCodeIsMissing() {
        log.info("Starting test: shouldShowErrorWhenPostalCodeIsMissing");
        orderFlowPage.addProductsFromConfig();
        orderFlowPage.goToCart();
        orderFlowPage.clickCheckout();

        orderFlowPage.enterUserInfo(
                FetchProperties.get("first_name"),
                FetchProperties.get("last_name"),
                null
        );

        orderFlowPage.clickContinue();
        Assert.assertEquals(orderFlowPage.getCheckoutErrorMessage(),
                "Error: Postal Code is required");
    }

    @Test(priority = 5)
    public void shouldShowErrorWhenAllCheckoutFieldsAreMissing() {
        log.info("Starting test: shouldShowErrorWhenAllCheckoutFieldsAreMissing");
        orderFlowPage.addProductsFromConfig();
        orderFlowPage.goToCart();
        orderFlowPage.clickCheckout();

        orderFlowPage.enterUserInfo(null, null, null);
        orderFlowPage.clickContinue();
        Assert.assertEquals(orderFlowPage.getCheckoutErrorMessage(),
                "Error: First Name is required");
    }
}
