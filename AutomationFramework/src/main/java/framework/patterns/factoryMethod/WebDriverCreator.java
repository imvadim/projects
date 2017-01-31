package framework.patterns.factoryMethod;

import org.openqa.selenium.WebDriver;

import static framework.patterns.singleton.WebDriverSingleton.getInstanceChrome;
import static framework.patterns.singleton.WebDriverSingleton.getInstanceFirefox;

public class WebDriverCreator {
    public WebDriver factoryMethod(String browser) {
        if (browser.equals("chrome")) {
            return getInstanceChrome();
        } else {
            return getInstanceFirefox();
        }
    }
}
