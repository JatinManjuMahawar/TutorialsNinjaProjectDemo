package com.tutorialsninja.pages.pack;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountCreationSuccessPage {
    WebDriver driver=null;
    @FindBy(xpath = "//div//h1[text()='Your Account Has Been Created!']")
    private WebElement registerVerifyRegistration;
    public AccountCreationSuccessPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public Boolean VerifySuccessfulRegistration(){
        Boolean isRegistrationSuccessful = false;
        try{
            isRegistrationSuccessful = registerVerifyRegistration.isDisplayed();}
        catch(Exception ex){}

        return isRegistrationSuccessful;
    }
}
