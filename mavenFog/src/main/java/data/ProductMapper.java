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
    private final Connection con;

    public ProductMapper() throws ConnectionException {
        con = DB.createConnection();
    }

    public Connection getCon() {
        return con;
    }
    
    //Creates new Product in the database
    //Throws CreateProductException if the insertion fails
    public void createProduct(Product product) throws CreateProductException {
        String sql = "INSERT INTO products (price, inner_height, width, length, has_shed, rooftop_type, shed_length, shed_width, rooftop_angle, rooftop_height, name) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement stmt = null;
        try {
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
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new CreateProductException();
        } finally {
            DB.closeStmt(stmt);
        }
    }//createProduct
    
    //finds the productID with the information from he product
    //Throws Query Exception if the query is not executable
    public int findProductID(Product product) throws QueryException {
        int productID = 0;
        String sql = "SELECCT product_id FROM products WHERE price = " + product.getPrice() + " AND inner_height = " + product.getInnerHeight() + " AND width = " + product.getWidth()
                + " AND length = " + product.getLength() + " AND has_shed = " + product.getHasShed() + " AND rooftop_type = " + product.getRooftopType()
                + " AND shed_length = " + product.getShedLength() + " AND shed_width = " + product.getShedWidth() + " AND rooftop_angle = " + product.getRoofAngle()
                + " AND rooftop_height = " + product.getRooftopHeight() + " AND name = " + product.getName() + "";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            if (rs.next()) {
                productID = rs.getInt("product_id");
            } else {
                throw new QueryException();
            }

        } catch (SQLException e) {
            throw new QueryException();
        } finally {
            DB.closeRs(rs);
            DB.closeStmt(stmt);
        }
        return productID;
    }//findProductID
    
    //Returns an ArrayList with all the products in the Database
    //Throws GetAllProducts Exception if the method is not executable or the list is empty
    public ArrayList<Product> getAllProducts() throws GetAllProductsException {
        ArrayList<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products";
        int productID, rooftopType, hasShed, roofAngle;
        double price, innerHeight, width, length, shedLength, shedWidth, rooftopHeight;
        String name;
        Product product;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                productID = rs.getInt("product_id");
                rooftopType = rs.getInt("rooftop_type");
                hasShed = rs.getInt("has_shed");
                roofAngle = rs.getInt("roof_angle");
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
}
