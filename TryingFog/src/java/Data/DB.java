package Data;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {

    private static String driver = "org.gjt.mm.mysql.Driver";
    private static String URL = "jdbc:mysql://localhost:3306/fog"; //change DATABASE
    private static String id = "root";
    private static String pw = "calarashi"; //change PASSWORD

    public Connection getConnection() {
        Connection con = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(URL, id, pw);  // The connection will be released upon program 

        } catch (Exception e) {
            System.out.println("\n*** Remember to insert your  ID and PW in the DBConnector class! ***\n");
            System.out.println("error in DBConnector.getConnection()");
            System.out.println(e);
        }

        return con;
    }

    public void releaseConnection(Connection con) {
        try {
            con.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
}
