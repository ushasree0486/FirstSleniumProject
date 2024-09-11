import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Properties;

public class DemoBrokenLinksText extends BaseSetUp {
    static Logger logger = Logger.getLogger(DemoBrokenLinksText.class);
    private WebDriver webdriver;
    private Properties properties;

    @Before
    public void init() {
        if (webdriver == null)
            super.init();
        webdriver = getWebDriver();
        properties = getObjRepo();
    }

    @Test
    public void test() {
        webdriver.get("https://demoqa.com/broken");
        // webdriver.get("https://www.bbc.com/broken");
        //storing the links in a list and traversing through the links
        List<WebElement> links = webdriver.findElements(By.tagName("a"));
        //this line will print the number of links and the count of links
        System.out.println("No of links are " + links.size());
        //checking the links fetched
        /*for(int i=0;i<links.size();i++){
            WebElement E1=links.get(i);
            String url=E1.getAttribute("herf");
            verifyLinks(url);*/

        for (WebElement we : links) {
            String href = we.getAttribute("href");
            verifyLinks(href);
        }
    }

    public static void verifyLinks(String linkUrl) {
        try {
            if (linkUrl != null) {
                URL url = new URL(linkUrl);
                //now we will be creating url connection and getting the response code
                HttpURLConnection httpURLConnect = (HttpURLConnection) url.openConnection();
                httpURLConnect.setConnectTimeout(5000);
                httpURLConnect.connect();
                if (httpURLConnect.getResponseCode() >= 400) {
                    System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage() + "is a broken link");
                } else {
                    System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage());
                }
            }
            //fetching and printing the response code obtained
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

