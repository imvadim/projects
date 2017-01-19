package by.epam.vadzimnovikau1.module6.features.pageObjectsWithoutActionsAndJavaScript;

import by.epam.vadzimnovikau1.module6.features.base.Email;
import by.epam.vadzimnovikau1.module6.features.pageObjects.AbstractPage;
import by.epam.vadzimnovikau1.module6.features.pageObjects.DraftsPage;
import by.epam.vadzimnovikau1.module6.features.pageObjects.SentPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class InboxPageOld extends AbstractPage {
	private String keysPressed = Keys.chord(Keys.CONTROL, Keys.RETURN);

	@FindBy(css = ".T-I.J-J5-Ji.T-I-KE.L3")
	private WebElement compose;

	@FindBy(css = ".vO")
	private WebElement to;

	@FindBy(css = ".aoT")
	private WebElement topic;

	@FindBy(css = ".Am.Al.editable.LW-avf")
	private WebElement letter;

	@FindBy(css = ".Ha")
	private WebElement close;

	@FindBy(partialLinkText = "Drafts")
	private WebElement goDrafts;

	@FindBy(partialLinkText = "Sent")
	private WebElement goSent;

	@FindBy(css = ".gbii")
	private WebElement logOutStep1;

	@FindBys({
			@FindBy(linkText = "Sign out")
	})
	private WebElement logOutStep2;

	public InboxPageOld(WebDriver driver) {
		super(driver);
	}

    public boolean loginIsCorrect(String title)
    {
    	return driver.getTitle().contains(title);
    }

	public InboxPageOld fillLetter(Email email){
		compose.click();
        to.sendKeys(email.getAddress());
        topic.sendKeys(email.getSubject());
        letter.sendKeys(email.getText());
        close.click();
		return new InboxPageOld(driver);
	}
	
	public DraftsPage toDrafts(){
        goDrafts.click();
		waiter().until(ExpectedConditions.titleContains("Drafts"));
		return new DraftsPage(driver);
	}

	public InboxPageOld sendEmail(){
		letter.sendKeys(keysPressed);
		return new InboxPageOld(driver);
	}
	
	public SentPage toSent(){
        goSent.click();
		waiter().until(ExpectedConditions.titleContains("Sent"));
		return new SentPage(driver);
	}	
	
	public SignInPageOld logOut(){
		logOutStep1.click();
		logOutStep2.click();
		return new SignInPageOld(driver);
	}
}