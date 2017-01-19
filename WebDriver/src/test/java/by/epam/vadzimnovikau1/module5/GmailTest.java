package by.epam.vadzimnovikau1.module5;

import by.epam.vadzimnovikau1.module5.pageObjects.InboxPage;
import by.epam.vadzimnovikau1.module5.pageObjects.DraftsPage;
import by.epam.vadzimnovikau1.module5.pageObjects.SignInPage;
import org.testng.Assert;
import org.testng.annotations.*;
import by.epam.vadzimnovikau1.module5.webDriverProperties.StartBrowser;

public class GmailTest extends StartBrowser {

    @Parameters({"username","password","title"})
    @Test(description="Verify that login was successful")
    private void loginToGmailOk(String username, String password, String title) {
        Assert.assertTrue(new SignInPage(super.driver).loginToGmail(username, password).loginIsCorrect(title),
                "Looks you are NOT logged in correctly!");
    }

    @Parameters({"address","subject","text"})
	@Test(dependsOnMethods={"loginToGmailOk"}, description="Verify that the email is displayed in Drafts folder")
    public void inDraftsOK(String address, String subject, String text) {
        Assert.assertTrue(new InboxPage(super.driver).newLetter().fillLetter(address, subject, text).toDrafts().inDrafts(subject));
    }
	
	@Parameters({"address","subject","text"})
	@Test(dependsOnMethods={"inDraftsOK"}, description="Verify that the contents of the email correspond to the information, provided during its creation")
    public void infoInEmailOK(String address, String subject, String text) {
        Assert.assertEquals(new DraftsPage(super.driver).correctEmail(address, subject), address + subject + text);
    }

	@Parameters({"subject"})
	@Test(dependsOnMethods={"infoInEmailOK"}, description="Verify that the email is not displayed in Drafts folder any more")
    public void notInDraftsOK(String subject) {
        Assert.assertFalse(new InboxPage(super.driver).sendEmail().toDrafts().inDrafts(subject));
    }

    @Parameters({"subject"})
    @Test(dependsOnMethods={"notInDraftsOK"}, description="Verify that the email is displayed in Sent folder")
    public void inSentOK(String subject) {
        Assert.assertTrue(new InboxPage(super.driver).toSent().inSent(subject));
        new InboxPage(super.driver).logOut();
    }

    @AfterSuite
    public void tearDown() {
        super.driver.quit();
    }
}