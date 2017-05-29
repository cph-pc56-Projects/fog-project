package data;

import exceptions.ConnectionException;
import exceptions.ConnectionException.CreateOrderException;
import exceptions.ConnectionException.DeleteOrderException;
import exceptions.ConnectionException.GetAllOrdersException;
import exceptions.ConnectionException.QueryException;
import exceptions.ConnectionException.UpdateOrderDetailsException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Order;

public class OrderMapper {

    private static Connection con;

    //Creates a connection to DB
    public static void setConnection() throws ConnectionException {
        con = DB.createConnection(); 
    }

    public static Connection getCon() {
        return con;
    }

    //Creates a new Order in the Database with status pending
    //Throws Create Order Exception if the input is not the right data type or the querry is wrong
    public static String createOrder(String customerID, String productID) throws CreateOrderException {
        String sqlOrders = "INSERT into orders (order_id, creation_date, customer_id, order_status)"
                + " VALUES (?, CURDATE(),?, 0)";
        String sqlOrderDetails = "INSERT into order_details (order_id, product_id) VALUES (?,?)";
        String orderID = null;
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sqlOrders);
            orderID = DB.generateID("orders", "order_id", con);
            stmt.setString(1, orderID);
            stmt.setString(2, customerID);
            stmt.executeUpdate();
            
