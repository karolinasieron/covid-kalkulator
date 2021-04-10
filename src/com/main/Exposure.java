package com.main;

public class Exposure {
    // Data.
    private int contactDays;
    private Contact[] contacts; //nazwa nie typ

    // Constructor.
    public Exposure(int contactDays){
        this.contactDays = contactDays;
        contacts = new Contact[this.contactDays];
    }

    public String getSummary() {
        System.out.println ("Exposure: [Time horizon: " +contactDays+ " day(s)]");
        String summaryToReturn = "";

        for (int i = 1; i<contactDays+1; i++) {
            String summaryOfContact;

            if(contacts[i-1] == null){
                summaryOfContact = "---";
            } else summaryOfContact = contacts[i-1].getSummary();

            String summary = "Day" + i + ": " + summaryOfContact;
            summaryToReturn = summaryToReturn + summary + "\n";
        }
        return summaryToReturn;
    }

    public int getContactDays () {
            return contactDays;
    }

    public Contact[] getContacts() {
        return contacts;
    }

    public void setContacts(Contact[] contacts) {
        this.contacts = contacts;
    }

    public void addContact(Contact contact){
        Contact[] contactsBefore = getContacts();
        int length = getContactDays();
        Contact[] contactsAfter = new Contact[length];

        for(int i = 1; i<length; i++){
            contactsAfter[i]=contactsBefore[i-1];
        }
        contactsAfter[0] = contact;

        setContacts(contactsAfter);
    }

}