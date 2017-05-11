/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author Alex
 */
public class Invoice {
    private final int invoice_id, product_id;
    private final Date creationDate;
    private final double price;

    public Invoice(int invoice_id, int product_id, Date creationDate, double price) {
        this.invoice_id = invoice_id;
        this.product_id = product_id;
        this.creationDate = creationDate;
        this.price = price;
    }

    public int getInvoice_id() {
        return invoice_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Invoice{" + "invoice_id=" + invoice_id + ", product_id=" + product_id + ", creationDate=" + creationDate + ", price=" + price + '}';
    }
    
    
}
