package com.pageobjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Objects;

public class ClothesSpecsPage {

    private final WebDriver driver;

    @FindBy(id = "short_description_content")
    private WebElement contentDescription;

    @FindBy(id = "our_price_display")
    private WebElement contentPrice;

    @FindBy(id = "quantity_wanted")
    private WebElement inputQuantity;

    @FindBy(id = "group_1")
    private WebElement inputSize;

    @FindBy(xpath = "//ul[@id='thumbs_list_frame']//li[contains(@id,'thumbnail')]")
    private List<WebElement> listImages;

    @FindBy(id = "add_to_cart")
    private WebElement btnAddToCart;


    public ClothesSpecsPage (WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void PrintSpecs(WebElement description, WebElement price){
        System.out.println("Description: " + description.getText());
        System.out.println("Price: "+ price.getText());
    }

    public int getRandomNumber(int min, int max) {
        return (int)Math.floor (Math.random() * (max - min) + min);
    }

    public void AddToCart(){

        CheckoutPage obj = new CheckoutPage(driver);
        List<String> vectorQuantity = obj.quantity;

        int n = getRandomNumber(2,5);

        PrintSpecs(contentDescription,contentPrice);
        vectorQuantity.add(String.valueOf(n));

        if(inputQuantity.getAttribute("value") != null){
            inputQuantity.clear();
            inputQuantity.sendKeys(String.valueOf(n));
            inputQuantity.sendKeys(Keys.ENTER);
        }

        Select select = new Select(inputSize);
        if(select.getFirstSelectedOption().getText()!= null){
            if(Objects.equals(select.getFirstSelectedOption().getText(), "S") && listImages.size() == 4){
                inputSize.sendKeys("L");
                inputSize.sendKeys(Keys.ENTER);
            }
        }

        btnAddToCart.click();

        System.out.println(vectorQuantity);

    }
    public boolean imagesQuantityisCorrect(){
        Select select = new Select(inputSize);
        if(Objects.equals(select.getFirstSelectedOption().getText(), "S")){
            return listImages.size() == 4;
        }
        else {
            return false;
        }
    }


}
