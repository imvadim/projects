package by.novikau.steps;

import by.novikau.WebDriverSingleton;
import by.novikau.pages.InboxPage;
import by.novikau.pages.SignInPage;
import org.jbehave.core.annotations.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class DefinitionSteps {
    private WebDriver driver;

    private static final String GMAIL_URL = "https://accounts.google.com/ServiceLogin?service=mail&continue=https://mail.google.com/mail/&hl=en";

    private InboxPage inbox;

    @BeforeStory
    public void setUp(){
        this.driver = WebDriverSingleton.getInstance();
    }

    @AfterStory
    public void tearDown(){
        WebDriverSingleton.closeDriver();
    }

    @Given("Open \"Gmail.com\" login page")
    public void givenOpenGmailcomPage() {
        driver.get(GMAIL_URL);
    }

    @When("Enter $username as username and $password as password")
    public void whenEnterLoginAndPassword(String username, String password) {
        inbox = new SignInPage(driver).loginToGmail(username, password);
    }

    @Then("Page containing $Inbox in title should be opened")
    public void thenPageContainingInboxInTitleShouldBeOpened(String title) {
        Assert.assertTrue(inbox.loginIsCorrect(title));
    }
}
