package baseTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utils.FetchProperties;

public class BaseClass {

    protected WebDriver driver;

    public void initializeDriver() {
        ChromeOptions options = new ChromeOptions();

        boolean isHeadless =
                Boolean.parseBoolean(FetchProperties.get("headless_mode"));

        if (isHeadless) {
            options.addArguments("--headless=new");
        }

        options.addArguments(
                "--window-size=1920,1080",
                "--disable-gpu",
                "--disable-extensions",
                "--disable-notifications",
                "--ignore-certificate-errors"
        );

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    protected void tearDown() {
        boolean closeBrowser =
                Boolean.parseBoolean(FetchProperties.get("close_browser"));

        if (closeBrowser && driver != null) {
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}
