package com.tutorialsninja.config.pack;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.*;
import com.aventstack.extentreports.reporter.configuration.*;

import java.io.File;

public class ExtentReport {
    public static ExtentReports generateExtentReports() {
        ExtentReports extentReports = new ExtentReports();
        File extentReport = new File("test-outputs/ExtentReports/extentReport.html");
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReport);

        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setReportName("Jatin");
        sparkReporter.config().setDocumentTitle("LatestExtentReport");
        sparkReporter.config().setTimeStampFormat("dd/MM/yyyy"); // Corrected timestamp format

        extentReports.attachReporter(sparkReporter);

        return extentReports;
    }



}
