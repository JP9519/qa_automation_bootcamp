import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestNGEjercicio2 extends Base{

    @BeforeTest(alwaysRun = true)
    public void initialize(){
        driver = initializeDriver();
        driver.get("https://demoqa.com/buttons/");
    }
    @Test(groups = {"a","b"})
    public void testClick1_1() {
        Actions action = new Actions((driver));
        WebElement doubleClickBtn = driver.findElement(By.id("doubleClickBtn"));
        action.doubleClick(doubleClickBtn).perform();
        //Thread.sleep(3000);

        //WebElement doubleClickText = driver.findElement(By.id("doubleClickMessage"));
        //WebDriverWait wait = new WebDriverWait(driver,1);
        //wait.until(ExpectedConditions.textToBePresentInElement(doubleClickText,"You have done a double click"));
    }

    @Test(groups = {"a"})
    public void testClick1_2(){
        Actions action = new Actions((driver));
        WebElement rightClickBtn = driver.findElement(By.id("rightClickBtn"));
        action.contextClick(rightClickBtn).perform();
    }

    @Test(groups = {"b"})
    public void testClick1_3(){
        WebElement clickBtn = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div[1]/div[3]/button"));
        clickBtn.click();
    }

    @Test(groups = {"a","c"})
    public void testExcluded(){
        System.out.println("test exclude");
    }

    @Test(groups = {"a","b"})
    public void testVerify(){

        WebElement doubleClickText = driver.findElement(By.id("doubleClickMessage"));
        WebElement rightClickText = driver.findElement(By.id("rightClickMessage"));
        WebElement clickText = driver.findElement(By.id("dynamicClickMessage"));

        String expectedText1 = "You have done a double click";
        String expectedText2 = "You have done a right click";
        String expectedText3 = "You have done a dynamic click";

        String actualText1 = doubleClickText.getText();
        String actualText2 = rightClickText.getText();
        String actualText3 = clickText.getText();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualText1,expectedText1,"actualText1 no coindice");//correcto
        softAssert.assertEquals(actualText2,expectedText2,"actualText2 no coindice");//correcto
        softAssert.assertEquals(actualText3,expectedText3,"actualText2 no coindice");//correcto
        softAssert.assertAll();
        System.out.println("Validado");

    }
    @AfterTest(alwaysRun = true)
    public void closeDriver(){
        driver.close();
    }
}
