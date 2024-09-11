import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class ToolTipDemo {
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
    public void testToolTipDemo1() {
        webDriver.get("https://seleniumpractise.blogspot.com/2019/09/bootstrap-tooltip-in-selenium.html");
        System.out.println("tool tip web page displayed");
        webDriver.manage().window().maximize();
        //get element for which we need to find tool tip
        WebElement toolTipButton = webDriver.findElement(By.linkText("Hover over me"));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(toolTipButton).perform();
        String toolTipText = webDriver.findElement(By.xpath("//div[text()='Hooray!']")).getText();
        System.out.println("retrived tool tip text as " + toolTipText);
        //verification if tool tip text is maching expected value
        Assert.assertEquals("Hooray!", toolTipText);
    }

    @Test
    public void testToolTipDemo2() {
        webDriver.manage().timeouts().pageLoadTimeout(3000, TimeUnit.SECONDS);
        webDriver.get("https://seleniumpractise.blogspot.com/2019/08/tooltip-in-selenium-or-help-text-example.html");
        webDriver.manage().window().maximize();
        //get element for which we need to find tool tip
        WebElement toolTipButton = webDriver.findElement(By.xpath("//*[@id=\"post-body-7354762648537595893\"]/div[1]/div"));
        //div[text()='Hover over me']
        Actions actions = new Actions(webDriver);
        actions.moveToElement(toolTipButton).perform();
        String toolTipText = webDriver.findElement(By.xpath("//span[contains(text(),'Tooltip text')]")).getText();
        System.out.println("retrived tool tip text as " + toolTipText);
        //verification if tool tip text is maching expected value
        Assert.assertEquals("Tooltip text", toolTipText);
    }
}