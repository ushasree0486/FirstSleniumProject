import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TestAlertPopUps {
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
    public void testSimpleAlert() {
        webDriver.get("https://demoqa.com/alerts");
        webDriver.manage().window().maximize();
        //this step result an alert on screen
        webDriver.findElement(By.id("alertButton")).click();
        Alert simpleAlert = webDriver.switchTo().alert();
        simpleAlert.accept();
        webDriver.close();
    }
@SneakyThrows
    @Test
    public void testPromptAlert() {
       /* webDriver.get("https://demoqa.com/alerts");
        webDriver.manage().window().maximize();
        //this step result an alert on screen
        WebElement element = webDriver.findElement(By.id("promtButton"));
        Alert promptAlert = webDriver.switchTo().alert();
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click()", element);
        String alertText = promptAlert.getText();
        System.out.println("Alert text is :" + alertText);
        //send some text to alert
        promptAlert.sendKeys("Test User");
        promptAlert.accept();*/


                    webDriver.get("https://demoqa.com/alerts");
                    webDriver.manage().window().maximize();

                    // Locate and click the prompt button
                    WebElement element = webDriver.findElement(By.id("promtButton"));
                    ((JavascriptExecutor) webDriver).executeScript("arguments[0].click()", element);

                    // Wait for the alert to be present
                    WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
                    wait.until(ExpectedConditions.alertIsPresent());

                    // Switch to the alert
                    Alert promptAlert = webDriver.switchTo().alert();

                    // Get and print alert text
                    String alertText = promptAlert.getText();
                    System.out.println("Alert text is: " + alertText);

                    // Send input to the alert and accept it
                    promptAlert.sendKeys("Test User");
                    promptAlert.accept();
                    webDriver.quit();
                }




    @Test
    public void testconfirmationAlert() {
        webDriver.get("https://demoqa.com/alerts");
        webDriver.manage().window().maximize();
        //this step result an alert on screen
        WebElement element = webDriver.findElement(By.id("confirmButton"));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click()", element);
        Alert confirmationAlert = webDriver.switchTo().alert();
        String alertText = confirmationAlert.getText();
        System.out.println("Alert text is :" + alertText);
        //send some text to alert
        confirmationAlert.accept();
    }

    @Test
    public void testAlertWithNoUserNameAndPasswordAndLoginAttempted() {
        webDriver.get("https://www.rediff.com/");
        webDriver.manage().window().maximize();
        webDriver.findElement(By.xpath("//*[@id='signin_info']/a[1]")).click();
        //webDriver.findElement(By.tagName("a"));
        webDriver.findElement(By.xpath("//input[@title='Sign in']")).click();
        Alert alert = webDriver.switchTo().alert();
        System.out.println(alert.getText());
        Assert.assertEquals("Please enter a valid user name", alert.getText());
        alert.dismiss();
    }

    @Test
    public void testAlertWithUserNameAndNoPasswordAndLoginAttempted() {
        webDriver.get("https://www.rediff.com/");
        webDriver.manage().window().maximize();
        webDriver.findElement(By.xpath("//*[@id='signin_info']/a[1]")).click();
        webDriver.findElement(By.xpath("//input[@title='Sign in']")).click();
        webDriver.switchTo().alert().accept();
        // System.out.println(alert.getText());
        webDriver.findElement(By.id("login1")).sendKeys("abcdef");
        webDriver.findElement(By.name("proceed")).click();
        Assert.assertEquals("Please enter your password", webDriver.switchTo().alert().getText());
    }

    @Test
    public void testFileUpLoad() {
        webDriver.get("https://html.com/input-type-file");
        webDriver.findElement(By.name("fileupload")).sendKeys("C:\\Users\\J Venkata Prasad\\java certification.txt");
    }

    @Test
    public void testHttpBasicAutenticationWithCredentials() {
        // webDriver.get("http://the-internet.herokuapp.com/basic_auth");
        webDriver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
        Assert.assertEquals("Congratulations! You must have the proper credentials.",
                webDriver.findElement(By.tagName("p")).getText());
        //Credentials c=new UsernameAndPassword("admin","admin");
    }

    @Test
    public void testFaceBookCookie() {
        webDriver.get("http://www.facebook.com");
       // webDriver.findElement(By.cssSelector("button[data-cookiebanner=\"accept_button\"]")).click();
        webDriver.findElement(By.xpath("//a[@data-testid=\"open-registration-form-button\"]")).click();
        // webDriver.findElement(By.xpath("//button[@title='Accept All']")).click();
    }
@Test
public void testPopUpOnIcIcI() {
    // Initialize WebDriver (ensure ChromeDriver is in your PATH)
    WebDriver webDriver = new ChromeDriver();
    try {
        webDriver.get("https://www.icicibank.com/");
        webDriver.manage().window().maximize();

        // Click on the login dropdown button using JavaScript Executor
        WebElement loginDropdownButton = webDriver.findElement(By.xpath("//*[@id='login-dropdown']/button/span"));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", loginDropdownButton);

        // Wait for the alert to be present
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());

        // Switch to the alert and handle it
        Alert alert = webDriver.switchTo().alert();
        System.out.println("Alert text: " + alert.getText());

        // Validate the alert message
        Assert.assertEquals(alert.getText(), "Please enter a valid user name");

        // Dismiss the alert
        alert.dismiss();
    } catch (NoSuchElementException e) {
        System.err.println("Element not found: " + e.getMessage());
    } catch (TimeoutException e) {
        System.err.println("Alert not found within the wait time: " + e.getMessage());
    } finally {
        // Quit the WebDriver to close the browser
       // webDriver.quit();
    }
}



}


