import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestDatePickUp {
    private WebDriver webDriver;
    private WebDriverWait wait;
    @Before
    public void init() {
        WebDriverManager.chromedriver().setup();
        //WebDriver driver = new FirefoxDriver();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(options);
    }
   /* public void testDate() {
        String expectedDate = "10-March-2025";
        String dateArray[] = expectedDate.split("-");
        String eMonth = dateArray[1];
        String eDate = dateArray[0];
        String eYear = dateArray[2];
        webDriver.get("https://www.skyscanner.com");
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        webDriver.manage().timeouts().pageLoadTimeout(3000, TimeUnit.SECONDS);
        webDriver.findElement(By.xpath("//*[@id=\"app-root\"]/div[1]/div/div/main/div[1]/div/div[3]/div/div/div[3]/div/div[1]/div/button")).click();
        String currentMonth = webDriver.findElement(By.xpath("//*[@id=\"app-root\"]/div[1]/div/div/main/div[1]/div/div[3]/div/div/div[3]/div/div[2]/div/div/div[1]/div[2]/div[2]/div[1]/h2")).getText().trim();
        //String currentYear = webDriver.findElement(By.xpath("//*[@id=\"calendar-container\"]/div/div[2]/div/div[1]/div[1]/div/")).getText().trim();
       // webDriver.findElement(By.xpath("//*[@id=\"calendar_title\"]"));
       // WebElement element = webDriver.findElement(By.xpath("//*[@id=\"app-root\"]/div[1]/div/div/main/div[1]/div/div[3]/div/div/div[3]/div/div[2]/div/div/div[1]/div[2]/div[1]/button[2]/span/svg"));
       /* while (!eMonth.equals(currentMonth) || !eYear.equals(currentYear)) {
            element = webDriver.findElement(By.xpath("//*[@id=\"app-root\"]/div[1]/div/div/main/div[1]/div/div[3]/div/div/div[3]/div/div[1]/div/button[1]/span[2]"));
            element.click();
            currentMonth = webDriver.findElement(By.xpath("//*[@id=\"app-root\"]/div[1]/div/div/main/div[1]/div/div[3]/div/div/div[3]/div/div[3]/div/div/div[1]/div[2]/div[2]/div[1]/h2")).getText();
            currentYear = webDriver.findElement(By.xpath("//*[@id=\"app-root\"]/div[1]/div/div/main/div[1]/div/div[3]/div/div/div[3]/div/div[1]/div/button[1]/span[2]")).getText();
        }*/
        //webDriver.findElement(By.xpath("//*[@id=\"app-root\"]/div[1]/div/div/main/div[1]/div/div[3]/div/div/div[3]/div/div[1]/div/button")).click();
        //String Month=webDriver.findElement(By.xpath("//*[@id=\"app-root\"]/div[1]/div/div/main/div[1]/div/div[3]/div/div/div[3]/div/div[2]/div/div/div[1]/div[2]/div[2]/div[1]/h2")).getText().trim();
      /* List<WebElement> dates = webDriver.findElements(By.xpath("//*[@id=\"app-root\"]/div[1]/div/div/main/div[1]/div/div[3]/div/div/div[3]/div/div[3]/div/div/div[1]/div[2]/div[2]/div[2]/div/div[2]/div[3]/div[1]/div/button"));
        System.out.println("Dates " + dates.size());
        for (WebElement e : dates) {
            if (e.getText().trim().equals(eDate)) {
                e.click();
                break;
            }
        }
    }*/
    /*
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
*/
    @Test
    public void testDatePicker() {
        String expectedDate = "10-March-2025";
        String[] dateArray = expectedDate.split("-");
        String eDay = dateArray[0];
        String eMonth = dateArray[1];
        String eYear = dateArray[2];

        webDriver.get("https://www.flydubai.com");
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));


        // Click on the departure date field to open the date picker
        WebElement departureDateField = webDriver.findElement(By.xpath("//input[@type='text']")); // Update XPath as needed
        departureDateField.click();

        // Select the date
        selectDate(eDay, eMonth, eYear);
    }

    private void selectDate(String day, String month, String year) {
        // Navigate to the correct year

        while (true) {
            String currentYear = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"calendar-container\"]/div/div[2]/div/div[1]/div[1]/div/span')]"))).getText().trim();
            if (currentYear.equals(year)) break;
            WebElement nextYearButton = webDriver.findElement(By.xpath("//*[@id=\"Desktop2date-picker\"]/div/div[1]/div[2]/div[2]/div[1]/div/span")); // Update XPath as needed
            nextYearButton.click();
        }

        // Navigate to the correct month
        while (true) {
            String currentMonth = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'calendar-month')]"))).getText().trim();
            if (currentMonth.equals(month)) break;
            WebElement nextMonthButton = webDriver.findElement(By.xpath("//button[contains(@class, 'calendar-next-month')]")); // Update XPath as needed
            nextMonthButton.click();
        }

        // Select the specific day
        List<WebElement> days = webDriver.findElements(By.xpath("//button[contains(@class, 'calendar-day') and not(contains(@class, 'calendar-day-disabled'))]")); // Update XPath as needed
        for (WebElement e : days) {
            if (e.getText().trim().equals(day)) {
                e.click();
                break;
            }
        }
    }
}


