package com.autopractice.accountests;

import com.autopractice.Base;
import com.dataprovider.AuthenticationDataProvider;
import com.dataprovider.SignUpDataProvider;
import com.pageobjects.*;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SignUp extends Base {
    private MainPage mainpage;
    private SignUpFormPage signUpFormPage;
    private AuthenticationPage authenticationPage;
    private MyAccountPage myAccountPage;

    @BeforeTest
    public void initialiaze(){
        driver= initializeDriver();
        mainpage = new MainPage(driver);
        authenticationPage = new AuthenticationPage(driver);
        signUpFormPage = new SignUpFormPage(driver);
        myAccountPage = new MyAccountPage(driver);

    }

    @BeforeMethod
    public void beforeMethod(){
        driver.get(mainpage.getUrl());
        mainpage.clickOnSignIn();
        authenticationPage.clickOnCreateAccount(AuthenticationDataProvider.USER_EMAIL);
    }

    @Test(dataProvider ="valid data", dataProviderClass = SignUpDataProvider.class)
    public void testUserRegistrationValidData(String firstname, String lastname, String email, String password, String dayOfBirth, String monthOfBirth, String yearOfBirth,
                                              String company, String address, String address2, String city, String state, String zipCode, String country,String addInfo,
                                              String homePhone, String cellPhone,String addAlias){
        signUpFormPage.fillForm(firstname, lastname, email, password,  dayOfBirth, monthOfBirth, yearOfBirth,company, address, address2, city, state, zipCode, country,addInfo, homePhone, cellPhone,addAlias);
        Assert.assertTrue(myAccountPage.lblWelcomeIsDisplayed());
    }


    @Test(dataProvider ="missing fields", dataProviderClass = SignUpDataProvider.class)
    public void testUserRegistrationMissingFields(String firstname, String lastname, String email, String password, String dayOfBirth, String monthOfBirth, String yearOfBirth,
                                              String company, String address, String address2, String city, String state, String zipCode, String country,String addInfo,
                                              String homePhone, String cellPhone,String addAlias) {

        signUpFormPage.fillForm(firstname, lastname, email, password, dayOfBirth, monthOfBirth, yearOfBirth, company, address, address2, city, state, zipCode, country, addInfo, homePhone, cellPhone, addAlias);
        Assert.assertTrue(signUpFormPage.lblErrorGeneralIsDisplayed());
    }

    @Test(dataProvider ="missing phone", dataProviderClass = SignUpDataProvider.class)
    public void testUserRegistrationMissingPhone(String firstname, String lastname, String email, String password, String dayOfBirth, String monthOfBirth, String yearOfBirth,
                                                  String company, String address, String address2, String city, String state, String zipCode, String country,String addInfo,
                                                  String homePhone, String cellPhone,String addAlias) {

        signUpFormPage.fillForm(firstname, lastname, email, password, dayOfBirth, monthOfBirth, yearOfBirth, company, address, address2, city, state, zipCode, country, addInfo, homePhone, cellPhone, addAlias);
        Assert.assertTrue(signUpFormPage.lblErrorPhoneIsDisplayed());
    }

    @Test(dataProvider ="missing zipcode", dataProviderClass = SignUpDataProvider.class)
    public void testUserRegistrationMissingZipCode(String firstname, String lastname, String email, String password, String dayOfBirth, String monthOfBirth, String yearOfBirth,
                                                 String company, String address, String address2, String city, String state, String zipCode, String country,String addInfo,
                                                 String homePhone, String cellPhone,String addAlias) {

        signUpFormPage.fillForm(firstname, lastname, email, password, dayOfBirth, monthOfBirth, yearOfBirth, company, address, address2, city, state, zipCode, country, addInfo, homePhone, cellPhone, addAlias);
        Assert.assertTrue(signUpFormPage.lblErrorZipCodeIsDisplayed());
    }

    @Test(dataProvider ="missing state", dataProviderClass = SignUpDataProvider.class)
    public void testUserRegistrationMissingState(String firstname, String lastname, String email, String password, String dayOfBirth, String monthOfBirth, String yearOfBirth,
                                                   String company, String address, String address2, String city, String state, String zipCode, String country,String addInfo,
                                                   String homePhone, String cellPhone,String addAlias) {

        signUpFormPage.fillForm(firstname, lastname, email, password, dayOfBirth, monthOfBirth, yearOfBirth, company, address, address2, city, state, zipCode, country, addInfo, homePhone, cellPhone, addAlias);
        Assert.assertTrue(signUpFormPage.lblErrorStateIsDisplayed());
    }

    @AfterTest
    public void closeDriver(){
        driver.quit();
    }
}
