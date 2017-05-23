package model;

public class Product {
    private final int productID, rooftopType, hasShed, roofAngle;
    private final double price, innerHeight, width, length, shedLength, shedWidth, rooftopHeight;
    private final String name;

    public Product(int productID, int rooftopType, int hasShed, int roofAngle, double price, double innerHeight, double width, double length, double shedLength, double shedWidth, double rooftopHeight, String name) {
        this.productID = productID;
        this.rooftopType = rooftopType;
        this.hasShed = hasShed;
        this.roofAngle = roofAngle;
        this.price = price;
        this.innerHeight = innerHeight;
        this.width = width;
        this.length = length;
        this.shedLength = shedLength;
        this.shedWidth = shedWidth;
        this.rooftopHeight = rooftopHeight;
        this.name = name;
    }

    public int getProductID() {
        return productID;
    }

    public int getRooftopType() {
        return rooftopType;
    }

    public int getHasShed() {
        return hasShed;
    }

    public int getRoofAngle() {
        return roofAngle;
    }

    public double getPrice() {
        return price;
    }

    public double getInnerHeight() {
        return innerHeight;
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

    public double getRooftopHeight() {
        return rooftopHeight;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Product{" + "productID=" + productID + ", rooftopType=" + rooftopType + ", hasShed=" + hasShed + ", roofAngle=" + roofAngle + ", price=" + price + ", innerHeight=" + innerHeight + ", width=" + width + ", length=" + length + ", shedLength=" + shedLength + ", shedWidth=" + shedWidth + ", rooftop_height=" + rooftopHeight + ", name=" + name + '}';
    }
    
    
}
