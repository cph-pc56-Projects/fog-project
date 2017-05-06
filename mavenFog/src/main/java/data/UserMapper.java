package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;


public class UserMapper {
    private final Connection con;

    public UserMapper() {
        con = new DB().getConnection();
    }
    
    public int createUser(String email, String password, String fName, String lName, String phone, String adress, String zipCode) throws Exception {
        int i = 0;
        
        try {
            PreparedStatement create = con.prepareStatement("INSERT INTO users (email, password, fName, lName, phone, adress, zipCode, role, creationDate)"
                    + " VALUES ('"+email+"', '"+password+"', '"+fName+"', '"+lName+"', '"+phone+"', '"+adress+"', "+ Integer.parseInt(zipCode)+", 0, CURDATE())");
            i = create.executeUpdate();
        } catch (Exception e) {
            System.out.println("Something wrong with createUser()");
        } finally {
            System.out.println("Insert Complete");
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
        } catch(SQLException x) {
            System.out.println("Email not found");
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
        } catch(SQLException x) {
            System.out.println("Email not found");
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
        } catch(SQLException x) {
            System.out.println("Role not found");
        }
        
        return role;
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
        } catch(SQLException x) {
            System.out.println("zipCode not found");
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
        } catch(SQLException x) {
            System.out.println("fName not found");
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
        } catch(SQLException x) {
            System.out.println("lName not found");
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
        } catch(SQLException x) {
            System.out.println("phone not found");
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
        } catch(SQLException x) {
            System.out.println("aress not found");
        }
        
        return adress;
    }
    
    
}
