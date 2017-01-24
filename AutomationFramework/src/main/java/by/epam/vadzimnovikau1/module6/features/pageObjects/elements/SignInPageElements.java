package by.epam.vadzimnovikau1.module6.features.pageObjects.elements;

import by.epam.vadzimnovikau1.module6.features.pageObjects.actions.AbstractPage;
import by.epam.vadzimnovikau1.module6.features.pageObjects.actions.InboxPageActions;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.io.IOException;

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