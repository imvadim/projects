package by.epam.vadzimnovikau1.module5.webDriverProperties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

public class StartBrowser {
    protected WebDriver driver;
    private static final String START_URL = "https://accounts.google.com/ServiceLogin?service=mail&continue=https://mail.google.com/mail/&hl=en";
    private static final String driverPath = "src\\main\\resources\\drivers\\chromedriver.exe";

    public StartBrowser(){
        System.setProperty("webdriver.chrome.driver", driverPath);
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        driver = new ChromeDriver(capabilities);
        driver.get(START_URL);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
}
