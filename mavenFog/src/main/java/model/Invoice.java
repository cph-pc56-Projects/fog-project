package model;

import java.sql.Date;

public class Invoice {
    private final int invoiceID, orderID, productID, customerID, salesRepID;
    private final Date creationDate;
    private final double totalPrice;

    public Invoice(int invoiceID, int orderID, int productID, int customerID, int salesRepID, Date creationDate, double totalPrice) {
        this.invoiceID = invoiceID;
        this.orderID = orderID;
        this.productID = productID;
        this.customerID = customerID;
        this.salesRepID = salesRepID;
        this.creationDate = creationDate;
        this.totalPrice = totalPrice;
    }

    public int getInvoiceID() {
        return invoiceID;
    }

    public int getOrderID() {
        return orderID;
    }

    public int getProductID() {
        return productID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public int getSalesRepID() {
        return salesRepID;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    
    
    
    
}
