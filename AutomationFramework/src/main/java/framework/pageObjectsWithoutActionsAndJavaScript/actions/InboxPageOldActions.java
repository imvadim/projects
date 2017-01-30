package framework.pageObjectsWithoutActionsAndJavaScript.actions;

import framework.pageObjects.actions.DraftsPageActions;
import framework.pageObjects.elements.InboxPageElements;
import framework.base.Email;
import framework.pageObjects.actions.SentPageActions;
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
		waiter(5).until(ExpectedConditions.visibilityOf(to));
        to.sendKeys(email.getAddress());
        topic.sendKeys(email.getSubject());
        letter.click();
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