import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import io.github.bonigarcia.wdm.managers.FirefoxDriverManager;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SmartBrowserLaunch {
    @SneakyThrows
    public static void main(String[] args)throws Exception {
        SmartBrowserLaunch sbl = new SmartBrowserLaunch();
        //sbl.testDriverManagerChrome();
        sbl.testDriverManagerFireFoxDriver();
    }
    public void testDriverManagerChrome() {
        WebDriverManager.chromedriver().setup();
        System.out.println(new ChromeDriverManager().getDownloadedDriverPath());
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver d= new ChromeDriver(options);
        d.get("https://www.bbc.co.uk");
        System.out.println("using ChromeDriver " + d.getTitle());
        d.quit();
}
    public void testDriverManagerFireFoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        System.out.println(new FirefoxDriverManager().getDownloadedDriverPath());
        WebDriver d = new FirefoxDriver();
        d.get("https://www.bbc.co.uk");
        System.out.println("using FireFoxDriver " + d.getTitle());
        d.quit();

    }
}