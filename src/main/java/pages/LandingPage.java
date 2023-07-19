package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class LandingPage {

    private final WebDriver driver;
    private JavascriptExecutor js = null;
    private final By acceptBanner = By.id("banner-accept");
    private final By firstIframe = By.xpath("//div[@id='mainContent']/iframe");
    private final By secondIframe = By.xpath("//p[contains(text(),'Document content goes here...')]/following::iframe");
    private final By aboutUsLink = By.xpath("//a[@href = '/about/index.htm' and contains(text(),' About us')]");
    private final By iframeUrl = By.xpath("//head/link[@rel='canonical']");
    private final By buttonTagElements = By.xpath("//button[contains(@id,'btn')]");
    private final By aTagBtnElements = By.xpath("//a[contains(@class,'btn')]");
    private final By divTagBtnElements = By.xpath("//div[contains(@class,'btn')]");
    private final By urls = By.xpath("//*[contains(@href,'/')]");
    private final By imgTagUrls = By.xpath("//img[contains(@src,'/')]");
    private final By inputFields = By.cssSelector("input");
    private final By loginBtn = By.cssSelector("a.btn-grey-border");
    private final By userEmailField = By.id("user_email");
    private final By passwordField = By.id("user_password");
    private final By userLoginBtn = By.id("user_login");
    private final By iframeContent = By.xpath("//meta[@name='description']");
    private final By skipPhoneBtn = By.id("skipMobileOtp");

    public LandingPage(WebDriver driver){
        this.driver = driver;
    }

    public void agreeingCookiesPolicy(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,1000)", "");
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstIframe));
        switchToFrame(driver.findElement(firstIframe));
        wait.until(ExpectedConditions.visibilityOfElementLocated(secondIframe));
        switchToFrame(driver.findElement(secondIframe));
        wait.until(ExpectedConditions.elementToBeClickable(acceptBanner));
        driver.findElement(acceptBanner).click();
        }

    public void clickingAboutUs(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,1250)", "");

        do {

            try{
                driver.findElement(aboutUsLink).click();
                wait.until(ExpectedConditions.attributeToBe(iframeUrl,"href","https://www.tutorialspoint.com/about/index.htm"));
            }
            catch (WebDriverException e){
                js.executeScript("window.scrollBy(0,200)", "");
            }

        } while (!driver.findElement(iframeUrl).getAttribute("href").equals("https://www.tutorialspoint.com/about/index.htm"));

    }

    public String getAboutUsUrl(){
        return driver.findElement(iframeUrl).getAttribute("href");
    }

    public List<String> getAllUrls(){
        return getAttribute(urls, "href");
    }

    public List<String> getAllUrlWithImgTag(){
        return getAttribute(imgTagUrls, "src");
    }

    public List<String> getAllBtnWithButtonTag(){
        return getAttribute(buttonTagElements, "id");
    }

    public List<String> getAllBtnWithATag(){
        return getText(aTagBtnElements);
    }

    public List<String> getAllBtnWithDivTag(){
        return getText(divTagBtnElements);
    }

    public List<String> getInputFields(){
        return getAttribute(inputFields, "placeholder");
    }

    public void clickLoginBtn(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        do {
            try{
                driver.findElement(loginBtn).click();
                wait.until(ExpectedConditions.attributeToBe(iframeUrl,"href","https://www.tutorialspoint.com/market/login.jsp"));
            }
            catch (WebDriverException e){
                js.executeScript("window.scrollBy(0,-50)", "");
            }
        }while (!driver.findElement(iframeUrl).getAttribute("href").equals("https://www.tutorialspoint.com/market/login.jsp"));

    }

    public void logIntoThePlatform(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(userEmailField));
        driver.findElement(userEmailField).sendKeys("cdanieloq@yahoo.com");
        driver.findElement(passwordField).sendKeys("FalsePassword");
        js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,100)", "");

        do {

            try{
                driver.findElement(userLoginBtn).click();
                wait.until(ExpectedConditions.attributeToBe(iframeContent,"content","Student Dashboard"));
            }
            catch (WebDriverException e){
                js.executeScript("window.scrollBy(0,50)", "");
                driver.findElement(userEmailField).clear();
                driver.findElement(userEmailField).sendKeys("cdanieloq@yahoo.com");
                driver.findElement(passwordField).clear();
                driver.findElement(passwordField).sendKeys("FalsePassword");
            }

        } while (!driver.findElement(iframeContent).getAttribute("content").equals("Student Dashboard"));

    }

    public void skipPhoneVerification(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(skipPhoneBtn));
        driver.findElement(skipPhoneBtn).click();
    }

    public void switchToFrame(WebElement frame){
        driver.switchTo().frame(frame);
    }

    public List<String> getAttribute(By locator, String attribute){
        List<WebElement> webElements = driver.findElements(locator);
        List<String> elements = new ArrayList<>();
        for (WebElement element : webElements){
            elements.add(element.getAttribute(attribute));
        }
        return elements;
    }

    public List<String> getText(By locator){
        List<WebElement> webElements = driver.findElements(locator);
        List<String> elements = new ArrayList<>();
        for (WebElement element : webElements){
            elements.add(element.getText());
        }
        return elements;
    }

}
