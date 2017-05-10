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
public class Delivery {
    private final int delivery_id;
    private int devStatus;
    private final Date deliveryDate;

    public Delivery(int delivery_id, int devStatus, Date deliveryDate) {
        this.delivery_id = delivery_id;
        this.devStatus = devStatus;
        this.deliveryDate = deliveryDate;
    }

    public int getDelivery_id() {
        return delivery_id;
    }

    public int getDevStatus() {
        return devStatus;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    @Override
    public String toString() {
        return "Delivery{" + "delivery_id=" + delivery_id + ", devStatus=" + devStatus + ", deliveryDate=" + deliveryDate + '}';
    }
    
    
}
