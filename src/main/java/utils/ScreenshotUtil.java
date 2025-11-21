package utils;

import baseTest.BaseClass;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil extends BaseClass {


    private static final String SCREENSHOTS_DIR = "src/main/resources/screenshots/";
    private static final String FILE_NAME_PATTERN = "screenshot_yyyyMMdd_HHmmssSSS.png";

    // Take screenshot method
    public static String captureScreenshot(WebDriver driver, String testName) {
        // Take screenshot
        TakesScreenshot ts = (TakesScreenshot) driver;
        File screenshot = ts.getScreenshotAs(OutputType.FILE);

        // Generate file name using the test name and timestamp
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmssSSS").format(new Date());
        String fileName = testName + "_" + timestamp + ".png";

        // Define the target file path
        File targetFile = new File(SCREENSHOTS_DIR + fileName);

        // Copy the screenshot to the target location
        try {
            FileUtils.copyFile(screenshot, targetFile);
            System.out.println("Screenshot saved at: " + targetFile.getAbsolutePath());
            return targetFile.getAbsolutePath();
        } catch (IOException e) {
            System.err.println("Failed to save screenshot: " + e.getMessage());
            return null;
        }
    }

    // Optional: Add custom logic for saving based on failure or success
    public static void captureOnFailure(WebDriver driver, String testName) {
        // Here you can add logic to capture screenshot when a test fails
        captureScreenshot(driver, testName + "_failure");
    }

    public static void captureOnSuccess(WebDriver driver, String testName) {
        // You can also capture screenshots after successful tests if needed
        captureScreenshot(driver, testName + "_success");
    }
}

