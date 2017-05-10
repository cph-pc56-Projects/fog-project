/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Order;

/**
 *
 * @author Alex
 */
public class OrderMapper {
    private final Connection con;
    private final DB db;
    
    public OrderMapper() {
        db = new DB();
        con = db.getConnection();
    }

    public DB getDb() {
        return db;
    }

    public Connection getCon() {
        return con;
    }
    //Creates a new Order with status pending
    public int createOrder(double price, int customer_id) {
        int i = 0;
        String sqlOrders = "INSERT into orders (price, creationDate, customer_id, orderStatus) VALUES (" + price + ",CURDATE(), " + customer_id + ", 0)";
        
        try {
            PreparedStatement create = con.prepareStatement(sqlOrders);
            i = create.executeUpdate();
            System.out.println("Insert Order Complete");
            int order_id = getOrderIDbyPriceAndCustomerID(price, customer_id);
            String sqlOrderDetails = "INSERT into orderdetails (order_id, product_id) VALUES (" + order_id + ", " + customer_id + ")";
            create = con.prepareStatement(sqlOrderDetails);
            i = create.executeUpdate();
            System.out.println("Insert orderDetails complete");
            
            
        } catch (Exception e) {
            System.out.println("Something wrong with createOrder()");
        } 
        return i;
    }
    
    // Creates a list with all the orders a customer has
    public ArrayList<Order> findOrdersByCustomer(int customerID) {
        ArrayList<Order> orders = new ArrayList<>();
        String sql = "SELECT * from orders INNER JOIN orderdetails ON orders.order_id = orderdetails.order_id where customer_id= " + customerID + "";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                 int order_id = rs.getInt("order_id");
                 double price = rs.getDouble("price");
                 Date date = rs.getDate("creationDate");
                 int customer_id = rs.getInt("customer_id");
                 int product_id = rs.getInt("product_id");
                 int salesRep_id = rs.getInt("salesRep_id");
                 int delivery_id = rs.getInt("delivery_id");
                 int invoice_id = rs.getInt("invoice_id");
                 int orderStatus = rs.getInt("orderStatus");
                 Order order = new Order(order_id, price, date, customer_id, product_id, salesRep_id, delivery_id, invoice_id, orderStatus);
                 orders.add(order);
            }
        } catch(SQLException x) {
            System.out.println("Customer_id not found!");
        }
        return orders;
    }
    
    public int getOrderIDbyPriceAndCustomerID(double price, int customer_id) {
        int order_id = 0;
        String sql = "SELECT order_id FROM orders WHERE price = " + price + " AND customer_id =" + customer_id + "";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                order_id = rs.getInt("order_id");
            }
        } catch(SQLException x) {
            System.out.println("Order_id not found");
        }
        return order_id;
    }
}
