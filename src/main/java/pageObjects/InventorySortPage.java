package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.internal.annotations.IListeners;
import utils.ExplicitWaits;

import java.util.ArrayList;
import java.util.List;

public class InventorySortPage extends BasePage {
    private ExplicitWaits explicitWaits;

    //Constructor
    public InventorySortPage(WebDriver driver) {
        super(driver);
        this.explicitWaits = new ExplicitWaits(driver);
    }

    //Locators
    @FindBy(xpath = "//select[@class='product_sort_container']")
    private WebElement drp_inventory;
    @FindBy(xpath = "//div[@class='inventory_item']")
    private List<WebElement> list_products;
    @FindBy(xpath = "//div[@class='inventory_item_price']")
    private List<WebElement> list_prices;


    //Action Methods
    public void selectSortOption(String value) {
        explicitWaits.waitForElementToBeClickable(drp_inventory);
        Select sortDropdown = new Select(drp_inventory);
        sortDropdown.selectByValue(value);
    }

    public List<String> getAllProductNames() {
        explicitWaits.waitForElementToBeVisible(list_products.get(0));
        List<String> names = new ArrayList<>();
        for (WebElement element : list_products) {
            names.add(element.getText().trim());
        }
        return names;
    }

    public List<Double> getAllProductPrices() {
        explicitWaits.waitForElementToBeVisible(list_prices.get(0));

        List<Double> prices = new ArrayList<>();
        for (WebElement element : list_prices) {
            String text = element.getText().replace("$", "").trim();
            prices.add(Double.parseDouble(text));
        }
        return prices;
    }

    public boolean isPageLoaded() {
        try {
            explicitWaits.waitForElementToBeVisible(list_products.get(0));
            return list_products.get(0).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
