package com.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private final WebDriver driver;

    @FindBy(className = "login")
    private WebElement btnLogin;

    @FindBy(xpath = "//div[@id='block_top_menu']//a[contains(.,'Women')]")
    private WebElement btnWoman;

    @FindBy(xpath = "//*[@class='logout']")
    private WebElement btnLogout;

    @FindBy(xpath = "//a[@title='View my shopping cart']")
    private WebElement btnCart;

    @FindBy(id="button_order_cart")
    private WebElement btnCheckout;



    public final String url = "http://automationpractice.com";

    public MainPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public WebElement ExplicitWait(WebDriver driver, WebElement locator,int seconds){
        WebDriverWait wait = new WebDriverWait(driver,seconds);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void clickOnSignIn(){
        btnLogin.click();
    }

    public String getUrl(){
        return url;
    }

    public void clickOnWomenTab() {
        btnWoman.click();
    }

    public void logout(){
        btnLogout.click();

    }

    public void ClickOnCheckout(){

        ExplicitWait(driver,btnCart,5);
        Actions builder = new Actions(driver);
        builder.moveToElement(btnCart).perform();
        ExplicitWait(driver,btnCheckout,5);
        btnCheckout.click();


    }

}
