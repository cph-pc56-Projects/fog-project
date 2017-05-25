package data;

import exceptions.ConnectionException;
import exceptions.ConnectionException.CreateOrderException;
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

    private final Connection con;

    public OrderMapper() throws ConnectionException {
        con = DB.createConnection();
    }

    public Connection getCon() {
        return con;
    }

    //Creates a new Order in the Database with status pending
    //Throws Create Order Exception if the input is not the right data type or the querry is wrong
    public void createOrder(double price, int customer_id, int product_id) throws CreateOrderException {
        String sqlOrders = "INSERT into orders (price, creation_date, customer_id, order_status)"
                + " VALUES (?,CURDATE(),?, 0)";
        String sqlOrderDetails = "INSERT into order_details (order_id, product_id) VALUES (?,?)";
        PreparedStatement stmt = null, stmt2 = null;
        int order_id = 0;
        try {
            stmt = con.prepareStatement(sqlOrders);
            stmt2 = con.prepareStatement(sqlOrderDetails);
            stmt.setDouble(1, price);
            stmt.setInt(2, customer_id);
            stmt.executeUpdate();
            order_id = getOrderIDbyPriceAndCustomerID(price, customer_id);
            stmt2.setInt(1, order_id);
            stmt2.setInt(2, product_id);
            stmt2.executeUpdate();
        } catch (SQLException e) {
            deleteOrder(order_id);
            throw new CreateOrderException();
        } finally {
            DB.closeStmt(stmt);
            DB.closeStmt(stmt2);
        }
    }//createOrder

    //Finds the order_id by the price and customer_id, used by the createOrder method
    //Throws SQLException if the query failes
    private int getOrderIDbyPriceAndCustomerID(double price, int customer_id) throws SQLException {
        int order_id = 0;
        String sql = "SELECT order_id FROM orders WHERE price = " + price + " AND customer_id = " + customer_id + "";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            order_id = rs.getInt("order_id");
        }
        try {
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (order_id == 0) {
            throw new SQLException();
        }
        return order_id;
    }//getOrderIDbyPriceAndCustomerID

    //Deletes an order for the createOrder method in case of failure
    private void deleteOrder(int order_id) {
        String sql = "DELETE FROM orders WHERE order_id = " + order_id + "";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not delete order in OrderMapper.createOrder()");
        } finally {
            DB.closeStmt(stmt);
        }
    }

    // Creates a list with all the orders a customer has
    // Throws Data Access Exception if not possible to execute
    public ArrayList<Order> findOrdersByCustomer(int customerID) throws QueryException {
        ArrayList<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders LEFT JOIN order_details ON orders.order_id = order_details.order_id WHERE customer_id = " + customerID + "";
        int order_id, customer_id, product_id, salesRep_id, delivery_id, invoice_id, orderStatus;
        double price;
        Date date;
        Order order;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                order_id = rs.getInt("order_id");
                price = rs.getDouble("price");
                date = rs.getDate("creation_date");
                customer_id = rs.getInt("customer_id");
                product_id = rs.getInt("product_id");
                salesRep_id = rs.getInt("sales_rep_id");
                delivery_id = rs.getInt("delivery_id");
                invoice_id = rs.getInt("invoice_id");
                orderStatus = rs.getInt("order_status");
                order = new Order(order_id, price, date, customer_id, product_id, salesRep_id, delivery_id, invoice_id, orderStatus);
                orders.add(order);
            }
        } catch (SQLException x) {
            x.printStackTrace();
            throw new QueryException();
        } finally {
            DB.closeRs(rs);
            DB.closeStmt(stmt);
        }
        return orders;
    }//findOrdersByCustomer
    
    //Returns an ArrayList with all the orders in the Database
    //Throws GetAllOrders Exception if the method is not executable or the list is empty
    public ArrayList<Order> getAllOrders() throws GetAllOrdersException {
        ArrayList<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders LEFT JOIN order_details ON orders.order_id = order_details.order_id";
        int order_id, customer_id, product_id, salesRep_id, delivery_id, invoice_id, orderStatus;
        double price;
        Date date;
        Order order;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                order_id = rs.getInt("order_id");
                price = rs.getDouble("price");
                date = rs.getDate("creation_date");
                customer_id = rs.getInt("customer_id");
                product_id = rs.getInt("product_id");
                salesRep_id = rs.getInt("sales_rep_id");
                delivery_id = rs.getInt("delivery_id");
                invoice_id = rs.getInt("invoice_id");
                orderStatus = rs.getInt("order_status");
                order = new Order(order_id, price, date, customer_id, product_id, salesRep_id, delivery_id, invoice_id, orderStatus);
                orders.add(order);
            }
        } catch (SQLException x) {
            x.printStackTrace();
            throw new GetAllOrdersException();
        } finally {
            DB.closeRs(rs);
            DB.closeStmt(stmt);
        }
        if (orders.isEmpty()) {throw new GetAllOrdersException();}
        return orders;
    }//getAllOrders

    //Used to automatically update the sales rep who finilized an order
    //Throws Update OrderDetails Exception if the update fails
    public void updateSalesRep(int salesRep_id, int order_id) throws UpdateOrderDetailsException {
        String sql = "UPDATE order_details SET sales_rep_id = " + salesRep_id + " WHERE order_id = " + order_id + "";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new UpdateOrderDetailsException();
        } finally {
            DB.closeStmt(stmt);
        }
    }//updateSalesRep

    //Used to automatically update the delivery ID when delivery is created
    //Throws Update OrderDetails Exception if the update fails
    public void updateDeliveryID(int delivery_id, int order_id) throws UpdateOrderDetailsException {
        String sql = "UPDATE order_details SET delivery_id = " + delivery_id + " WHERE order_id = " + order_id + "";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new UpdateOrderDetailsException();
        } finally {
            DB.closeStmt(stmt);
        }
    }//updateDeliveryID

    //Used to automatically update the invoice ID when invoice is created
    //Throws Update OrderDetails Exception if the update fails
    public void updateInvoiceID(int invoice_id, int order_id) throws UpdateOrderDetailsException {
        String sql = "UPDATE order_details SET invoice_id = " + invoice_id + " WHERE order_id = " + order_id + "";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new UpdateOrderDetailsException();
        } finally {
            DB.closeStmt(stmt);
        }
    }//uppdateInvoiceID
}//OrderMapper
