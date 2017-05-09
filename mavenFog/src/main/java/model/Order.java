package model;

import java.sql.Date;

public class Order {
    private final int orderID, customerID;
    private int productID, salesRepID, deliveryID, invoiveID;
    private double price;
    private Date date;

    public Order(int orderID, double price, Date date, int customerID, int productID, int salesRepID,  int deliveryID, int invoiveID) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.productID = productID;
        this.salesRepID = salesRepID;
        this.price = price;
        this.deliveryID = deliveryID;
        this.invoiveID = invoiveID;
        this.date = date;
    }
    
    
    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setSalesRepID(int salesRepID) {
        this.salesRepID = salesRepID;
    }

    public void setDeliveryID(int deliveryID) {
        this.deliveryID = deliveryID;
    }

    public void setInvoiveID(int invoiveID) {
        this.invoiveID = invoiveID;
    }

    public int getOrderID() {
        return orderID;
    }

    public Date getDate() {
        return date;
    }

    public int getCustomerID() {
        return customerID;
    }

    public int getProductID() {
        return productID;
    }

    public int getSalesRepID() {
        return salesRepID;
    }

    public double getPrice() {
        return price;
    }

    public int getDeliveryID() {
        return deliveryID;
    }

    public int getInvoiveID() {
        return invoiveID;
    }

    @Override
    public String toString() {
        return "Order{" + "orderID=" + orderID + ", customerID=" + customerID + ", productID=" + productID + ", salesRepID=" + salesRepID + ", deliveryID=" + deliveryID + ", invoiveID=" + invoiveID + ", price=" + price + ", date=" + date + '}';
    }

    
    
    
    
}
