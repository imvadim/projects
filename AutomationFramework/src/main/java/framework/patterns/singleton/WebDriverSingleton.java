package framework.patterns.singleton;

import framework.patterns.decorator.Decorator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;

public class WebDriverSingleton {
    private static WebDriver driver;
    private static JavascriptExecutor js;
    private static File src;

    private WebDriverSingleton() {
    }

    public static WebDriver getInstanceChrome() {
        if (null == driver) {
            System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\drivers\\chromedriver.exe");
            driver = new ChromeDriver();

            js = ((JavascriptExecutor) driver);
            src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Decorator
            driver = new Decorator(driver);
        }
        return driver;
    }

    public static WebDriver getInstanceFirefox() {
        if (null == driver) {
            System.setProperty("webdriver.gecko.driver", "src\\main\\resources\\drivers\\geckodriver.exe");
            driver = new FirefoxDriver();

            js = ((JavascriptExecutor) driver);
            src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Decorator
            driver = new Decorator(driver);
        }
        return driver;
    }

    public static JavascriptExecutor getJs() {
        return js;
    }

    public static File getSrc() {
        return src;
    }
}
