/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Patrick
 */
public class User {
    private String email, fName, lName, phone, adress, creationDate;
    private int zipCode;
    private final int role;

    public User(String email, String fName, String lName, String phone, String adress, String creationDate, int zipCode, int role) {
        this.email = email;
        this.fName = fName;
        this.lName = lName;
        this.phone = phone;
        this.adress = adress;
        this.creationDate = creationDate;
        this.zipCode = zipCode;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getPhone() {
        return phone;
    }

    public String getAdress() {
        return adress;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public int getZipCode() {
        return zipCode;
    }

    public int getRole() {
        return role;
    }
    
    
}
