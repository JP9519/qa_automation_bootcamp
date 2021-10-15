package com.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AuthenticationPage {
    private final WebDriver driver;

    @FindBy(id = "email_create")
    private WebElement inputEmail;

    @FindBy(id = "SubmitCreate")
    private WebElement btnSubmitCreate;

    @FindBy(id = "email")
    private WebElement inputEmailAddress;

    @FindBy(id = "passwd")
    private WebElement inputPassword;

    @FindBy(id = "SubmitLogin")
    private WebElement btnLogin;



    public AuthenticationPage(WebDriver driver){
        this.driver= driver;
        PageFactory.initElements(driver,this);
    }

    public void clickOnCreateAccount(String email){
        inputEmail.sendKeys(email);
        btnSubmitCreate.click();
    }

    public void clickOnLogin(String email, String password){
        inputEmailAddress.sendKeys(email);
        inputPassword.sendKeys(password);
        btnLogin.click();
    }
}
