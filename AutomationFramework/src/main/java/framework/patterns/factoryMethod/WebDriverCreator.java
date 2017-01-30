package framework.patterns.factoryMethod;

import org.openqa.selenium.WebDriver;

import static framework.patterns.singleton.WebDriverSingleton.getInstanceChrome;
import static framework.patterns.singleton.WebDriverSingleton.getInstanceFirefox;

public class WebDriverCreator {

//    protected WebDriver driver;

    public WebDriver factoryMethod(String browser) {

//        switch (browser) {
//            case "chrome":
//                return getInstanceChrome();
//            break;
//            case "firefox":
//                return getInstanceFirefox();
//            break;
//            default:
//                return getInstanceChrome();
//            break;
//        }

        if(browser.equals("chrome")){
            return getInstanceChrome();
        }else {
            return getInstanceFirefox();
        }
    }

//    public abstract void quit();
}
