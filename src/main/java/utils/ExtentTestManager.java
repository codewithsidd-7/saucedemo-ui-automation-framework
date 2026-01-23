package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static final ExtentReports extent = ExtentManager.getInstance();

    public static ExtentTest getTest() {
        return test.get();
    }

    public static void startTest(String testName) {
        ExtentTest extentTest = extent.createTest(testName);
        test.set(extentTest);
    }

    public static void endTest() {
        extent.flush();
        test.remove();
    }
}
