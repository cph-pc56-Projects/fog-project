package data;

import exceptions.ConnectionException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB {
    private final static String driver = "com.mysql.jdbc.Driver";
    private final static String URL = "jdbc:mysql://localhost:3306/fog";
    private final static String id = "fog";
    private final static String pw = "fog123";
    
    //Creates connection to the Database. Throws Exception if the connection is not established.    
    public Connection createConnection() throws ConnectionException {
        Connection con = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(URL, id, pw);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Can`t establish connection to the Database!");
            throw new ConnectionException();
        } 
        return con;
    }
    
    //Closes the connection to the Database
    public void releaseConnection(Connection con) {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Couldn't close connection!");
        }
    }
    
    //Closes a given PreparedStatement
    public static void closeStmt(PreparedStatement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println("Could not close prepared statement!");
            }
        }
    }
    
    //Closes a given ResultSet
    public static void closeRs(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Could not close resultset!");
            }
        }
    }
}
