// Autor: Juan Pedro Marcos Miranda

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static utilities.RandomEmail.randomEmail;

public class Test1 extends Base {

    @BeforeTest
    public void initialize(){
        driver = initializeDriver();
        driver.get("https://demoqa.com/automation-practice-form");
        WebElement ads = driver.findElement(By.xpath("//*[@id=\"close-fixedban\"]")); //cerrar ads en la parte inferior de la página, se sobrepone e interfiere con el boton Submit.
        ads.click();

    }

    @Test
    public void testForm() throws InterruptedException {


        WebElement firstNameInput = driver.findElement(By.id("firstName"));
        firstNameInput.sendKeys("firstname test");

        WebElement lastNameInput = driver.findElement(By.id("lastName"));
        lastNameInput.sendKeys("lastname test");

        WebElement emailInput = driver.findElement(By.id("userEmail"));
        emailInput.sendKeys(randomEmail());

        // Al hacer click en el siguiente radioButton me salia el siguiente error:org.openqa.selenium.ElementClickInterceptedException: element click intercepted
        //Segun lo que encontré en el URL siguiente:
        // https://www.lambdatest.com/blog/how-to-deal-with-element-is-not-clickable-at-point-exception-using-selenium/
        //Existen 4 posibles causas. Para este caso en específico me parece que el error se da debido a que hay otra elemento superpuesto(label) al radioButton.
        //por lo que use el javascriptExecutor como posible solucion.
        List<WebElement> rtdBtns = driver.findElements(By.name("gender"));
        /* rtdBtns.get(i)
        0-male   1-female   2-other
        */
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", rtdBtns.get(0));
        //WebDriverWait wait = new WebDriverWait(driver,5 );
        //wait.until(ExpectedConditions.elementToBeClickable(rtdBtns.get(1))).click();


        WebElement numberInput = driver.findElement(By.id("userNumber"));
        numberInput.sendKeys("1234567890");

        WebElement birthdateInput = driver.findElement(By.xpath("//*[@id=\"dateOfBirthInput\"]"));
        birthdateInput.click();

        WebElement monthInput = driver.findElement(By.xpath("//*[@id=\"dateOfBirth\"]/div[2]/div[2]/div/div/div[2]/div[1]/div[2]/div[1]/select"));
        monthInput.sendKeys("September");

        WebElement yearInput = driver.findElement(By.xpath("//*[@id=\"dateOfBirth\"]/div[2]/div[2]/div/div/div[2]/div[1]/div[2]/div[2]/select"));
        yearInput.sendKeys("2000");

        String day = "15";
        WebElement dayInput = driver.findElement(By.xpath("//*[@id=\"dateOfBirth\"]/div[2]/div[2]/div/div/div[2]/div[2]/div/div[contains(text(),"+ day +")]"));
        dayInput.click();

        //WebDriverWait wait = new WebDriverWait(driver,5);
        WebElement subjectInput = driver.findElement(By.xpath("//*[@id=\"subjectsInput\"]"));
        //subjectInput.click();
        subjectInput.sendKeys("Maths");
        subjectInput.sendKeys(Keys.ENTER);
        subjectInput.sendKeys("Physics");
        subjectInput.sendKeys(Keys.ENTER);


        List<WebElement> rtdBtns2 = driver.findElements(By.xpath("//*[@type='checkbox']"));
        /* rtdBtns.get(i)
        0 - Sports
        1 - Reading
        2 - Music
        */
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", rtdBtns2.get(1));
        //Thread.sleep(3000);

        WebElement addressInput = driver.findElement(By.id("currentAddress"));
        addressInput.sendKeys("5th Ave");

        WebElement stateInput = driver.findElement(By.id("react-select-3-input"));
        //stateInput.click();
        stateInput.sendKeys("NCR");
        //Thread.sleep(3000);
        stateInput.sendKeys(Keys.ENTER);
        //Thread.sleep(3000);

        WebElement cityInput = driver.findElement(By.id("react-select-4-input"));
        cityInput.sendKeys("Delhi");
        cityInput.sendKeys(Keys.ENTER);
        //Thread.sleep(3000);

        WebElement submitBtn = driver.findElement(By.id("submit"));
        WebDriverWait wait = new WebDriverWait(driver,5 );
        wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();
        //submitBtn.click();
        Thread.sleep(2000);


        // Asserts
        String actualTitle;
        String actualUrl;
        String expectedTitle ="ToolsQA";
        String expectedUrl = "https://demoqa.com/automation-practice-form";

        actualTitle = driver.getTitle();
        actualUrl = driver.getCurrentUrl();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualTitle,expectedTitle);// correcto
        softAssert.assertEquals(actualUrl,expectedUrl);// correcto
        softAssert.assertAll();

    }

    @AfterTest
    public void closeDriver(){

        driver.close();
    }



}
