import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Base {

    public WebDriver driver;

    public WebDriver initializeDriver(){
        String webDriverPath = System.getProperty("user.dir") + "//tools//chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", webDriverPath);
        this.driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        return driver;

    }

}
