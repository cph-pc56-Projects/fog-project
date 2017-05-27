package servlets;

import data.DB;
import data.DeliveryMapper;
import data.InvoiceMapper;
import data.OrderMapper;
import exceptions.ConnectionException;
import exceptions.ConnectionException.CreateInvoiceException;
import exceptions.ConnectionException.DeleteOrderException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Invoice;
import model.Order;


@WebServlet(name = "Admin", urlPatterns = {"/Admin"})
public class Admin extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Order order;
        Invoice invoice;
        String deliveryID, orderID, form;
        try  {
            form = request.getParameter("admin");
            orderID = request.getParameter("orderID");
            InvoiceMapper.setConnection();
            OrderMapper.setConnection();
            DeliveryMapper.setConnection();
            order = OrderMapper.getOrder(orderID);
            switch (form) {
                case "deleteOrder":
                    OrderMapper.deleteOrder(order.getOrderID());
                    break;
                    
                case "createInvoice":
                    double totalPrice = (double) session.getAttribute("totalPrice");
                    InvoiceMapper.createInvoice(totalPrice, order.getOrderID());
                    break;
                    
                case "completeDelivery":
                    deliveryID = (String) request.getParameter("deliveryID");
                    DeliveryMapper.updateDeliveryStatus(1, deliveryID);
                    break;
                    
                case "cancelDelivery":
                    deliveryID = (String) request.getParameter("deliveryID");
                    DeliveryMapper.updateDeliveryStatus(2, deliveryID);
                    break;
            }
            
            request.getRequestDispatcher("Orders").forward(request, response);
        } catch (DeleteOrderException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CreateInvoiceException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ConnectionException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ConnectionException.QueryException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DB.releaseConnection(OrderMapper.getCon());
            DB.releaseConnection(InvoiceMapper.getCon());
            DB.releaseConnection(DeliveryMapper.getCon());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (IOException | ServletException ex) {
            printServerFailure(response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (IOException | ServletException ex) {
            printServerFailure(response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    private void printServerFailure(HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        try {
            PrintWriter out = response.getWriter();
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Server Failure</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Our servers can`t handle your request at the moment. We are trying to fix this as soon as possible. Please try again later.");
            out.println("</body>");
            out.println("</html>");
        } catch (IOException ex) {
            
        }
    }

}
