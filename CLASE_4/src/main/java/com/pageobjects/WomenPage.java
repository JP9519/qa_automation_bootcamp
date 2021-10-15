package com.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class WomenPage {

    private final WebDriver driver;

    @FindBy(xpath = "//a[@class='button lnk_view btn btn-default']")
    private List<WebElement> listBtnMore;

    @FindBy(xpath ="//a[@class='product_img_link']//img[@class='replace-2x img-responsive']")
    private List<WebElement> listImgs;

    @FindBy(xpath = "//*[@id=\"center_column\"]/ul/li[1]/div/div[1]/div/a[1]/img")
    private WebElement img;

    @FindBy(xpath ="//*[@id='center_column']/ul/li[1]/div/div[2]/div[2]/a[2]")
    private WebElement btnMore;

    public WomenPage (WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public WebElement ExplicitWait(WebDriver driver, WebElement locator,int seconds){
        WebDriverWait wait = new WebDriverWait(driver,seconds);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void selectClothes(int index) {
        Actions builder = new Actions(driver);
        ExplicitWait(driver,listImgs.get(index),5);
        builder.moveToElement(listImgs.get(index)).perform();
        ExplicitWait(driver,listBtnMore.get(index),5);
        listBtnMore.get(index).click();
    }

    public void selectClothes() {
        Actions builder = new Actions(driver);
        ExplicitWait(driver,img,5);
        builder.moveToElement(img).perform();
        ExplicitWait(driver,btnMore,5);
        btnMore.click();
    }

}
