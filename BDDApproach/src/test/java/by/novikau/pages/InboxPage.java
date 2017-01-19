package by.novikau.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InboxPage extends AbstractPage {

	public InboxPage(WebDriver driver) {
		super(driver);
	}

    public boolean loginIsCorrect(String title)
    {
    	return driver.getTitle().contains(title);
    }
}