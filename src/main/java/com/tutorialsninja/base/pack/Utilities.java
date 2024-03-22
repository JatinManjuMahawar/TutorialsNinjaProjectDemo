package com.tutorialsninja.base.pack;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utilities {
    public static String captureScreenshot(WebDriver driver, String testName){

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH-mm-ss.SSSSSSSSS");
        String formattedDateTime = currentDateTime.format(formatter);

        String screenshotPath = System.getProperty("user.dir")+"\\test-outputs\\Screenshots\\"+formattedDateTime+testName+".PNG";

        File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileHandler.copy(screenshotFile, new File(screenshotPath));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

        return screenshotPath;
    }
}
