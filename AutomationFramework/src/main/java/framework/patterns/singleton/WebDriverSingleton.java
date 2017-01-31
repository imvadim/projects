package framework.patterns.singleton;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverSingleton {
    private static WebDriver driver;

    private WebDriverSingleton() {
    }

    public static WebDriver getInstanceChrome() {
        if (null == driver) {
            System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\drivers\\chromedriver.exe");
            driver = new ChromeDriver();
        }
        return driver;
    }

    public static WebDriver getInstanceFirefox() {
        if (null == driver) {
            System.setProperty("webdriver.gecko.driver", "src\\main\\resources\\drivers\\geckodriver.exe");
            driver = new FirefoxDriver();
        }
        return driver;
    }
}
