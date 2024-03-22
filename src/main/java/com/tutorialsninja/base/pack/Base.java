package com.tutorialsninja.base.pack;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.io.*;
import java.time.*;
import java.util.*;

public class Base {
    static WebDriver driver = null;
    static Properties prop = new Properties();
    static FileInputStream fis;
    public static Properties getProperties() throws Exception {
        try {
            File file = new File("src/main/java/com/tutorialsninja/config/pack/config.properties");
            fis = new FileInputStream(file);

            prop = new Properties();
            prop.load(fis);
        } catch (Exception t) {
            throw t;
        }
        return prop;
    }
    public static WebDriver initializeBrowserAndProperties(String browserName) throws Exception {


        if (browserName.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(0));
        prop = getProperties();
        System.out.println("BaseURL: " + prop.getProperty("BaseURL"));

        driver.get(prop.getProperty("BaseURL"));
        return driver;
    }

    public static Object[][] readTestDataExcel(String sheetname) {

        XSSFWorkbook workbook;
        XSSFSheet sheet;
        FileInputStream fis;
        int rows=0;
        int cols=0;
        Object[][] testData = new Object[rows][cols];

        try {
            File file = new File("src/main/java/com/tutorialsninja/config/pack/TutorialsNinjaTestData.xlsx");
            fis = new FileInputStream(file);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet(sheetname);
            rows = sheet.getLastRowNum();
            cols = sheet.getRow(0).getLastCellNum();
            testData = new Object[rows][cols];

            for (int i = 0; i < rows; i++) {
                XSSFRow row = sheet.getRow(i+1);
                for (int j = 0; j < cols; j++) {
                    XSSFCell cell = row.getCell(j);
                    CellType cellType = cell.getCellType();
                    switch (cellType) {
                        case STRING:
                            testData[i][j] = cell.getStringCellValue();
                            break;
                        case NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                testData[i][j] = cell.getDateCellValue();
                                break;
                            } else {
                                testData[i][j] = Double.toString(cell.getNumericCellValue());
                                break;
                            }
                    }
                }

            }
        } catch (Exception ex) {

        }


        return testData;
}






}
