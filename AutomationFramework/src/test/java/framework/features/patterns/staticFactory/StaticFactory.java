package framework.features.patterns.staticFactory;

import framework.features.base.Email;
import framework.features.pageObjects.actions.AbstractPage;
import org.openqa.selenium.WebDriver;

public class StaticFactory extends AbstractPage {
    public StaticFactory(WebDriver driver) {
        super(driver);
    }

    public static Email createDefaultEmail(){
        Email email = new Email();
        email.setAddress("automatization.test@gmail.com");
        email.setSubject("Default letter");
        email.setText("Hello! Here you can see default text.");

        return email;
    }
}
