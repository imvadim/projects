package by.epam.vadzimnovikau1.module5.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {

    protected WebDriver driver;

    public AbstractPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public WebDriverWait waiter(){
        return waiter(1);
    }

    public WebDriverWait waiter(int time){
        return new WebDriverWait(driver, time);
    }

    public void mineSendKeys(WebElement element, String keysPressed){
        new Actions(driver).sendKeys(element, keysPressed).build().perform();
    }
}
