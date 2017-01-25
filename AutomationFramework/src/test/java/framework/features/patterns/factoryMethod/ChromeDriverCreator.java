package framework.features.patterns.factoryMethod;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.util.concurrent.TimeUnit;


public class ChromeDriverCreator extends WebDriverCreator{

    @Override
    public WebDriver factoryMethod(){
        System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\drivers\\chromedriver.exe");
//        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//        driver = new ChromeDriver(capabilities);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

    @Override
    public void quit(){
        driver.close();
    }
}
