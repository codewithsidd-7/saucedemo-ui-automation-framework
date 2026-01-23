package baseTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utils.FetchProperties;

public class BaseClass {
    protected WebDriver driver;

    public void initializeDriver() {
        ChromeOptions options = new ChromeOptions();

        // Read flag from config.properties
        boolean isHeadless = Boolean.parseBoolean(FetchProperties.get("headless_mode"));
        if (isHeadless) {
            options.addArguments("--headless=new");
        }

        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-notifications");
        options.addArguments("--ignore-certificate-errors");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
