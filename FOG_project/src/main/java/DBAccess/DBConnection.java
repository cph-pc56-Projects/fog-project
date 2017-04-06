/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author trez__000
 */
public class DBConnection {
    
    
// JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost:3306/sakila?useSSL=false";

   //  Database credentials
   static final String USER = "root";
   static final String PASS = "lillekagemand";
   
   public static void main(String[] args) throws SQLException, ClassNotFoundException{
   Connection conn = null;
   Statement stmt = null;
   try{
      //STEP 2: Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");

      //STEP 3: Open a connection
      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL,USER,PASS);

      //STEP 4: Execute a query
      System.out.println("Creating statement...");
      stmt = conn.createStatement();
      String sql;
      sql = "SELECT actor_id, first_name, last_name, last_update FROM actor";
      ResultSet rs = stmt.executeQuery(sql);

      //STEP 5: Extract data from result set
      while(rs.next()){
         //Retrieve by column name
         int id  = rs.getInt("actor_id");
         String firstName = rs.getString("first_name");
         String lastName = rs.getString("last_name");
         String lastupdate = rs.getString("last_update");

         //Display values
         System.out.print("Id: " + id);
         System.out.print(", First name: " + firstName);
         System.out.print(", Last name: " + lastName);
         System.out.println(" Last updated : " + lastupdate);
      }
      //STEP 6: Clean-up environment
      rs.close();
      stmt.close();
      conn.close();
   }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
      }// nothing we can do
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
   System.out.println("Goodbye!");
     }//main
     
}
//stmt.executeUpdate ("INSERT INTO COFFEES " +"VALUES('Amaretto', 49, 9.99, 0, 0)");
