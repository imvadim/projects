package framework.features.pageObjects.actions;

import framework.features.pageObjects.elements.SignInPageElements;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.io.IOException;

public class SignInPageActions extends SignInPageElements {

    public SignInPageActions(WebDriver driver) {
        super(driver);
    }

    public InboxPageActions loginToGmail(String login, String password){
        highlightElement(loginInput);
        loginInput.sendKeys(login);
        nextBtn.click();
        pwdInput.sendKeys(password);
        signInBtn.click();
        waiter(5).until(ExpectedConditions.titleContains("Inbox"));
        return new InboxPageActions(driver);
    }

    private void highlightElement(WebElement webElement){
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].style.backgroundColor = 'yellow'", webElement);
        makeScreenShot();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void makeScreenShot(){
        File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(src, new File("highlight.png"));
        }

        catch (IOException e)
        {
            System.out.println(e.getMessage());

        }
    }
}