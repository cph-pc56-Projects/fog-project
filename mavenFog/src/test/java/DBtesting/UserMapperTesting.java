/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBtesting;

import exceptions.ConnectionException;

import data.UserMapper;
import exceptions.CreateCustomerException;
import java.sql.SQLException;

/**
 *
 * @author Patrick
 */
public class UserMapperTesting {
    
    public static void main(String[] args) throws SQLException {
        try {
            UserMapper mapper = new UserMapper();
       
        mapper.createCustomer("testEmail", "testPassword", "testFname","testLname", "6969699889889998989899", "testAdress", "6969");
        } catch (CreateCustomerException e) {
            System.out.println("createuserexception");
        } catch (ConnectionException ex) {
            System.out.println("connectionexception");
        }
        
        
//        System.out.println(mapper.getEmail("testEmail"));
//        System.out.println(mapper.getPassword("testEmail"));
//        System.out.println(mapper.getRole("testEmail"));
//        System.out.println(mapper.getAccountID("testEmail"));
//        System.out.println(mapper.getZipCode("testEmail"));
//        System.out.println(mapper.getFirstName("testEmail"));
//        System.out.println(mapper.getLastName("testEmail"));
//        System.out.println(mapper.getAdress("testEmail"));
//        System.out.println(mapper.getPhone("testEmail"));
//        System.out.println("");
//        mapper.updateEmail("updatedEmail", mapper.getAccountID("testEmail"));
//        mapper.updatePassword("updatedPassword", mapper.getAccountID("updatedEmail"));
//        mapper.updateAdress("updatedAdress", mapper.getAccountID("updatedEmail"));
//        mapper.updatePhone("updatedPhone", mapper.getAccountID("updatedEmail"));
//        mapper.updateZipcode(1111, mapper.getAccountID("updatedEmail"));
//        System.out.println(mapper.getEmail("updatedEmail"));
//        System.out.println(mapper.getPassword("updatedEmail"));
//        System.out.println(mapper.getRole("updatedEmail"));
//        System.out.println(mapper.getAccountID("updatedEmail"));
//        System.out.println(mapper.getZipCode("updatedEmail"));
//        System.out.println(mapper.getFirstName("updatedEmail"));
//        System.out.println(mapper.getLastName("updatedEmail"));
//        System.out.println(mapper.getAdress("updatedEmail"));
//        System.out.println(mapper.getPhone("updatedEmail"));
    }
}
