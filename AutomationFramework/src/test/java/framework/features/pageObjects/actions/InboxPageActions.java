package framework.features.pageObjects.actions;

import framework.features.base.Email;
import framework.features.pageObjects.elements.InboxPageElements;
import framework.features.pageObjects.elements.SignInPageElements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class InboxPageActions extends InboxPageElements {
	public InboxPageActions(WebDriver driver) {
		super(driver);
	}

    public boolean loginIsCorrect(String title)
    {
    	return driver.getTitle().contains(title);
    }

	public InboxPageActions fillLetter(Email email){
		compose.click();
		waiter(5).until(ExpectedConditions.visibilityOf(to));
		to.sendKeys(email.getAddress());
		topic.sendKeys(email.getSubject());
		letter.click();
		letter.sendKeys(email.getText());
		close.click();
		return new InboxPageActions(driver);
	}
	
	public DraftsPageActions toDrafts(){
        goDrafts.click();
		waiter().until(ExpectedConditions.titleContains("Drafts"));
		return new DraftsPageActions(driver);
	}

	public InboxPageActions sendEmail(){
		letter.sendKeys(keysPressed);
		return new InboxPageActions(driver);
	}
	
	public SentPageActions toSent(){
        goSent.click();
		waiter().until(ExpectedConditions.titleContains("Sent"));
		return new SentPageActions(driver);
	}	
	
	public SignInPageElements logOut(){
		logOutStep1.click();
		logOutStep2.click();
		return new SignInPageElements(driver);
	}
}