/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import exceptions.ConnectionException;
import exceptions.ConnectionException.CreateCustomerException;
import exceptions.ConnectionException.GetAllUsersException;
import exceptions.ConnectionException.QueryException;
import java.util.ArrayList;
import model.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alex
 */
public class UserMapperTest {
    
    public UserMapperTest() {
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
     * Test of createCustomer method, of class UserMapper.
     */
    @Test
    public void testCreateCustomer() {
        String email = "testEmail", password = "testPass", fName = "testFrist", lName = "testLast", phone = "69696969", adress = "testAddress", zipCode = "6969";
        try {
            UserMapper.setConnection();
            UserMapper.createCustomer(email, password, fName, lName, phone, adress, zipCode);
            assertEquals(fName, UserMapper.getFirstName(email));
            UserMapper.deleteUser(email);
        } catch (CreateCustomerException | ConnectionException | QueryException ex) {
            
        } finally {
            DB.releaseConnection(UserMapper.getCon());
        }
    }

    /**
     * Test of getAllUsers method, of class UserMapper.
     */
    @Test
    public void testGetAllUsers() {
        try {
            int i =0;
            UserMapper.setConnection();
            ArrayList<User> result = UserMapper.getAllUsers();
            for (User users : result) {
               i++;
            }
            assertEquals(result.size(), i);
        } catch (ConnectionException | GetAllUsersException ex) {
            ex.printStackTrace();
        } finally {
            DB.releaseConnection(UserMapper.getCon());
        }
       
    }

    /**
     * Test of getRole method, of class UserMapper.
     */
    @Test
    public void testGetRole() {
        try {
            UserMapper.setConnection();
            int result = UserMapper.getRole("fog_admin@gmail.com");
            assertEquals(2, result);
        } catch (ConnectionException | QueryException ex) {
            
        } finally {
            DB.releaseConnection(UserMapper.getCon());
        }
    }

    /**
     * Test of getAccountID method, of class UserMapper.
     */
    @Test
    public void testGetAccountID() throws Exception {
        try {
            UserMapper.setConnection();
            String result = UserMapper.getAccountID("fog_admin@gmail.com");
            assertEquals("1", result);
        } catch (ConnectionException | QueryException ex) {
            
        } finally {
            DB.releaseConnection(UserMapper.getCon());
        }
    }

    /**
     * Test of getZipCode method, of class UserMapper.
     */
    @Test
    public void testGetZipCode() throws Exception {
        try {
            UserMapper.setConnection();
            int result = UserMapper.getZipCode("fog_admin@gmail.com");
            assertEquals(3456, result);
        } catch (ConnectionException | QueryException ex) {
           
        } finally {
            DB.releaseConnection(UserMapper.getCon());
        }
    }

    /**
     * Test of getFirstName method, of class UserMapper.
     */
    @Test
    public void testGetFirstName() throws Exception {
        try {
            UserMapper.setConnection();
            String result = UserMapper.getFirstName("fog_admin@gmail.com");
            assertEquals("Admin", result);
        } catch (ConnectionException | QueryException ex) {
            
        } finally {
            DB.releaseConnection(UserMapper.getCon());
        }
    }

    /**
     * Test of getLastName method, of class UserMapper.
     */
    @Test
    public void testGetLastName() throws Exception {
        try {
            UserMapper.setConnection();
            String result = UserMapper.getLastName("fog_admin@gmail.com");
            assertEquals("Fog", result);
        } catch (ConnectionException | QueryException ex) {
           
        } finally {
            DB.releaseConnection(UserMapper.getCon());
        }
    }

    /**
     * Test of getPhone method, of class UserMapper.
     */
    @Test
    public void testGetPhone() throws Exception {
        try {
            UserMapper.setConnection();
            int result = UserMapper.getPhone("fog_admin@gmail.com");
            assertEquals(12345678, result);
        } catch (ConnectionException | QueryException ex) {
            
        } finally {
            DB.releaseConnection(UserMapper.getCon());
        }
    }

    /**
     * Test of getAdress method, of class UserMapper.
     */
    @Test
    public void testGetAdress() throws Exception {
        try {
            UserMapper.setConnection();
            String result = UserMapper.getAdress("fog_admin@gmail.com");
            assertEquals("Fog Admin 34", result);
        } catch (ConnectionException | QueryException ex) {
            
        } finally {
            DB.releaseConnection(UserMapper.getCon());
        }
    }

    /**
     * Test of updateEmail method, of class UserMapper.
     */
    @Test
    public void testUpdateEmail() throws Exception {
        try {
            UserMapper.setConnection();
            UserMapper.updateEmail("update", "1");
            assertEquals("update", UserMapper.getEmail("1"));
            UserMapper.updateEmail("fog_admin@gmail.com", "1");
            assertEquals("fog_admin@gmail.com", UserMapper.getEmail("1"));
        } catch (ConnectionException | QueryException ex) {
            
        } finally {
            DB.releaseConnection(UserMapper.getCon());
        }
    }

    /**
     * Test of updatePassword method, of class UserMapper.
     */
    @Test
    public void testUpdatePassword() throws Exception {
        try {
            UserMapper.setConnection();
            UserMapper.updatePassword("update", "1");
            assertEquals("update", UserMapper.getPassword("1"));
            UserMapper.updatePassword("fog_admin", "1");
            assertEquals("fog_admin", UserMapper.getPassword("1"));            
        } catch (ConnectionException | QueryException ex) {
           
        } finally {
            DB.releaseConnection(UserMapper.getCon());
        }
    }

    /**
     * Test of updateAdress method, of class UserMapper.
     */
    @Test
    public void testUpdateAdress() throws Exception {
        try {
            UserMapper.setConnection();
            UserMapper.updateAdress("update", "1");
            assertEquals("update", UserMapper.getAdress("fog_admin@gmail.com"));
            UserMapper.updateAdress("Fog Admin 34", "1");
            assertEquals("Fog Admin 34", UserMapper.getAdress("fog_admin@gmail.com"));        
        } catch (ConnectionException | QueryException ex) {
            
        } finally {
            DB.releaseConnection(UserMapper.getCon());
        }
    }

    /**
     * Test of updatePhone method, of class UserMapper.
     */
    @Test
    public void testUpdatePhone() throws Exception {
        try {
            UserMapper.setConnection();
            UserMapper.updatePhone("6969", "1");
            assertEquals(6969, UserMapper.getPhone("fog_admin@gmail.com"));
            UserMapper.updatePhone("12345678", "1");
            assertEquals(12345678, UserMapper.getPhone("fog_admin@gmail.com"));        
        } catch (ConnectionException | QueryException ex) {
            
        } finally {
            DB.releaseConnection(UserMapper.getCon());
        }
    }

    /**
     * Test of updateZipcode method, of class UserMapper.
     */
    @Test
    public void testUpdateZipcode() throws Exception {
        try {
            UserMapper.setConnection();
            UserMapper.updateZipcode("6969", "1");
            assertEquals(6969, UserMapper.getZipCode("fog_admin@gmail.com"));
            UserMapper.updateZipcode("3456", "1");
            assertEquals(3456, UserMapper.getZipCode("fog_admin@gmail.com"));        
        } catch (ConnectionException | QueryException ex) {
            
        } finally {
            DB.releaseConnection(UserMapper.getCon());
        }
    }
    
}
