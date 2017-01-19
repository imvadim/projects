package by.epam.vadzimnovikau1.module6.features.pageObjectsWithoutActionsAndJavaScript;

import by.epam.vadzimnovikau1.module6.features.pageObjects.AbstractPage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class SignInPageOld extends AbstractPage {

    @FindBy(id = "Email")
    private WebElement loginInput;

    @FindBy(id = "next")
    private WebElement nextBtn;

    @FindBy(id = "Passwd")
    private WebElement pwdInput;

    @FindBy (id = "signIn")
    private WebElement signInBtn;

    public SignInPageOld(WebDriver driver) {
        super(driver);
    }

    public InboxPageOld loginToGmail(String login, String password){

        loginInput.sendKeys(login);

        nextBtn.click();

        pwdInput.sendKeys(password);

        signInBtn.click();

        return new InboxPageOld(driver);
    }
}