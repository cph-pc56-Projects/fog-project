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
public class createUserTest {
    
    public static void main(String[] args) throws Exception {
       UserMapper mapper = new UserMapper();
       
       int i = mapper.createUser("I am lindssay lohan", "lollerblades", "du er grim", "er du gal en kartoffel", "444", "aaa", "4444");
       
        System.out.println(i);
        System.out.println("Password ");
        System.out.println(mapper.getPassword("a"));
    }
}
