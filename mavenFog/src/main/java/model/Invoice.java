package model;

import java.sql.Date;

public class Invoice {
    private final String invoiceID, orderID, productID, customerID, salesRepID;
    private final Date creationDate;
    private final double totalPrice;

    public Invoice(String invoiceID, String orderID, String productID, String customerID, String salesRepID, Date creationDate, double totalPrice) {
        this.invoiceID = invoiceID;
        this.orderID = orderID;
        this.productID = productID;
        this.customerID = customerID;
        this.salesRepID = salesRepID;
        this.creationDate = creationDate;
        this.totalPrice = totalPrice;
    }

    public String getInvoiceID() {
        return invoiceID;
    }

    public String getOrderID() {
        return orderID;
    }

    public String getProductID() {
        return productID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getSalesRepID() {
        return salesRepID;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    
    
    
    
}
