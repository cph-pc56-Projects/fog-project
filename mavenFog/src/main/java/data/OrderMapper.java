/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Alex
 */
public class OrderMapper {
    private final Connection con;

    public OrderMapper() {
        con = new DB().getConnection();
    }
    // Creates a list with all the orders a customer has
    public ArrayList<String> findOrdersByCustomer(int customerID) {
        ArrayList<String> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders, WHERE customer_id = " + customerID + "";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                 int order_id = rs.getInt("order_id");
                 int product_id = rs.getInt("product_id");
                 int salesRep_id = rs.getInt("salesRep_id");
            }
        } catch(SQLException x) {
            System.out.println("Email not found");
        }
        return orders;
    }
}
