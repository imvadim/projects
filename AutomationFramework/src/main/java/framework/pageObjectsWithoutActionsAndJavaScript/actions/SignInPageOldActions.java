package framework.pageObjectsWithoutActionsAndJavaScript.actions;

import framework.pageObjects.elements.SignInPageElements;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SignInPageOldActions extends SignInPageElements {

    public SignInPageOldActions(WebDriver driver) {
        super(driver);
    }

    public InboxPageOldActions loginToGmail(String login, String password){
//        waiter(5).until(ExpectedConditions.visibilityOf(nextBtn));
        loginInput.sendKeys(login);
        nextBtn.click();
        waiter(5).until(ExpectedConditions.visibilityOf(pwdInput));
        pwdInput.sendKeys(password);
        signInBtn.click();
        waiter(5).until(ExpectedConditions.titleContains("Inbox"));
        return new InboxPageOldActions(driver);
    }
}