package framework.test;

import framework.values.TestValues;
import framework.patterns.factoryMethod.WebDriverCreator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    private WebDriver driver;

    @BeforeClass(alwaysRun = true)
    @Parameters("browser")
    protected void setUp(String browser) {
        WebDriverCreator creator = new WebDriverCreator();
        driver = creator.factoryMethod(browser);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get(new TestValues().getGmail_url());
    }

    @AfterClass(alwaysRun = true)
    protected void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected WebDriver getDriver() {
        return this.driver;
    }
}
