package framework;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class TestValues {
    public String gmail_url;
    public String username;
    public String password;

    public TestValues(){
        java.util.Properties prop = new java.util.Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("config.properties");

            prop.load(input);

            setGmail_url(prop.getProperty("gmail_url"));
            setPassword(prop.getProperty("username"));
            setUsername(prop.getProperty("password"));

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void setGmail_url(String gmail_url) {
        this.gmail_url = gmail_url;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGmail_url() {
        return gmail_url;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
