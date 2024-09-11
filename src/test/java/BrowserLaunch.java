import lombok.SneakyThrows;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BrowserLaunch {
    @SneakyThrows
    public static void main(String[] args) throws Exception {
        // testChromeDriverLaunch();
        testFireFoxDriverLaunch();
    }

    private static void testChromeDriverLaunch() {
        String driverPath = "D:\\Usha\\drivers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);
        System.out.println(System.getProperties());

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.bbc.co.uk");
        if (driver.getTitle().startsWith("BBC")) {
            System.out.println("chrome launch test case passed");
        } else {
            System.out.println("chrome launch test case failed");
        }
    }

    private static void testFireFoxDriverLaunch() throws IOException {

        Properties p = new Properties();
        p.load(new FileInputStream(new File("selenium.properties")));

        String driverPath = p.get("firefox.driver.path").toString();
        System.setProperty("webdriver.gecko.driver", driverPath);
        // System.out.println(System.getProperties());
        WebDriver d = new FirefoxDriver();
        d.get("https://timesofindia.com");
        if (d.getTitle().startsWith("BBC")) {
            System.out.println("fire fox launch test case passed");
        } else {
            System.out.println("fire fox launch test case failed");
        }

    }
}