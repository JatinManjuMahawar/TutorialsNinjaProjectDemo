package com.tutorialsninja.pages.pack;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.w3c.dom.ls.LSException;

public class RegisterPage {
    WebDriver driver;
    @FindBy(xpath = "//span[text()='My Account'][@class='hidden-xs hidden-sm hidden-md']")
    private WebElement registerMyAccountDropdown;
    @FindBy(xpath = "//ul//li//a[text()='Register']")
    private WebElement registerRegisterButton;
    @FindBy(xpath = "//*[@name='firstname']")
    private WebElement registerFirstname;
    @FindBy(xpath = "//*[@name='lastname']")
    private WebElement registerLastname;
    @FindBy(xpath = "//*[@name='email']")
    private WebElement registerEmail;
    @FindBy(xpath = "//*[@name='telephone']")
    private WebElement registerTelephone;
    @FindBy(xpath = "//*[@id='input-password']")
    private WebElement registerPassword;
    @FindBy(xpath = "//*[@id='input-confirm']")
    private WebElement registerConfirmPassword;
    @FindBy(xpath = "(//input[@type='radio'])[2]")
    private WebElement registerSubscribeNewsletter;
    @FindBy(xpath = "//input[@type='checkbox'][@name='agree']")
    private WebElement registerPrivacyPolicyCheckbox;
    @FindBy(xpath = "//input[@type='submit'][@class='btn btn-primary']")
    private WebElement registerSubmitRegistrationDetailsButton;



    public RegisterPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void clickOnMyAccountDropdown(){
        registerMyAccountDropdown.click();
    }
    public void clickOnRegisterOption(){
        registerRegisterButton.click();
    }

    public void sendRegistrationDetails(String Email, String Password, String ConfirmPassword,String Firstname, String Lastname, String Telephone, Boolean Subscribe){
        registerFirstname.sendKeys(Firstname);
        registerLastname.sendKeys(Lastname);
        registerEmail.sendKeys(Email);
        registerTelephone.sendKeys(Telephone);
        registerPassword.sendKeys(Password);
        registerConfirmPassword.sendKeys(ConfirmPassword);
        if(Subscribe==true){
            registerSubscribeNewsletter.click();
        }
        registerPrivacyPolicyCheckbox.click();
        registerSubmitRegistrationDetailsButton.click();

    }



}
