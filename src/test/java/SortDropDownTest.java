import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class SortDropDownTest {
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
    public void testSortIngDropDown(){
        webDriver.get("C:\\Users\\J Venkata Prasad\\Desktop\\css crashCorse\\my website\\css\\DropDown.html");
        WebElement cars= webDriver.findElement(By.id("cars"));
        Select s= new Select(cars);
        List<WebElement> options=s.getOptions();
        List<String> toBeSortedCarList=new ArrayList<>();
        List<String> originalcarList=new ArrayList<>();
        for (WebElement e:options){
            toBeSortedCarList.add(e.getText());
            originalcarList.add(e.getText());
        }
        System.out.println("Unsorted car list "+originalcarList);
        Collections.sort(toBeSortedCarList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareToIgnoreCase(o2);
            }
        });
        System.out.println("Sorted car list "+toBeSortedCarList);
        System.out.println(toBeSortedCarList.equals(originalcarList));
        Assert.assertEquals(toBeSortedCarList,originalcarList);
        Assert.assertTrue(toBeSortedCarList.equals(originalcarList));//testcase will be failed because they are not equal
    }
}

