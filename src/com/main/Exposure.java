package com.main;

public class Exposure {
    // Data.
    private int contactDays;
    private Contact[] contacts; //nazwa nie typ

    // Constructor.
    public Exposure(int contactDays){
        this.contactDays = contactDays;
        contacts = new Contact [this.contactDays];
        // TODO: Make dynamic array which length is equal to contactDays.
    }
public String getSummary() {
        for(int i = 1; i<=contactDays; i++){
            String summary = contacts[i].getSummary();
            // [Typ: NO (0.0)]
            return "coś"+i+summary;
            // coś2[Typ: NO (0.0)]
        }
    return null;
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