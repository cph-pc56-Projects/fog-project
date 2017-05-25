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
        UserMapper instance = null;
        try {
            instance = new UserMapper();
            instance.createCustomer(email, password, fName, lName, phone, adress, zipCode);
            assertEquals(fName, instance.getFirstName(email));
            instance.deleteUser(email);
        } catch (CreateCustomerException | ConnectionException | QueryException ex) {
            
        } finally {
            DB.releaseConnection(instance.getCon());
        }
    }

    /**
     * Test of getAllUsers method, of class UserMapper.
     */
    @Test
    public void testGetAllUsers() {
        UserMapper instance = null;
        try {
            instance = new UserMapper();
            ArrayList<User> result = instance.getAllUsers();
            assertEquals(result.size(), 7);
        } catch (ConnectionException | GetAllUsersException ex) {
            ex.printStackTrace();
        } finally {
            DB.releaseConnection(instance.getCon());
        }
       
    }

    /**
     * Test of getRole method, of class UserMapper.
     */
    @Test
    public void testGetRole() {
        UserMapper instance = null;
        try {
            instance = new UserMapper();
            int result = instance.getRole("fog_admin@gmail.com");
            assertEquals(2, result);
        } catch (ConnectionException | QueryException ex) {
            
        } finally {
            DB.releaseConnection(instance.getCon());
        }
    }

    /**
     * Test of getAccountID method, of class UserMapper.
     */
    @Test
    public void testGetAccountID() throws Exception {
        UserMapper instance = null;
        try {
            instance = new UserMapper();
            int result = instance.getAccountID("fog_admin@gmail.com");
            assertEquals(1, result);
        } catch (ConnectionException | QueryException ex) {
            
        } finally {
            DB.releaseConnection(instance.getCon());
        }
    }

    /**
     * Test of getZipCode method, of class UserMapper.
     */
    @Test
    public void testGetZipCode() throws Exception {
        UserMapper instance = null;
        try {
            instance = new UserMapper();
            int result = instance.getZipCode("fog_admin@gmail.com");
            assertEquals(3456, result);
        } catch (ConnectionException | QueryException ex) {
           
        } finally {
            DB.releaseConnection(instance.getCon());
        }
    }

    /**
     * Test of getFirstName method, of class UserMapper.
     */
    @Test
    public void testGetFirstName() throws Exception {
        UserMapper instance = null;
        try {
            instance = new UserMapper();
            String result = instance.getFirstName("fog_admin@gmail.com");
            assertEquals("Admin", result);
        } catch (ConnectionException | QueryException ex) {
            
        } finally {
            DB.releaseConnection(instance.getCon());
        }
    }

    /**
     * Test of getLastName method, of class UserMapper.
     */
    @Test
    public void testGetLastName() throws Exception {
        UserMapper instance = null;
        try {
            instance = new UserMapper();
            String result = instance.getLastName("fog_admin@gmail.com");
            assertEquals("Fog", result);
        } catch (ConnectionException | QueryException ex) {
           
        } finally {
            DB.releaseConnection(instance.getCon());
        }
    }

    /**
     * Test of getPhone method, of class UserMapper.
     */
    @Test
    public void testGetPhone() throws Exception {
        UserMapper instance = null;
        try {
            instance = new UserMapper();
            int result = instance.getPhone("fog_admin@gmail.com");
            assertEquals(12345678, result);
        } catch (ConnectionException | QueryException ex) {
            
        } finally {
            DB.releaseConnection(instance.getCon());
        }
    }

    /**
     * Test of getAdress method, of class UserMapper.
     */
    @Test
    public void testGetAdress() throws Exception {
        UserMapper instance = null;
        try {
            instance = new UserMapper();
            String result = instance.getAdress("fog_admin@gmail.com");
            assertEquals("Fog Admin 34", result);
        } catch (ConnectionException | QueryException ex) {
            
        } finally {
            DB.releaseConnection(instance.getCon());
        }
    }

    /**
     * Test of updateEmail method, of class UserMapper.
     */
    @Test
    public void testUpdateEmail() throws Exception {
        UserMapper instance = null;
        try {
            instance = new UserMapper();
            instance.updateEmail("update", 1);
            assertEquals("update", instance.getEmail(1));
            instance.updateEmail("fog_admin@gmail.com", 1);
            assertEquals("fog_admin@gmail.com", instance.getEmail(1));
        } catch (ConnectionException | QueryException ex) {
            
        } finally {
            DB.releaseConnection(instance.getCon());
        }
    }

    /**
     * Test of updatePassword method, of class UserMapper.
     */
    @Test
    public void testUpdatePassword() throws Exception {
        UserMapper instance = null;
        try {
            instance = new UserMapper();
            instance.updatePassword("update", 1);
            assertEquals("update", instance.getPassword(1));
            instance.updatePassword("fog_admin", 1);
            assertEquals("fog_admin", instance.getPassword(1));            
        } catch (ConnectionException | QueryException ex) {
           
        } finally {
            DB.releaseConnection(instance.getCon());
        }
    }

    /**
     * Test of updateAdress method, of class UserMapper.
     */
    @Test
    public void testUpdateAdress() throws Exception {
        UserMapper instance = null;
        try {
            instance = new UserMapper();
            instance.updateAdress("update", 1);
            assertEquals("update", instance.getAdress("fog_admin@gmail.com"));
            instance.updateAdress("Fog Admin 34", 1);
            assertEquals("Fog Admin 34", instance.getAdress("fog_admin@gmail.com"));        
        } catch (ConnectionException | QueryException ex) {
            
        } finally {
            DB.releaseConnection(instance.getCon());
        }
    }

    /**
     * Test of updatePhone method, of class UserMapper.
     */
    @Test
    public void testUpdatePhone() throws Exception {
        UserMapper instance = null;
        try {
            instance = new UserMapper();
            instance.updatePhone("6969", 1);
            assertEquals(6969, instance.getPhone("fog_admin@gmail.com"));
            instance.updatePhone("12345678", 1);
            assertEquals(12345678, instance.getPhone("fog_admin@gmail.com"));        
        } catch (ConnectionException | QueryException ex) {
            
        } finally {
            DB.releaseConnection(instance.getCon());
        }
    }

    /**
     * Test of updateZipcode method, of class UserMapper.
     */
    @Test
    public void testUpdateZipcode() throws Exception {
        UserMapper instance = null;
        try {
            instance = new UserMapper();
            instance.updateZipcode("6969", 1);
            assertEquals(6969, instance.getZipCode("fog_admin@gmail.com"));
            instance.updateZipcode("3456", 1);
            assertEquals(3456, instance.getZipCode("fog_admin@gmail.com"));        
        } catch (ConnectionException | QueryException ex) {
            
        } finally {
            DB.releaseConnection(instance.getCon());
        }
    }
    
}
