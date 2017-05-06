package model;

public class Order {
    private final int orderID, customerID;
    private int productID, salesRepID, price, deliveryID, invoiveID;
    private String creationDate;

    public Order(int orderID, int customerID, int productID, int salesRepID, int price, int deliveryID, int invoiveID, String creationDate) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.productID = productID;
        this.salesRepID = salesRepID;
        this.price = price;
        this.deliveryID = deliveryID;
        this.invoiveID = invoiveID;
        this.creationDate = creationDate;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setSalesRepID(int salesRepID) {
        this.salesRepID = salesRepID;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDeliveryID(int deliveryID) {
        this.deliveryID = deliveryID;
    }

    public void setInvoiveID(int invoiveID) {
        this.invoiveID = invoiveID;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
    
    public int getOrderID() {
        return orderID;
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

    public int getPrice() {
        return price;
    }

    public int getDeliveryID() {
        return deliveryID;
    }

    public int getInvoiveID() {
        return invoiveID;
    }

    public String getCreationDate() {
        return creationDate;
    }
    
    
    
}
