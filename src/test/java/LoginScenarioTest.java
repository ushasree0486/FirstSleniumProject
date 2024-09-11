import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LoginScenarioTest {
   private WebDriver d=null;
    @Before//Before means if there are cases it will be executed 10 times
    public void init(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
         d= new ChromeDriver(options);

    }
    @SneakyThrows
    @Test

    public void testSuccessfulLogin(){
        //WebDriverManager.firefoxdriver().setup();
      /*  WebDriverManager.chromedriver().setup();
       ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver d= new ChromeDriver(options);*/
       // WebDriver d=new ChromeDriver();
        //WebDriver d= new FirefoxDriver();
        d.get("https://www.saucedemo.com/");
        WebElement uName=d.findElement(By.xpath("//*[@id=\"user-name\"]"));
        uName.sendKeys("standard_user");
        WebElement uPassWord=d.findElement(By.xpath("//*[@id=\"password\"]"));
        uPassWord.sendKeys("secret_sauce");
        WebElement loginButton = d.findElement(By.xpath("//*[@id=\"login-button\"]"));
        //*[@id="login-button"]
        loginButton.click();//*[@id="header_container"]
        WebElement element=d.findElement(By.xpath("//*[@id=\"header_container\"]"));
       // System.out.println(d.getCurrentUrl());
        //System.out.println(element.isDisplayed());
        Assert.assertTrue(element.isDisplayed());
       // d.quit();
    }
    @Test
    @SneakyThrows
   public void testLoginFailsIncorrectCredentials() {
        //WebDriverManager.firefoxdriver().setup()
      /* WebDriverManager.chromedriver().setup();
       ChromeOptions options = new ChromeOptions();
       options.addArguments("--remote-allow-origins=*");
       WebDriver d= new ChromeDriver(options);*/
        // WebDriver d=new ChromeDriver();
        //WebDriver d= new FirefoxDriver();
        d.get("https://www.saucedemo.com/");
        WebElement uName = d.findElement(By.xpath("//*[@id=\"user-name\"]"));
        uName.sendKeys("standard_user");
        WebElement uPassWord = d.findElement(By.xpath("//*[@id=\"password\"]"));
        uPassWord.sendKeys("Randompassword");
        WebElement loginButton = d.findElement(By.xpath("//*[@id=\"login-button\"]"));
        //*[@id="login-button"]
        loginButton.click();//*[@id="header_container"]
        WebElement element = d.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3"));
        System.out.println(element.isDisplayed());
        System.out.println(element.getText());
        Assert.assertEquals("Epic sadface: Username and password do not match any user in this service", element.getText());
        // d.quit();
    }

    @After
    public void clean(){
              d.quit();
    }


}
