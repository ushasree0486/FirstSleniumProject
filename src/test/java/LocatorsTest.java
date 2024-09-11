import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class LocatorsTest extends BaseSetUp {
    private Logger log = Logger.getLogger(LocatorsTest.class);
    private WebDriver webDriver;
    private Properties properties;

    @Before
    public void setup() {
        webDriver = getWebDriver();
        properties = getObjRepo();
        log.info("child class before called");
    }

    @Test
    public void testLocatorUsingId() {
        webDriver.get("http://www.automationpractice.pl/index.php");
        webDriver.manage().window().maximize();
        WebElement searchElement = webDriver.findElement(By.id("search_query_top"));
        searchElement.sendKeys("T-shirt");
    }

    @Test
    public void testLocatorUsingName() {
        webDriver.get("http://www.automationpractice.pl/index.php");
        webDriver.manage().window().maximize();
        WebElement searchElement = webDriver.findElement(By.id("search_query_top"));
        searchElement.sendKeys("T-shirt");
        WebElement searchButton = webDriver.findElement(By.name("submit_search"));
        searchButton.click();
    }

    @Test
    public void testLocatorUsingLinkText() {
        webDriver.get("http://www.automationpractice.pl/index.php");
    /*webDriver.get("http:/www.bbc.com");
     webDriver.findElement(By.linkText("Sport")).click();*/
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // webDriver.findElement(By.partialLinkText("brink of history")).click();
        // webDriver.manage().window().maximize();
        WebElement searchElement = webDriver.findElement(By.id("search_query_top"));
        searchElement.sendKeys("T-shirt");
        WebElement searchButton = webDriver.findElement(By.name("submit_search"));
        searchButton.click();
     /*WebElement linkTextElement=webDriver.findElement(By.linkText("Printed Chiffon Dress"));
     linkTextElement.click();*/
        WebElement partialLinkTextElement = webDriver.findElement(By.partialLinkText("Chiffon Dress"));
        partialLinkTextElement.click();
    }

    @Test
    public void testLocatorsByClassName() {
        webDriver.get("http://www.automationpractice.pl/index.php");
        webDriver.manage().window().maximize();
        // webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println(webDriver.findElements(By.className("header_container")).size());
    }

    @Test
    public void testLocatorsByTagname() {
        webDriver.get("http://www.automationpractice.pl/index.php");
        webDriver.manage().window().maximize();
        // webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println(webDriver.findElements(By.tagName("a")).size());
        System.out.println(webDriver.findElements(By.tagName("img")).size());
    }

    @Test
    public void testLocatorsByCssSelector() {
        webDriver.get("http://www.facebook.com");
        webDriver.manage().window().maximize();
        // tag and id
        /*WebElement usernameElement = webDriver.findElement(By.cssSelector("#email"));//tag is optional
       // WebElement usernameElement = webDriver.findElement(By.cssSelector("input#email"));
        usernameElement.sendKeys("abc@gmail.com");
   */    //tag and class
        /*WebElement usernameElement2 = webDriver.findElement(By.cssSelector(".inputtext"));
       // WebElement usernameElement2 = webDriver.findElement(By.cssSelector("input.inputtext"));
        usernameElement2.sendKeys("abc@gmail.com");
        *///tag and attribute
        /*WebElement usernameElement3 = webDriver.findElement(By.cssSelector("input[name=email]"));
        // WebElement usernameElement3 = webDriver.findElement(By.cssSelector("[name=email]"));
        usernameElement3.sendKeys("abc@gmail.com");
        *///tad class attribute
        WebElement usernameElement4 = webDriver.findElement(By.cssSelector("input.inputtext[name=email]"));
        // WebElement usernameElement4 = webDriver.findElement(By.cssSelector(".inputtext[name=email]"));
        usernameElement4.sendKeys("abc@gmail.com");
    }
    @Test
    public void testByXPath() {
        //using absolute path
        // webDriver.get("http://opensource-demo.orangehrmlive.com");
        //webDriver.manage().window().maximize();
        /*webDriver.findElement(By.xpath("/html/body/div/div[1]/div/div[1]/div/div[2]/div[2]/form/div[1]/div/div[2]/input")).sendKeys("Admin");
        webDriver.findElement(By.xpath("/html/body/div/div[1]/div/div[1]/div/div[2]/div[2]/form/div[2]/div/div[2]/input")).sendKeys("admin123");
        webDriver.findElement(By.xpath("/html/body/div/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button")).click();
*/
        //relative Xpath
        /*webDriver.findElement(By.xpath("input[@id='txtUsername']")).sendKeys("Admin");
        webDriver.findElement(By.xpath("input[@id='txtPassword']")).sendKeys("admin123");
        webDriver.findElement(By.xpath("input[@id='btnLogin']")).click();*/

        webDriver.get("http://www.saucedemo.com");
        webDriver.manage().window().maximize();
        webDriver.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys("standard_user");
        webDriver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("secret_sauce");
        webDriver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
    }
    @Test
    public void testXpathWithOptions(){
        //or
        /*webDriver.get("http://www.automationpractice.pl/index.php");
        webDriver.manage().window().maximize();
        webDriver.findElement(By.xpath("//*[@id='search_query_top' or @name='search_query']")).sendKeys("T-shirt");
        webDriver.findElement(By.xpath("//button[@name=\"submit_search\" or @class=\"btn btn-default button-search\"]")).click();*/

        //and
        /*webDriver.get("http://www.automationpractice.pl/index.php");
        webDriver.manage().window().maximize();
        webDriver.findElement(By.xpath("//*[@id='search_query_top' and @type='text']")).sendKeys("T-shirt");
        webDriver.findElement(By.xpath("//button[@name=\"submit_search\" and @class=\"btn btn-default button-search\"]")).click();
*/
        //contains or starts with
        webDriver.get("http://opensource-demo.orangehrmlive.com");
        webDriver.manage().window().maximize();
        webDriver.findElement(By.xpath("//input[contains(@name,'usern')]")).sendKeys("Admin");
        //input[contains(@name,'name')]//contains need not start with starting character. it can start anywhere
        webDriver.findElement(By.xpath("//input[contains(@name,'passw')]")).sendKeys("admin123");
        webDriver.findElement(By.xpath("//button[contains(@type,'sub')]")).click();

       /* webDriver.get("http://opensource-demo.orangehrmlive.com");
        webDriver.manage().window().maximize();
        webDriver.findElement(By.xpath("//input[starts-with(@name,'usern')]")).sendKeys("Admin");
        webDriver.findElement(By.xpath("//input[starts-with(@name,'passw')]")).sendKeys("admin123");
        webDriver.findElement(By.xpath("//button[starts-with(@type,'sub')]")).click();*/

        //text
        /*webDriver.get("http://opensource-demo.orangehrmlive.com");
        webDriver.manage().window().maximize();
        webDriver.findElement(By.xpath("//a[text()=\"OrangeHRM, Inc\"]")).click();
*/
//chained xpath
       /* webDriver.get("http://www.automationpractice.pl/index.php");
        webDriver.manage().window().maximize();
        webDriver.findElement(By.xpath("//form[@id=\"searchbox\"]//input[4]")).sendKeys("T-shirt");
        //form[@id="searchbox"]//input[@class="search_query form-control ac_input"]
        //input[@id="search_query_top" or input[@class="search_query form-control ac_input1"]//correct and incorrect
        webDriver.findElement(By.xpath("//form[@id=\"searchbox\"]//button")).click();*/


    }


}

