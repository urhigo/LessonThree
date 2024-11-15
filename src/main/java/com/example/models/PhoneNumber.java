package com.example.models;

import com.example.Interface.Printable;

public class PhoneNumber implements Printable {

    private String phoneNumber;

    public PhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void printInformationAboutObject(Object object) {
        System.out.println(object);
    }
}
