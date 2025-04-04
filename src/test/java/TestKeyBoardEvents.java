import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class TestKeyBoardEvents {
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
    public void testKeyEventsWithShiftKey(){
        webDriver.get("http://demoqa.com/auto-complete");
        WebElement element =webDriver.findElement(By.xpath(" //div[@class='auto-complete__control css-yk16xz-control']"));
        Actions actions= new Actions(webDriver);
        actions.keyDown(element, Keys.SHIFT);
        actions.sendKeys("we succed when our efforts are more than our excuses");
        actions.keyUp(Keys.SHIFT);
        actions.build().perform();
    }
    @Test
    public void testKeyEventsForCtrlCCtrlV(){
        webDriver.get("http://demoqa.com/text-box/");
        //create object of the actions class
        Actions actions = new Actions(webDriver);
        //enter username
        WebElement fullName= webDriver.findElement(By.id("userName"));
        fullName.sendKeys("Mr.Peter Hynes");
        //enter the email
        WebElement email= webDriver.findElement(By.id("userEmail"));
        email.sendKeys("peterHynes@toolsqa.com");
        //enter the current address
        WebElement currentAddress= webDriver.findElement(By.id("currentAddress"));
        currentAddress.sendKeys("43 School Lane London EC71 9Go");
        //select the current adress using ctrl+A
        actions.keyDown(currentAddress,Keys.CONTROL);
        actions.sendKeys("a");
        actions.keyUp(currentAddress,Keys.CONTROL);
        actions.build().perform();
        //copy the current address using ctrl+c
        actions.keyDown(Keys.CONTROL);
        actions.sendKeys("c");
        actions.keyUp(Keys.CONTROL);
        actions.build().perform();
        //press the TAB keyto switch focus to Permenent address
        actions.sendKeys(Keys.TAB);
        //paste the adrdress in the permenent address field using ctrl+V
        actions.keyDown(Keys.CONTROL);
        actions.sendKeys("v");
        actions.keyUp(Keys.CONTROL);
        actions.build().perform();
        //compare text of current address and permenent address
        WebElement permanentAddress = webDriver.findElement(By.id("permanentAddress"));
        Assert.assertEquals(currentAddress.getAttribute("value"),permanentAddress.getAttribute("value"));

    }
}
