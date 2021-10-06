import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Test1 extends Base {
    @BeforeTest
    public void initialize(){
        driver = initializeDriver();
    }

    @Test
    public void test() throws InterruptedException {
        //driver.get("https://www.google.com/");
        driver.get("https://demoqa.com/automation-practice-form");

        //WebElement firstName = driver.findElement(By.id("firstName"));
        //WebElement firstName = driver.findElement(By.xpath("//*[@id=\"firstName\"]"));
        firstName.sendKeys("Juan");

        //WebElement lastName = driver.findElement(By.id("lastName"));
        //lastName.sendKeys("Marcos");

        //WebElement userEmail = driver.findElement(By.id("userEmail"));
        //userEmail.sendKeys("jpmarcos95@gmail.com");

        Thread.sleep(5000);
    }

    @AfterTest
    public void closeDriver(){
        driver.close();
    }
}
