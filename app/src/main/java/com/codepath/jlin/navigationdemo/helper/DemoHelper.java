package com.codepath.jlin.navigationdemo.helper;

import com.codepath.jlin.navigationdemo.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class DemoHelper {

    private static List<Contact> contactList;

    public static List<Contact> createContactList() {
        if (contactList != null && !contactList.isEmpty()) {
            return contactList;
        }

        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("Bertram Gilfoyle", "415-323-4397",
                "Call if server goes down or need some sarcasm at the party."));
        contacts.add(new Contact("Big Head", "415-888-8888",
                "Ask him to fund Pied Piper instead of going to all these VCs."));
        contacts.add(new Contact("Dinesh Chugtai", "626-777-9933", "Call when shitty code needs some fixin'"));
        contacts.add(new Contact("Erlich Bachman", "650-890-3451",
                "This guy might be a grade A a**hole, but he sure is good to know when you are in trouble."));
        contacts.add(new Contact("Gavin Belsom", "510-349-1313",
                "Follow up on his promise that he would buy our middle-out company for $250m. Tell him he was not drunk."));
        contacts.add(new Contact("Jared Dunn", "408-998-3999",
                "Great guy to call whenever you need a German interpreter, or really for anything."));
        contacts.add(new Contact("Jian-Yang", "510-423-9546", "Is actually a really awesome standup comedian"));
        contacts.add(new Contact("Monica Hall", "415-456-3421",
                "The reason I siged with Raviga in the first place."));
        return contacts;
    }
}
