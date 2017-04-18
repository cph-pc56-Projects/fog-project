/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.sql.SQLException;

/**
 *
 * @author Alex
 */
public class main {
    
    public static void main (String[] args) {
        DB db = new DB();
        UserMapper mapper = new UserMapper();
        String email = "Sth is wrong";
        try {
             email = mapper.getEmail("alex@alex.com");
        } catch (SQLException x) {
            System.out.println(x);
        }
        System.out.println(email);
    }
}
