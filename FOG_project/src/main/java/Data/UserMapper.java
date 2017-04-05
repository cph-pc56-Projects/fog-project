package Data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Model.User;


public class UserMapper {
    private Connection con;

    public UserMapper() {
        con = new DB().getConnection();
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
