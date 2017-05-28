package data;

import exceptions.ConnectionException;
import exceptions.ConnectionException.CreateProductException;
import exceptions.ConnectionException.GetAllProductsException;
import exceptions.ConnectionException.QueryException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Product;

public class ProductMapper {
    
    private static Connection con;

    //Creates a connection to DB
    public static void setConnection() throws ConnectionException {
        con = DB.createConnection(); 
    }


    public static Connection getCon() {
        return con;
    }
    
    //Creates new Product in the database
    //Throws CreateProductException if the insertion fails
    public static String createProduct(Product product) throws CreateProductException {
        String sql = "INSERT INTO products (price, inner_height, width, length, has_shed, rooftop_type, shed_length, shed_width, rooftop_angle, rooftop_height, name, product_id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        String productID = "";
        PreparedStatement stmt = null;
        try {
            productID = DB.generateID("products", "product_id", con);
            stmt = con.prepareStatement(sql);
            stmt.setDouble(1, product.getPrice());
            stmt.setDouble(2, product.getInnerHeight());
            stmt.setDouble(3, product.getWidth());
            stmt.setDouble(4, product.getLength());
            stmt.setInt(5, product.getHasShed());
            stmt.setInt(6, product.getRooftopType());
            stmt.setDouble(7, product.getShedLength());
            stmt.setDouble(8, product.getShedWidth());
            stmt.setInt(9, product.getRoofAngle());
            stmt.setDouble(10, product.getRooftopHeight());
            stmt.setString(11, product.getName());
            stmt.setString(12, productID);
            stmt.executeUpdate();
        } catch (SQLException | QueryException e) {
            throw new CreateProductException();
        } finally {
            DB.closeStmt(stmt);
        }
        return productID;
    }//createProduct
    
    //Returns an ArrayList with all the products in the Database
    //Throws GetAllProducts Exception if the method is not executable or the list is empty
    public static ArrayList<Product> getAllProducts() throws GetAllProductsException {
        ArrayList<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products";
        String productID, name;
        int rooftopType, hasShed, roofAngle;
        double price, innerHeight, width, length, shedLength, shedWidth, rooftopHeight;
        Product product;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                productID = rs.getString("product_id");
                rooftopType = rs.getInt("rooftop_type");
                hasShed = rs.getInt("has_shed");
                roofAngle = rs.getInt("rooftop_angle");
                price = rs.getDouble("price");
                innerHeight = rs.getDouble("inner_height");
                width = rs.getDouble("width");
                length = rs.getDouble("length");
                shedLength = rs.getDouble("shed_length");
                shedWidth = rs.getDouble("shed_width");
                rooftopHeight = rs.getDouble("rooftop_height");
                name = rs.getString("name");
                product = new Product(productID, rooftopType, hasShed, roofAngle, price, innerHeight, width, length, shedLength, shedWidth, rooftopHeight, name);
                products.add(product);
            }
        } catch (SQLException x) {
            x.printStackTrace();
            throw new GetAllProductsException();
        } finally {
            DB.closeRs(rs);
            DB.closeStmt(stmt);
        }
        if (products.isEmpty()) {throw new GetAllProductsException();}
        return products;
    }//getAllProducts
    
    //Returns an ArrayList with all the products in the Database
    //Throws GetAllProducts Exception if the method is not executable or the list is empty
    public static Product getProduct(String productID) throws QueryException {
        Product product = null;
        String sql = "SELECT * FROM products WHERE product_id = '" + productID + "'";
        String name;
        int rooftopType, hasShed, roofAngle;
        double price, innerHeight, width, length, shedLength, shedWidth, rooftopHeight;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                productID = rs.getString("product_id");
                rooftopType = rs.getInt("rooftop_type");
                hasShed = rs.getInt("has_shed");
                roofAngle = rs.getInt("rooftop_angle");
                price = rs.getDouble("price");
                innerHeight = rs.getDouble("inner_height");
                width = rs.getDouble("width");
                length = rs.getDouble("length");
                shedLength = rs.getDouble("shed_length");
                shedWidth = rs.getDouble("shed_width");
                rooftopHeight = rs.getDouble("rooftop_height");
                name = rs.getString("name");
                product = new Product(productID, rooftopType, hasShed, roofAngle, price, innerHeight, width, length, shedLength, shedWidth, rooftopHeight, name);
            }
        } catch (SQLException x) {
            throw new QueryException();
        } finally {
            DB.closeRs(rs);
            DB.closeStmt(stmt);
        }
        return product;
    }//getAllProducts
}
