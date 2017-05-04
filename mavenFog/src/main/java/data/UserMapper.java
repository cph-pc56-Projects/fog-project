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
            x.printStackTrace();
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
            x.printStackTrace();
        }
        
        return password;
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
    
    
}
