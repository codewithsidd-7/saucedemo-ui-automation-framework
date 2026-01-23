package pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.ExplicitWaits;
import utils.FetchProperties;
import org.testng.Assert;

public class LoginPage extends BasePage {

    private static final Logger log = LogManager.getLogger(LoginPage.class);
    private ExplicitWaits explicitWaits;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.explicitWaits = new ExplicitWaits(driver);
    }

    @FindBy(xpath = "//*[@id='user-name']")
    private WebElement fld_user_name;
    @FindBy(xpath = "//*[@id='password']")
    private WebElement fld_password;
    @FindBy(xpath = "//*[@id='login-button']")
    private WebElement btn_login;
    @FindBy(xpath = "//*[@id='login_button_container']/div/form/div[3]/h3")
    private WebElement error_msg;

    private void clearFields() {
        explicitWaits.waitForElementToBeVisible(fld_user_name);
        fld_user_name.clear();
        fld_password.clear();
    }

    public void loginWithoutPassword() {
        clearFields();
        fld_user_name.sendKeys(FetchProperties.get("valid_username"));
        btn_login.click();
        explicitWaits.waitForElementToBeVisible(error_msg);
        log.info("Login without password error: {}", error_msg.getText());
        Assert.assertTrue(error_msg.getText().contains("Password is required"));
    }

    public void loginWithoutUsername() {
        clearFields();
        fld_password.sendKeys(FetchProperties.get("valid_password"));
        btn_login.click();
        explicitWaits.waitForElementToBeVisible(error_msg);
        log.info("Login without username error: {}", error_msg.getText());
        Assert.assertTrue(error_msg.getText().contains("Username is required"));
    }

    public void loginWithInvalidCredentials() {
        clearFields();
        fld_user_name.sendKeys(FetchProperties.get("invalid_username"));
        fld_password.sendKeys(FetchProperties.get("invalid_password"));
        btn_login.click();
        explicitWaits.waitForElementToBeVisible(error_msg);
        log.info("Login with invalid credentials error: {}", error_msg.getText());
        Assert.assertTrue(error_msg.getText().contains("do not match any user"));
    }

    public void validLogin() {
        clearFields();
        fld_user_name.sendKeys(FetchProperties.get("valid_username"));
        fld_password.sendKeys(FetchProperties.get("valid_password"));
        btn_login.click();
        log.info("Valid login performed for user {}", FetchProperties.get("valid_username"));
    }
}
