package com.main;

public class Contact {

    // Data.
    String contactSpecName; // NO, LOW, HIGH
    float contactRisk; // NO risk float, LOW risk float, HIGH risk float

    // Contstructor.
    public Contact(String contactSpecName, float contactRisk){
        this.contactRisk = contactRisk;
        this.contactSpecName = contactSpecName;
    }

    // Getters and setters.
    public float getContactRisk() {
        return contactRisk;
    }

    public void setContactRisk(float contactRisk) {
        this.contactRisk = contactRisk;
    }

    public String getContactSpecName() {
        return contactSpecName;
    }

    public void setContactSpecName(String contactSpecName) {
        this.contactSpecName = contactSpecName;
    }

    // Functions.
    public String getSummary(){
        String summary;
        summary = "[Typ: "+this.getContactSpecName()+" ("+this.getContactRisk()+")]"; // ex. [Typ: LOW (0.3)]
        return summary;
    }

}
