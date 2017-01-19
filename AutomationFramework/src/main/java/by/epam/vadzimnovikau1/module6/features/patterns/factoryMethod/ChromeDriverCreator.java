package by.epam.vadzimnovikau1.module6.features.patterns.factoryMethod;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.util.concurrent.TimeUnit;


public class ChromeDriverCreator extends WebDriverCreator{

    private static final String driverPath = "src\\main\\resources\\drivers\\chromedriver.exe";

    @Override
    public WebDriver factoryMethod(){
        System.setProperty("webdriver.chrome.driver", driverPath);
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        driver = new ChromeDriver(capabilities);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

    @Override
    public void quit(){
        driver.close();
    }
}
