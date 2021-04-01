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
        System.out.println ("Exposure: [Time horizon:" +this.contactDays+ "day(s)]")
        for (int i = 1; i< contactDays; i++) }
            Exposure.getContactDays();
            String summary = contacts[i].getSummary();
            return "Day" +contacts [i]+ ": [Typ:" + summary; + "]"
            return null;



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