import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

public class TestXpathAxesLocator {
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
    public void testXPath() {
        webDriver.get("https://money.rediff.com/gainers/bse/daily/groupa");
        webDriver.manage().window().maximize();
        //using self :getting details of current node
        String text = webDriver.findElement(By.xpath("//a[contains(text(),'Alembic Pharmaceutic')]/self::a")).getText();
        System.out.println(text);

        //using parent :selecting parent of current node
        String text2 = webDriver.findElement(By.xpath("//a[contains(text(),'Alembic Pharmaceutic')]/self::a/parent::td")).getText();
        System.out.println(text2);

        //using ancestor finf child
        final List<WebElement> elements=webDriver.findElements(By.xpath("//a[contains(text(),'Alembic Pharmaceutic')]/self::a/ancestor::tr/child::td"));
        System.out.println("child elements are "+ elements.size());
        Assert.assertEquals(6,elements.size());

        //finding ancestor (parent&grand parents of current node)
        /*final WebElement ancestorNode=webDriver.findElement(By.xpath("//a[contains(text(),'Alembic Pharmaceutic')]/ancestor::*"));
        System.out.println("Ancester Node "+ ancestorNode.getText());*/

        //find descendants(children and grand children
        final List<WebElement> descendantElements=webDriver.findElements(By.xpath("//a[contains(text(),'Alembic Pharmaceutic')]/self::a/ancestor::tr/descendant::*"));
        System.out.println("descendant elements are "+ descendantElements.size());

        //following nodes select everything after the closing tag of current node
        final List<WebElement> followingElements=webDriver.findElements(By.xpath("//a[contains(text(),'Alembic Pharmaceutic')]/self::a/following::*"));
        System.out.println("following elements are "+ followingElements.size());

        //following elements ancestor of particular type
        final List<WebElement> followingElementsOfAncestorOfGivenType=webDriver.findElements(By.xpath("//a[contains(text(),'Alembic Pharmaceutic')]/ancestor::tr/following::tr"));
        System.out.println("following ancestor elements are "+ followingElementsOfAncestorOfGivenType.size());

        //following siblings:select siblings of following nodes
        final List<WebElement> followingSiblings=webDriver.findElements(By.xpath("//a[contains(text(),'Alembic Pharmaceutic')]/ancestor::tr/following-sibling::tr"));
        System.out.println("following siblings are "+ followingSiblings.size());

        //preceding node:select all nodes before current node in document
        final List<WebElement> precedingElements=webDriver.findElements(By.xpath("//a[contains(text(),'Alembic Pharmaceutic')]/preceding::*"));
        System.out.println("preceding elements are "+ precedingElements.size());

        //preceding sibling:select all siblings before the current node
        final List<WebElement> precedingSiblings=webDriver.findElements(By.xpath("//a[contains(text(),'Alembic Pharmaceutic')]/ancestor::tr/preceding-sibling::*"));
        System.out.println("preceding siblings are "+ precedingSiblings.size());


    }
}
