package by.epam.vadzimnovikau1.module6.features.pageObjects.elements;

import by.epam.vadzimnovikau1.module6.features.pageObjects.actions.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class SentPageElements extends AbstractPage {
    private boolean here = false;

    @FindBy(css = ".y6>span")
    protected List<WebElement> mails;

    public SentPageElements(WebDriver driver) {
        super(driver);
    }
}