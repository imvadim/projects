package by.epam.vadzimnovikau1.module6.features.pageObjects;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.io.IOException;

public class SignInPage extends AbstractPage {

    @FindBy(id = "Email")
    private WebElement loginInput;

    @FindBy(id = "next")
    private WebElement nextBtn;

    @FindBy(id = "Passwd")
    private WebElement pwdInput;

    @FindBy (id = "signIn")
    private WebElement signInBtn;

    public SignInPage(WebDriver driver) {
        super(driver);
    }

    public InboxPage loginToGmail(String login, String password){
        highlightElement(loginInput);
        loginInput.sendKeys(login);
        nextBtn.click();
        pwdInput.sendKeys(password);
        signInBtn.click();
        waiter(5).until(ExpectedConditions.titleContains("Inbox"));
        return new InboxPage(driver);
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