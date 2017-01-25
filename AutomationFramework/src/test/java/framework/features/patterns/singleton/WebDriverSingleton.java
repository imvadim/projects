package framework.features.patterns.singleton;

import framework.features.patterns.decorator.Decorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

public class WebDriverSingleton {

    private static WebDriver driver;
    private static final String driverPath = "src\\test\\resources\\drivers\\chromedriver.exe";

    private WebDriverSingleton(){}

    public static WebDriver getInstance(){
        if(null == driver){
            System.setProperty("webdriver.chrome.driver", driverPath);
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            driver = new ChromeDriver(capabilities);

            // Decorator
            driver = new Decorator(driver);

            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void closeDriver(){
        driver.quit();
        driver = null;
    }

}
