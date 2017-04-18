package Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class UserMapper {
    private Connection con;
    
    
    public UserMapper()  {
        con = new DB().getConnection();
    }
    DB db = new DB();
    public String getEmail (String email) throws SQLException {
        String userEmail = null;
        try {
            Connection conn = db.getConnection();
            PreparedStatement statement = conn.prepareStatement("select email from users where email = "+"'"+email+"'");
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                userEmail = result.getString("email");
            }
        } catch (SQLException x) {
            System.out.println("Couldn't get email "+ x);
        }
        return userEmail;
    }
    
    
    public String getEmaill (String email){
        String userEmail = null;
        ResultSet rs = null;
        Statement stmt = null;        
        String SQLString
                = "select email from users where email = " + email;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQLString);
            if (rs.next()){
                userEmail = rs.getString("email");
            }
        } catch(SQLException x) {
            System.out.println("Email not found " + x);
        }
        return userEmail;
    }
    
    public String getPassword(String email){
        String password = null;
        ResultSet rs = null;
        Statement stmt = null;        
        String SQLString
                = "select password from users where email = " + email;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQLString);
            if (rs.next()){
                password = rs.getString("password");
            }
        } catch(SQLException x) {
            System.out.println("Password not found");
        }
        return password;
    }
    
    
    
    
    
}
