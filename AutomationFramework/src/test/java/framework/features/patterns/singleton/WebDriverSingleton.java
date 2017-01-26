package framework.features.patterns.singleton;

import framework.features.patterns.decorator.Decorator;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverSingleton {

    private static WebDriver driver;

    private WebDriverSingleton(){}

    public static WebDriver getInstance(){
        if(null == driver){
//            ChromeDriverManager.getInstance().setup();
//            System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\drivers\\chromedriver.exe");
//            driver = new ChromeDriver();

//            FirefoxDriverManager.getInstance().setup();
            System.setProperty("webdriver.gecko.driver", "src\\test\\resources\\drivers\\geckodriver.exe");
            driver = new FirefoxDriver();

            // Decorator
            driver = new Decorator(driver);

            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void closeDriver(){
        driver.close();
//        driver = null;
    }

}
