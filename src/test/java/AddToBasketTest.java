import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import  org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class AddToBasketTest {
    private WebDriver d=null;
    @Before
    public void init(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        d= new ChromeDriver(options);
    }
    @SneakyThrows
    @Test
    public void testAddToBasket(){
        d.get("https://www.saucedemo.com/");
        WebElement uName=d.findElement(By.xpath("//*[@id=\"user-name\"]"));
        uName.sendKeys("standard_user");
        WebElement uPassWord=d.findElement(By.xpath("//*[@id=\"password\"]"));
        uPassWord.sendKeys("secret_sauce");
        WebElement loginButton = d.findElement(By.xpath("//*[@id=\"login-button\"]"));
        loginButton.click();
        d.get("https://www.saucedemo.com/inventory.html");

        WebElement addToCart1 =d.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-backpack\"]"));
        // "//*[@id=\"add-to-cart-sauce-labs-back-pack\"]"
        addToCart1.click();//*[@id="add-to-cart"]
        WebElement addToCart2 =d.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-bike-light\"]"));
        addToCart2.click();
        WebElement itemCountInBasket =d.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a/span"));
        System.out.println(itemCountInBasket.getText());
        Assert.assertEquals("2",itemCountInBasket.getText());
    }

}
