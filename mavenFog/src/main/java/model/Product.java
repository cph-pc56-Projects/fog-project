/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Alex
 */
public class Product {
    private final int product_id, rooftopType, roofAngle, hasShed;
    private final double height, width, length, shedLength, shedWidth;

    public Product(int product_id, int rooftopType, int roofAngle, int hasShed, double height, double width, double length, double shedLength, double shedWidth) {
        this.product_id = product_id;
        this.rooftopType = rooftopType;
        this.roofAngle = roofAngle;
        this.hasShed = hasShed;
        this.height = height;
        this.width = width;
        this.length = length;
        this.shedLength = shedLength;
        this.shedWidth = shedWidth;
    }

    public int getProduct_id() {
        return product_id;
    }

    public int getRooftopType() {
        return rooftopType;
    }

    public int getRoofAngle() {
        return roofAngle;
    }

    public int getHasShed() {
        return hasShed;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public double getLength() {
        return length;
    }

    public double getShedLength() {
        return shedLength;
    }

    public double getShedWidth() {
        return shedWidth;
    }

    @Override
    public String toString() {
        return "Product{" + "product_id=" + product_id + ", rooftopType=" + rooftopType + ", roofAngle=" + roofAngle + ", hasShed=" + hasShed + ", height=" + height + ", width=" + width + ", length=" + length + ", shedLength=" + shedLength + ", shedWidth=" + shedWidth + '}';
    }
    
    
}
