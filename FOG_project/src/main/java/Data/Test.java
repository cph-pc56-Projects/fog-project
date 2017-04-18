/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;



/**
 *
 * @author trez__000
 */
public class Test {
    public static void main (String[] args) throws Exception {
        UserMapper mapper = new UserMapper();
        String data = "nothing";
        data = mapper.getEmail("alex@alex.com");
        System.out.println(data);
    }
} 
