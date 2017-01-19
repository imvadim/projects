package by.novikau.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class SignInPage extends AbstractPage {

    @FindBy(id = "Email")
    private WebElement loginInput;

    @FindBy(id = "next")
    private WebElement nextBtn;

    @FindBy(id = "Passwd")
    private WebElement pwdInput;

    @FindBy(id = "signIn")
    private WebElement signInBtn;

    public SignInPage(WebDriver driver) {
        super(driver);
    }

    public InboxPage loginToGmail(String login, String password){

        loginInput.sendKeys(login);

        nextBtn.click();

        pwdInput.sendKeys(password);

        signInBtn.click();

        return new InboxPage(driver);
    }
}