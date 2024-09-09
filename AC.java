package com.example.unit_management;

public class AC {
    private  String hoursleft;
    private String tailNo;
    private String model;


    private String unitname;

    private String status;
    private String lastreason;
    private String nextRepair;
   // private String remarks;

    public AC(String tailNo, String model, String serviceHours, String unitname,String status, String lastreason, String nextRepair) {
        this.tailNo = tailNo;
        this.model = model;
        this.hoursleft = serviceHours;
     //   this.activeHours = activeHours;
     //   this.incidentDate = incidentDate;
        this.unitname = unitname;
       // this.type = type;
       // this.lastreason = status;
        this.lastreason = lastreason;
        this.nextRepair = nextRepair;
       // this.remarks=remarks;
        this.status=status;
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

    public String getHoursleft() {
      System.out.println("doneee");
        return hoursleft;
    }

    public void setHoursleft(String serviceHours) {
        this.hoursleft= serviceHours;
    }





    public String getUnitname() {
        return unitname;
    }

    public void setUnitname(String country) {
        this.unitname = country;
    }



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public String getNextRepair() {
        return nextRepair;
    }

    public void setNextRepair(String nextRepair) {
        this.nextRepair = nextRepair;
    }
    public String getLastreason()
    {     System.out.println("doneeeeeeeeee");
        return  lastreason;}

    public void setLastreason(String lastreason) {
        this.lastreason = lastreason;
    }
}
