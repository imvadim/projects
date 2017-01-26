package framework.test;

import framework.features.base.Email;
import framework.features.pageObjects.actions.DraftsPageActions;
import framework.features.pageObjects.actions.InboxPageActions;
import framework.features.pageObjectsWithoutActionsAndJavaScript.actions.InboxPageOldActions;
import framework.features.pageObjectsWithoutActionsAndJavaScript.actions.SignInPageOldActions;
import framework.features.patterns.singleton.WebDriverSingleton;
import framework.features.patterns.staticFactory.StaticFactory;
import org.testng.Assert;
import org.testng.annotations.*;

public class GmailTestDecoratorWithSingleton extends BaseTest {

    @Parameters({"gmail_url"})
    @BeforeGroups(groups = "singleton", description = "Start browser")
    protected void setUp(String url) {
        driver = WebDriverSingleton.getInstanceFirefox(); //getInstanceChrome()
        driver.get(url);
    }

    @Parameters({"username","password","title"})
    @Test(description="Verify that login was successful", groups = "singleton")
    private void loginToGmailSingletonOk(String username, String password, String title) {
        Assert.assertTrue(new SignInPageOldActions(driver).loginToGmail(username, password).loginIsCorrect(title),
                "Looks you are NOT logged in correctly!");
    }

//    @Test(dependsOnMethods={"loginToGmailSingletonOk"}, description="Verify that THE DEFAULT email is displayed in Drafts folder")
//    private void defaultEmailInDraftsOK() {
//        Email email = new StaticFactory(driver).createDefaultEmail();
//        Assert.assertTrue(new InboxPageOldActions(driver).fillLetter(email).toDrafts().inDrafts(email.getSubject()));
//    }

    @Parameters({"subject","text"})
	@Test(dependsOnMethods={"loginToGmailSingletonOk"}, description="Verify that email is displayed in Drafts folder")
    private void secondEmailInDraftsOK(String subject, String text) {
        Email email = new StaticFactory(driver).createDefaultEmail().withSubject(subject).withText(text);
        Assert.assertTrue(new InboxPageOldActions(driver).fillLetter(email).toDrafts().inDrafts(email.getSubject()));
    }
	
	@Parameters({"address","subject","text"})
	@Test(dependsOnMethods={"secondEmailInDraftsOK"}, description="Verify that the contents of the email correspond to the information, provided during its creation")
    private void infoInEmailOK(String address, String subject, String text) {
        Assert.assertEquals(new DraftsPageActions(driver).correctEmail(address, subject), address + subject + text);
    }

	@Parameters({"subject"})
	@Test(dependsOnMethods={"infoInEmailOK"}, description="Verify that the email is not displayed in Drafts folder any more", groups = "checkpointNotInDraftsOK")
    public void notInDraftsOK(String subject) {
        Assert.assertFalse(new InboxPageOldActions(driver).sendEmail().toDrafts().inDrafts(subject));
    }

    @Parameters({"subject"})
    @Test(groups = "singleton", dependsOnMethods={"notInDraftsOK"}, description="Verify that the email is displayed in Sent folder")
    private void inSentOK(String subject) {
        Assert.assertTrue(new InboxPageOldActions(driver).toSent().inSent(subject));
        new InboxPageActions(driver).logOut();
    }

    @AfterGroups(groups = "singleton")
    protected void tearDown() {
        if (driver != null) {
            WebDriverSingleton.closeDriver();
        }
    }
}