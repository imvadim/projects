package framework.pageObjects.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    protected WebDriverWait waiter(){
        return waiter(1);
    }

    protected WebDriverWait waiter(int time){
        return new WebDriverWait(driver, time);
    }
}
