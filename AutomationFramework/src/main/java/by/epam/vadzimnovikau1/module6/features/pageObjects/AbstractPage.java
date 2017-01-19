package by.epam.vadzimnovikau1.module6.features.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractPage {

    protected WebDriver driver;

    public AbstractPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public WebDriverWait waiter(){
        return new WebDriverWait(driver, 2);
    }

    public void rightClick(WebElement element){
        new Actions(driver).contextClick(element).build().perform();
        new Actions(driver).release(element).build().perform();
    }

    public void mineSendKeys(WebElement element, String keysPressed){
        new Actions(driver).sendKeys(element, keysPressed).build().perform();
    }
}
