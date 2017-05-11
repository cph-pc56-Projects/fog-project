package data;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {

    private static String driver = "com.mysql.jdbc.Driver";
    private static String URL = "jdbc:mysql://localhost:3306/fog";
    private static String id = "fog";
    private static String pw = "fog123"; //change PASSWORD if needed

    public Connection getConnection() {
        Connection con = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(URL, id, pw);  // The connection will be released upon program 
            System.out.println("Connection to DB established!");

        } catch (Exception e) {
            System.out.println("error in DB.getConnection()");
            System.out.println("Connection was not established!");
        }
        return con;
    }

    public void releaseConnection(Connection con) {
        try {
            con.close();
        } catch (Exception e) {
            System.err.println(e);
            System.out.println("Couldn't close connection...");
        }
    }

}
