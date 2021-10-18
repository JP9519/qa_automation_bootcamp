package com.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CheckoutPage {
    private final WebDriver driver;

    @FindBy(xpath = "//*[@class='cart_unit']//span[@class ='price' and contains(@id,'product_price')]")
    private List<WebElement> listUnitPrice;

    @FindBy(xpath = "//*[@class='cart_total']//span[@class ='price' and contains(@id,'total_product_price')]")
    private List<WebElement> listSubTotal;

    @FindBy(xpath = "//*[@class='cart_quantity_input form-control grey']")
    private List<WebElement> listQuantity;

    @FindBy(id = "total_product")
    private WebElement textSubTotal;

    @FindBy(id = "total_shipping")
    private WebElement textShipping;

    @FindBy(id ="total_price_without_tax")
    private WebElement textTotalNoTaxes;

    @FindBy(id ="total_tax")
    private WebElement textTaxes;

    @FindBy(id ="total_price")
    private WebElement textTotal;

    @FindBy(xpath = "//*[@id='center_column'] // *[contains(@class,'button btn btn-default standard-checkout button-medium')]")
    private WebElement btnProceed;

    @FindBy(xpath= "//*[@class='form-control']")
    private WebElement inputComment;

    @FindBy(id = "cgv")
    private WebElement chkTerms;

    @FindBy(className = "bankwire")
    private WebElement btnPayByBankWire;

    @FindBy(xpath = "//*[@id='center_column'] // *[contains(@class,'button btn btn-default button-medium')]")
    private WebElement btnConfirmOrder;

    @FindBy(xpath ="//*[@class='dark' and contains(.,'Your order on My Store is complete.')]")
    private WebElement lblSuccessOrder;

    @FindBy(id="summary_products_quantity")
    private WebElement lblTotalProductsCart;

    public CheckoutPage (WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public boolean UnitProductIsCorrect(){
        System.out.println(listUnitPrice.get(0).getText());
        System.out.println(listUnitPrice.get(1).getText());
        System.out.println(listUnitPrice.get(2).getText());

        boolean statement = false;
        ClothesSpecsPage clothesSpecsPage = new ClothesSpecsPage(driver);
        String[] listCantidad = clothesSpecsPage.getCantidad();
        String[] listPrice= clothesSpecsPage.getPrecio();
        System.out.println(Arrays.toString(listCantidad));
        System.out.println(Arrays.toString(listPrice));

        for (int i = 0; i < listUnitPrice.size(); i++){

            if(listUnitPrice.get(i).getText().substring(1,5).equals(listPrice[i].substring(1))){
                statement = true;
            }
            else{
                statement= false;
            }
        }
        return statement;
    }
    public void CheckoutOrder()  {
        btnProceed.click();
        //Thread.sleep(5000);
        inputComment.sendKeys("hola");
        //Thread.sleep(5000);
        btnConfirmOrder.click();
        chkTerms.click();
        btnProceed.click();
        btnPayByBankWire.click();
        btnConfirmOrder.click();
    }

    public boolean lblSuccessOrderIsDisplayed(){
        return lblSuccessOrder.isDisplayed();
    }

    public boolean lblQuantityProductAddedToCartIsCorrect(){

        int totalQuantity = 0;

        for (int i = 0; i < listQuantity.size(); i++) {
            int quantityProduct = Integer.parseInt(listQuantity.get(i).getAttribute("value"));
            totalQuantity += quantityProduct;
        }

        String msg = String.valueOf(totalQuantity) + " Products";
        System.out.println(msg);
        System.out.println(lblTotalProductsCart.getText());

        return Objects.equals(lblTotalProductsCart.getText(), msg);

    }

    public boolean TotalUnitPriceIsCorrect() {
        int flag = 0;

        for (int i = 0; i < listUnitPrice.size(); i++) {
            float priceProduct = Float.valueOf(listUnitPrice.get(i).getText().substring(1, 6));
            int quantityProduct = Integer.parseInt(listQuantity.get(i).getAttribute("value"));
            float subTotalProduct = Float.valueOf(listSubTotal.get(i).getText().substring(1));
            float subTotalCalculated = priceProduct * quantityProduct;
            //System.out.println("Precio unitario: "+ priceProduct);
            //System.out.println("Cantidad: "+quantityProduct);
           //System.out.println("Subtotal: "+ listSubTotal.get(i).getText());
            //System.out.println("Subtotal: "+subTotalProduct);
            //System.out.println("Subtotal Calculado: "+subTotalCalculated);

            if (subTotalProduct == subTotalCalculated) {
                flag += 1;

            } else {
                flag += 0;
            }
        }
        //System.out.println(flag);
        //System.out.println(listUnitPrice.size());
        return flag == listUnitPrice.size();
    }


    public boolean TotalIsCorrect() {
        boolean flag = false;
        float subTotalCalculated = 0;

        float subTotalPage = Float.valueOf(textSubTotal.getText().substring(1));
        float shippingPage = Float.valueOf(textShipping.getText().substring(1));
        float taxesPage = Float.valueOf(textTaxes.getText().substring(1));
        float totalPage = Float.valueOf(textTotal.getText().substring(1));

        float total = subTotalPage + shippingPage + taxesPage;

        for (int i = 0; i < listUnitPrice.size(); i++) {
            float subTotalProduct = Float.valueOf(listSubTotal.get(i).getText().substring(1));
            subTotalCalculated += subTotalProduct;
        }

        if (subTotalPage == subTotalCalculated){
            if(total == totalPage){
                flag = true;
            }
        }

        return flag;

    }
}
