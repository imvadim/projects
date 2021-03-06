package framework.patterns.decorator;

import org.openqa.selenium.*;

import java.io.File;
import java.util.List;
import java.util.Set;

public class Decorator implements WebDriver {
    private static JavascriptExecutor js;
    private static File src;
    protected WebDriver driver;

    public Decorator(WebDriver driver) {
        this.driver = driver;
        js = ((JavascriptExecutor) driver);
        src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    }

    public void get(String s) {
        driver.get(s);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public List<WebElement> findElements(By by) {
        return driver.findElements(by);
    }

    public WebElement findElement(By by) {
        System.out.println(by.toString());
        return driver.findElement(by);
    }

    public String getPageSource() {
        return driver.getPageSource();
    }

    public void close() {
        driver.close();
    }

    public void quit() {
        driver.quit();
    }

    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    public String getWindowHandle() {
        return driver.getWindowHandle();
    }

    public TargetLocator switchTo() {
        return driver.switchTo();
    }

    public Navigation navigate() {
        return driver.navigate();
    }

    public Options manage() {
        return driver.manage();
    }

    public static JavascriptExecutor getJs() {
        return js;
    }

    public static File getSrc() {
        return src;
    }
}
