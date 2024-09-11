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

public class TestMouseEvents {
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
    public void testMouseRightClick() {
        webDriver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
        webDriver.manage().window().maximize();
        Actions actions = new Actions(webDriver);
        // retrive wbelement to perform right click
        WebElement btnElement = webDriver.findElement(By.xpath("/html/body/div/section/div/div/div/p/span"));
        //right click the button to display context menu&nbsp
        actions.contextClick(btnElement).perform();
        System.out.println("Right click context menu displasyed");
        //following code is to select item from context menu which gets open up on right click
        //depending upon your application specific test case
         //select and click 'copy me' i.e 2nd option in context menu
        WebElement elementOpen = webDriver.findElement(By.xpath("//span[text()='Copy']"));
        elementOpen.click();
        //accept the alert
        webDriver.switchTo().alert().accept();
        System.out.println("Right click alert accepted");
    }

    @Test
    public void testDoubleClick() {
        webDriver.get("http://demoqa.com/buttons/");
        System.out.println("demoqa web page displayed");
        //maximise browser window
        webDriver.manage().window().maximize();
        Actions actions = new Actions(webDriver);
        //retrieve WebElement to perform Double click webelement
        WebElement btnElement = webDriver.findElement(By.id("doubleClickBtn"));
        //doubleclick the button
        actions.doubleClick(btnElement).perform();
        //Assert.assertEquals("You have done a double click", webDriver.findElement(By.id("doubleClickBtn")));
    }

    @Test
    public void testDragandDrop() {
        String url = "http://demoqa.com/droppable";
        webDriver.get(url);
        webDriver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
        //actions class method to drag and drop
        Actions actions=new Actions(webDriver);
        WebElement from= webDriver.findElement(By.id("draggable"));
        WebElement to= webDriver.findElement(By.id("droppable"));
        //perform drag and drop
        actions.dragAndDrop(from,to).perform();
        actions.clickAndHold(from).moveToElement(to).release().build().perform();


        //verify text changed in to drop here box
        String textTo =to.getText();
        Assert.assertEquals("Dropped!",textTo);
    }
    @Test
    public void testDragAndDropBy(){
        webDriver.get("http://jqueryui.com/slider/");
        webDriver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
        //actions class method to drag and drop
        Actions builder=new Actions(webDriver);
        webDriver.switchTo().frame(0);
        WebElement from = webDriver.findElement(By.id("slider"));
        //perform drag and drop By
        builder.dragAndDropBy(from,100,100).perform();
        System.out.println("Dropped");
    }
    @Test
    public void testMouseHoverAction(){
        //launch the url
        webDriver.get("http://opensource-demo.orangehrmlive.com");
        //maximize window
        webDriver.manage().window().maximize();
        //adding wait
        webDriver.manage().timeouts().implicitlyWait(1000,TimeUnit.MILLISECONDS);
        //instantiate Actions class
        Actions actions = new Actions(webDriver);
        //Retrive webelement 'Music' to perform mouse hover
        webDriver.findElement(By.xpath("//input[starts-with(@name,'usern')]")).sendKeys("Admin");
        webDriver.findElement(By.xpath("//input[starts-with(@name,'passw')]")).sendKeys("admin123");
        webDriver.findElement(By.xpath("//button[starts-with(@type,'sub')]")).click();

        WebElement adminElement= webDriver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[1]/a"));
        actions.moveToElement(adminElement).click().perform();
        WebElement userManagementElement = webDriver.findElement(By.xpath("//span[normalize-space()='User Management']"));
        actions.moveToElement(userManagementElement).click().perform();
       // WebElement usersElement = webDriver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[1]/ul/li/a"));
       // actions.moveToElement(usersElement).click().perform();
    }
}















