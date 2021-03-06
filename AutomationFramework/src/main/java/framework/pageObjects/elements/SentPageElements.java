package framework.pageObjects.elements;

import framework.pageObjects.actions.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SentPageElements extends BasePage {
    private boolean here = false;

    @FindBy(css = ".y6>span")
    protected List<WebElement> mails;

    public SentPageElements(WebDriver driver) {
        super(driver);
    }
}