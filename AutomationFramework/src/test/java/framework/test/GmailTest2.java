package framework.test;

import framework.base.Email;
import framework.pageObjects.actions.DraftsPageActions;
import framework.pageObjects.actions.InboxPageActions;
import framework.pageObjects.actions.SignInPageActions;
import framework.values.TestValues;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GmailTest2 extends BaseTest {
    private String username = (new TestValues().getUsername());
    private String password = (new TestValues().getPassword());

    private String title = "Inbox";
    private String address = "automatization.test@gmail.com";
    private String subject = "Second letter";
    private String text = "Hello! The framework has created the second draft.";

    @Test(description = "Verify that login was successful")
    private void loginToGmailOk() {
        Assert.assertTrue(new SignInPageActions(getDriver()).loginToGmail(username, password).loginIsCorrect(title),
                "Looks you are NOT logged in correctly!");
    }

    @Test(dependsOnMethods = {"loginToGmailOk"}, description = "Verify that the email is displayed in Drafts folder")
    private void emailInDraftsOK() {
        Email email = new Email(address, subject, text);
        Assert.assertTrue(new InboxPageActions(getDriver()).fillLetter(email).toDrafts().inDrafts(email.getSubject()));
    }

    @Test(dependsOnMethods = {"emailInDraftsOK"},
            description = "Verify that the contents of the email correspond to the information, provided during its creation")
    private void infoInEmailOK() {
        Assert.assertEquals(new DraftsPageActions(getDriver()).correctEmail(address, subject), address + subject + text);
    }

    @Test(dependsOnMethods = {"infoInEmailOK"}, description = "Verify that the email is not displayed in Drafts folder any more")
    private void notInDraftsOK() {
        Assert.assertFalse(new InboxPageActions(getDriver()).sendEmail().toDrafts().inDrafts(subject));
    }

    @Test(dependsOnMethods = {"notInDraftsOK"}, description = "Verify that the email is displayed in Sent folder")
    private void inSentOK() {
        Assert.assertTrue(new InboxPageActions(getDriver()).toSent().inSent(subject));
        new InboxPageActions(getDriver()).logOut();
    }

}