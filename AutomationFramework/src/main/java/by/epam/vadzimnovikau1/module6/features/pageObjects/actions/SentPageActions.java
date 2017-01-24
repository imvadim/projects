package by.epam.vadzimnovikau1.module6.features.pageObjects.actions;

import by.epam.vadzimnovikau1.module6.features.pageObjects.elements.SentPageElements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SentPageActions extends SentPageElements {
    private boolean here = false;

    public SentPageActions(WebDriver driver) {
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