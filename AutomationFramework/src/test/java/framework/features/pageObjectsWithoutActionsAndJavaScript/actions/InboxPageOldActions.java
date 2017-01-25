package framework.features.pageObjectsWithoutActionsAndJavaScript.actions;

import framework.features.pageObjects.actions.DraftsPageActions;
import framework.features.pageObjects.elements.InboxPageElements;
import framework.features.base.Email;
import framework.features.pageObjects.actions.SentPageActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class InboxPageOldActions extends InboxPageElements {

	public InboxPageOldActions(WebDriver driver) {
		super(driver);
	}

    public boolean loginIsCorrect(String title)
    {
    	return driver.getTitle().contains(title);
    }

	public InboxPageOldActions fillLetter(Email email){
		compose.click();
        to.sendKeys(email.getAddress());
        topic.sendKeys(email.getSubject());
        letter.sendKeys(email.getText());
        close.click();
		return new InboxPageOldActions(driver);
	}
	
	public DraftsPageActions toDrafts(){
        goDrafts.click();
		waiter().until(ExpectedConditions.titleContains("Drafts"));
		return new DraftsPageActions(driver);
	}

	public InboxPageOldActions sendEmail(){
		letter.sendKeys(keysPressed);
		return new InboxPageOldActions(driver);
	}
	
	public SentPageActions toSent(){
        goSent.click();
		waiter().until(ExpectedConditions.titleContains("Sent"));
		return new SentPageActions(driver);
	}	
	
	public SignInPageOldActions logOut(){
		logOutStep1.click();
		logOutStep2.click();
		return new SignInPageOldActions(driver);
	}
}