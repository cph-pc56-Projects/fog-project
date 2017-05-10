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
        mapper.createOrder(6.8, 1);
        ArrayList<Order> orders = mapper.findOrdersByCustomer(1);
                
        for(Order order:orders) {
            System.out.println(order);

        }

        
        
        
    }
}
