package data;

import exceptions.ConnectionException;
import exceptions.ConnectionException.CreateInvoiceException;
import exceptions.ConnectionException.GetAllInvoicesException;
import exceptions.ConnectionException.QueryException;
import exceptions.ConnectionException.UpdateOrderDetailsException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Invoice;

public class InvoiceMapper {

    private static Connection con;

    //Creates a connection to DB
    public static void setConnection() throws ConnectionException {
        con = DB.createConnection();
    }

    public static Connection getCon() {
        return con;
    }

    //Creates an invoice in DB
    //Throws ConnectionException if we cant connect to the OrderMapper
    //Throws CreateInvoiceException if we cant execute the query
    public static String createInvoice(double totalPrice, String orderID) throws CreateInvoiceException {
        String sql = "INSERT INTO invoice (invoice_id, creation_date, total_price) VALUES (?, curdate(), ?)";
        String invoiceID = "";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            invoiceID = DB.generateID("invoice", "invoice_id", con);
            stmt.setString(1, invoiceID);
            stmt.setDouble(2, totalPrice);
            stmt.executeUpdate();

            //Updates the invoice_id in orderDetails throws UpdateOrderDetailsException or QueryException
            OrderMapper.setConnection();
            OrderMapper.updateInvoiceID(invoiceID, orderID);
        } catch (SQLException | QueryException e) {
            throw new CreateInvoiceException();
        } catch (UpdateOrderDetailsException | ConnectionException ee) {
            //Delete the invoice if the second part of the try block fails
            deleteInvoice(invoiceID);
            throw new CreateInvoiceException();
        } finally {
            DB.closeStmt(stmt);
            DB.releaseConnection(OrderMapper.getCon());
        }
        return invoiceID;
    }

    //Deletes an invoice input from the Database in case of failure in the createInvoice() method
    private static void deleteInvoice(String invoiceID) {
        String sql = "DELETE FROM invoice WHERE invoice_id = '" + invoiceID + "'";
        String set = "SET SQL_SAFE_UPDATES = 0;";
        String reset = "SET SQL_SAFE_UPDATES = 1;";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
            stmt = con.prepareStatement(set);
            stmt.executeUpdate();
            stmt = con.prepareStatement(reset);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeStmt(stmt);
        }
    }//deleteInvoice

    //Returns an ArrayList with all the invoices in the Database
    //Throws GetAllInvoicesException if the method is not executable or the list is empty
    public static ArrayList<Invoice> getAllInvoice() throws GetAllInvoicesException {
        ArrayList<Invoice> invoices = new ArrayList<>();
        String sql = "SELECT * FROM invoice,orders NATURAL JOIN order_details WHERE invoice.invoice_id = order_details.invoice_id";
        String invoiceID, orderID, productID, customerID, salesRepID;
        double totalPrice;
        Date invoiceDate;
        Invoice invoice;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                invoiceID = rs.getString("invoice_id");
                totalPrice = rs.getDouble("total_price");
                invoiceDate = rs.getDate("creation_date");
                orderID = rs.getString("order_id");
                productID = rs.getString("product_id");
                customerID = rs.getString("customer_id");
                salesRepID = rs.getString("sales_rep_id");
                invoice = new Invoice(invoiceID, orderID, productID, customerID, salesRepID, invoiceDate, totalPrice);
                invoices.add(invoice);
            }
        } catch (SQLException x) {
            throw new GetAllInvoicesException();
        } finally {
            DB.closeRs(rs);
            DB.closeStmt(stmt);
        }
        return invoices;
    }//getAllInvoice

    //Returns an ArrayList with all the invoices in the Database
    //Throws GetAllInvoicesException if the method is not executable or the list is empty
    public static ArrayList<Invoice> getAllInvoiceByCustomer(String customerID) throws GetAllInvoicesException {
        ArrayList<Invoice> invoices = new ArrayList<>();
        String sql = "SELECT * FROM invoice,orders NATURAL JOIN order_details WHERE invoice.invoice_id = order_details.invoice_id AND customer_id = '" + customerID + "'";
        String invoiceID, orderID, productID, salesRepID;
        double totalPrice;
        Date invoiceDate;
        Invoice invoice;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                invoiceID = rs.getString("invoice_id");
                totalPrice = rs.getDouble("total_price");
                invoiceDate = rs.getDate("creation_date");
                orderID = rs.getString("order_id");
                productID = rs.getString("product_id");
                customerID = rs.getString("customer_id");
                salesRepID = rs.getString("sales_rep_id");
                invoice = new Invoice(invoiceID, orderID, productID, customerID, salesRepID, invoiceDate, totalPrice);
                invoices.add(invoice);
            }
        } catch (SQLException x) {
            throw new GetAllInvoicesException();
        } finally {
            DB.closeRs(rs);
            DB.closeStmt(stmt);
        }
        return invoices;
    }//getAllInvoice
}
