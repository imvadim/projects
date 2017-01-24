package by.epam.vadzimnovikau1.module6.features.pageObjectsWithoutActionsAndJavaScript.actions;

import by.epam.vadzimnovikau1.module6.features.pageObjects.elements.SignInPageElements;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SignInPageOldActions extends SignInPageElements {

    public SignInPageOldActions(WebDriver driver) {
        super(driver);
    }

    public InboxPageOldActions loginToGmail(String login, String password){
        loginInput.sendKeys(login);
        nextBtn.click();
        pwdInput.sendKeys(password);
        signInBtn.click();
        waiter(5).until(ExpectedConditions.titleContains("Inbox"));
        return new InboxPageOldActions(driver);
    }
}