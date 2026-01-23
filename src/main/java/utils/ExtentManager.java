package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    private static ExtentReports extent;

    public static synchronized ExtentReports getInstance() {
        if (extent == null) {
            String fileName = "test-output/ExtentReport_" + System.currentTimeMillis() + ".html";
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(fileName);

            sparkReporter.config().setTheme(Theme.DARK);
            sparkReporter.config().setDocumentTitle("Automation Report");
            sparkReporter.config().setReportName("Regression Suite Results");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            // System info
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Tester", "Sidd");
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
            extent.setSystemInfo("Environment", "QA");
        }
        return extent;
    }
}
