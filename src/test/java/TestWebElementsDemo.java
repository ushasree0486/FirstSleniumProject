import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class TestWebElementsDemo {
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
    @SneakyThrows
    public void testCheckBox() {
        webDriver.get("C:\\Users\\J Venkata Prasad\\Desktop\\css crashCorse\\my website\\css\\javasc.html");
        WebElement checkbox_All = webDriver.findElement(By.xpath("/html/body/input[5]"));
        if (checkbox_All.isDisplayed() && checkbox_All.isEnabled() && !checkbox_All.isSelected()) {
            checkbox_All.click();
        }
    }

    @Test
    public void testRadioButton() {
        webDriver.get("C:\\Users\\J Venkata Prasad\\Desktop\\css crashCorse\\my website\\css\\checkboxradio.html");
        WebElement radioTestingButton = webDriver.findElement(By.xpath("//input[@type='Radio' and @value='Testing']"));
        if (!radioTestingButton.isSelected()) {
            radioTestingButton.click();
        }
        /*WebElement radioJavaButton = webDriver.findElement(By.xpath("//input[@type='Radio' and @value='Java']"));
        if (!radioJavaButton.isEnabled()) {
           // System.out.println("java radio button is disabled");
           Assert.assertTrue(!radioJavaButton.isEnabled());
      }*/
        WebElement perlCheckBox = webDriver.findElement(By.xpath("//input[@type='checkbox' and @value='Perl']"));
        String status = perlCheckBox.isEnabled() ? "Enabled" : "Disabled";
        // System.out.println("perl Check Box is " +status);
        //Assert.assertTrue(!perlCheckBox.isEnabled());
        Assert.assertFalse(perlCheckBox.isEnabled());
    }

    @Test
    public void testDropDown() {
        webDriver.get("https://www.techlistic.com/p/selenium-practice-form.html");
        webDriver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("Ushha");
        webDriver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("Jadapalli");
        // WebElement subUnit = webDriver.findElement(By.xpath("//span[contains(text(),'Continents')]"));
        WebElement seleniumCommands = webDriver.findElement(By.id("selenium_commands"));
        Select seleniumCommandsDropDown = new Select(seleniumCommands);
        //Assert.assertEquals("Browser Commands",seleniumCommandsDropDown.getFirstSelectedOption().getText());
        //seleniumCommandsDropDown.selectByVisibleText("Switch Commands");
        // seleniumCommandsDropDown.selectByIndex(4);
        List<WebElement> options = seleniumCommandsDropDown.getOptions();
        for (WebElement we : options) {
            if (we.getText().equals("Wait Commands")) {
                we.click();
                break;
            }
        }
    }

    @Test
    public void testDropDownJobSeve() {
        webDriver.get("https://jobserve.com");
        //https://jobserve.com/in/en/Job-Search
        webDriver.findElement(By.id("txtKey")).sendKeys("selenium sponsership");
        webDriver.findElement(By.id("txtLoc")).sendKeys("U.K");
        WebElement selRad = webDriver.findElement(By.id("selRad"));
        Select selRadDropDown = new Select(selRad);
        Assert.assertEquals("Within 50 kms", selRadDropDown.getFirstSelectedOption().getText());
        //selRadDropDown.selectByVisibleText("Within 75 kms");
        //selRadDropDown.selectByValue("25");
        //selRadDropDown.selectByIndex(1);
        List<WebElement> options = selRadDropDown.getOptions();
        for (WebElement we : options) {
            if (we.getText().equals("Within 75 kms")) {
                we.click();
                break;
            }
        }
        WebElement selJType = webDriver.findElement(By.id("selJType"));
        Select selJTypeDropDown = new Select(selJType);
        //selJTypeDropDown.selectByVisibleText("Permanent");
        //selJTypeDropDown.selectByIndex(2);
        //selJTypeDropDown.selectByValue("3");
        List<WebElement> options1 = selJTypeDropDown.getOptions();
        for (WebElement we : options1) {
            if (we.getText().equals("Contract")) {
                we.click();
                break;
            }
        }
        webDriver.findElement(By.id("btnSearch")).click();

    }

    @Test
    public void testDropDownRadionOnJobserve() {
        webDriver.get("https://jobserve.com");
        String location = webDriver.findElement(By.xpath("//*[@id=\"txtLoc\"]")).getText();
        WebElement distance = webDriver.findElement(By.id("selRad"));
        Select distanceSelection = new Select(distance);
        //Assert.assertEquals("Within 50 kms", distanceSelection.getFirstSelectedOption().getText());
        distanceSelection.selectByVisibleText("Within 15 kms");
        Select adAge = new Select(webDriver.findElement(By.id("selAge")));
        adAge.selectByIndex(5);
        Select jobType = new Select(webDriver.findElement(By.id("selJType")));
        adAge.selectByValue("2");
        webDriver.findElement(By.id("txtKey")).sendKeys("QATest Engineer");
        webDriver.findElement(By.id("btnSearch")).click();
        String text=webDriver.findElement(By.xpath("//h4[@class='cutout2 cct2 jobshead']")).getText();
        System.out.println(text);
        Assert.assertTrue(text.contains("jobs for QATest Engineer"+location));
    }
    @Test
    public void testDropDownRadionOnJobserve2() {
        webDriver.get("https://jobserve.com");
        String location = webDriver.findElement(By.xpath("//*[@id=\"txtLoc\"]")).getText();
        WebElement distance = webDriver.findElement(By.id("selRad"));
        Select distanceSelection = new Select(distance);
        //Assert.assertEquals("Within 50 kms", distanceSelection.getFirstSelectedOption().getText());
        selectOptionForDropDown(distance,"Within 15 kms");
        WebElement adAge = webDriver.findElement(By.id("selAge"));
        selectOptionForDropDown(adAge,"With in 3 days");
        WebElement jobType = webDriver.findElement(By.id("selJType"));
        selectOptionForDropDown(jobType,"Contract");
        webDriver.findElement(By.id("txtKey")).sendKeys("QATest Engineer");
        webDriver.findElement(By.id("btnSearch")).click();
        String text=webDriver.findElement(By.xpath("//h4[@class='cutout2 cct2 jobshead']")).getText();
        System.out.println(text);
        Assert.assertTrue(text.contains("jobs for QATest Engineer"+location));
    }
    private void selectOptionForDropDown(WebElement webElement,String valueToSearch){
        Select select=new Select(webElement);
        List<WebElement>options=select.getOptions();
        for (WebElement element:options){
            if(element.getText().contains(valueToSearch)){
                element.click();
                break;
            }
        }
    }

}