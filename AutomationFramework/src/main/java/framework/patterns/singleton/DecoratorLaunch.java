package framework.patterns.singleton;

import framework.patterns.decorator.Decorator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class DecoratorLaunch {
    private static JavascriptExecutor js;
    private static File src;

    DecoratorLaunch(WebDriver driver) {
        js = ((JavascriptExecutor) driver);
        src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Decorator
//        driver = new Decorator(driver);
    }


}