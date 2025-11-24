package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import utils.ExplicitWaits;
import utils.FetchProperties;

import static utils.FetchProperties.properties;

public class LoginPage extends BasePage {

    private ExplicitWaits explicitWaits;
    //Constructor
    public LoginPage(WebDriver driver) {
        super(driver);
        this.explicitWaits=new ExplicitWaits(driver);
    }

    //Locators
    @FindBy(xpath = "//*[@id='user-name']")
    private WebElement fld_user_name;
    @FindBy(xpath = "//*[@id='password']")
    private WebElement fld_password;
    @FindBy(xpath = "//*[@id='login-button']")
    private WebElement btn_login;
    @FindBy (xpath = "//*[@id='login_button_container']/div/form/div[3]/h3[contains(normalize-space(), 'Epic sadface: Password is required')]")
    private WebElement error_msg_password;
    @FindBy(xpath = "//*[@id='login_button_container']/div/form/div[3]/h3[contains(normalize-space(), 'Epic sadface: Username is required')]")
    private WebElement error_msg_username;
    @FindBy(xpath = "//*[@id='login_button_container']/div/form/div[3]/h3[contains(normalize-space(), 'Epic sadface: Username and password do not match any user in this service')]")
    private WebElement error_msg_invalid_credentials;


    //Action Methods
    public void clearFields() {
        explicitWaits.waitForElementToBeVisible(fld_user_name);
        fld_user_name.clear();

        explicitWaits.waitForElementToBeVisible(fld_password);
        fld_password.clear();
    }



    public void loginWithoutPassword() {
        clearFields();
        fld_user_name.click();
        fld_user_name.sendKeys(FetchProperties.get("valid_username"));
        btn_login.click();
        explicitWaits.waitForElementToBeVisible(error_msg_password);
        String actualError = error_msg_password.getText();
        Assert.assertTrue(actualError.contains("Epic sadface: Password is required"));

    }

    public void validLogin(){
        clearFields();
        fld_user_name.click();
        fld_user_name.sendKeys(FetchProperties.get("valid_username"));
        fld_password.click();
        fld_password.sendKeys(FetchProperties.get("valid_password"));
        btn_login.click();
    }

    public void loginWithoutUsername(){
        clearFields();
        fld_password.click();
        fld_password.sendKeys(FetchProperties.get("valid_password"));
        btn_login.click();
        explicitWaits.waitForElementToBeVisible(error_msg_username);
        String actualError = error_msg_username.getText();
        Assert.assertTrue(actualError.contains("Epic sadface: Username is required"));

    }

    public void loginWithInvalidCredentials(){
        clearFields();
        fld_user_name.click();
        fld_user_name.sendKeys(FetchProperties.get("invalid_username"));
        fld_password.click();
        fld_password.sendKeys(FetchProperties.get("invalid_password"));
        btn_login.click();
        explicitWaits.waitForElementToBeVisible(error_msg_invalid_credentials);
        String actualError = error_msg_invalid_credentials.getText();
        Assert.assertTrue(actualError.contains("Epic sadface: Username and password do not match any user in this service"));

    }


}

