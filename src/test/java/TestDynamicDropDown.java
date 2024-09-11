import com.google.common.base.Equivalence;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestDynamicDropDown extends BaseSetUp {
    private WebDriver webDriver;
    private Properties objectRepo;

    @Before
    public void init() {
        if (webDriver == null)
            super.init();
        webDriver = getWebDriver();
        objectRepo = getObjRepo();
    }

    @Test
    @SneakyThrows
    public void testPopulateSourceCity() {
        webDriver.get("https://www.skyscanner.com");
        webDriver.manage().timeouts().pageLoadTimeout(2000, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
        populateSourceCity();
    }

    private void populateSourceCity() throws InterruptedException {
        WebElement element = webDriver.findElement(By.xpath("//span[contains(text(),'From')]"));
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
       // js.executeScript("argument[0].click()",element);
        webDriver.findElement(By.xpath("//input[@id='originInput-input']")).sendKeys(objectRepo.get("SEARCH_TEXT").toString());
        Thread.sleep(3000);
        List<WebElement> searchResults = webDriver.findElements(By.xpath("//ul[@id='originInput-menu']"));
        for (WebElement we : searchResults) {
            System.out.println(we.getText());
            if (we.getText().contains(objectRepo.get("SEARCH_SELECTION").toString())) {
                try {
                    we.click();
                    break;
                } catch (WebDriverException ex) {
                    JavascriptExecutor js1 = (JavascriptExecutor) webDriver;
                    js1.executeScript("argument[0].click()", we);
                }
            }
        }
    }
}
