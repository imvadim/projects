package framework.test;

import framework.features.patterns.factoryMethod.WebDriverCreator;
import org.openqa.selenium.WebDriver;

abstract class BaseTest {
    protected WebDriver driver;
    protected WebDriverCreator creator;

    protected abstract void setUp(String url);

    protected abstract void tearDown();
}
