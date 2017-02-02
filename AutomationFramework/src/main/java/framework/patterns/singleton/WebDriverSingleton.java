package framework.patterns.singleton;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverSingleton {
    private static volatile WebDriver driver;

    public static WebDriver getInstanceChrome() {
        WebDriver localDriver = driver;
        if (localDriver == null)
            synchronized (WebDriverSingleton.class) {
                localDriver = driver;
                if (localDriver == null)
                    System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\drivers\\chromedriver.exe");
                driver = new ChromeDriver();

            }
        return driver;
    }

    public static WebDriver getInstanceFirefox() {
        WebDriver localDriver = driver;
        if (localDriver == null)
            synchronized (WebDriverSingleton.class) {
                localDriver = driver;
                if (localDriver == null)
                    System.setProperty("webdriver.gecko.driver", "src\\main\\resources\\drivers\\geckodriver.exe");
                driver = new FirefoxDriver();
            }
        return driver;
    }
}