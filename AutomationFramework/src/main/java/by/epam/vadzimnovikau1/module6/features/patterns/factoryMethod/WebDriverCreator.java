package by.epam.vadzimnovikau1.module6.features.patterns.factoryMethod;

import org.openqa.selenium.WebDriver;

public abstract class WebDriverCreator {

    protected WebDriver driver;

    public abstract WebDriver factoryMethod();

    public abstract void quit();
}
