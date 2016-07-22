package com.codepath.jlin.navigationdemo.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Contact implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.phoneNumber);
        dest.writeString(this.notes);
    }

    protected Contact(Parcel in) {
        this.name = in.readString();
        this.phoneNumber = in.readString();
        this.notes = in.readString();
    }

    public static final Parcelable.Creator<Contact> CREATOR = new Parcelable.Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel source) {
            return new Contact(source);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };
}
