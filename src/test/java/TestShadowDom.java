import com.google.common.collect.ImmutableMap;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class TestShadowDom {
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
    public void testShadowDom() {
        // webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        webDriver.get("https://shop.polymer-project.org/");

        //webDriver.get("https://www.facebook.com");
        //webDriver.findElement(By.xpath("//a[@aria-label='men's Outerwear Shop now'))")).click();
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        WebElement shadowHost = webDriver.findElement(By.tagName("shop-app"));
        SearchContext shadowDom = (SearchContext) js.executeScript("return arguments[0].shadowRoot", shadowHost);
        //WebElement shadowDom = (WebElement) js.executeScript("return arguments[0].shadowRoot",shadowHost);
        WebElement ironpages = shadowDom.findElement(By.tagName("iron-pages"));
        //WebElement ironPages = shadowDom.findElement(By.tagName("iron-pages"));
        WebElement shadowHome = ironpages.findElement(By.tagName("home"));
        WebElement shadowDom2 = (WebElement) js.executeScript("return arguments[0].shadowRoot", shadowHome);
        WebElement div = shadowDom2.findElement(By.tagName("div"));
        WebElement element = div.findElement(By.tagName("shop-button"));
        element.findElement(By.tagName("a")).click();
    }

}
