package tests;

import baseTest.BaseClass;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.AddRemoveCartPage;
import pageObjects.LoginPage;
import utils.FetchProperties;

public class AddRemoveCartTest extends BaseClass {

    private LoginPage lp;
    private AddRemoveCartPage cartPage;

    @BeforeClass
    public void setUp() {
        driver.get(FetchProperties.get("base_url"));
        lp = new LoginPage(driver);
        lp.validLogin();
        cartPage = new AddRemoveCartPage(driver);
    }

    @Test(priority = 1)
    public void testAddSingleRandomProductToCart() {
        cartPage.addRandomProductToCart();
        Assert.assertEquals(
                cartPage.getCartItemCount(),
                1,
                "Cart count did not update after adding a single product"
        );
    }

    @Test(priority = 2)
    public void testRemoveSingleRandomProductFromCart() {
        cartPage.removeRandomProductFromCart();
        Assert.assertEquals(
                cartPage.getCartItemCount(),
                0,
                "Cart count did not update after removing a single product"
        );
    }

    @Test(priority = 3)
    public void testAddMultipleRandomProductsToCart() {
        int addCount = Integer.parseInt(FetchProperties.get("add_multiple_products_to_cart"));
        cartPage.addMultipleProducts(addCount);
        Assert.assertEquals(
                cartPage.getCartItemCount(),
                addCount,
                "Cart count did not match after adding multiple products"
        );
    }

    @Test(priority = 4)
    public void testRemoveMultipleRandomProductsFromCart() {
        int removeCount = Integer.parseInt(FetchProperties.get("remove_multiple_products_from_cart"));
        int currentCount = cartPage.getCartItemCount();

        cartPage.removeMultipleProducts(removeCount);

        int expectedCount = currentCount - removeCount;
        if (expectedCount < 0) expectedCount = 0;
        Assert.assertEquals(
                cartPage.getCartItemCount(),
                expectedCount,
                "Cart count did not match after removing multiple products"
        );
    }
}
