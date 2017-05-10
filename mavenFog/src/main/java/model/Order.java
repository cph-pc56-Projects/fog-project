package model;

import java.sql.Date;

public class Order {
    private final int orderID, customerID;
    private int productID, salesRepID, deliveryID, invoiceID, orderStatus;
    private double price;
    private Date date;

    public Order(int orderID, double price, Date date, int customerID, int productID, int salesRepID,  int deliveryID, int invoiceID, int orderStatus) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.productID = productID;
        this.salesRepID = salesRepID;
        this.price = price;
        this.deliveryID = deliveryID;
        this.invoiceID = invoiceID;
        this.date = date;
        this.orderStatus = orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
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
        this.invoiceID = invoiveID;
    }

    public int getOrderStatus() {
        return orderStatus;
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
        return invoiceID;
    }

    @Override
    public String toString() {
        return "Order{" + "orderID=" + orderID + ", customerID=" + customerID + ", productID=" + productID + ", salesRepID=" + salesRepID + ", deliveryID=" + deliveryID + ", invoiceID=" + invoiceID + ", orderStatus=" + orderStatus + ", price=" + price + ", date=" + date + '}';
    }

    

    
    
    
    
}
