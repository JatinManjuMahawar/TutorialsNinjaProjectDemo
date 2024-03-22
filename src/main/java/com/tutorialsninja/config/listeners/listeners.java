package com.tutorialsninja.config.listeners;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.tutorialsninja.base.pack.Utilities;
import com.tutorialsninja.config.pack.ExtentReport;
import freemarker.template.utility.DateUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.testng.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class listeners implements ITestListener {

    ExtentReports extentReport;
    ExtentTest extentTest;
    @Override
    public void onStart(ITestContext context) {
        System.out.println("Execution Started");
        extentReport = ExtentReport.generateExtentReports();
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getName(); // .getName() returns name of the test
        extentTest = extentReport.createTest(testName);
        extentTest.log(Status.INFO,testName + " Started Executing");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getName(); // .getName() returns name of the test
        extentTest.log(Status.PASS, testName +  " got passed");
        System.out.println("Test : " + testName +  " got passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getName(); // .getName() returns name of the test

        WebDriver driver = null;
        try {
            driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        String screenshotPath = Utilities.captureScreenshot(driver, result.getTestName());
        extentTest.addScreenCaptureFromPath(screenshotPath);
        extentTest.log(Status.INFO, result.getThrowable());
        extentTest.log(Status.FAIL, testName +  " got failed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = result.getName(); // .getName() returns name of the test
        extentTest.log(Status.SKIP, testName +  " got skipped");
        System.out.println(result.getThrowable());
    }


    @Override
    public void onFinish(ITestContext context) {
        extentReport.flush();

        File file = new File(System.getProperty("user.dir")+"\\test-outputs\\ExtentReports\\extentReport.html");
        try {
            Desktop.getDesktop().browse(file.toURI());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
