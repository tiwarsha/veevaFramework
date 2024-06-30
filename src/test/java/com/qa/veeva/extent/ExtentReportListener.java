package com.qa.veeva.extent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.qa.veeva.factory.DriverFactory;

public class ExtentReportListener implements ITestListener {

    private static final String OUTPUT_FOLDER = "./target/";
    private static final String FILE_NAME = "TestExecutionReport.html";

    private static ExtentReports extent = init();
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    private static ExtentReports init() {
        Path path = Paths.get(OUTPUT_FOLDER);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ExtentReports extentReports = new ExtentReports();
        ExtentSparkReporter reporter = new ExtentSparkReporter(OUTPUT_FOLDER + FILE_NAME);
        reporter.config().setReportName("veeva automation reports");
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("System", "windows");
        extentReports.setSystemInfo("Author", "Shailesh Tiwari");
        extentReports.setSystemInfo("Build#", "1.1");
        extentReports.setSystemInfo("Team", "veeva QA Team");
        extentReports.setSystemInfo("Customer Name", "veeva");

        return extentReports;
    }

    @Override
    public synchronized void onStart(ITestContext context) {
        System.out.println("Test Suite started!");
    }

    @Override
    public synchronized void onFinish(ITestContext context) {
        System.out.println(("Test Suite is ending!"));
        extent.flush();
        test.remove();
    }

    @Override
    public synchronized void onTestStart(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        String qualifiedName = result.getMethod().getQualifiedName();
        int last = qualifiedName.lastIndexOf(".");
        int mid = qualifiedName.substring(0, last).lastIndexOf(".");
        String className = qualifiedName.substring(mid + 1, last);

        System.out.println(methodName + " started!");
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),
                result.getMethod().getDescription());

        extentTest.assignCategory(result.getTestContext().getSuite().getName());
        extentTest.assignCategory(className);
        test.set(extentTest);
        test.get().getModel().setStartTime(getTime(result.getStartMillis()));

        // Adding custom log message to Extent Report
        addLog(Status.INFO, "Starting execution of test method: " + methodName);
    }

    @Override
    public synchronized void onTestSuccess(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " passed!"));
        test.get().pass("Test passed");
        test.get().getModel().setEndTime(getTime(result.getEndMillis()));

        // Adding custom log message to Extent Report
        addLog(Status.INFO, "Test method passed successfully: " + result.getMethod().getMethodName());
    }

    @Override
    public synchronized void onTestFailure(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " failed!"));
        String methodName = result.getMethod().getMethodName();

        test.get().fail(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(DriverFactory.getScreenshot()).build());
        test.get().getModel().setEndTime(getTime(result.getEndMillis()));

        // Adding custom log message to Extent Report
        addLog(Status.INFO, "Test method failed: " + methodName);
    }

    @Override
    public synchronized void onTestSkipped(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " skipped!"));
        String methodName = result.getMethod().getMethodName();
        test.get().skip(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(DriverFactory.getScreenshot()).build());
        test.get().getModel().setEndTime(getTime(result.getEndMillis()));

        // Adding custom log message to Extent Report
        addLog(Status.INFO, "Test method skipped: " + methodName);
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }

    // Method to add custom log message
    public static void addLog(Status status, String message) {
        try {
            test.get().log(status, message);
        } catch (Exception e) {
            System.err.println("Error adding log to Extent Report: " + e.getMessage());
        }
    }

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}
}
