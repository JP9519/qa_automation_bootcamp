package com.autopractice.accountests;

import com.autopractice.Base;
import com.dataprovider.AuthenticationDataProvider;
import com.dataprovider.SignUpDataProvider;
import com.pageobjects.*;
import org.testng.Assert;
import org.testng.annotations.*;

public class OrderClothes extends Base {

    private MainPage mainpage;
    private AuthenticationPage authenticationPage;
    private MyAccountPage myAccountPage;
    private WomenPage womenPage;
    private ClothesSpecsPage clothesSpecsPage;
    private SuccessfullyAddedPage successfullyAddedPage;
    private CheckoutPage checkoutPage;

    @BeforeTest(alwaysRun = true)
    public void initialiaze(){
        driver= initializeDriver();

        mainpage = new MainPage(driver);
        authenticationPage = new AuthenticationPage(driver);
        myAccountPage = new MyAccountPage(driver);
        womenPage = new WomenPage(driver);
        clothesSpecsPage = new ClothesSpecsPage(driver);
        successfullyAddedPage = new SuccessfullyAddedPage(driver);
        checkoutPage = new CheckoutPage(driver);

    }

    @BeforeMethod
    public void beforeMethod(){
        //driver.get(mainpage.getUrl());

    }

    @Test
    public void testLogin(){
        driver.get(mainpage.getUrl());
        mainpage.clickOnSignIn();
        authenticationPage.clickOnLogin(AuthenticationDataProvider.USER_EMAIL_ADDRESS,AuthenticationDataProvider.USER_PASS);
        Assert.assertTrue(myAccountPage.lblWelcomeIsDisplayed());
    }

    @Test(dataProvider = "adding product",dataProviderClass = SignUpDataProvider.class)
    public void testProducts(String index,int i) {
        mainpage.clickOnWomenTab();
        womenPage.selectClothes(Integer.parseInt(index));
        Assert.assertTrue(clothesSpecsPage.imagesQuantityisCorrect());
        clothesSpecsPage.AddToCart(i);
        successfullyAddedPage.ClickOnContinueShopping();
        Assert.assertTrue(successfullyAddedPage.icnSuccessAddedIsDisplayed());


    }
    @Test
    public void testProductsCheckout() {
        mainpage.ClickOnCheckout();
        Assert.assertTrue(checkoutPage.TotalUnitPriceIsCorrect());
        //Assert.assertTrue(checkoutPage.TotalIsCorrect());
        checkoutPage.CheckoutOrder();
        Assert.assertTrue(checkoutPage.lblSuccessOrderIsDisplayed());
    }


    @AfterMethod
    public void AfterMethod(){
    }

    @AfterTest
    public void closeDriver(){
        mainpage.logout();
        driver.quit();
    }
}
