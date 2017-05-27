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
import java.sql.Connection;
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


//    /**
//     * Test of createCustomer method, of class UserMapper.
//     */
//    @Test
//    public void testCreateCustomer() {
//        String email = "testEmail", password = "testPass", fName = "testFrist", lName = "testLast", phone = "69696969", adress = "testAddress", zipCode = "6969";
//        try {
//            UserMapper.setConnection();
//            UserMapper.createCustomer(email, password, fName, lName, phone, adress, zipCode);
//            User user = UserMapper.getUser("testEmail");
//            assertEquals(fName, user.getfName());
//            UserMapper.deleteUser(email);
//        } catch (CreateCustomerException | ConnectionException | QueryException ex) {
//            
//        } finally {
//            DB.releaseConnection(UserMapper.getCon());
//        }
//    }

//    /**
//     * Test of getAllUsers method, of class UserMapper.
//     */
//    @Test
//    public void testGetAllUsers() {
//        try {
//            int i =0;
//            UserMapper.setConnection();
//            ArrayList<User> result = UserMapper.getAllUsers();
//            for (User users : result) {
//               i++;
//            }
//            assertEquals(result.size(), i);
//        } catch (ConnectionException | GetAllUsersException ex) {
//            ex.printStackTrace();
//        } finally {
//            DB.releaseConnection(UserMapper.getCon());
//        }
//       
//    }
//
//    /**
//     * Test of getRole method, of class UserMapper.
//     */
//    @Test
//    public void testGetRole() {
//        try {
//            UserMapper.setConnection();
//            User user = UserMapper.getUser("fog_admin@gmail.com");
//            assertEquals(2, user.getRole());
//        } catch (ConnectionException | QueryException ex) {
//            
//        } finally {
//            DB.releaseConnection(UserMapper.getCon());
//        }
//    }
//
//    /**
//     * Test of getAccountID method, of class UserMapper.
//     */
//    @Test
//    public void testGetAccountID() throws Exception {
//        try {
//            UserMapper.setConnection();
//            User user = UserMapper.getUser("fog_admin@gmail.com");
//            assertEquals("1", user.getAccountID());
//        } catch (ConnectionException | QueryException ex) {
//            
//        } finally {
//            DB.releaseConnection(UserMapper.getCon());
//        }
//    }
//
//    /**
//     * Test of getZipCode method, of class UserMapper.
//     */
//    @Test
//    public void testGetZipCode() throws Exception {
//        try {
//            UserMapper.setConnection();
//            User user = UserMapper.getUser("fog_admin@gmail.com");
//            int result = user.getZipCode();
//            assertEquals(3456, result);
//        } catch (ConnectionException | QueryException ex) {
//           
//        } finally {
//            DB.releaseConnection(UserMapper.getCon());
//        }
//    }
//
//    /**
//     * Test of getFirstName method, of class UserMapper.
//     */
//    @Test
//    public void testGetFirstName() throws Exception {
//        try {
//            UserMapper.setConnection();
//            User user = UserMapper.getUser("fog_admin@gmail.com");
//            assertEquals("Admin", user.getfName());
//        } catch (ConnectionException | QueryException ex) {
//            
//        } finally {
//            DB.releaseConnection(UserMapper.getCon());
//        }
//    }
//
//    /**
//     * Test of getLastName method, of class UserMapper.
//     */
//    @Test
//    public void testGetLastName() throws Exception {
//        try {
//            UserMapper.setConnection();
//            User user = UserMapper.getUser("fog_admin@gmail.com");
//            assertEquals("Fog", user.getlName());
//        } catch (ConnectionException | QueryException ex) {
//           
//        } finally {
//            DB.releaseConnection(UserMapper.getCon());
//        }
//    }
//
//    /**
//     * Test of getPhone method, of class UserMapper.
//     */
//    @Test
//    public void testGetPhone() throws Exception {
//        try {
//            UserMapper.setConnection();
//            User user = UserMapper.getUser("fog_admin@gmail.com");
//            assertEquals(12345678, user.getPhone());
//        } catch (ConnectionException | QueryException ex) {
//            
//        } finally {
//            DB.releaseConnection(UserMapper.getCon());
//        }
//    }
//
//    /**
//     * Test of getAdress method, of class UserMapper.
//     */
//    @Test
//    public void testGetAdress() throws Exception {
//        try {
//            UserMapper.setConnection();
//            User user = UserMapper.getUser("fog_admin@gmail.com");
//            String result = user.getAddress();
//            assertEquals("Fog Admin 34", result);
//        } catch (ConnectionException | QueryException ex) {
//            
//        } finally {
//            DB.releaseConnection(UserMapper.getCon());
//        }
//    }

//    /**
//     * Test of updateEmail method, of class UserMapper.
//     */
//    @Test
//    public void testUpdateEmail() throws Exception {
//        try {
//            UserMapper.setConnection();
//            User user = UserMapper.getUser("fog_admin@gmail.com");
//            UserMapper.updateEmail("update", "1");
//            assertEquals("update", user.getEmail());
//            UserMapper.updateEmail("fog_admin@gmail.com", "1");
//            assertEquals("fog_admin@gmail.com", user.getEmail());
//        } catch (ConnectionException | QueryException ex) {
//            
//        } finally {
//            DB.releaseConnection(UserMapper.getCon());
//        }
//    }

//    /**
//     * Test of updatePassword method, of class UserMapper.
//     */
//    @Test
//    public void testUpdatePassword() throws Exception {
//        try {
//            UserMapper.setConnection();
//            User user = UserMapper.getUser("fog_admin@gmail.com");
//            UserMapper.updatePassword("update", "1");
//            assertEquals("update", user.get);
//            UserMapper.updatePassword("fog_admin", "1");
//            assertEquals("fog_admin", UserMapper.getPassword("1"));            
//        } catch (ConnectionException | QueryException ex) {
//           
//        } finally {
//            DB.releaseConnection(UserMapper.getCon());
//        }
//    }

