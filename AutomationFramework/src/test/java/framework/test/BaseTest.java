package framework.test;

import framework.patterns.decorator.Decorator;
import framework.patterns.factoryMethod.WebDriverCreator;
import framework.values.TestValues;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    private WebDriver driver;

    @BeforeClass
    @Parameters("browser")
    protected void setUp(String browser) {
        WebDriverCreator creator = new WebDriverCreator();
        driver = creator.factoryMethod(browser);
        driver = new Decorator(driver);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(new TestValues().getGmail_url());
    }

    @AfterClass
    protected void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected WebDriver getDriver() {
        return this.driver;
    }
}
