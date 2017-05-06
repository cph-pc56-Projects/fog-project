/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBtesting;

import data.UserMapper;

/**
 *
 * @author Patrick
 */
public class UserMapperTesting {
    
    public static void main(String[] args) throws Exception {
        UserMapper mapper = new UserMapper();
       
        int i = mapper.createUser("testEmail", "testPassword", "testFname","testLname", "testPhone", "testAdress", "6969");
        System.out.println(i);
        System.out.println(mapper.getEmail("testEmail"));
        System.out.println(mapper.getPassword("testEmail"));
        System.out.println(mapper.getRole("testEmail"));
        System.out.println(mapper.getAccountID("testEmail"));
        System.out.println(mapper.getZipCode("testEmail"));
        System.out.println(mapper.getFirstName("testEmail"));
        System.out.println(mapper.getLastName("testEmail"));
        System.out.println(mapper.getAdress("testEmail"));
        System.out.println(mapper.getPhone("testEmail"));
        System.out.println("");
        mapper.updateEmail("updatedEmail", mapper.getAccountID("testEmail"));
        mapper.updatePassword("updatedPassword", mapper.getAccountID("updatedEmail"));
        mapper.updateAdress("updatedAdress", mapper.getAccountID("updatedEmail"));
        mapper.updatePhone("updatedPhone", mapper.getAccountID("updatedEmail"));
        mapper.updateZipcode(1111, mapper.getAccountID("updatedEmail"));
        System.out.println(mapper.getEmail("updatedEmail"));
        System.out.println(mapper.getPassword("updatedEmail"));
        System.out.println(mapper.getRole("updatedEmail"));
        System.out.println(mapper.getAccountID("updatedEmail"));
        System.out.println(mapper.getZipCode("updatedEmail"));
        System.out.println(mapper.getFirstName("updatedEmail"));
        System.out.println(mapper.getLastName("updatedEmail"));
        System.out.println(mapper.getAdress("updatedEmail"));
        System.out.println(mapper.getPhone("updatedEmail"));
    }
}
