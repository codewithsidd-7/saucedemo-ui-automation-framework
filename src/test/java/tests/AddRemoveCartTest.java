package tests;

import baseTest.BaseClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.AddRemoveCartPage;
import pageObjects.LoginPage;
import utils.FetchProperties;

public class AddRemoveCartTest extends BaseClass {

    private static final Logger log = LogManager.getLogger(AddRemoveCartTest.class);
    private LoginPage loginPage;
    private AddRemoveCartPage cartPage;

    @BeforeClass
    public void setUp() {
        initializeDriver();
        driver.get(FetchProperties.get("base_url"));
        loginPage = new LoginPage(driver);
        loginPage.validLogin();
        cartPage = new AddRemoveCartPage(driver);
        log.info("Setup completed and logged in successfully");
    }

    @Test(priority = 1)
    public void testAddSingleRandomProductToCart() {
        cartPage.addRandomProductToCart();
        int count = cartPage.getCartItemCount();
        log.info("Verifying cart count after adding one product: {}", count);
        Assert.assertEquals(count, 1, "Cart count did not update after adding a single product");
    }

    @Test(priority = 2)
    public void testRemoveSingleRandomProductFromCart() {
        cartPage.removeRandomProductFromCart();
        int count = cartPage.getCartItemCount();
        log.info("Verifying cart count after removing one product: {}", count);
        Assert.assertEquals(count, 0, "Cart count did not update after removing a single product");
    }

    @Test(priority = 3)
    public void testAddMultipleRandomProductsToCart() {
        int addCount = Integer.parseInt(FetchProperties.get("add_multiple_products_to_cart"));
        cartPage.addMultipleProducts(addCount);
        int count = cartPage.getCartItemCount();
        log.info("Verifying cart count after adding {} products: {}", addCount, count);
        Assert.assertEquals(count, addCount, "Cart count did not match after adding multiple products");
    }

    @Test(priority = 4)
    public void testRemoveMultipleRandomProductsFromCart() {
        int removeCount = Integer.parseInt(FetchProperties.get("remove_multiple_products_from_cart"));
        int currentCount = cartPage.getCartItemCount();

        cartPage.removeMultipleProducts(removeCount);
        int expectedCount = Math.max(0, currentCount - removeCount);
        int count = cartPage.getCartItemCount();

        log.info("Verifying cart count after removing {} products: {}", removeCount, count);
        Assert.assertEquals(count, expectedCount, "Cart count did not match after removing multiple products");
    }
}
