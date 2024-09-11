import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class FindElementDemo {
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
    public void testFindByElement(){
        webDriver.get("https://www.guru99.com");
        WebElement java_technologies = webDriver.findElement(By.id("java_technologies"));
        System.out.println(java_technologies.findElement(By.xpath(".//a[@title='JUnit']")).getText());
        System.out.println(java_technologies.findElement(By.xpath("//a[@title='NumPy']")).getText());
        //System.out.println(java_technologies.findElement(By.xpath(".//a[@title='NumPy']")).getText());
        System.out.println(java_technologies.findElements(By.xpath(".//a")).size());
        System.out.println(java_technologies.findElements(By.xpath("//a")).size());

    }
}
