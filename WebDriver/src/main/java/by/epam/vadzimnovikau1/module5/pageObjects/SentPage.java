package by.epam.vadzimnovikau1.module5.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class SentPage extends AbstractPage {
    private boolean here = false;

    @FindBy(css = ".y6>span")
    private List<WebElement> mails;

    public SentPage(WebDriver driver) {
        super(driver);
    }

    public boolean inSent(String subject){
        waiter().until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".y6>span")));

        for(WebElement emailsub : mails){
            if(emailsub.getText().equals(subject)){
                here = true;
                break;
            }
        }

        return this.here;
    }
}