package com.main;

public class ExposureNotificationCalculator {
    // Data
    private int contactDays;
    private Exposure exposure;

    //Constructor
    public ExposureNotificationCalculator(int contactDays) {
        this.contactDays = contactDays;
        this.exposure = new Exposure(contactDays);
    }

    //Getters and setters
    public int getContactDays() {
        return contactDays;
    }

    public void setContactDays(int contactDays) {
        this.contactDays = contactDays;
    }

    public Exposure getExposure() {
        return exposure;
    }

    public void setExposure(Exposure exposure) {
        this.exposure = exposure;
    }

    //Methods
    public void addHighRiskContact(){
        Contact contact = new Contact("HIGH", ExposureNotificationApp.CONTACT_SPEC_HIGH_VALUE);
        exposure.addContact(contact);
    }

    public void addLowRiskContact(){
        Contact contact = new Contact("LOW", ExposureNotificationApp.CONTACT_SPEC_LOW_VALUE);
        exposure.addContact(contact);
    }

    public void addNoRiskContact(){
        Contact contact = new Contact("NO", ExposureNotificationApp.CONTACT_SPEC_NO_VALUE);
        exposure.addContact(contact);
    }

    public float getWeightedExposureSum(){
        Contact[] contacts = exposure.getContacts();
        int exposureDays = exposure.getContactDays();
        float risk = 0f;

        for(int i = 0; i<exposureDays; i++){
            float contactRisk;

            if(contacts[i] == null){
                contactRisk = 0f;
            } else  contactRisk = contacts[i].getContactRisk();

            float temporary = 0f;
            if(i == 1){
                temporary = contactRisk*ExposureNotificationApp.TRL_6*0.2f;
            } else if(i>1 && i<5){
                temporary = contactRisk*ExposureNotificationApp.TRL_8*0.2f;
            } else if(i == 5){
                temporary = contactRisk*ExposureNotificationApp.TRL_5*0.2f;
            } else if(i == 6){
                temporary = contactRisk*ExposureNotificationApp.TRL_3*0.2f;
            } else if(i>7){
                temporary = contactRisk*ExposureNotificationApp.TRL_1*0.2f;
            }
            risk = risk + temporary;
        }
        return risk;
    }

    public boolean isExposure(){
        if(getWeightedExposureSum()>0.85f){
            return true;
        }
        else return false;
    }

    public String getSummary(){
        //ExposureNotificationCalculator: [Exposure: yes (Weighted Exposure Sum = 1.12)]
        float riskSum = getWeightedExposureSum();
        boolean isExposure = isExposure();
        String previousSummary = exposure.getSummary();
        if(isExposure == true){
            String summary =  "ExposureNotificationCalculator: [Exposure: yes (Weighted Exposure Sum = "+riskSum+")]";
            return summary + "\n" + previousSummary;
        } else {
            String summary = "ExposureNotificationCalculator: [Exposure: no (Weighted Exposure Sum = "+riskSum+")]";
            return summary + "\n" + previousSummary;
        }
    }
}
