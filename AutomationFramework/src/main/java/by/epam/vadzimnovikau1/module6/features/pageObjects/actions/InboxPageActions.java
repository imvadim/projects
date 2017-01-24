package by.epam.vadzimnovikau1.module6.features.pageObjects.actions;

import by.epam.vadzimnovikau1.module6.features.base.Email;
import by.epam.vadzimnovikau1.module6.features.pageObjects.elements.InboxPageElements;
import by.epam.vadzimnovikau1.module6.features.pageObjects.elements.SignInPageElements;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class InboxPageActions extends InboxPageElements {
	private String keysPressed = Keys.chord(Keys.CONTROL, Keys.RETURN);

	public InboxPageActions(WebDriver driver) {
		super(driver);
	}

    public boolean loginIsCorrect(String title)
    {
    	return driver.getTitle().contains(title);
    }

    public InboxPageActions newLetter(){
        compose.click();
        return new InboxPageActions(driver);
	}

	public InboxPageActions fillLetter(Email email){
		compose.click();
		to.sendKeys(email.getAddress());
		topic.sendKeys(email.getSubject());
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
		mineSendKeys(letter, keysPressed);
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