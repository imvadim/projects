package by.epam.vadzimnovikau1.module6;

import by.epam.vadzimnovikau1.module6.features.base.Email;
import by.epam.vadzimnovikau1.module6.features.pageObjects.DraftsPage;
import by.epam.vadzimnovikau1.module6.features.pageObjects.InboxPage;
import by.epam.vadzimnovikau1.module6.features.pageObjectsWithoutActionsAndJavaScript.InboxPageOld;
import by.epam.vadzimnovikau1.module6.features.pageObjectsWithoutActionsAndJavaScript.SignInPageOld;
import by.epam.vadzimnovikau1.module6.features.patterns.singleton.WebDriverSingleton;
import by.epam.vadzimnovikau1.module6.features.patterns.staticFactory.StaticFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class GmailTestDecoratorWithSingleton {
    private WebDriver driver;
    private static final String GITHUB_URL = "https://github.com/imvadim/projects";
    private static final String GMAIL_URL = "https://accounts.google.com/ServiceLogin?service=mail&continue=https://mail.google.com/mail/&hl=en";

    @Test(description = "Verify that Singleton works correctly")
    private void testGithub() {
        this.driver = WebDriverSingleton.getInstance();
        driver.get(GITHUB_URL);
    }

    @Parameters({"username","password","title"})
    @Test(dependsOnMethods={"testGithub"}, description="Verify that login was successful")
    private void loginToGmailOk(String username, String password, String title) {
        driver.get(GMAIL_URL);
        Assert.assertTrue(new SignInPageOld(driver).loginToGmail(username, password).loginIsCorrect(title),
                "Looks you are NOT logged in correctly!");
    }

    @Test(dependsOnMethods={"loginToGmailOk"}, description="Verify that THE DEFAULT email is displayed in Drafts folder")
    public void defaultEmailInDraftsOK() {
        Email email = new StaticFactory(driver).createDefaultEmail();
        Assert.assertTrue(new InboxPageOld(driver).fillLetter(email).toDrafts().inDrafts(email.getSubject()));
    }

    @Parameters({"subject","text"})
	@Test(dependsOnMethods={"defaultEmailInDraftsOK"}, description="Verify that THE SECOND email is displayed in Drafts folder")
    public void secondEmailInDraftsOK(String subject, String text) {
        Email email = new StaticFactory(driver).createDefaultEmail().withSubject(subject).withText(text);
        Assert.assertTrue(new InboxPageOld(driver).fillLetter(email).toDrafts().inDrafts(email.getSubject()));
    }
	
	@Parameters({"address","subject","text"})
	@Test(dependsOnMethods={"secondEmailInDraftsOK"}, description="Verify that the contents of the email correspond to the information, provided during its creation")
    public void infoInEmailOK(String address, String subject, String text) {
        Assert.assertEquals(new DraftsPage(driver).correctEmail(address, subject), address + subject + text);
    }

	@Parameters({"subject"})
	@Test(dependsOnMethods={"infoInEmailOK"}, description="Verify that the email is not displayed in Drafts folder any more")
    public void notInDraftsOK(String subject) {
        Assert.assertFalse(new InboxPageOld(driver).sendEmail().toDrafts().inDrafts(subject));
    }

    @Parameters({"subject"})
    @Test(dependsOnMethods={"notInDraftsOK"}, description="Verify that the email is displayed in Sent folder")
    public void inSentOK(String subject) {
        Assert.assertTrue(new InboxPageOld(driver).toSent().inSent(subject));
        new InboxPage(driver).logOut();
    }

    @AfterSuite
    public void tearDown() {
        WebDriverSingleton.closeDriver();
    }
}