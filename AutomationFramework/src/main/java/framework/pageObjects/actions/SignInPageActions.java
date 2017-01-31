package framework.pageObjects.actions;

import framework.pageObjects.elements.SignInPageElements;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.io.IOException;

import static framework.patterns.singleton.WebDriverSingleton.getJs;
import static framework.patterns.singleton.WebDriverSingleton.getSrc;

public class SignInPageActions extends SignInPageElements {

    public SignInPageActions(WebDriver driver) {
        super(driver);
    }

    public InboxPageActions loginToGmail(String login, String password) {
        highlightElement(loginInput);
        loginInput.sendKeys(login);
        nextBtn.click();
        waiter(5).until(ExpectedConditions.visibilityOf(pwdInput));
        pwdInput.sendKeys(password);
        signInBtn.click();
        waiter(5).until(ExpectedConditions.titleContains("Inbox"));
        return new InboxPageActions(driver);
    }

    private void highlightElement(WebElement webElement) {

        getJs().executeScript("arguments[0].style.backgroundColor = 'yellow'", webElement);
        makeScreenShot();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void makeScreenShot() {

        try {
            FileUtils.copyFile(getSrc(), new File("highlight.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());

        }
    }
}