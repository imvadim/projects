package framework.test;

import framework.patterns.factoryMethod.WebDriverCreator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    private String browser = "chrome";
    private String url = "https://accounts.google.com/ServiceLogin?service=mail&#38;continue=https://mail.google.com/mail/&#38;hl=en";

    private WebDriver driver;
    private WebDriverCreator creator = new WebDriverCreator();

    @BeforeSuite(groups = "factory", description = "Start browser")
    public void setUp() {
        driver = creator.factoryMethod(browser);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(url);
    }

    @AfterSuite(groups = "factory")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}
