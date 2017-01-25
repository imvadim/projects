package framework.test;

import framework.features.base.Email;
import framework.features.pageObjects.actions.DraftsPageActions;
import framework.features.pageObjects.actions.InboxPageActions;
import framework.features.pageObjects.actions.SignInPageActions;
import framework.features.patterns.factoryMethod.ChromeDriverCreator;
import framework.features.patterns.staticFactory.StaticFactory;
import org.testng.Assert;
import org.testng.annotations.*;

public class GmailTestFactoryMethod extends BaseTest{

    @Parameters({"gmail_url"})
    @BeforeGroups(groups = "factory", description = "Start browser")
    protected void setUp(String url){
        creator = new ChromeDriverCreator();
        driver = creator.factoryMethod();
        driver.get(url);
    }

    @Parameters({"username","password","title"})
    @Test(groups = "factory", description = "Verify that login was successful", dependsOnGroups = "checkpoint")
    private void loginToGmailOk(String username, String password, String title) {
        Assert.assertTrue(new SignInPageActions(driver).loginToGmail(username, password).loginIsCorrect(title),
                "Looks you are NOT logged in correctly!");
    }

    @Test(dependsOnMethods={"loginToGmailOk"}, description="Verify that THE DEFAULT email is displayed in Drafts folder")
    private void defaultEmailInDraftsOK() {
        Email email = new StaticFactory(driver).createDefaultEmail();
        Assert.assertTrue(new InboxPageActions(driver).fillLetter(email).toDrafts().inDrafts(email.getSubject()));
    }

    @Parameters({"subject","text"})
	@Test(dependsOnMethods={"defaultEmailInDraftsOK"}, description="Verify that THE SECOND email is displayed in Drafts folder")
    private void secondEmailInDraftsOK(String subject, String text) {
        Email email = new StaticFactory(driver).createDefaultEmail().withSubject(subject).withText(text);
        Assert.assertTrue(new InboxPageActions(driver).fillLetter(email).toDrafts().inDrafts(email.getSubject()));
    }
	
	@Parameters({"address","subject","text"})
	@Test(dependsOnMethods={"secondEmailInDraftsOK"}, description="Verify that the contents of the email correspond to the information, provided during its creation")
    private void infoInEmailOK(String address, String subject, String text) {
        Assert.assertEquals(new DraftsPageActions(driver).correctEmail(address, subject), address + subject + text);
    }

	@Parameters({"subject"})
	@Test(dependsOnMethods={"infoInEmailOK"}, description="Verify that the email is not displayed in Drafts folder any more")
    public void notInDraftsOK(String subject) {
        Assert.assertFalse(new InboxPageActions(driver).sendEmail().toDrafts().inDrafts(subject));
    }

    @Parameters({"subject"})
    @Test(groups = "factory", dependsOnMethods={"notInDraftsOK"}, description="Verify that the email is displayed in Sent folder")
    private void inSentOK(String subject) {
        Assert.assertTrue(new InboxPageActions(driver).toSent().inSent(subject));
        new InboxPageActions(driver).logOut();
    }

    @AfterGroups(groups = "factory")
    protected void tearDown() {
        creator.quit();
    }
}