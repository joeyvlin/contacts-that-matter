package com.codepath.jlin.navigationdemo.model;

import java.io.Serializable;

public class Contact implements Serializable {

    private static final long serialVersionUID = 5177222050535318633L;

    private String name;
    private String phoneNumber;
    private String notes;

    public Contact(String name, String phoneNumber, String notes) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.notes = notes;
    }

    public String getNotes() {
        return notes;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
