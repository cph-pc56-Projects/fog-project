package data;

import exceptions.ConnectionException;
import exceptions.ConnectionException.CreateInvoiceException;
import exceptions.ConnectionException.QueryException;
import exceptions.ConnectionException.UpdateOrderDetailsException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InvoiceMapper {
    private final Connection con;

    public InvoiceMapper() throws ConnectionException {
        con = DB.createConnection();
    }


    public Connection getCon() {
        return con;
    }
    
    public void createInvoice(int productID, int customerID, int salesRepID, double totalPrice, int orderID) throws CreateInvoiceException {
        String sql = "INSERT INTO invoice (product_id, customer_id, sales_rep_id, creation_date, total_price) VALUES (? , ?, ?, curdate(), ?)";
        PreparedStatement stmt = null;
        int invoiceID = 0;
        OrderMapper oMapper = null;
        try {
            oMapper = new OrderMapper();
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, productID);
            stmt.setInt(2, customerID);
            stmt.setInt(3, salesRepID);
            stmt.setDouble(4, totalPrice);
            stmt.executeUpdate();

            //Updates the invoice_id in orderDetails throws UpdateOrderDetailsException or QueryException
            invoiceID = getInvoiceID(orderID);
            oMapper.updateInvoiceID(invoiceID, orderID);
        } catch (SQLException e) {
            throw new CreateInvoiceException();
        } catch (UpdateOrderDetailsException | QueryException ee) {
                deleteInvoice(invoiceID);
                throw new CreateInvoiceException();
        } finally {
            DB.closeStmt(stmt);
            DB.releaseConnection(oMapper.getCon());
        }
    }

    //Finds the InvoiceID by order_id
    //Throws QueryException if the input is not the right data type or the querry is wrong
    public int getInvoiceID(int order_id) throws QueryException {
        int invoiceID = 0;
        String sql = "SELECT invoice_id FROM invoice WHERE order_id = " + order_id + "";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            if (rs.next()) {
                invoiceID = rs.getInt("invoice_id");
            }
        } catch (SQLException x) {
            throw new QueryException();
        } finally {
            DB.closeRs(rs);
            DB.closeStmt(stmt);
            if (invoiceID == 0) {
                throw new QueryException();
            }
        }
        return invoiceID;
    }//getInvoiceID
    
    //Deletes an invoice input from the Database in case of failure in the createInvoice() method
    private void deleteInvoice(int invoiceID) {
        String sql = "DELETE FROM invoice WHERE invoice_id = " + invoiceID + "";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeStmt(stmt);
        }
    }//deleteInvoice
}
