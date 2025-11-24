package tests;

import baseTest.BaseClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import utils.FetchProperties;

public class LoginTest extends BaseClass {
    LoginPage lp;

    @BeforeMethod
    public void setUpPage() {
        driver.get(FetchProperties.get("base_url"));
        lp = new LoginPage(driver);
    }

    @Test(priority = 1)
    public void loginWithoutUsername(){
        lp.loginWithoutUsername();
    }

    @Test(priority = 2)
    public void loginWithoutPassword() {
        lp.loginWithoutPassword();
    }

    @Test(priority = 3)
    public void loginWithInvalidCredentials(){
        lp.loginWithInvalidCredentials();
    }

    @Test(priority = 4)
    public void validLogin() {
        lp.validLogin();
    }
}
