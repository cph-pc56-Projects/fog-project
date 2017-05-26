/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import exceptions.ConnectionException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;

/**
 *
 * @author Alex
 */
public class DBTest {
    
    public DBTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createConnection method, of class DB.
     */
    @org.junit.Test
    public void testCreateConnection() throws Exception {
        DB instance = new DB();
        Connection expResult = null;
        Connection result = instance.createConnection();
        assertNotEquals(expResult, result);
    }

    /**
     * Test of releaseConnection method, of class DB.
     */
    @org.junit.Test
    public void testReleaseConnection()  {
        try {
        DB instance = new DB();
        Connection result = instance.createConnection();
        result.close();
        instance.releaseConnection(result);
        assertTrue(result.isClosed());
        } catch (ConnectionException e) {
            System.out.println("Can`t create connection");
        } catch (SQLException ex) {
            System.out.println("Can`t close the connection");
        }
        
    }

    /**
     * Test of closeStmt method, of class DB.
     */
    @org.junit.Test
    public void testCloseStmt() {
        String sql = "";
        PreparedStatement stmt;
        try {
            Connection con = DB.createConnection();
            stmt = con.prepareStatement(sql);
            DB.closeStmt(stmt);
            DB.releaseConnection(con);
            assertTrue(stmt.isClosed());
        } catch (ConnectionException ex) {
            
        } catch (SQLException ex) {
            
        } 
    }

    /**
     * Test of closeRs method, of class DB.
     */
    @org.junit.Test
    public void testCloseRs() {
        String sql = "SELECT * FROM users";
        PreparedStatement stmt;
        ResultSet rs;
        try {
            Connection con = DB.createConnection();
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            DB.closeRs(rs);
            DB.closeStmt(stmt);
            DB.releaseConnection(con);
            assertTrue(rs.isClosed());
        } catch (ConnectionException ex) {
            
        } catch (SQLException ex) {
            
        } 
    }

    
}
