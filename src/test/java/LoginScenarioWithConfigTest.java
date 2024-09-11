import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.Properties;

public class LoginScenarioWithConfigTest extends BaseSetUp {
    private Logger log = Logger.getLogger(Log.class.getName());
        private WebDriver webDriver;
        private Properties properties;
        @Before
        public void setup() {
            webDriver = getWebDriver();
            properties = getObjRepo();
            log.info("child class before called");
        }
        @SneakyThrows
        @Test
        public void testSuccessfulLogin() {
            log.info("...Starting testSuccessfull login... ");
            webDriver.get(properties.get("url").toString());
            WebElement uName = webDriver.findElement(By.xpath(properties.get("username").toString()));
            uName.sendKeys(properties.get("uname_val").toString());
            WebElement uPassWord = webDriver.findElement(By.xpath(properties.get("password").toString()));
            uPassWord.sendKeys(properties.get("upass_val").toString());
            WebElement loginButton = webDriver.findElement(By.xpath(properties.get("login_btn").toString()));
            loginButton.click();
            WebElement element = webDriver.findElement(By.xpath(properties.get("product_label").toString()));
            Assert.assertTrue(element.isDisplayed());
            log.info("...ending testSuccesful login executed");
        }

    }

