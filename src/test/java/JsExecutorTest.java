import Utils.JsExecutorUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;

public class JsExecutorTest {
    private WebDriver webDriver;

    @Before
    public void init() {
        WebDriverManager.chromedriver().setup();
        //WebDriver driver = new FirefoxDriver();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(options);

    }

    @Test
    public void testEnterTextUsingJsExecutor() {
        webDriver.get("https://www.facebook.com");
        webDriver.manage().window().maximize();
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        //webDriver.findElement(By.cssSelector("button[data-cookiebanner=accept_button")).click();
        //js.executeScript("document.getElementById('email').value=\"usha@gmail.com\"");
        webDriver.findElement(By.id("email")).sendKeys("usha@gmail.com");
    }

    @Test
    @SneakyThrows
    public void testFlashUsingJsExecutor() {
        webDriver.get("https://www.orangehrm.com/");
        WebElement element = webDriver.findElement(By.xpath("//input[@id='Form_submitForm_action_request']"));
        JsExecutorUtils.flash(element, webDriver);
        //input[@id='Form_submitForm_action_request']
        JsExecutorUtils.drawBorder(element, webDriver);
        File screenShotAs = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
        File target = new File("c:\\screenshot\\HRM.jpg");
        FileUtils.copyFile(screenShotAs, target);
        //copy file copies the source file to target file(destination file)
    }

    @Test
    public void testCaptureTitleOfTheImage() {
        webDriver.get("https://www.orangehrm.com/");
        // System.out.println(webDriver.getTitle());
        System.out.println(JsExecutorUtils.getTitle(webDriver));
    }
@Test
    public void testClickElementByJsExecutor() {
        webDriver.get("https://www.orangehrm.com/");
        WebElement el = webDriver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/div[2]/ul/li[2]/a"));
       // el.click();
        //*[@id="header-novbar"]/ul[3]/li[1]/a
        JavascriptExecutor js=(JavascriptExecutor)webDriver;
        WebElement el2=webDriver.findElement(By.xpath("//*[@id=\"Form_submitForm_action_request\"]"));
    //*[@id="header-novbar"]/ul[3]/li[2]/a
        js.executeScript("arguments[1].click()" ,el,el2);
    }
    @Test
    public void testAlertByJSExecutor(){
        webDriver.get("https://www.orangehrm.com/");
        WebElement el = webDriver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/div[2]/ul/li[2]/a"));
        JavascriptExecutor js=(JavascriptExecutor)webDriver;
        js.executeScript("arguments[0].click()" ,el);
        JsExecutorUtils.generateAlert(webDriver,"You Clicked On Contact Sales");
    }
    @Test
    public void testScrollTillElementAppears(){
        webDriver.get("https://www.orangehrm.com/");
        WebElement el = webDriver.findElement(By.xpath("/html/body/div/div/div/div/section[3]/div[4]/div/div[1]/div[1]/div/img"));
        JsExecutorUtils.scrollTillElementIsInView(el, webDriver);
    }
    @Test
    public void scrollTillEndOfTheDoccument(){
        webDriver.get("https://www.orangehrm.com/");
        JsExecutorUtils.scrollTillEndOfDocument(webDriver);
    }

}
