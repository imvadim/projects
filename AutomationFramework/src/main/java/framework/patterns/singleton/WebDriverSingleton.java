package framework.patterns.singleton;

import framework.patterns.decorator.Decorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverSingleton {

    private static WebDriver driver;

    private WebDriverSingleton(){}

    public static WebDriver getInstanceChrome(){
        if(null == driver){
            driver = new ChromeDriver();

            // Decorator
            driver = new Decorator(driver);

        }
        return driver;
    }

    public static WebDriver getInstanceFirefox(){
        if(null == driver){
            driver = new FirefoxDriver();

            // Decorator
            driver = new Decorator(driver);
        }
        return driver;
    }

    public static void closeDriver(){
        driver.quit();
    }

}
