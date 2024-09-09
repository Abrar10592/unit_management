package com.example.unit_management;
public class User {
    private String id;
    private String name;
    private String dob;
    private String type;
    private String email;
    private String unitId;
    private String address;

    public User(String id, String name, String dob, String type, String email, String unitId, String address) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.type = type;
        this.email = email;
        this.unitId = unitId;
        this.address = address;
    }

    // Getters and setters for all fields
    public String getId() { return id; }
    public String getName() { return name; }
    public String getDob() { return dob; }
    public String getType() { return type; }
    public String getEmail() { return email; }
    public String getUnitId() { return unitId; }
    public String getAddress() { return address; }

    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDob(String dob) { this.dob = dob; }
    public void setType(String type) { this.type = type; }
    public void setEmail(String email) { this.email = email; }
    public void setUnitId(String unitId) { this.unitId = unitId; }
    public void setAddress(String address) { this.address = address; }
}
