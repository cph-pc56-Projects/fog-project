package model;

public class User {
    private final String email, fName, lName, address;
    private final int zipCode, phone, role, accountID;

    public User(String email, String fName, String lName, String address, int zipCode, int phone, int role, int accountID) {
        this.email = email;
        this.fName = fName;
        this.lName = lName;
        this.phone = phone;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public int getZipCode() {
        return zipCode;
    }

    public int getPhone() {
        return phone;
    }

    public int getRole() {
        return role;
    }

    public int getAccountID() {
        return accountID;
    }

    @Override
    public String toString() {
        return "User{" + "email=" + email + ", fName=" + fName + ", lName=" + lName + ", address=" + address + ", zipCode=" + zipCode + ", phone=" + phone + ", role=" + role + ", accountID=" + accountID + '}';
    }
    
    
    
    
    
}
