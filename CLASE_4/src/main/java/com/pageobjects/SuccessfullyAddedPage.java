package com.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SuccessfullyAddedPage {

    private final WebDriver driver;

    @FindBy(xpath = "//i[contains(@class,'icon-ok')]")
    private WebElement icnSuccessAdded;

    @FindBy(xpath = "//*[@class='continue btn btn-default button exclusive-medium']")
    private WebElement btnContinueShopping;

    public SuccessfullyAddedPage (WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public boolean icnSuccessAddedIsDisplayed(){
        return icnSuccessAdded.isDisplayed();
    }

    public void ClickOnContinueShopping(){
        WebDriverWait wait = new WebDriverWait(driver,5);
        wait.until(ExpectedConditions.elementToBeClickable(btnContinueShopping));
        btnContinueShopping.click();
    }
}


