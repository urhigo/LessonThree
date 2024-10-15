package models;

import Interface.Printable;

public class Email implements Printable {

    private String email;

    public Email(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void printInformationAboutObject(Object object) {
        System.out.println(object);
    }
}
