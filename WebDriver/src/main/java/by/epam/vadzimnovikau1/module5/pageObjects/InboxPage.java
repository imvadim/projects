package by.epam.vadzimnovikau1.module5.pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class InboxPage extends AbstractPage {
	private String keysPressed = Keys.chord(Keys.CONTROL, Keys.RETURN);

//	@FindBy(css = ".T-I.J-J5-Ji.T-I-KE.L3")
	@FindBy(xpath = "html/body/div[7]/div[3]/div/div[2]/div[1]/div[1]/div[1]/div[2]/div/div/div[1]/div/div")
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

//	@FindBy(partialLinkText = "Sent")
	@FindBy(xpath = "html/body/div[7]/div[3]/div/div[2]/div[1]/div[1]/div[1]/div[2]/div/div/div[2]/div/div[1]/div[1]/div/div[3]/div/div/div[2]/span/a")
	private WebElement goSent;

	@FindBy(css = ".gbii")
	private WebElement logOutStep1;

	@FindBys({
			@FindBy(linkText = "Sign out")
	})
	private WebElement logOutStep2;

	public InboxPage(WebDriver driver) {
		super(driver);
	}

    public boolean loginIsCorrect(String title)
    {
    	return driver.getTitle().contains(title);
    }

    public InboxPage newLetter(){
        compose.click();
        return new InboxPage(driver);
	}
		
	public InboxPage fillLetter(String address, String subject, String text){
        to.sendKeys(address);
        topic.sendKeys(subject);
        letter.sendKeys(text);
        close.click();
		return new InboxPage(driver);
	}
	
	public DraftsPage toDrafts(){
        goDrafts.click();
		waiter().until(ExpectedConditions.titleContains("Drafts"));
		return new DraftsPage(driver);
	}

	public InboxPage sendEmail(){
		mineSendKeys(letter, keysPressed);
		return new InboxPage(driver);
	}
	
	public SentPage toSent(){
        goSent.click();
		waiter().until(ExpectedConditions.titleContains("Sent"));
		return new SentPage(driver);
	}	
	
	public SignInPage logOut(){
		logOutStep1.click();
		logOutStep2.click();
		return new SignInPage(driver);
	}
}