//    /**
//     * Test of updateAdress method, of class UserMapper.
//     */
//    @Test
//    public void testUpdateAdress() throws Exception {
//        try {
//            UserMapper.setConnection();
//            User user = UserMapper.getUser("fog_admin@gmail.com");
//            UserMapper.updateAdress("update", "1");
//            assertEquals("update", user.getAddress());
//            UserMapper.updateAdress("Fog Admin 34", "1");
//            assertEquals("Fog Admin 34", user.getAddress());        
//        } catch (ConnectionException | QueryException ex) {
//            
//        } finally {
//            DB.releaseConnection(UserMapper.getCon());
//        }
//    }

//    /**
//     * Test of updatePhone method, of class UserMapper.
//     */
//    @Test
//    public void testUpdatePhone() throws Exception {
//        try {
//            UserMapper.setConnection();
//            User user = UserMapper.getUser("fog_admin@gmail.com");
//            UserMapper.updatePhone("6969", "1");
//            assertEquals(6969, user.getPhone());
//            UserMapper.updatePhone("12345678", "1");
//            assertEquals(12345678, user.getPhone());        
//        } catch (ConnectionException | QueryException ex) {
//            
//        } finally {
//            DB.releaseConnection(UserMapper.getCon());
//        }
//    }
//
//    /**
//     * Test of updateZipcode method, of class UserMapper.
//     */
//    @Test
//    public void testUpdateZipcode() throws Exception {
//        try {
//            UserMapper.setConnection();
//            User user = UserMapper.getUser("fog_admin@gmail.com");
//            UserMapper.updateZipcode("6969", "1");
//            assertEquals(6969, user.getZipCode());
//            UserMapper.updateZipcode("3456", "1");
//            assertEquals(3456, user.getZipCode());        
//        } catch (ConnectionException | QueryException ex) {
//            
//        } finally {
//            DB.releaseConnection(UserMapper.getCon());
//        }
//    }

    /**
     * Test of setConnection method, of class UserMapper.
     */
    @Test
    public void testSetConnection() throws Exception {
        System.out.println("setConnection");
        UserMapper.setConnection();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCon method, of class UserMapper.
     */
    @Test
    public void testGetCon() {
        System.out.println("getCon");
        Connection expResult = null;
        Connection result = UserMapper.getCon();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validateLoginDetails method, of class UserMapper.
     */
    @Test
    public void testValidateLoginDetails() throws Exception {
        System.out.println("validateLoginDetails");
        String email = "";
        String password = "";
        UserMapper.validateLoginDetails(email, password);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createCustomer method, of class UserMapper.
     */
    @Test
    public void testCreateCustomer() throws Exception {
        System.out.println("createCustomer");
        String email = "";
        String password = "";
        String fName = "";
        String lName = "";
        String phone = "";
        String adress = "";
        String zipCode = "";
        UserMapper.createCustomer(email, password, fName, lName, phone, adress, zipCode);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createSalesRep method, of class UserMapper.
     */
    @Test
    public void testCreateSalesRep() throws Exception {
        System.out.println("createSalesRep");
        String email = "";
        String password = "";
        String fName = "";
        String lName = "";
        String phone = "";
        String adress = "";
        String zipCode = "";
        UserMapper.createSalesRep(email, password, fName, lName, phone, adress, zipCode);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllUsers method, of class UserMapper.
     */
    @Test
    public void testGetAllUsers() throws Exception {
        System.out.println("getAllUsers");
        ArrayList<User> expResult = null;
        ArrayList<User> result = UserMapper.getAllUsers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUser method, of class UserMapper.
     */
    @Test
    public void testGetUser() throws Exception {
        System.out.println("getUser");
        String email = "";
        User expResult = null;
        User result = UserMapper.getUser(email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateEmail method, of class UserMapper.
     */
    @Test
    public void testUpdateEmail() throws Exception {
        System.out.println("updateEmail");
        String email = "";
        String acc_id = "";
        UserMapper.updateEmail(email, acc_id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updatePassword method, of class UserMapper.
     */
    @Test
    public void testUpdatePassword() throws Exception {
        System.out.println("updatePassword");
        String password = "";
        String acc_id = "";
        UserMapper.updatePassword(password, acc_id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateAdress method, of class UserMapper.
     */
    @Test
    public void testUpdateAdress() throws Exception {
        System.out.println("updateAdress");
        String adress = "";
        String acc_id = "";
        UserMapper.updateAdress(adress, acc_id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updatePhone method, of class UserMapper.
     */
    @Test
    public void testUpdatePhone() throws Exception {
        System.out.println("updatePhone");
        String phone = "";
        String acc_id = "";
        UserMapper.updatePhone(phone, acc_id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateZipcode method, of class UserMapper.
     */
    @Test
    public void testUpdateZipcode() throws Exception {
        System.out.println("updateZipcode");
        String zipCode = "";
        String acc_id = "";
        UserMapper.updateZipcode(zipCode, acc_id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteUser method, of class UserMapper.
     */
    @Test
    public void testDeleteUser() {
        System.out.println("deleteUser");
        String email = "";
        UserMapper.deleteUser(email);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateUserStatus method, of class UserMapper.
     */
    @Test
    public void testUpdateUserStatus() throws Exception {
        System.out.println("updateUserStatus");
        int status = 0;
        String accountID = "";
        UserMapper.updateUserStatus(status, accountID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
