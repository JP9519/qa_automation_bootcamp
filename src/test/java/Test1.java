import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Test1 extends Base{
    @BeforeTest
    public void initialize(){
        driver = initializeDriver();
    }

    @Test
    public void test(){
        driver.get("https://www.google.com");
    }

    @AfterTest
    public void CloseDriver(){
        driver.close();
    }

}
