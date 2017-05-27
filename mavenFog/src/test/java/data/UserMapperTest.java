package data;

import exceptions.ConnectionException;
import exceptions.ConnectionException.CreateCustomerException;
import exceptions.ConnectionException.GetAllUsersException;
import exceptions.ConnectionException.LoginError;
import exceptions.ConnectionException.QueryException;
import exceptions.ConnectionException.UpdateStatusException;
import java.util.ArrayList;
import model.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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
     * Test of setConnection method, of class UserMapper.
     */
    @Test
    public void testSetConnection() {
        try {
            UserMapper.setConnection();
            assertNotNull(UserMapper.getCon());
        } catch (ConnectionException ex) {
            
        } finally {
            DB.releaseConnection(UserMapper.getCon());
        }
    }

    /**
     * Test of validateLoginDetails method, of class UserMapper.
     */
    @Test
    public void testValidateLoginDetails() {
        boolean validated = true;
        try {
            UserMapper.setConnection();
            UserMapper.validateLoginDetails("fog_admin@gmail.com", "fog_admin");
            assertTrue(validated);
        } catch (ConnectionException | QueryException | LoginError ex) {
            assertTrue(!validated);
        } finally {
            DB.releaseConnection(UserMapper.getCon());
        }
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
            User user = UserMapper.getUser("testEmail");
            assertEquals(fName, user.getfName());
            UserMapper.deleteUser(email);
        } catch (CreateCustomerException | ConnectionException | QueryException ex) {
            
        } finally {
            DB.releaseConnection(UserMapper.getCon());
        }
    }

    /**
     * Test of createSalesRep method, of class UserMapper.
     */
    @Test
    public void testCreateSalesRep() {
        String email = "testEmail", password = "testPass", fName = "testFrist", lName = "testLast", phone = "69696969", adress = "testAddress", zipCode = "6969";
        try {
            UserMapper.setConnection();
            UserMapper.createCustomer(email, password, fName, lName, phone, adress, zipCode);
            User user = UserMapper.getUser("testEmail");
            assertEquals(fName, user.getfName());
            UserMapper.deleteUser(email);
        } catch (CreateCustomerException | ConnectionException | QueryException ex) {
            
        } finally {
            DB.releaseConnection(UserMapper.getCon());
        }
    }
    
    /**
     * Test of deleteUser method, of class UserMapper.
     */
    @Test
    public void testDeleteUser() {
        boolean failed = false;
        try {
            String email = "testEmail", password = "testPass", fName = "testFrist", lName = "testLast", phone = "69696969", adress = "testAddress", zipCode = "6969";
            UserMapper.setConnection();
            UserMapper.createCustomer(email, password, fName, lName, phone, adress, zipCode);
            UserMapper.deleteUser("testEmail");
            assertTrue(!failed);
        } catch (CreateCustomerException | ConnectionException ex) {
            assertTrue(failed);
        }
    }
    
    /**
     * Test of getAllUsers method, of class UserMapper.
     */
    @Test
    public void testGetAllUsers() {
        try {
            UserMapper.setConnection();
            ArrayList<User> users = UserMapper.getAllUsers();
            assertNotNull(users);
        } catch (ConnectionException | GetAllUsersException ex) {
            ex.printStackTrace();
        } finally {
            DB.releaseConnection(UserMapper.getCon());
        }
    }

    /**
     * Test of getUser method, of class UserMapper.
     */
    @Test
    public void testGetUser() {
        try {
            UserMapper.setConnection();
            User user = UserMapper.getUser("fog_admin@gmail.com");
            assertNotNull(user);
        } catch (ConnectionException | QueryException ex) {
        
        } finally {
            DB.releaseConnection(UserMapper.getCon());
        }
    }

    /**
     * Test of updateEmail method, of class UserMapper.
     */
    @Test
    public void testUpdateEmail() {
        boolean failed = false;
        try {
            UserMapper.setConnection();
            User user = UserMapper.getUser("fog_admin@gmail.com");
            UserMapper.updateEmail("update", user.getAccountID());
            UserMapper.updateEmail("fog_admin@gmail.com", user.getAccountID());
            assertTrue(!failed);
        } catch (ConnectionException | QueryException | ConnectionException.UpdateUserInfoException ex) {
            assertTrue(failed);
        } finally {
            DB.releaseConnection(UserMapper.getCon());
        }
    }

    /**
     * Test of updatePassword method, of class UserMapper.
     */
    @Test
    public void testUpdatePassword() {
        boolean failed = false;
        try {
            UserMapper.setConnection();
            User user = UserMapper.getUser("fog_admin@gmail.com");
            UserMapper.updatePassword("update", user.getAccountID());
            UserMapper.updatePassword("fog_admin", user.getAccountID());
            assertTrue(!failed);
        } catch (ConnectionException | QueryException | ConnectionException.UpdateUserInfoException ex) {
            assertTrue(failed);
        } finally {
            DB.releaseConnection(UserMapper.getCon());
        }
        
    }

    /**
     * Test of updateAdress method, of class UserMapper.
     */
    @Test
    public void testUpdateAdress() {
        boolean failed = false;
        try {
            UserMapper.setConnection();
            User user = UserMapper.getUser("fog_admin@gmail.com");
            UserMapper.updateAdress("update", user.getAccountID());
            UserMapper.updateAdress(user.getAddress(), user.getAccountID());
            assertTrue(!failed);
        } catch (ConnectionException | QueryException | ConnectionException.UpdateUserInfoException ex) {
            assertTrue(failed);
        } finally {
            DB.releaseConnection(UserMapper.getCon());
        }
    }

    /**
     * Test of updatePhone method, of class UserMapper.
     */
    @Test
    public void testUpdatePhone() {
        boolean failed = false;
        try {
            UserMapper.setConnection();
            User user = UserMapper.getUser("fog_admin@gmail.com");
            UserMapper.updatePhone("69696969", user.getAccountID());
            UserMapper.updatePhone(Integer.toString(user.getPhone()), user.getAccountID());
            assertTrue(!failed);
        } catch (ConnectionException | QueryException | ConnectionException.UpdateUserInfoException ex) {
            assertTrue(failed);
        } finally {
            DB.releaseConnection(UserMapper.getCon());
        }
    }

    /**
     * Test of updateZipcode method, of class UserMapper.
     */
    @Test
    public void testUpdateZipcode() {
        boolean failed = false;
        try {
            UserMapper.setConnection();
            User user = UserMapper.getUser("fog_admin@gmail.com");
            UserMapper.updateZipcode("69696", user.getAccountID());
            UserMapper.updateZipcode(Integer.toString(user.getZipCode()), user.getAccountID());
            assertTrue(!failed);
        } catch (ConnectionException | QueryException | ConnectionException.UpdateUserInfoException ex) {
            assertTrue(failed);
        } finally {
            DB.releaseConnection(UserMapper.getCon());
        }
    }

    /**
     * Test of updateUserStatus method, of class UserMapper.
     */
    @Test
    public void testUpdateUserStatus() {
        boolean failed = false;
        try {
            UserMapper.setConnection();
            User user = UserMapper.getUser("fog_admin@gmail.com");
            UserMapper.updateUserStatus(0, user.getAccountID());
            UserMapper.updateUserStatus(1, user.getAccountID());
            assertTrue(!failed);
        } catch (ConnectionException |  UpdateStatusException | QueryException e) {
            assertTrue(failed);
        } finally {
            DB.releaseConnection(UserMapper.getCon());
        }
    }

}
