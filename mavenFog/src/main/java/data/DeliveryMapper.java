package data;

import exceptions.ConnectionException;
import exceptions.ConnectionException.CreateDeliveryException;
import exceptions.ConnectionException.GetAllDeliveryException;
import exceptions.ConnectionException.QueryException;
import exceptions.ConnectionException.UpdateOrderDetailsException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Delivery;

public class DeliveryMapper {

    private static Connection con;

    public static void setConnection() throws ConnectionException {
        con = DB.createConnection(); 
    }


    public static Connection getCon() {
        return con;
    }

    //Creates new delivery in the Database
    //Throws ConnectionException if we cant connect to the OrderMapper
    //Throws CreateDelivery Exception if we cant execute the query
    public static void createDelivery(Date date, int orderID, String moreInfo, double price) throws ConnectionException, CreateDeliveryException {
        String sql = "INSERT into delivery (delivery_date, delivery_status, order_id, more_info, price) VALUES (? , 0, ?, ?, ?)";
        OrderMapper oMapper = new OrderMapper();
        PreparedStatement stmt = null;
        int delivery_id = 0;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setDate(1, date);
            stmt.setInt(2, orderID);
            stmt.setString(3, moreInfo);
            stmt.setDouble(4, price);
            stmt.executeUpdate();

            //Updates the delivery_id in orderDetails throws UpdateOrderDetailsException or QueryException
            delivery_id = getDeliveryID(orderID);
            oMapper.updateDeliveryID(delivery_id, orderID);
        } catch (SQLException e) {
            throw new CreateDeliveryException();
        } catch (UpdateOrderDetailsException | QueryException ee) {
                deleteDelivery(delivery_id);
                throw new CreateDeliveryException();
        } finally {
            DB.closeStmt(stmt);
            DB.releaseConnection(oMapper.getCon());
        }
    }//createDelivery

    //Finds the deliveryID by order_id
    //Throws QueryException if the input is not the right data type or the querry is wrong
    public static int getDeliveryID(int order_id) throws QueryException {
        int deliveryID = 0;
        String sql = "SELECT delivery_id FROM delivery WHERE order_id = " + order_id + "";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            if (rs.next()) {
                deliveryID = rs.getInt("delivery_id");
            }
        } catch (SQLException x) {
            throw new QueryException();
        } finally {
            DB.closeRs(rs);
            DB.closeStmt(stmt);
            if (deliveryID == 0) {
                throw new QueryException();
            }
        }
        return deliveryID;
    }

    //Deletes a delivery input from the Database in case of failure in the createDelivery() method
    private static void deleteDelivery(int delivery_id) {
        String sql = "DELETE FROM delivery WHERE delivery_id = " + delivery_id + "";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeStmt(stmt);
        }
    }//deleteDelivery

    //Returns an ArrayList with all the deliveries in the Database
    //Throws GetAllDeliveries Exception if the method is not executable or the list is empty
    public static ArrayList<Delivery> getAllDelivery() throws GetAllDeliveryException {
        ArrayList<Delivery> deliveries = new ArrayList<>();
        String sql = "SELECT * FROM delivery";
        int deliveryID, deliveryStatus, orderID;
        Date deliveryDate;
        double price;
        String moreInfo;
        Delivery delivery;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                deliveryID = rs.getInt("delivery_id");
                deliveryStatus = rs.getInt("delivery_status");
                orderID = rs.getInt("order_id");
                deliveryDate = rs.getDate("delivery_date");
                price = rs.getDouble("price");
                moreInfo = rs.getString("more_info");
                delivery = new Delivery(deliveryID, deliveryStatus, orderID, deliveryDate, price, moreInfo);
                deliveries.add(delivery);
            }
        } catch (SQLException x) {
            x.printStackTrace();
            throw new GetAllDeliveryException();
        } finally {
            DB.closeRs(rs);
            DB.closeStmt(stmt);
        }
        if (deliveries.isEmpty()) {throw new GetAllDeliveryException();}
        return deliveries;
    }//getAllDelivery
    
    //Updates the Delivery status when the delivery is cancelled or completed
    //Throws QueryException if the input is not the right data type or the querry is wrong
    public static void updateDeliveryStatus(int delivery_status, int delivery_id) throws QueryException {
        String sql = "UPDATE delivery SET delivery_status = " + delivery_status + " WHERE delivery_id = " + delivery_id + "";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new QueryException();
        } finally {
            DB.closeStmt(stmt);
        }
    }//updateDeliveryStatus
}
