package data;

import exceptions.ConnectionException;
import exceptions.ConnectionException.GetAllOrdersException;
import exceptions.ConnectionException.QueryException;
import exceptions.ConnectionException.UpdateOrderDetailsException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Order;
import model.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alex
 */
public class OrderMapperTest {
    
    public OrderMapperTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setConnection method, of class OrderMapper.
     */
    @Test
    public void testSetConnection() {
        try {
            OrderMapper.setConnection();
            assertNotNull(OrderMapper.getCon());
        } catch (ConnectionException ex) {
            
        } finally {
            DB.releaseConnection(UserMapper.getCon());
        }
    }

    /**
     * Test of createOrder method, of class OrderMapper.
     */
    @Test
    public void testCreateOrder() {
        try {
            OrderMapper.setConnection();
            UserMapper.setConnection();
            User user = UserMapper.getUser("fog_admin@gmail.com");
            String orderID = OrderMapper.createOrder(user.getAccountID(), "1");
            Order order = OrderMapper.getOrder(orderID);
            OrderMapper.deleteOrder(orderID);
            assertEquals(user.getAccountID(), order.getCustomerID());
        } catch (ConnectionException.CreateOrderException | ConnectionException.QueryException | ConnectionException | ConnectionException.DeleteOrderException ex) {
            
        }
    }

    /**
     * Test of findOrdersByCustomer method, of class OrderMapper.
     */
    @Test
    public void testFindOrdersByCustomer() {
        try {
            OrderMapper.setConnection();
            UserMapper.setConnection();
            User user = UserMapper.getUser("customer@gmail.com");
            ArrayList<Order> orders = OrderMapper.findOrdersByCustomer(user.getAccountID());
            assertNotNull(orders);
        } catch (ConnectionException | QueryException | GetAllOrdersException ex) {
            
        } finally {
            DB.releaseConnection(OrderMapper.getCon());
            DB.releaseConnection(UserMapper.getCon());
            
        }
    }

    /**
     * Test of getAllOrders method, of class OrderMapper.
     */
    @Test
    public void testGetAllOrders() {
        try {
            OrderMapper.setConnection();
            ArrayList<Order> orders = OrderMapper.getAllOrders();
            assertNotNull(orders);
        } catch (ConnectionException | GetAllOrdersException ex) {
            
        } finally {
            DB.releaseConnection(UserMapper.getCon());
        }
    }

    /**
     * Test of updateOrderStatus method, of class OrderMapper.
     */
    @Test
    public void testUpdateOrderStatus() {
        boolean failed = false;
        try {
            OrderMapper.setConnection();
            Order order = OrderMapper.getOrder("1");
            OrderMapper.updateOrderStatus(2, "1");
            OrderMapper.updateOrderStatus(order.getOrderStatus(), "1");
            assertTrue(!failed);
        } catch (UpdateOrderDetailsException | QueryException | ConnectionException ex) {
            assertTrue(failed);
        } finally {
            DB.releaseConnection(OrderMapper.getCon());
        }
    }

    /**
     * Test of updateSalesRep method, of class OrderMapper.
     */
    @Test
    public void testUpdateSalesRep() {
        boolean failed = false;
        try {
            OrderMapper.setConnection();
            Order order = OrderMapper.getOrder("1");
            OrderMapper.updateSalesRep("5", "1");
            OrderMapper.updateSalesRep(order.getSalesRepID(), "1");
            assertTrue(!failed);
        } catch (UpdateOrderDetailsException | QueryException | ConnectionException ex) {
            assertTrue(failed);
        } finally {
            DB.releaseConnection(OrderMapper.getCon());
        }
    }

    /**
     * Test of updateDeliveryID method, of class OrderMapper.
     */
    @Test
    public void testUpdateDeliveryID() {
        boolean failed = false;
        try {
            OrderMapper.setConnection();
            Order order = OrderMapper.getOrder("1");
            OrderMapper.updateDeliveryID("4", "1");
            OrderMapper.updateDeliveryID(order.getDeliveryID(), "1");
            assertTrue(!failed);
        } catch (UpdateOrderDetailsException | QueryException | ConnectionException ex) {
            assertTrue(failed);
        } finally {
            DB.releaseConnection(OrderMapper.getCon());
        }
    }

    /**
     * Test of updateInvoiceID method, of class OrderMapper.
     */
    @Test
    public void testUpdateInvoiceID() {
        boolean failed = false;
        try {
            OrderMapper.setConnection();
            Order order = OrderMapper.getOrder("1");
            OrderMapper.updateInvoiceID("3", "1");
            OrderMapper.updateInvoiceID(order.getInvoiceID(), "1");
            assertTrue(!failed);
        } catch (UpdateOrderDetailsException | QueryException | ConnectionException ex) {
            assertTrue(failed);
        } finally {
            DB.releaseConnection(OrderMapper.getCon());
        }
    }  

}
