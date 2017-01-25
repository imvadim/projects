package by.novikau.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractPage {

    protected WebDriver driver;

    public AbstractPage(WebDriver driver){
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
