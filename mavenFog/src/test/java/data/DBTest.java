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
    public void testCreateConnection() {
        Connection con = null;
        try {
            con = DB.createConnection();
            assertNotNull(con);
        } catch (ConnectionException ex) {
            
        } finally {
            DB.releaseConnection(con);
        }
    }

    /**
     * Test of releaseConnection method, of class DB.
     */
    @org.junit.Test
    public void testReleaseConnection()  {
        boolean failed = false;
        try {
            Connection con = DB.createConnection();
            DB.releaseConnection(con);
            assertTrue(!failed);
        } catch (ConnectionException e) {
            assertTrue(failed);
        } 
        
    }

    /**
     * Test of closeStmt method, of class DB.
     */
    @org.junit.Test
    public void testCloseStmt() {
        PreparedStatement stmt;
        try {
            Connection con = DB.createConnection();
            stmt = con.prepareStatement("");
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
