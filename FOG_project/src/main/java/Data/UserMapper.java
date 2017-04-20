package Data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Model.User;
import java.sql.PreparedStatement;


public class UserMapper {
    private Connection con;

    public UserMapper() {
        con = new DB().getConnection();
    }
    
    public String getEmail (String email) throws SQLException {
        String userEmail = null;
        String sql = "select email from users where email ="+"'" + email + "'";
        
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
    
    
    
    
    
    public User getUser(int id) {
        ResultSet rs = null;
        Statement stmt = null;
        User user = null;

        String SQLString
                = "select * from members where id = " + id;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQLString);
            if (rs.next()) {
                user = new User(id,
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        Integer.parseInt(rs.getString(9)));
            }
        } catch (SQLException e) {
            System.out.println("Fail in UserMapper - getUser");
            System.out.println(e.getMessage());
        }
        return user;
    }
}
