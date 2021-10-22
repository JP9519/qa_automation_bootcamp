package Yelp.steps;

import Yelp.pageobject.MainPage;
import net.serenitybdd.core.steps.ScenarioActor;
import net.thucydides.core.annotations.Steps;

public class UsuarioYelp extends ScenarioActor {
    String actor;

    @Steps(shared=true)
    MainPage mainPage;

    public void navigatesTo(){
        mainPage.setDefaultBaseUrl("https://www.yelp.com/");
        mainPage.open();
    }

    public void searchFor(){
        mainPage.searchRestaurant();
    }

    public void searchRestaurant(String restaurant) throws InterruptedException {
        mainPage.searchTypeRestaurant(restaurant);
        mainPage.numTotalResults();

    }

    public void filterBy(String filter) throws InterruptedException {
        mainPage.selectFilter(filter);

    }

    public void enterFirstResult(){
        mainPage.selectFirstResult();
    }

    public void viewDetails() throws InterruptedException {
        mainPage.getDetails();
    }
}
