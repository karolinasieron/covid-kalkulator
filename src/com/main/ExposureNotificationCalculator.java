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
    public void addHighRiskContact(Contact contact){
        exposure.addContact(contact);
    }

    public void addLowRiskContact(Contact contact){
        exposure.addContact(contact);
    }

    public void addNoRiskContact(Contact contact){
        exposure.addContact(contact);
    }

    public float getWeightedExposureSum(){
        Contact[] contacts = exposure.getContacts();
        int exposureDays = exposure.getContactDays();
        float risk = 0f;

        for(int i = 1; i<=exposureDays; i++){
            float contactRisk = contacts[i].getContactRisk();
            float temporary = 0f;
            if(i == 1){
                temporary = contactRisk*ExposureNotificationAppSkeleton.TRL_6*0.2f;
            } else if(i>1 && i<5){
                temporary = contactRisk*ExposureNotificationAppSkeleton.TRL_8*0.2f;
            } else if(i == 5){
                temporary = contactRisk*ExposureNotificationAppSkeleton.TRL_5*0.2f;
            } else if(i == 6){
                temporary = contactRisk*ExposureNotificationAppSkeleton.TRL_3*0.2f;
            } else if(i>7){
                temporary = contactRisk*ExposureNotificationAppSkeleton.TRL_1*0.2f;
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

        if(isExposure == true){
            return "ExposureNotificationCalculator: [Exposure: yes (Weighted Exposure Sum = "+riskSum+")]";
        } else {
            return "ExposureNotificationCalculator: [Exposure: no (Weighted Exposure Sum = "+riskSum+")]";
        }
    }
}
