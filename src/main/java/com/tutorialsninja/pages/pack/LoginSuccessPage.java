package com.tutorialsninja.pages.pack;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginSuccessPage {
    WebDriver driver=null;
    @FindBy(xpath = "//*[text()='Edit your account information']")
    private WebElement loginVerifySuccessfulLogin;
    public LoginSuccessPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public Boolean VerifySuccessfulLogin(){
        Boolean isLoginSuccessful = false;
        try{
            isLoginSuccessful = loginVerifySuccessfulLogin.isDisplayed();}
        catch(Exception ex){}

        return isLoginSuccessful;
    }

}
