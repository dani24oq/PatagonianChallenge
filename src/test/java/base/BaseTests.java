package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.LandingPage;

public class BaseTests {

    private WebDriver driver;
    protected LandingPage landingPage;

    @BeforeMethod
    public void setUp(){
        ChromeOptions option = new ChromeOptions();
        option.setPageLoadStrategy(PageLoadStrategy.EAGER);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(option);
        driver.manage().window().maximize();
        driver.get("http://tutorialspoint.com/html/html_iframes.htm");
        landingPage = new LandingPage(driver);
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
