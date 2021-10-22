package Yelp.pageobject;


import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import net.thucydides.core.webdriver.WebDriverFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainPage extends PageObject {

   //Main Page

    @FindBy(xpath="//*[@id='find_desc']")
    WebElementFacade findButton;

    @FindBy(xpath="//*[@data-suggest-query='Restaurants']")
    WebElementFacade RestaurantsItem;

    //Search Page

    @FindBy(xpath = "//input[@id='search_description']")
    WebElementFacade searchBox;

    @FindBy(xpath="//form[@id='header_find_form']//button[@value='submit']")
    WebElementFacade searchButton;

    @FindBy(xpath="//*[contains(text(),'Phone number')]//following-sibling::p")
    WebElementFacade restaurantPhone;

    @FindBy(xpath="//*[contains(text(),'Get Directions')]//parent::p//following-sibling::p")
    WebElementFacade restaurantAddress;

    @FindBy(xpath="//span[contains(text(),'Yelp Sort')]/parent::button")
    WebElementFacade reviewSortByButton;

    @FindBy(xpath="//*[contains(text(),'Newest First')]//ancestor::button")
    WebElementFacade newestFirstButton;

    static String xpathNombreRestaurante = "//*[contains(@class,'ResultsContainer')]//descendant::h4";
    static String xpathFiltrodistancia = "//div[@class=' padding-t2__09f24__1r5gq padding-b2__09f24__3MG59 border-color--default__09f24__3Epto']//descendant::span";
    static String xpathTotalNumberOfPages = "//*[@id='main-content']/div/ul/li[22]/div/div[2]";
    static List<WebElementFacade> myListOfRestaurants;
    static List<WebElementFacade> listRating;
    static List<WebElementFacade> listReviews;
    static List<WebElementFacade> myListOfDistances;
    static Integer count;

    public void searchRestaurant(){
     findButton.click();
     RestaurantsItem.click();
    }

    public void searchTypeRestaurant(String restaurant) {
     searchBox.clear();
     searchBox.sendKeys(restaurant);
     searchButton.click();
     //myListOfRestaurants = withTimeoutOf(30, TimeUnit.SECONDS).findAll(By.xpath(xpathNombreRestaurante));
    // numSearchPerPage(myListOfRestaurants);
     //Serenity.recordReportData().withTitle("Total de Resultados").andContents("Numero de resultado en la primera pagina es: "+ count);
    }

    public void selectFirstResult(){

     myListOfRestaurants=withTimeoutOf(30,TimeUnit.SECONDS).findAll(By.xpath(xpathNombreRestaurante));
         for(WebElementFacade inputElement:myListOfRestaurants){
           if(inputElement.getText().contains(".")){
             Serenity.recordReportData().withTitle("Nombre del primer restaurante").andContents(inputElement.getText());
             inputElement.click();
             break;
           }
         }
    }

    public void selectFilter(String distancia){
     count = 0;
     myListOfDistances = withTimeoutOf(20, TimeUnit.SECONDS).findAll(By.xpath(xpathFiltrodistancia));
           for(WebElementFacade inputElement: myListOfDistances){
              if(inputElement.getText().contains(distancia)){
                Serenity.recordReportData().withTitle("Filtro de Distancia").andContents(myListOfDistances.get(count).getText());
                myListOfDistances.get(count).click();
              }
              count++;
           }
    }

    public void getDetails() throws InterruptedException {
        String phone = "El telefono del restaurante es : " + restaurantPhone.getText();
        String address = "La dirección del restaurante es : " + restaurantAddress.getText();

        String xpathRating = "//span[@class=' display--inline__373c0__1gaV4 border-color--default__373c0__1yxBb']//descendant::div";
        listRating = withTimeoutOf(5,TimeUnit.SECONDS).findAll(By.xpath(xpathRating));
        String rating = "El restaurante tiene : " + listRating.get(0).getAttribute("aria-label");

        reviewSortByButton.click();
        newestFirstButton.click();
        Thread.sleep(4000);

        String xpathReviews ="//p[@class='comment__373c0__Nsutg css-n6i4z7']";
        listReviews = withTimeoutOf(5,TimeUnit.SECONDS).findAll(By.xpath(xpathReviews));
        String firstReview="El primer review mas reciente es: " +listReviews.get(0).getText();
        String secondReview="El segundo review mas reciente es: " +listReviews.get(1).getText();
        String thirdReview="El tercero review mas reciente es: " +listReviews.get(2).getText();
        String details = phone + "\n" + address + "\n" + rating + "\n" + firstReview + "\n" + secondReview + "\n" +
             thirdReview;
        Serenity.recordReportData().withTitle("Details y Reviews").andContents(details);

    }

    public void numSearchPerPage( List<WebElementFacade> lista){
       count =0;
       for(WebElementFacade inpuElement : lista){
             if(inpuElement.getText().contains(".")){
              count = count+1;
             }
       }
     }

    public void numTotalResults() throws InterruptedException {

        int length;
        int lastPageURl;
        int nTotalResults;
        WebDriver driver = getDriver();

        WebElementFacade numberPages =  withTimeoutOf(30, TimeUnit.SECONDS).find(By.xpath(xpathTotalNumberOfPages));

        length = numberPages.getText().length();
        String numPages= numberPages.getText().substring(length-2,length);
        lastPageURl= Integer.valueOf(numPages)-1;


        myListOfRestaurants = withTimeoutOf(30, TimeUnit.SECONDS).findAll(By.xpath(xpathNombreRestaurante));
        numSearchPerPage(myListOfRestaurants);
        String resultsFirstPage= "Numero de resultado en la primera pagina es: "+ count;

        String currentURl = driver.getCurrentUrl();
        driver.get(currentURl+"&start="+lastPageURl+"0");

        myListOfRestaurants = withTimeoutOf(30, TimeUnit.SECONDS).findAll(By.xpath(xpathNombreRestaurante));
        numSearchPerPage(myListOfRestaurants);

        //Thread.sleep(4000);

        nTotalResults = (Integer.valueOf(numPages)-1)*10 + count ;
        Serenity.recordReportData().withTitle("Resultados").andContents(resultsFirstPage +
                "\n" + "Numero total de resultados: "+nTotalResults);

        driver.get(currentURl);
        //Thread.sleep(4000);


    }

}
