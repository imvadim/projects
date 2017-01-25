package framework.features.pageObjects.elements;

import framework.features.pageObjects.actions.AbstractPage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class SignInPageElements extends AbstractPage {

    @FindBy(id = "Email")
    protected WebElement loginInput;

    @FindBy(id = "next")
    protected WebElement nextBtn;

    @FindBy(id = "Passwd")
    protected WebElement pwdInput;

    @FindBy (id = "signIn")
    protected WebElement signInBtn;

    public SignInPageElements(WebDriver driver) {
        super(driver);
    }
}