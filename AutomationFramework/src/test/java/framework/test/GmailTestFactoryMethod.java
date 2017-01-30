package framework.test;

import framework.TestValues;
import framework.base.Email;
import framework.pageObjects.actions.DraftsPageActions;
import framework.pageObjects.actions.InboxPageActions;
import framework.pageObjectsWithoutActionsAndJavaScript.actions.InboxPageOldActions;
import framework.pageObjectsWithoutActionsAndJavaScript.actions.SignInPageOldActions;
import org.testng.Assert;
import org.testng.annotations.*;

public class GmailTestFactoryMethod extends BaseTest {
//    public String username = "automatization.test@gmail.com";
//    public String password = "fw748fg099a";

    private String username = (new TestValues().getUsername());
    private String password = (new TestValues().getPassword());




    private String title = "Inbox";
    private String address = "automatization.test@gmail.com";
    private String subject = "Second letter";
    private String text = "Hello! The framework has created the second draft.";

    @Test(groups = "factory", description = "Verify that login was successful")
    private void loginToGmailOk() {
        Assert.assertTrue(new SignInPageOldActions(driver).loginToGmail(username, password).loginIsCorrect(title),
                "Looks you are NOT logged in correctly!");
    }



//    , dependsOnGroups = "checkpointNotInDraftsOK"
    @Test(groups = "factory", dependsOnMethods = {"loginToGmailOk"}, description = "Verify that the email is displayed in Drafts folder")
    private void EmailInDraftsOK() {
        Email email = new Email(address, subject, text);
        Assert.assertTrue(new InboxPageOldActions(driver).fillLetter(email).toDrafts().inDrafts(email.getSubject()));
    }

    @Test(groups = "factory", dependsOnMethods = {"EmailInDraftsOK"}, description = "Verify that the contents of the email correspond to the information, provided during its creation")
    private void infoInEmailOK() {
        Assert.assertEquals(new DraftsPageActions(driver).correctEmail(address, subject), address + subject + text);
    }

    @Test(groups = "factory", dependsOnMethods = {"infoInEmailOK"}, description = "Verify that the email is not displayed in Drafts folder any more")
    public void notInDraftsOK() {
        Assert.assertFalse(new InboxPageOldActions(driver).sendEmail().toDrafts().inDrafts(subject));
    }

    @Test(groups = "factory", dependsOnMethods = {"notInDraftsOK"}, description = "Verify that the email is displayed in Sent folder")
    private void inSentOK() {
        Assert.assertTrue(new InboxPageOldActions(driver).toSent().inSent(subject));
        new InboxPageActions(driver).logOut();
    }

}