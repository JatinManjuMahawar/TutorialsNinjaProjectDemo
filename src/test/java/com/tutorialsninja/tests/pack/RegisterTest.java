package com.tutorialsninja.tests.pack;

import com.tutorialsninja.base.pack.Base;
import com.tutorialsninja.pages.pack.AccountCreationSuccessPage;
import com.tutorialsninja.pages.pack.RegisterPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class RegisterTest {
    public WebDriver driver = null;

    Properties prop = new Properties();
    FileInputStream fis;

    RegisterPage registerObj;
    AccountCreationSuccessPage registerSuccessfulObj;
    @BeforeMethod
    public void loginAndClickLogin() throws Exception {
        driver = Base.initializeBrowserAndProperties("chrome");
        registerObj = new RegisterPage(driver);
        registerSuccessfulObj = new AccountCreationSuccessPage(driver);

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

    public void getRegisterPage(){
        registerObj.clickOnMyAccountDropdown();
        registerObj.clickOnRegisterOption();
    }
    @Test
    public void sendRegisterDetailsAndVerifyRegistration(){
        getRegisterPage();

        registerObj.sendRegistrationDetails(prop.getProperty("Email"),prop.getProperty("Password")
                , prop.getProperty("ConfirmPassword"), prop.getProperty("Firstname"), prop.getProperty("Lastname")
                , prop.getProperty("Telephone"), true);

        Boolean isRegistrationSuccessful = registerSuccessfulObj.VerifySuccessfulRegistration();

        Assert.assertEquals(isRegistrationSuccessful, true);

    }
}
