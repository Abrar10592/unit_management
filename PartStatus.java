package com.example.unit_management;

public class PartStatus {

    private int entryno;
    private String tailno;
    private String name_;
    private String company_name;
    private String next_repair_date;
    private String name;
    private String description;
    private int hours_left;
    private String status;

    public PartStatus() {

    }

    public void setEntryno(int entryno) {
        this.entryno = entryno;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTailno(String tailno) {
        this.tailno = tailno;
    }

    public void setName_(String name) {
        this.name_ = name;
    }

    public void setCompany_name(String companyName) {
        this.company_name = companyName;
    }

    public void setNext_repair_date(String nextRepairDate) {
        this.next_repair_date = nextRepairDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public void setStatus(String status) {
        this.status = status;
    }

    public void setHours_left(int hoursLeft) {
        this.hours_left = hoursLeft;
        System.out.println("tata");
    }


    // Constructor
    public PartStatus(int entryno, String tailno, String name_, String company_name, String next_repair_date,String name, String description, int hours_left, String status) {
        this.entryno = entryno;
        this.tailno = tailno;
        this.name_ = name_;
        this.company_name = company_name;
        this.next_repair_date = next_repair_date;
        this.description = description;
        this.hours_left = hours_left;
        this.status = status;
        this.name=name;
    }

    // Getters
    public int getEntryno() {
        return entryno;
    }

    public String getTailno() {
        return tailno;
    }

    public String getName_() {
        return name_;
    }

    public String getCompany_name() {
        return company_name;
    }

    public String getNext_repair_date() {
        return next_repair_date;
    }

    public String getDescription() {
        return description;
    }

    public int getHours_left() {
        return hours_left;
    }

    public String getStatus() {
        return status;
    }
    public  String getName()
    { return  name;}
}
