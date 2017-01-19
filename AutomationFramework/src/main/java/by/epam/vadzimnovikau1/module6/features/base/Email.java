package by.epam.vadzimnovikau1.module6.features.base;

public class Email {
	private String address;
	private String subject;
	private String text;

//    public Email(String address, String subject, String text){
//        this.address = address;
//        this.subject = subject;
//        this.text = text;
//    }

//    public Email(){}

    public Email withSubject(String subject){
        this.setSubject(subject);
        return this;
    }

    public Email withText(String text){
        this.setText(text);
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAddress() {
        return address;
    }

    public String getSubject() {
        return subject;
    }

    public String getText() {
        return text;
    }
}
