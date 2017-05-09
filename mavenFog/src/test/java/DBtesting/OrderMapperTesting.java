/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBtesting;

import data.OrderMapper;
import data.UserMapper;
import java.util.ArrayList;
import model.Order;


/**
 *
 * @author Alex
 */
public class OrderMapperTesting {
    public static void main(String[] args) {
        OrderMapper mapper = new OrderMapper();
        ArrayList<Order> orders = mapper.findOrdersByCustomer(3);
        UserMapper mapper1 = new UserMapper();
        System.out.println(mapper1.getAccountID("customer@gmail.com"));
        
        for(Order order:orders) {
            System.out.println(order);
//            System.out.println(order.getCustomerID());
//            System.out.println(order.getDate());
//            System.out.println(order.getDeliveryID());
//            System.out.println("");
        }
        
    }
}
