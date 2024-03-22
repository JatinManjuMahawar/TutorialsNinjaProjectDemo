package com.tutorialsninja.tests.pack;

import com.tutorialsninja.pages.pack.LoginPage;
import com.tutorialsninja.pages.pack.LoginSuccessPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import com.tutorialsninja.base.pack.Base;

import java.io.*;
import java.util.Properties;

public class LoginTest {
    public WebDriver driver = null;
//Jatin
    Properties prop = new Properties();
    FileInputStream fis;

    LoginPage loginObj;
    LoginSuccessPage lcpObj;
    @BeforeMethod
    public void loginAndClickLogin() throws Exception {
        driver = Base.initializeBrowserAndProperties("chrome");
        loginObj = new LoginPage(driver);
        lcpObj = new LoginSuccessPage(driver);

        try {
            File file = new File("src/main/java/com/tutorialsninja/config/pack/Property.properties");
            fis = new FileInputStream(file);

            prop = new Properties();
            prop.load(fis);
        } catch (Exception t) {
            throw t;
        }
    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
    @Test(dataProvider = "getLoginTestData", priority = 4)
    public void loginWithValidCredentials(String Email, String Password){
        loginObj.clickOnMyAccountDropdown();
        loginObj.cliclOnLoginOption();
        loginObj.sendLoginDetailsAndSubmit(Email, Password);
        Boolean isLoginSuccessful = lcpObj.VerifySuccessfulLogin();

        Assert.assertEquals(isLoginSuccessful, true);
    }
    @Test(dataProvider = "getInvalidLoginTestData", priority = 3,dependsOnMethods = "loginWithNoCredentials")
    public void loginWithInvalidCredentials(String Email, String Password){
        loginObj.clickOnMyAccountDropdown();
        loginObj.cliclOnLoginOption();
        loginObj.sendLoginDetailsAndSubmit(Email, Password);
        Boolean isLoginSuccessful = lcpObj.VerifySuccessfulLogin();
        Assert.assertEquals(isLoginSuccessful, false);
    }
    @Test(priority = 2)
    public void loginWithInvalidPassword(){
        loginObj.clickOnMyAccountDropdown();
        loginObj.cliclOnLoginOption();
        loginObj.sendLoginDetailsAndSubmit(prop.getProperty("ValidEmail"), prop.getProperty("InvalidPassword"));
        Boolean isLoginSuccessful = lcpObj.VerifySuccessfulLogin();
        Assert.assertEquals(isLoginSuccessful, false);
    }
    @Test(priority = 1)
    public void loginWithInvalidEmail(){
        loginObj.clickOnMyAccountDropdown();
        loginObj.cliclOnLoginOption();
        loginObj.sendLoginDetailsAndSubmit(prop.getProperty("InvalidEmail"), prop.getProperty("ValidPassword"));
        Boolean isLoginSuccessful = lcpObj.VerifySuccessfulLogin();
        Assert.assertEquals(isLoginSuccessful, false);
    }
    @Test(priority = 0)
    public void loginWithNoCredentials(){
        loginObj.clickOnMyAccountDropdown();
        loginObj.cliclOnLoginOption();
        loginObj.sendLoginDetailsAndSubmit("", "");
        Boolean isLoginSuccessful = lcpObj.VerifySuccessfulLogin();
        Assert.assertEquals(isLoginSuccessful, true);
    }
    @DataProvider
    public Object[][] getLoginTestData(){

        Object[][] testData = Base.readTestDataExcel("ValidCredentials");

        return testData;
    }
    @DataProvider
    public Object[][] getInvalidLoginTestData(){

        Object[][] testData = Base.readTestDataExcel("InValidCredentials");

        return testData;
    }

}
