package model;

import java.sql.Date;

public class Delivery {
    private final int deliveryID, deliveryStatus, orderID;
    private final Date deliveryDate;
    private final double price;
    private final String moreInfo;

    public Delivery(int deliveryID, int deliveryStatus, int orderID, Date deliveryDate, double price, String moreInfo) {
        this.deliveryID = deliveryID;
        this.deliveryStatus = deliveryStatus;
        this.orderID = orderID;
        this.deliveryDate = deliveryDate;
        this.price = price;
        this.moreInfo = moreInfo;
    }

    public int getDeliveryID() {
        return deliveryID;
    }

    public int getDeliveryStatus() {
        return deliveryStatus;
    }

    public int getOrderID() {
        return orderID;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public double getPrice() {
        return price;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    @Override
    public String toString() {
        return "Delivery{" + "deliveryID=" + deliveryID + ", deliveryStatus=" + deliveryStatus + ", orderID=" + orderID + ", deliveryDate=" + deliveryDate + ", price=" + price + ", moreInfo=" + moreInfo + '}';
    }

    
}
