/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.Connection;

/**
 *
 * @author Alex
 */
public class AdminMapper {
    private final Connection con;

    public AdminMapper() {
        con = new DB().getConnection();
    }
    
    
}
