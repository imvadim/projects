package by.epam.vadzimnovikau1.module6.features.pageObjects.actions;

import by.epam.vadzimnovikau1.module6.features.pageObjects.elements.DraftsPageElements;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DraftsPageActions extends DraftsPageElements {
    private boolean here = false;
    private String address;

    public DraftsPageActions(WebDriver driver) {
        super(driver);
    }

    public boolean inDrafts(String subject){

        try{
            waiter().until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".TC")));
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

    public String correctEmail(String address, String subject){

        for(WebElement emailsub : mails){
            if(emailsub.getText().equals(subject)){
                emailsub.click();
                break;
            }
        }

        try {
            driver.findElement(By.cssSelector("span[email='" + address + "']"));
            this.address = address;
        }	catch (NoSuchElementException e){
            this.address = "Wrong email";
        }

        return this.address + topic.getAttribute("value") + letter.getText();
    }
}