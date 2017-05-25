package servlets;

import data.*;
import exceptions.*;
import model.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Orders", urlPatterns = {"/Orders"})
public class Orders extends HttpServlet {

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
        HttpSession session = request.getSession();
        OrderMapper oMapper = null;
        DeliveryMapper dMapper = null;
        InvoiceMapper iMapper = null;
        UserMapper uMapper = null;
        ProductMapper pMapper = null;
        try {
            response.setContentType("text/html;charset=UTF-8");
            //Create objects which OPEN a connection to DB
            oMapper = new OrderMapper();
            dMapper = new DeliveryMapper();
            iMapper = new InvoiceMapper();
            uMapper = new UserMapper();
            pMapper = new ProductMapper();

            User user = (User) session.getAttribute("user");
            if (user.getRole() == 0) {
                ArrayList<Order> orders = oMapper.findOrdersByCustomer(user.getAccountID());
                session.setAttribute("orders", (Object) orders);
                response.sendRedirect(request.getParameter("from"));
            } else {
                ArrayList<Order> orders = oMapper.getAllOrders();
                ArrayList<Delivery> deliveries = dMapper.getAllDelivery();
                ArrayList<Invoice> invoices = iMapper.getAllInvoice();
                ArrayList<User> users = uMapper.getAllUsers();
                ArrayList<Product> products = pMapper.getAllProducts();
                session.setAttribute("orders", (Object) orders);
                session.setAttribute("deliveries", (Object) deliveries);
                session.setAttribute("invoices", (Object) invoices);
                session.setAttribute("users", (Object) users);
                session.setAttribute("products", (Object) products);
                response.sendRedirect("admin/admin.jsp");
            }
        } catch (ConnectionException r) {
            session.setAttribute("error", "connectionException");
            response.sendRedirect(request.getParameter("from"));
        } catch (ConnectionException.QueryException ex) {
            session.setAttribute("error", "queryException");
            response.sendRedirect(request.getParameter("from"));
        } catch (ConnectionException.GetAllOrdersException ex) {
            Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ConnectionException.GetAllDeliveryException ex) {
            Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ConnectionException.GetAllInvoicesException ex) {
            Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ConnectionException.GetAllUsersException ex) {
            Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ConnectionException.GetAllProductsException ex) {
            Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DB.releaseConnection(oMapper.getCon());
            DB.releaseConnection(dMapper.getCon());
            DB.releaseConnection(iMapper.getCon());
            DB.releaseConnection(uMapper.getCon());
            DB.releaseConnection(pMapper.getCon());
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
        processRequest(request, response);
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
        processRequest(request, response);
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

}
