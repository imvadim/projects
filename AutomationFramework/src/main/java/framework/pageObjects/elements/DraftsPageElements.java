package framework.pageObjects.elements;

import framework.pageObjects.actions.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class DraftsPageElements extends AbstractPage {

    @FindBy(css = ".y6>span")
    protected List<WebElement> mails;

    @FindBy(css = "input[name='subjectbox']")
    protected WebElement topic;

    @FindBy(css = ".Am.Al.editable.LW-avf")
    protected WebElement letter;

    public DraftsPageElements(WebDriver driver) {
        super(driver);
    }
}