            stmt = con.prepareStatement(sqlOrderDetails);
            stmt.setString(1, orderID);
            stmt.setString(2, productID);
            stmt.executeUpdate();
        } catch (SQLException | QueryException e) {
            try {
                //Deletes the input if we fail to finish all queries
                deleteOrder(orderID);
            } catch (DeleteOrderException ex) {
                ex.printStackTrace();
            }
            throw new CreateOrderException();
        } finally {
            DB.closeStmt(stmt);
        }
        return orderID;
    }//createOrder

    //Deletes an order for the createOrder method in case of failure
    public static void deleteOrder(String orderID) throws DeleteOrderException {
        String sql = "DELETE FROM orders WHERE order_id = '" + orderID + "';";
        String orderDetails = "DELETE FROM order_details WHERE order_id = '" + orderID + "';";
        String set = "SET SQL_SAFE_UPDATES = 0;";
        String reset = "SET SQL_SAFE_UPDATES = 1;";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(set);
            stmt.executeUpdate();
            stmt = con.prepareStatement(orderDetails);
            stmt.executeUpdate();
            stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
            stmt = con.prepareStatement(reset);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DeleteOrderException();
        } finally {
            DB.closeStmt(stmt);
        }
    }//deleteOrder

    // Creates a list with all the orders a customer has
    // Throws GetAllOrdersException if not possible to execute
    public static ArrayList<Order> findOrdersByCustomer(String customerID) throws GetAllOrdersException {
        ArrayList<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders LEFT JOIN order_details ON orders.order_id = order_details.order_id WHERE customer_id = '" + customerID + "'";
        int orderStatus;
        String orderID, productID, salesRepID, deliveryID, invoiceID;
        Date date;
        Order order;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                orderID = rs.getString("order_id");
                date = rs.getDate("creation_date");
                customerID = rs.getString("customer_id");
                productID = rs.getString("product_id");
                salesRepID = rs.getString("sales_rep_id");
                deliveryID = rs.getString("delivery_id");
                invoiceID = rs.getString("invoice_id");
                orderStatus = rs.getInt("order_status");
                order = new Order(orderID, date, customerID, productID, salesRepID, deliveryID, invoiceID, orderStatus);
                orders.add(order);
            }
        } catch (SQLException x) {
            throw new GetAllOrdersException();
        } finally {
            DB.closeRs(rs);
            DB.closeStmt(stmt);
        }
        return orders;
    }//findOrdersByCustomer
    
    //Returns an ArrayList with all the orders in the Database
    //Throws GetAllOrders Exception if the method is not executable or the list is empty
    public static ArrayList<Order> getAllOrders() throws GetAllOrdersException {
        ArrayList<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders LEFT JOIN order_details ON orders.order_id = order_details.order_id";
        int orderStatus;
        String orderID, productID, salesRepID, deliveryID, invoiceID, customerID;
        Date date;
        Order order;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                orderID = rs.getString("order_id");
                date = rs.getDate("creation_date");
                customerID = rs.getString("customer_id");
                productID = rs.getString("product_id");
                salesRepID = rs.getString("sales_rep_id");
                deliveryID = rs.getString("delivery_id");
                invoiceID = rs.getString("invoice_id");
                orderStatus = rs.getInt("order_status");
                order = new Order(orderID, date, customerID, productID, salesRepID, deliveryID, invoiceID, orderStatus);
                orders.add(order);
            }
        } catch (SQLException x) {
            throw new GetAllOrdersException();
        } finally {
            DB.closeRs(rs);
            DB.closeStmt(stmt);
        }
        return orders;
    }//getAllOrders
    
    //Returns an Order
    //Throws Query Exception if the method is not executable or the list is empty
    public static Order getOrder(String orderID) throws QueryException {
        String sql = "SELECT * FROM orders NATURAL JOIN order_details WHERE orders.order_id = order_details.order_id AND order_id = '" + orderID + "'";
        int orderStatus;
        String productID, salesRepID, deliveryID, invoiceID, customerID;
        Date date;
        Order order = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                date = rs.getDate("creation_date");
                customerID = rs.getString("customer_id");
                productID = rs.getString("product_id");
                salesRepID = rs.getString("sales_rep_id");
                deliveryID = rs.getString("delivery_id");
                invoiceID = rs.getString("invoice_id");
                orderStatus = rs.getInt("order_status");
                order = new Order(orderID, date, customerID, productID, salesRepID, deliveryID, invoiceID, orderStatus);
            }
        } catch (SQLException x) {
            throw new QueryException();
        } finally {
            DB.closeRs(rs);
            DB.closeStmt(stmt);
        }
        if (order==null) {throw new QueryException();}
        return order;
    }//getOrder
    
    //Used to update the orderStatus to finilized or pending
    //Throws Update OrderDetails Exception if the update fails
    public static void updateOrderStatus(int status, String order_id) throws UpdateOrderDetailsException {
        String sql = "UPDATE orders SET order_status = " + status + " WHERE order_id = '" + order_id + "';";
        String set = "SET SQL_SAFE_UPDATES = 0;";
        String reset = "SET SQL_SAFE_UPDATES = 1;";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(set);
            stmt.executeUpdate();
            stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
            stmt = con.prepareStatement(reset);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UpdateOrderDetailsException();
        } finally {
            DB.closeStmt(stmt);
        }
    }//updateOrderStatus

    //Used to automatically update the sales rep who finilized an order
    //Throws Update OrderDetails Exception if the update fails
    public static void updateSalesRep(String salesRep_id, String order_id) throws UpdateOrderDetailsException {
        String sql = "UPDATE order_details SET sales_rep_id = " + salesRep_id + " WHERE order_id = '" + order_id + "'";
        String set = "SET SQL_SAFE_UPDATES = 0";
        String reset = "SET SQL_SAFE_UPDATES = 1";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(set);
            stmt.executeUpdate();
            stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
            stmt = con.prepareStatement(reset);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UpdateOrderDetailsException();
        } finally {
            DB.closeStmt(stmt);
        }
    }//updateSalesRep

    //Used to automatically update the delivery ID when delivery is created
    //Throws Update OrderDetails Exception if the update fails
    public static void updateDeliveryID(String delivery_id, String order_id) throws UpdateOrderDetailsException {
        String sql = "UPDATE order_details SET delivery_id = " + delivery_id + " WHERE order_id = '" + order_id + "'";
        String set = "SET SQL_SAFE_UPDATES = 0;";
        String reset = "SET SQL_SAFE_UPDATES = 1;";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(set);
            stmt.executeUpdate();
            stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
            stmt = con.prepareStatement(reset);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UpdateOrderDetailsException();
        } finally {
            DB.closeStmt(stmt);
        }
    }//updateDeliveryID

    //Used to automatically update the invoice ID when invoice is created
    //Throws Update OrderDetails Exception if the update fails
    public static void updateInvoiceID(String invoice_id, String order_id) throws UpdateOrderDetailsException {
        String sql = "UPDATE order_details SET invoice_id = " + invoice_id + " WHERE order_id = '" + order_id + "'";
        String set = "SET SQL_SAFE_UPDATES = 0;";
        String reset = "SET SQL_SAFE_UPDATES = 1;";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(set);
            stmt.executeUpdate();
            stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
            stmt = con.prepareStatement(reset);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UpdateOrderDetailsException();
        } finally {
            DB.closeStmt(stmt);
        }
    }//uppdateInvoiceID
}//OrderMapper
