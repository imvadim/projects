package framework.pageObjects.elements;

import framework.pageObjects.actions.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class SignInPageElements extends BasePage {

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