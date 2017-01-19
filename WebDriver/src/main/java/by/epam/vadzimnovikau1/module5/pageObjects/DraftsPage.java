package by.epam.vadzimnovikau1.module5.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class DraftsPage extends AbstractPage {
    private boolean here = false;
    private String address;

    @FindBy(css = ".y6>span")
    private List<WebElement> mails;

    @FindBy(css = "input[name='subjectbox']")
    private WebElement topic;

    @FindBy(css = ".Am.Al.editable.LW-avf")
    private WebElement letter;

    public DraftsPage(WebDriver driver) {
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