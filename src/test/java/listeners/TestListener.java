package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.Status;
import utils.ExtentTestManager;
import utils.ScreenshotUtil;
import baseTest.BaseClass;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        System.out.println("[TEST STARTED] " + testName);
        ExtentTestManager.startTest(testName);
        ExtentTestManager.getTest().log(Status.INFO, "Test started: " + testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        System.out.println("[TEST PASSED] " + testName);
        ExtentTestManager.getTest().log(Status.PASS, "Test passed: " + testName);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        System.out.println("[TEST FAILED] " + testName);
        System.out.println("Reason: " + result.getThrowable());

        ExtentTestManager.getTest().log(Status.FAIL, "Test failed: " + testName);
        ExtentTestManager.getTest().log(Status.FAIL, result.getThrowable());

        // Capture screenshot
        BaseClass baseTestInstance = (BaseClass) result.getInstance();
        WebDriver driver = baseTestInstance.getDriver();

        String screenshotPath = ScreenshotUtil.captureScreenshot(driver, testName);
        if (screenshotPath != null) {
            ExtentTestManager.getTest().addScreenCaptureFromPath(screenshotPath);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        System.out.println("[TEST SKIPPED] " + testName);
        ExtentTestManager.getTest().log(Status.SKIP, "Test skipped: " + testName);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        System.out.println("[TEST FAILED BUT WITHIN SUCCESS %] " + testName);
        ExtentTestManager.getTest().log(Status.WARNING, "Test failed but within success percentage: " + testName);
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("=== Suite Started: " + context.getName() + " ===");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("=== Suite Finished: " + context.getName() + " ===");
        ExtentTestManager.endTest();
    }
}
