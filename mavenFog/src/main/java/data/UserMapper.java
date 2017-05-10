package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;


public class UserMapper {
    private final Connection con;
    private final DB db;
    

    public UserMapper() {
        db = new DB();
        con = db.getConnection();
    }

    public DB getDb() {
        return db;
    }

    public Connection getCon() {
        return con;
    }

    
    public int createUser(String email, String password, String fName, String lName, String phone, String adress, String zipCode) throws Exception {
        int i = 0;
        String sql = "INSERT INTO users (email, password, fName, lName, phone, adress, zipCode, role, creationDate)"
                    + " VALUES ('"+email+"', '"+password+"', '"+fName+"', '"+lName+"', '"+phone+"', '"+adress+"', "+ Integer.parseInt(zipCode)+", 0, CURDATE())";
        try {
            PreparedStatement create = con.prepareStatement(sql);
            i = create.executeUpdate();
            System.out.println("createUser Complete");
        } catch (Exception e) {
            System.out.println("Something wrong with createUser()");
        } 
        return i;
    }
    
    public String getEmail (String email) throws SQLException {
        String userEmail = null;
        String sql = "select email from users where email = " + "'" + email + "'";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                userEmail = rs.getString("email");
            }
            System.out.println("getEmail complete");
        } catch(SQLException x) {
            System.out.println("Something wrong with getEmail");
        }
        return userEmail;
    }
    
    public String getPassword(String email){
        String password = null;
        String sql = "select password from users where email = " + "'" + email + "'";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                password = rs.getString("password");
            }
            System.out.println("getPassword complete");
        } catch(SQLException x) {
            System.out.println("Something wrong with getPassword");
        }
        return password;
    }
    
    
    
    public int getRole (String email) {
        int role = 0;
        String sql = "select role from users where email = " + "'" + email + "'";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                role = rs.getInt("role");
            }
            System.out.println("getRole complete");
        } catch(SQLException x) {
            System.out.println("Something wrong with getRole");
        }
        return role;
    }
    
    public int getAccountID (String email) {
        int id = 0;
        String sql = "select account_id from users where email = " + "'" + email + "'";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                id = rs.getInt("account_id");
            }
            System.out.println("getAccountID complete");
        } catch(SQLException x) {
            System.out.println("Something wrong with getAccountID");
        }
        return id;
    }
    
    public int getZipCode (String email) {
        int zipCode = 0;
        String sql = "select zipCode from users where email = " + "'" + email + "'";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                zipCode = rs.getInt("zipCode");
            }
            System.out.println("getZipCode complete");
        } catch(SQLException x) {
            System.out.println("zSomething wrong with getZipCode");
        }
        return zipCode;
    }
    
    public String getFirstName (String email) {
        String fName = null;
        String sql = "select fName from users where email = " + "'" + email + "'";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                fName = rs.getString("fName");
            }
            System.out.println("getFirstName complete");
        } catch(SQLException x) {
            System.out.println("Something wrong with getFirstName");
        }
        return fName;
    }
    
    public String getLastName (String email) {
        String lName = null;
        String sql = "select lName from users where email = " + "'" + email + "'";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                lName = rs.getString("lName");
            }
            System.out.println("geLastName complete");
        } catch(SQLException x) {
            System.out.println("Something wrong with getLastName");
        }
        return lName;
    }
    
    public String getPhone (String email) {
        String phone = null;
        String sql = "select phone from users where email = " + "'" + email + "'";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                phone = rs.getString("phone");
            }
            System.out.println("getPhone complete");
        } catch(SQLException x) {
            System.out.println("Something wrong with getPhone");
        }
        return phone;
    }
    
    public String getAdress (String email) {
        String adress = null;
        String sql = "select adress from users where email = " + "'" + email + "'";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                adress = rs.getString("adress");
            }
            System.out.println("getAdress complete");
        } catch(SQLException x) {
            System.out.println("Something wrong with getAdress");
        }
        return adress;
    }
    
    public int updateEmail(String email, int acc_id) throws Exception {
        int i = 0;
        String sql = "UPDATE users SET email = '" + email + "' WHERE account_id = " + acc_id + "";
        try {
            PreparedStatement update = con.prepareStatement(sql);
            i = update.executeUpdate();
            System.out.println("Update Complete");
        } catch (Exception e) {
            System.out.println("Something wrong with updateEmail()");
        } 
        return i;
    } 
    
    public int updatePassword(String password, int acc_id) throws Exception {
        int i = 0;
        String sql = "UPDATE users SET password = '" + password + "' WHERE account_id = " + acc_id + "";
        try {
            PreparedStatement update = con.prepareStatement(sql);
            i = update.executeUpdate();
            System.out.println("Update Complete");
        } catch (Exception e) {
            System.out.println("Something wrong with updatePassword()");
        } 
        return i;
    }
    
    public int updateAdress(String adress, int acc_id) throws Exception {
        int i = 0;
        String sql = "UPDATE users SET adress = '" + adress + "' WHERE account_id = " + acc_id + "";
        try {
            PreparedStatement update = con.prepareStatement(sql);
            i = update.executeUpdate();
            System.out.println("Update Complete");
        } catch (Exception e) {
            System.out.println("Something wrong with updateAdress()");
        } 
        return i;
    }
    
    public int updatePhone(String phone, int acc_id) throws Exception {
        int i = 0;
        String sql = "UPDATE users SET phone = '" + phone + "' WHERE account_id = " + acc_id + "";
        try {
            PreparedStatement update = con.prepareStatement(sql);
            i = update.executeUpdate();
            System.out.println("Update Complete");
        } catch (Exception e) {
            System.out.println("Something wrong with updatePhone()");
        } 
        return i;
    }
    
    public int updateZipcode(int zipCode, int acc_id) throws Exception {
        int i = 0;
        String sql = "UPDATE users SET zipCode = " + zipCode + " WHERE account_id = " + acc_id + "";
        try {
            PreparedStatement update = con.prepareStatement(sql);
            i = update.executeUpdate();
            System.out.println("Update Complete");
        } catch (Exception e) {
            System.out.println("Something wrong with updatePassword()");
        } 
        return i;
    }
    
    
    
}
