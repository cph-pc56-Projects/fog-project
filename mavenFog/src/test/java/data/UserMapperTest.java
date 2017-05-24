/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

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


    /**
     * Test of createCustomer method, of class UserMapper.
     */
    @Test
    public void testCreateCustomer() throws Exception {
        System.out.println("createCustomer");
        String email = "test";
        String password = "";
        String fName = "";
        String lName = "";
        String phone = "";
        String adress = "";
        String zipCode = "";
        UserMapper instance = new UserMapper();
        instance.createCustomer(email, password, fName, lName, phone, adress, zipCode);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllUsers method, of class UserMapper.
     */
    @Test
    public void testGetAllUsers() throws Exception {
        System.out.println("getAllUsers");
        UserMapper instance = new UserMapper();
        ArrayList<User> expResult = null;
        ArrayList<User> result = instance.getAllUsers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEmail method, of class UserMapper.
     */
    @Test
    public void testGetEmail() throws Exception {
        System.out.println("getEmail");
        String email = "";
        UserMapper instance = new UserMapper();
        String expResult = "";
        String result = instance.getEmail(email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPassword method, of class UserMapper.
     */
    @Test
    public void testGetPassword() throws Exception {
        System.out.println("getPassword");
        String email = "";
        UserMapper instance = new UserMapper();
        String expResult = "";
        String result = instance.getPassword(email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRole method, of class UserMapper.
     */
    @Test
    public void testGetRole() throws Exception {
        System.out.println("getRole");
        String email = "";
        UserMapper instance = new UserMapper();
        int expResult = 0;
        int result = instance.getRole(email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAccountID method, of class UserMapper.
     */
    @Test
    public void testGetAccountID() throws Exception {
        System.out.println("getAccountID");
        String email = "";
        UserMapper instance = new UserMapper();
        int expResult = 0;
        int result = instance.getAccountID(email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getZipCode method, of class UserMapper.
     */
    @Test
    public void testGetZipCode() throws Exception {
        System.out.println("getZipCode");
        String email = "";
        UserMapper instance = new UserMapper();
        int expResult = 0;
        int result = instance.getZipCode(email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFirstName method, of class UserMapper.
     */
    @Test
    public void testGetFirstName() throws Exception {
        System.out.println("getFirstName");
        String email = "";
        UserMapper instance = new UserMapper();
        String expResult = "";
        String result = instance.getFirstName(email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLastName method, of class UserMapper.
     */
    @Test
    public void testGetLastName() throws Exception {
        System.out.println("getLastName");
        String email = "";
        UserMapper instance = new UserMapper();
        String expResult = "";
        String result = instance.getLastName(email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPhone method, of class UserMapper.
     */
    @Test
    public void testGetPhone() throws Exception {
        System.out.println("getPhone");
        String email = "";
        UserMapper instance = new UserMapper();
        String expResult = "";
        String result = instance.getPhone(email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAdress method, of class UserMapper.
     */
    @Test
    public void testGetAdress() throws Exception {
        System.out.println("getAdress");
        String email = "";
        UserMapper instance = new UserMapper();
        String expResult = "";
        String result = instance.getAdress(email);
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
        int acc_id = 0;
        UserMapper instance = new UserMapper();
        instance.updateEmail(email, acc_id);
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
        int acc_id = 0;
        UserMapper instance = new UserMapper();
        instance.updatePassword(password, acc_id);
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
        int acc_id = 0;
        UserMapper instance = new UserMapper();
        instance.updateAdress(adress, acc_id);
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
        int acc_id = 0;
        UserMapper instance = new UserMapper();
        instance.updatePhone(phone, acc_id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateZipcode method, of class UserMapper.
     */
    @Test
    public void testUpdateZipcode() throws Exception {
        System.out.println("updateZipcode");
        int zipCode = 0;
        int acc_id = 0;
        UserMapper instance = new UserMapper();
        instance.updateZipcode(zipCode, acc_id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
