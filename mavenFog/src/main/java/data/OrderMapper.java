package data;

import exceptions.ConnectionException;
import exceptions.ConnectionException.CreateOrderException;
import exceptions.ConnectionException.DataAccessException;
import exceptions.ConnectionException.QueryException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Order;

public class OrderMapper {

    private final Connection con;
    private final DB db;

    public OrderMapper() throws ConnectionException {
        db = new DB();
        con = db.createConnection();
    }

    public DB getDb() {
        return db;
    }

    public Connection getCon() {
        return con;
    }

    //Creates a new Order in the Database with status pending
    //Throws Query Exception if the query is not executable. 
    //Throws Create Order Exception if the input is not the right data type or the querry is wrong
    public void createOrder(double price, int customer_id, int product_id) throws QueryException, CreateOrderException {
        String sqlOrders = "INSERT into orders (price, creationDate, customer_id, orderStatus)"
                + " VALUES (?,CURDATE(),?, 0)";
        String sqlOrderDetails = "INSERT into orderdetails (order_id, product_id) VALUES (?,?)";
        try {
            PreparedStatement stmt = con.prepareStatement(sqlOrders);
            PreparedStatement stmt2 = con.prepareStatement(sqlOrderDetails);
            try {
                stmt.setDouble(1, price);
                stmt.setInt(2, customer_id);
                stmt.executeUpdate();
                int order_id = getOrderIDbyPriceAndCustomerID(price, customer_id);
                stmt2.setInt(1, order_id);
                stmt2.setInt(2, product_id);
                stmt2.executeUpdate();
            } catch (SQLException e) {
                throw new CreateOrderException();
            } finally {
                if (stmt != null) {
                    stmt.close();
                }
                if (stmt2 != null) {
                    stmt2.close();
                }
            }
        } catch (SQLException e) {
            throw new QueryException();
        }
    }
    
    //Finds the order_id by the price and customer_id
    private int getOrderIDbyPriceAndCustomerID(double price, int customer_id) throws SQLException {
        int order_id = 0;
        String sql = "SELECT order_id FROM orders WHERE price = " + price + " AND customer_id = " + customer_id + "";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            order_id = rs.getInt("order_id");
        }
        rs.close();
        stmt.close();
        if (order_id == 0) {
            throw new SQLException();
        }
        return order_id;
    }//getOrderIDbyPriceAndCustomerID

    // Creates a list with all the orders a customer has
    // Throws Data Access Exception if not possible to execute
    public ArrayList<Order> findOrdersByCustomer(int customerID) throws DataAccessException {
        ArrayList<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders LEFT JOIN orderdetails ON orders.order_id = orderdetails.order_id WHERE customer_id = " + customerID + "";
        int order_id = 0, customer_id = 0, product_id = 0, salesRep_id = 0, delivery_id = 0, invoice_id = 0, orderStatus = 0;
        double price = 0;
        Date date = null;
        Order order = null;
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
                try {
                    while (rs.next()) {
                        order_id = rs.getInt("order_id");
                        price = rs.getDouble("price");
                        date = rs.getDate("creationDate");
                        customer_id = rs.getInt("customer_id");
                        product_id = rs.getInt("product_id");
                        salesRep_id = rs.getInt("salesRep_id");
                        delivery_id = rs.getInt("delivery_id");
                        invoice_id = rs.getInt("invoice_id");
                        orderStatus = rs.getInt("orderStatus");
                        order = new Order(order_id, price, date, customer_id, product_id, salesRep_id, delivery_id, invoice_id, orderStatus);
                        orders.add(order);
                    } 
                } finally {
                    if (rs!=null) {rs.close();}
                    if (stmt!=null) {stmt.close();}
                }

        } catch (SQLException x) {
            throw new DataAccessException();
        } 
        return orders;
    }

    

    public int updateSalesRep(int salesRep_id, int order_id) {
        int i = 0;
        String sql = "UPDATE orderdetails SET salesRep_id = " + salesRep_id + " WHERE order_id = " + order_id + "";
        try {
            PreparedStatement update = con.prepareStatement(sql);
            i = update.executeUpdate();
            System.out.println("updateSalesRep Complete");
        } catch (Exception e) {
            System.out.println("Something wrong with updateSalesRep");
        }
        return i;
    }

    public int updateDeliveryID(int delivery_id, int order_id) {
        int i = 0;
        String sql = "UPDATE orderdetails SET delivery_id = " + delivery_id + " WHERE order_id = " + order_id + "";
        try {
            PreparedStatement update = con.prepareStatement(sql);
            i = update.executeUpdate();
            System.out.println("updateDeliveryID Complete");
        } catch (Exception e) {
            System.out.println("Something wrong with updateDeliveryID");
        }
        return i;
    }

    public int updateInvoiceID(int invoice_id, int order_id) {
        int i = 0;
        String sql = "UPDATE orderdetails SET invoice_id = " + invoice_id + " WHERE order_id = " + order_id + "";
        try {
            PreparedStatement update = con.prepareStatement(sql);
            i = update.executeUpdate();
            System.out.println("updateInvoiceID Complete");
        } catch (Exception e) {
            System.out.println("Something wrong with updateInvoiceID");
        }
        return i;
    }
}
