package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.FetchProperties;

public class BasePage {

    protected WebDriver driver;
    protected String baseUrl;

    // Constructor
    public BasePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);

        // Fetch base URL from properties
        baseUrl = FetchProperties.get("base_url");
    }

    // Optional helper method to navigate
    public void navigateToBaseUrl() {
        driver.get(baseUrl);
    }
}
