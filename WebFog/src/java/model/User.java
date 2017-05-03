package model;

public class User {

    private int id, balance;
    private String username;
    private String password;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String regdate;

    public User(int id, String username, String password, String name, String address, String phone, String email, String regdate, int balance) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.regdate = regdate;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getRegdate() {
        return regdate;
    }

    public int getBalance() {
        return balance;
    }
    
}
