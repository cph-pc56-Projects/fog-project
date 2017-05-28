package data;

import exceptions.ConnectionException;
import exceptions.ConnectionException.QueryException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class DB {
    private final static String driver = "com.mysql.jdbc.Driver";
    private final static String URL = "jdbc:mysql://localhost:3306/fog";
    private final static String id = "fog";
    private final static String pw = "fog123";
    
    //Creates connection to the Database. Throws Exception if the connection is not established.    
    public static Connection createConnection() throws ConnectionException {
        Connection con = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(URL, id, pw);
        } catch (SQLException | ClassNotFoundException e) {
            throw new ConnectionException();
        } 
        return con;
    }
    
    //Closes the connection to the Database
    public static void releaseConnection(Connection con) {
        if (con!=null) {    
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }    
    }
    
    //Closes a given PreparedStatement
    public static void closeStmt(PreparedStatement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
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
            }
        }
    }
    
    //Returns a unique ID for the Mapper Classes to put into DB
    public static String generateID(String table, String column, Connection con) throws QueryException {
        String uniqueID = randomID();
        String sql = "SELECT " + column + " FROM " + table + " WHERE " + column + " = '" + uniqueID +"'";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            if (rs.next()) {
                generateID(table, column, con);
            } else {
                return uniqueID;
            }
        } catch (SQLException e) {
            throw new QueryException();
        
        } finally {
            closeStmt(stmt);
            closeRs(rs);
        }
        return uniqueID;
    }
    
    //Creates random String IDs with 10 integers
    private static String randomID() {
        String uniqueID = "";
        Random rand = new Random();
        while (uniqueID.length()<10) {
            uniqueID += Integer.toString(rand.nextInt(10) + 0);
        }
        return uniqueID;
    }
    
    
}
