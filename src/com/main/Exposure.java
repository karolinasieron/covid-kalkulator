package com.main;

public class Exposure {
    // Data.
    private int contactDays;
   private Contact[] contacts;

    // Constructor.
    public Exposure(int contactDays){
        this.contactDays = contactDays;
        contacts = new Contact [this.contactDays];
        // TODO: Make dynamic array which length is equal to contactDays.
    }
public String getSummary() {
        return "test";
}
public int getContactDays () {
        return contactDays;
}

    public Contact[] getContacts() {
        return contacts;
    }
// TODO: Make functions from instruction!!!

}
//test gita