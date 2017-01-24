package by.epam.vadzimnovikau1.module6.features.pageObjects.elements;

import by.epam.vadzimnovikau1.module6.features.pageObjects.actions.AbstractPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

public class InboxPageElements extends AbstractPage {
	protected String keysPressed = Keys.chord(Keys.CONTROL, Keys.RETURN);

	@FindBy(css = ".T-I.J-J5-Ji.T-I-KE.L3")
	protected WebElement compose;

	@FindBy(css = ".vO")
	protected WebElement to;

	@FindBy(css = ".aoT")
	protected WebElement topic;

	@FindBy(css = ".Am.Al.editable.LW-avf")
	protected WebElement letter;

	@FindBy(css = ".Ha")
	protected WebElement close;

	@FindBy(partialLinkText = "Drafts")
	protected WebElement goDrafts;

	@FindBy(partialLinkText = "Sent")
	protected WebElement goSent;

	@FindBy(css = ".gbii")
	protected WebElement logOutStep1;

	@FindBys({
			@FindBy(linkText = "Sign out")
	})
	protected WebElement logOutStep2;

	public InboxPageElements(WebDriver driver) {
		super(driver);
	}
}