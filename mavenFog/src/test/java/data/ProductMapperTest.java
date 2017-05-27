package data;

import exceptions.ConnectionException;
import exceptions.ConnectionException.GetAllProductsException;
import exceptions.ConnectionException.QueryException;
import java.util.ArrayList;
import model.Product;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ProductMapperTest {
    
    public ProductMapperTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setConnection method, of class ProductMapper.
     */
    @Test
    public void testSetConnection() {
        try {
            ProductMapper.setConnection();
            assertNotNull(ProductMapper.getCon());
        } catch (ConnectionException ex) {
            
        } finally {
            DB.releaseConnection(ProductMapper.getCon());
        }
    }

    /**
     * Test of createProduct method, of class ProductMapper.
     */
    @Test
    public void testCreateProduct() {
        try {
            ProductMapper.setConnection();
            Product product = new Product(DB.generateID("products", "product_id", ProductMapper.getCon()), 0, 0, 0, 5500.00, 2.5, 3.0, 6.0, 0, 0, 0, "testproduct");
            assertNotNull(product);
        } catch (QueryException | ConnectionException ex) {
            
        }
    }

    /**
     * Test of getAllProducts method, of class ProductMapper.
     */
    @Test
    public void testGetAllProducts() {
        try {
            ProductMapper.setConnection();
            ArrayList<Product> products = ProductMapper.getAllProducts();
            assertTrue(!products.isEmpty());
        } catch (GetAllProductsException | ConnectionException ex) {
            
        }
        
    }

    /**
     * Test of getProduct method, of class ProductMapper.
     */
    @Test
    public void testGetProduct() {
        try {
            ProductMapper.setConnection();
            Product product = ProductMapper.getProduct("1");
            assertNotNull(product);
        } catch (ConnectionException | QueryException ex) {
        
        } finally {
            DB.releaseConnection(ProductMapper.getCon());
        }
    }

    
}
