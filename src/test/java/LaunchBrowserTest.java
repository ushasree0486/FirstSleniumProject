import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;


public class LaunchBrowserTest {
    static Logger logger = Logger.getLogger(LaunchBrowserTest.class);
    private WebDriver d;
@BeforeClass
    public static void init() {
        PropertyConfigurator.configure("log4j.properties");
        logger.info("performing initialization");
        WebDriverManager.chromedriver().setup();
        //WebDriverManager.firefoxdriver().setup();
        logger.trace("this is TRACE");
        logger.debug("this is DEBUG");
        logger.info("this is INFO");
        logger.warn("this is WARN");
        logger.error("this is ERROR");
        logger.fatal("this is FATAL");
    }
    @Test
        public void lanunchBrowser() {
        logger.info("launch browser");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        d = new ChromeDriver(options);
        //d=new ChromeDriver();
        //d=new FirefoxDriver();
        d.get("https://www.bbc.com");
        }
        @After
    public void cleanUp(){
    logger.info("performing clean up");
    d.quit();
    }
}
