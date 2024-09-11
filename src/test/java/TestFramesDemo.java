import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

public class TestFramesDemo {
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
    public void testFrame() {
        webDriver.get("https://demoqa.com/frames");
        //System.out.println(webDriver.findElement(By.id("sampleHeading")).getText());
        System.out.println(webDriver.findElements(By.tagName("iframe")).size());
       // webDriver.switchTo().frame("frame1");
        webDriver.switchTo().frame(3);
        String sampleHeading = webDriver.findElement(By.id("sampleHeading")).getText();
        // System.out.println(sampleHeading);
        Assert.assertEquals("This is a sample page", sampleHeading);
    }

    @Test
    public void testNestedFrame() {
        //navigate to the demo site
        webDriver.get("https://demoqa.com/nestedframes");
        //no of frames on page
        int countIframesInInPage = webDriver.findElements(By.tagName("iframe")).size();
        //locate the frame1 on the web page
        System.out.println("No of Frames on a Page "+countIframesInInPage);
       // Assert.assertEquals(10, countIframesInInPage);
        WebElement frame1 = webDriver.findElement(By.id("frame1"));
        webDriver.switchTo().frame(frame1);
        //no of frames on frame1
        int countFramesInFrame1=webDriver.findElements(By.tagName("iframe")).size();
        System.out.println("Number of iframes inside the frame1 "+ countFramesInFrame1 );
        //switch to chlid frame
        webDriver.switchTo().frame(0);
        int countFramesInFrame2=webDriver.findElements(By.tagName("iframe")).size();
        System.out.println("Number of iframes inside the frame2 "+ countFramesInFrame2);
        //switchback context to child frame to parent frame
        webDriver.switchTo().parentFrame();
        //switch context parent to default content
        webDriver.switchTo().defaultContent();
        //locate the element inside frame1
        WebElement frame1Element = webDriver.findElement(By.tagName("body"));
        //get the text for frame1 element
        String frame1Text = frame1Element.getText();
        //try to print text present inside parent frame
        System.out.println("Frame1 is :" + frame1Text);
        webDriver.close();
    }

    @Test
    public void testframeByWebElement() {
        //initialise browser
        webDriver.get("https://demoqa.com/frames");
        //navigate to url
        WebElement frame1 = webDriver.findElement(By.id("frame1"));
       // switching the webDriver context to frame1
        webDriver.switchTo().frame(frame1);
        //identifying the frame heading in a web element
        WebElement frame1Heading = webDriver.findElement(By.id("sampleHeading"));
        //finding the text of frame1 heading
        String frame1Text = frame1Heading.getText();
        //print the heading
        System.out.println("text of the frame1 heading is "+frame1Text);
        webDriver.close();
    }
}
