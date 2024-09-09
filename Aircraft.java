package com.example.unit_management;

public class Aircraft {
    private String tailNo;
    private String model;
    private String serviceHours;
    private String activeHours;
    private String incidentDate;
    private String country;
    private String type;
    private String status;
    private String unit;
    private String nextRepair;

    public Aircraft(String tailNo, String model, String serviceHours, String activeHours, String incidentDate, String country, String type, String status, String unit, String nextRepair) {
        this.tailNo = tailNo;
        this.model = model;
        this.serviceHours = serviceHours;
        this.activeHours = activeHours;
        this.incidentDate = incidentDate;
        this.country = country;
        this.type = type;
        this.status = status;
        this.unit = unit;
        this.nextRepair = nextRepair;
    }

    // Getters and setters
    public String getTailNo() {
        return tailNo;
    }

    public void setTailNo(String tailNo) {
        this.tailNo = tailNo;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getServiceHours() {
        return serviceHours;
    }

    public void setServiceHours(String serviceHours) {
        this.serviceHours = serviceHours;
    }

    public String getActiveHours() {
        return activeHours;
    }

    public void setActiveHours(String activeHours) {
        this.activeHours = activeHours;
    }

    public String getIncidentDate() {
        return incidentDate;
    }

    public void setIncidentDate(String incidentDate) {
        this.incidentDate = incidentDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getNextRepair() {
        return nextRepair;
    }

    public void setNextRepair(String nextRepair) {
        this.nextRepair = nextRepair;
    }
}
