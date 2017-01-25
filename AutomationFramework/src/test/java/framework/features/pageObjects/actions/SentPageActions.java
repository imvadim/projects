package framework.features.pageObjects.actions;

import framework.features.pageObjects.elements.SentPageElements;
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
        try{
            waiter().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".TC")));
        }catch (Exception e){
            for(WebElement emailsub : mails){
                if(emailsub.getText().equals(subject)){
                    this.here = true;
                    break;
                }
            }
        }
        return this.here;
    }
}