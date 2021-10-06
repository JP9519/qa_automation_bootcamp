import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Base {
    public WebDriver driver;

    public WebDriver initializeDriver(){
        String WebDriverPath =System.getProperty("user.dir")+"//tools//chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", WebDriverPath);
        this.driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        return driver;

    }
}