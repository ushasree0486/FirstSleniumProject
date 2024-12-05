import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class TestWaitDemo {
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
    public void testImplicitWait() {
        webDriver.get("https://www.google.com");
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.findElement(By.xpath("//*[@id=\"APjFqb\"]")).click();
        webDriver.findElement(By.name("q")).sendKeys("Automation");
        webDriver.findElement(By.name("q")).sendKeys(Keys.RETURN);
        // webDriver.findElement(By.xpath("abc"));
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(60));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[normalize-space()='Automation']")));
        element.click();
    }
    @Test
    @SneakyThrows
    public void testExplicitWaitWithGenericMethod1() {
        webDriver.get("https://www.google.com");
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.findElement(By.xpath("//*[@id=\"APjFqb\"]")).click();
        webDriver.findElement(By.name("q")).sendKeys("Automation");
        webDriver.findElement(By.name("q")).sendKeys(Keys.RETURN);
        // getWebElementWithExplicitWait(webDriver, 10, By.id("abc"));
        // getWebElementWithExplicitWait(webDriver, 30, By.id("def"));
        // getWebElementWithExplicitWait(webDriver, 40, By.id("ghi"));

    }


    @Test
    @SneakyThrows
    public void testExplicitWaitWithGenericMethod() {
        // Initialize the WebDriver (example uses ChromeDriver)
        webDriver = new ChromeDriver();
        try {
            webDriver.get("https://www.google.com");
            webDriver.manage().window().maximize();

            // Implicit wait (optional, generally avoid mixing implicit and explicit waits)
            webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            // Interacting with elements
            webDriver.findElement(By.xpath("//*[@id=\"APjFqb\"]")).click(); // If needed
            WebElement searchBox = webDriver.findElement(By.name("q"));
            searchBox.sendKeys("Automation");
            searchBox.sendKeys(Keys.RETURN);

            // Using the generic method to wait for an element
            WebElement element = getWebElementWithExplicitWait(webDriver, 60, By.id("abc"));
            if (element != null) {
                System.out.println("Element found: " + element.getText());
            } else {
                System.out.println("Element not found within the timeout period.");
            }
        } finally {
            // Quit the browser
            webDriver.quit();
        }
    }

    /**
     * Generic method to wait for an element with explicit wait.
     *
     * @param driver    The WebDriver instance.
     * @param timeout   The timeout duration in seconds.
     * @param locator   The By locator to find the element.
     * @return The WebElement if found and visible, otherwise null.
     */
    public WebElement getWebElementWithExplicitWait(WebDriver driver, int timeout, By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (org.openqa.selenium.TimeoutException e) {
            System.err.println("Timeout: Element not found - " + locator.toString());
            return null;
        }
    }






    @Test
    @SneakyThrows
    public void testFluentWait() {
        // Navigate to Google and maximize the window
        webDriver.get("https://www.google.com");
        webDriver.manage().window().maximize();
       // webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Perform search on Google
        webDriver.findElement(By.xpath("//*[@id='APjFqb']")).click();
        WebElement searchBox = webDriver.findElement(By.name("q"));
        searchBox.sendKeys("abc");
        searchBox.sendKeys(Keys.RETURN);

        // Wait for the element to be present using WebDriverWait
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
        //WebElement searchInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Search Google or type a URL']")));

        // Optional: Execute JavaScript if needed (uncomment and modify if required)
        // JavascriptExecutor js = (JavascriptExecutor) webDriver;
        // js.executeScript("arguments[0].click();", searchInput);

        // FluentWait to wait for a specific condition
        Wait<WebDriver> fluentWait = new FluentWait<>(webDriver)
                .withTimeout(Duration.ofSeconds(60))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(org.openqa.selenium.NoSuchElementException.class);

        // Wait for the element using FluentWait
              WebElement element2 = fluentWait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                return  driver.findElement(By.xpath("//*[@id=\"rso\"]/div[1]/div/div/div/div/div/div/div/div[1]/div/span/a/h3"));
            }
        });

        // Interact with the element if needed
        // element2.click();

    }
}