package com.pageobjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SignUpFormPage {

    private final WebDriver driver;

    @FindBy(xpath = "//input[@type=\"radio\"]")
    private List<WebElement> rbtnList;

    @FindBy(xpath="//*[@id=\"customer_firstname\"]")
    private WebElement inputFirstName;

    @FindBy(id="customer_lastname")
    private WebElement inputLastName;

    @FindBy(id="email")
    private WebElement inputEmail;

    @FindBy(id="passwd")
    private WebElement inputPassword;

    @FindBy(id="days")
    private WebElement inputDayOfBirth;

    @FindBy(id="months")
    private WebElement inputMonthOfBirth;

    @FindBy(id="years")
    private WebElement inputYearOfBirth;

    @FindBy(id="newsletter")
    private WebElement chkNewsletter;

    @FindBy(id="optin")
    private WebElement chkSpecialOffers;

    @FindBy(id="firstname")
    private WebElement inputFirstName2;

    @FindBy(id="lastname")
    private WebElement inputLastName2;

    @FindBy(id="company")
    private WebElement inputCompany;

    @FindBy(id="address1")
    private WebElement inputAddress;

    @FindBy(id="address2")
    private WebElement inputAddress2;

    @FindBy(id="city")
    private WebElement inputCity;

    @FindBy(id="id_state")
    private WebElement inputState;

    @FindBy(id="postcode")
    private WebElement inputZipCode;

    @FindBy(id="id_country")
    private WebElement inputCountry;

    @FindBy(id="other")
    private WebElement inputAddInfo;

    @FindBy(id="phone")
    private WebElement inputHomePhone;

    @FindBy(id="phone_mobile")
    private WebElement inputMobilePhone;

    @FindBy(id="alias")
    private WebElement inputAddressAlias;

    @FindBy(id="submitAccount")
    private WebElement btnRegister;

    @FindBy(xpath = "//div[@class='alert alert-danger']// li[contains(.,'You must register at least one phone number.')]")
    private WebElement lblErrorPhone;

    //@FindBy(xpath = "//li[contains(.,'lastname') and contains(.,'is required')]")
    //private WebElement lblErrorLastname;

    //@FindBy(xpath = "//li[contains(.,'firstname') and contains(.,'is required')]")
    //private WebElement lblErrorFirstname;

    //@FindBy(xpath ="//li[contains(.,'passwd') and contains(.,'is required')]")
    //private WebElement lblErrorPassword;

    @FindBy(xpath ="//div[@class='alert alert-danger']// li[contains(.,' is required.')]")
    private WebElement lblErrorGeneral;

    @FindBy(xpath ="//div[@class='alert alert-danger']// li[contains(.,'The Zip/Postal code')]")
    private WebElement lblErrorZipCode;

    @FindBy(xpath ="//div[@class='alert alert-danger']// li[contains(.,'This country requires you to choose a State.')]")
    private WebElement lblErrorState;


    public SignUpFormPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public WebElement ExplicitWait(WebDriver driver, WebElement locator,int seconds){
        WebDriverWait wait = new WebDriverWait(driver,seconds);
        return wait.until(ExpectedConditions.visibilityOf(locator));
    }

    public boolean lblErrorZipCodeIsDisplayed(){
        return lblErrorZipCode.isDisplayed();
    }

    public boolean lblErrorStateIsDisplayed(){
        return lblErrorState.isDisplayed();
    }

    public boolean lblErrorPhoneIsDisplayed(){
        return lblErrorPhone.isDisplayed();
    }

    public boolean lblErrorGeneralIsDisplayed(){
        return lblErrorGeneral.isDisplayed();
    }


    public void fillForm(String firstname, String lastname, String email, String password, String dayOfBirth, String monthOfBirth, String yearOfBirth,
                         String company, String address, String address2, String city, String state, String zipCode, String country,String addInfo,
                         String homePhone, String cellPhone,String addAlias){

        if(firstname!=null){
            ExplicitWait(driver,inputFirstName,5);
            inputFirstName.sendKeys(firstname);
        }

        if(lastname!=null){
            ExplicitWait(driver,inputLastName,5);
            inputLastName.sendKeys(lastname);
        }

        if(email!=null){
            ExplicitWait(driver,inputEmail,5);
            inputEmail.clear();
            inputEmail.sendKeys(email);
        }

        if(password!=null){
            ExplicitWait(driver,inputPassword,5);
            inputPassword.sendKeys(password);
        }

        if(dayOfBirth!=null){
            inputDayOfBirth.sendKeys(dayOfBirth);
            inputDayOfBirth.sendKeys(Keys.ENTER);
        }

        if(monthOfBirth!=null){
            inputMonthOfBirth.sendKeys(monthOfBirth);
            inputMonthOfBirth.sendKeys(Keys.ENTER);
        }

        if(yearOfBirth!=null){
            inputYearOfBirth.sendKeys(yearOfBirth);
            inputYearOfBirth.sendKeys(Keys.ENTER);
        }

        chkNewsletter.click();
        chkSpecialOffers.click();

        if(company!=null){
            inputCompany.sendKeys(company);
        }

        if(address!=null){
            inputAddress.sendKeys(address);
        }

        if(address2!=null){
            inputAddress2.sendKeys(address2);
        }

        if(city!=null){
            inputCity.sendKeys(city);
        }

        if(state!=null){
            inputState.sendKeys(state);
        }

        if(zipCode!=null){
            inputZipCode.sendKeys(zipCode);
        }

        if(country!=null){
            inputCountry.sendKeys(country);
        }

        if(addInfo!=null){
            inputAddInfo.sendKeys(addInfo);
        }

        if(homePhone!=null){
            inputHomePhone.sendKeys(homePhone);
        }

        if(cellPhone!=null){
            inputMobilePhone.sendKeys(homePhone);
        }

        if(addAlias!=null){
            inputAddressAlias.clear();
            inputAddressAlias.sendKeys(addAlias);
        }

        rbtnList.get(0).click();
        btnRegister.click();

    }

}
