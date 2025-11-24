package tests;

import baseTest.BaseClass;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.InventorySortPage;
import pageObjects.LoginPage;
import utils.FetchProperties;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InventorySortTest extends BaseClass {
    LoginPage lp;
    InventorySortPage inventory;

    @BeforeClass
    public void setUp() {
        driver.get(FetchProperties.get("base_url"));
        lp = new LoginPage(driver);
        lp.validLogin();
        inventory = new InventorySortPage(driver);
        Assert.assertTrue(inventory.isPageLoaded(), "Inventory page did not load properly.");
    }

    @Test(priority = 1)
    public void testSortAtoZ() {
        inventory.selectSortOption(FetchProperties.get("AtoZ"));
        List<String> actualNames = inventory.getAllProductNames();
        List<String> expectedNames = new ArrayList<>(actualNames);
        Collections.sort(expectedNames);
        Assert.assertEquals(actualNames, expectedNames, "Products are not sorted A→Z");
    }

    @Test(priority = 2)
    public void testSortZtoA() {
        inventory.selectSortOption(FetchProperties.get("ZtoA"));
        List<String> actualNames = inventory.getAllProductNames();
        List<String> expectedNames = new ArrayList<>(actualNames);
        Collections.sort(expectedNames, Collections.reverseOrder());
        Assert.assertEquals(actualNames, expectedNames, "Products are not sorted Z→A");
    }

    @Test(priority = 3)
    public void testSortPriceLowToHigh() {
        inventory.selectSortOption(FetchProperties.get("low_to_high"));
        List<Double> actualPrices = inventory.getAllProductPrices();
        List<Double> expectedPrices = new ArrayList<>(actualPrices);
        Collections.sort(expectedPrices);
        Assert.assertEquals(actualPrices, expectedPrices, "Prices are not sorted Low→High");
    }

    @Test(priority = 4)
    public void testSortPriceHighToLow() {
        inventory.selectSortOption(FetchProperties.get("high_to_low"));
        List<Double> actualPrices = inventory.getAllProductPrices();
        List<Double> expectedPrices = new ArrayList<>(actualPrices);
        Collections.sort(expectedPrices, Collections.reverseOrder());
        Assert.assertEquals(actualPrices, expectedPrices, "Prices are not sorted High→Low");
    }
}

