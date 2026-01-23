package tests;

import baseTest.BaseClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import utils.FetchProperties;

public class LoginTest extends BaseClass {

    private static final Logger log = LogManager.getLogger(LoginTest.class);
    private LoginPage lp;

    @BeforeMethod
    public void setUpPage() {
        driver.get(FetchProperties.get("base_url"));
        lp = new LoginPage(driver);
    }

    @Test(priority = 1)
    public void loginWithoutUsername() {
        log.info("Starting test: loginWithoutUsername");
        lp.loginWithoutUsername();
    }

    @Test(priority = 2)
    public void loginWithoutPassword() {
        log.info("Starting test: loginWithoutPassword");
        lp.loginWithoutPassword();
    }

    @Test(priority = 3)
    public void loginWithInvalidCredentials() {
        log.info("Starting test: loginWithInvalidCredentials");
        lp.loginWithInvalidCredentials();
    }

    @Test(priority = 4)
    public void validLogin() {
        log.info("Starting test: validLogin");
        lp.validLogin();
    }
}
