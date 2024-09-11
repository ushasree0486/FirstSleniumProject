package Utils;

import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JsExecutorUtils {
    public static void flash(WebElement webelement, WebDriver webDriver) {
        String bgColor = webelement.getCssValue("backgroundColor");
        //String bgColor=webelement.getCssValue("color");
        System.out.println("Color == " + bgColor);
        for (int i = 0; i < 10; i++) {
            changeColor("#000000", webelement, webDriver);
            changeColor(bgColor, webelement, webDriver);
        }
    }

    public static void changeColor(String color, WebElement webelement, WebDriver webDriver) {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].style.backgroundColor ='" + color + "'", webelement);

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void drawBorder(WebElement webelement, WebDriver webDriver) {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].style.border ='5px solid red'", webelement);

    }

    public static String getTitle(WebDriver webDriver) {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        return js.executeScript("return document.title").toString();
    }

    public static void generateAlert(WebDriver webDriver, String message) {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("alert('" + message + "')");
    }

    public static void refreshPage(WebDriver webDriver) {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("history.go(0)");
    }

    public static void scrollTillElementIsInView(WebElement webelement, WebDriver webDriver) {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].scrollIntoView(true)", webelement);

    }

    public static void scrollTillEndOfDocument(WebDriver webDriver) {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
    }
}
