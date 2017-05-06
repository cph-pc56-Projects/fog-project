package model;

public class User {
    private String email, fName, lName, phone, adress;
    private int zipCode;
    private final int role, accountID;

    public User(String email, String fName, String lName, String phone, String adress, int zipCode, int role, int accountID) {
        this.email = email;
        this.fName = fName;
        this.lName = lName;
        this.phone = phone;
        this.adress = adress;
        this.zipCode = zipCode;
        this.role = role;
        this.accountID = accountID;
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

    public int getZipCode() {
        return zipCode;
    }

    public int getRole() {
        return role;
    }

    public int getAccountID() {
        return accountID;
    }
    
    
    
}
