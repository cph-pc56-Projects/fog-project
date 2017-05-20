package data;

import exceptions.ConnectionException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeliveryMapper {
    private final Connection con;
    private final DB db;
    
    public DeliveryMapper() throws ConnectionException {
        db = new DB();
        con = db.createConnection();
    }

    public DB getDb() {
        return db;
    }

    public Connection getCon() {
        return con;
    }
    
    //Creates new delivery in the Database
    public int createDelivery(Date date, int order_id) throws ConnectionException {
        int i = 0;
        String sql = "INSERT into delivery (deliveryDate, devStatus, order_id) VALUES (" + date + ", 0, " + order_id + ")";
        OrderMapper oMapper = new OrderMapper();
        try {
            PreparedStatement create = con.prepareStatement(sql);
            i = create.executeUpdate();
            System.out.println("Insert Delivery Complete");
            
//Updates the delivery_id in orderDetails
            i = oMapper.updateDeliveryID(getDeliveryID(order_id), order_id);
            System.out.println("Update complete");
            
        } catch (Exception e) {
            System.out.println("Something wrong with createOrder()");
        } finally {
            oMapper.getDb().releaseConnection(oMapper.getCon());
        }
        return i;
    }
    
    public int getDeliveryID (int order_id) {
        int id = 0;
        String sql = "SELECT delivery_id FROM delivery WHERE order_id = " + order_id + "";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                id = rs.getInt("delivery_id");
            }
            System.out.println("getDeliveryID complete");
        } catch(SQLException x) {
            System.out.println("Something wrong with getDeliveryID");
        }
        return id;
    
    }
    
    public void updateDeliveryStatus() {
    
    }
}
