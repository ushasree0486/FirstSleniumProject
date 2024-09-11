import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class WebDriverComandsDemo {
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
    public void testGetCommands() {
        webDriver.get("https://www.makemytrip.com");
        System.out.println("Current Title is: " + webDriver.getTitle());
        System.out.println("Current Url is: " + webDriver.getCurrentUrl());
        System.out.println("Page Source is: " + webDriver.getPageSource());
        System.out.println("Get Window Handle is: " + webDriver.getWindowHandle());
        //webDriver.close();
        webDriver.quit();
    }
    @Test
    public void testWindowHandels() {
        webDriver.get("file:///D:/Usha/CloseAndQuit.html");
        webDriver.findElement(By.linkText("Visit BBC!")).click();
        // webDriver.findElement(By.linkText("visit Google!")).click();
        webDriver.findElement(By.linkText("Visit W3Schools.com!")).click();
        webDriver.findElement(By.linkText("Visit MakeMyTrip.com!")).click();
        Set<String> windowHandels = webDriver.getWindowHandles();
        for (String id : windowHandels) {
            System.out.println("ID : " + id);
            String title = webDriver.switchTo().window(id).getTitle();
            if (title.contains("W3Schools")) break;
        }

        Map<String, String> m = new HashMap<>();
        for (String id : windowHandels) {
            System.out.println("ID : " + id);
            String title = webDriver.switchTo().window(id).getTitle();
            m.put(title, id);
        }
        for (Map.Entry<String, String> e : m.entrySet()) {
            if (e.getKey().contains("BBC"))
            webDriver.switchTo().window(e.getValue());
            System.out.println("Switching to BBC");
            break;
        }




        /*String parentWindowHandel = webDriver.getWindowHandle();
        Iterator<String> iterator = windowHandels.iterator();
        while (iterator.hasNext()) {
            String reference_childWindow = iterator.next();
            webDriver.switchTo().window(reference_childWindow);
            System.out.println(webDriver.getTitle());
        }
        webDriver.switchTo().window(parentWindowHandel);
        System.out.println("URL of parent window " + webDriver.getCurrentUrl());
*/
    }

    @Test
    public void testCloseAndQuit() {
        webDriver.get("file:///D:/Usha/CloseAndQuit.html");
        //webDriver.findElement(By.tagName("a"));
        webDriver.findElement(By.linkText("Visit BBC!")).click();
        webDriver.quit();
    }

    @Test
    public void testCAndQuit2() {
        webDriver.get("https://demoqa.com/browser-windows");
        //open new child window with in the main window
        String parentWindow = webDriver.getWindowHandle();
        webDriver.findElement(By.xpath("//button[@id='tabButton']")).click();
        // webDriver.findElement(By.xpath("//button[@id='windowButton']")).click();
        webDriver.findElement(By.xpath("//button[@id='messageWindowButton']")).click();
        System.out.println("size :" + webDriver.getWindowHandles().size());
        // webDriver.switchTo().window("parentWindow");

        //webDriver.findElement(By.id("windowbutton")).click();
//        //Get handels of the window
//        String mainWindowHandle= webDriver.getWindowHandle();
//        Set<String> allWindowHandels=webDriver.getWindowHandles();
//        Iterator <String> iterator=allWindowHandels.iterator();

    }

    @Test
    public void testNavigateCommands() {
        webDriver.get("https://www.facebook.com");
        System.out.println("URL via get is " + webDriver.getCurrentUrl());
        webDriver.navigate().to("https://www.bbc.co.uk");
        System.out.println("URL via navigate to " + webDriver.getCurrentUrl());
        webDriver.navigate().back();
        System.out.println("URL via navigate back " + webDriver.getCurrentUrl());
        webDriver.navigate().forward();
        System.out.println("URL via navigate forward " + webDriver.getCurrentUrl());
        webDriver.navigate().refresh();


    }


}