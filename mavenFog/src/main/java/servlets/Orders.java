package servlets;

import data.*;
import exceptions.ConnectionException;
import exceptions.ConnectionException.*;
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
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        try {

            ////Create connections to DB
            OrderMapper.setConnection();
            DeliveryMapper.setConnection();
            InvoiceMapper.setConnection();
            UserMapper.setConnection();
            ProductMapper.setConnection();

            //Get the user from the current session and fetch orders for that user(/!\if admin - take all orders)
            User user = (User) session.getAttribute("user");
            if (user.getRole() == 0) {
                ArrayList<Order> orders = OrderMapper.findOrdersByCustomer(user.getAccountID());
                session.setAttribute("orders", (Object) orders);
                response.sendRedirect(request.getParameter("from"));
            } else {
                //fetch all data, so admin can browse everything from the DB
                ArrayList<Order> orders = OrderMapper.getAllOrders();
                ArrayList<Delivery> deliveries = DeliveryMapper.getAllDelivery();
                ArrayList<Invoice> invoices = InvoiceMapper.getAllInvoice();
                ArrayList<User> users = UserMapper.getAllUsers();
                ArrayList<Product> products = ProductMapper.getAllProducts();
                session.setAttribute("orders", (Object) orders);
                session.setAttribute("deliveries", (Object) deliveries);
                session.setAttribute("invoices", (Object) invoices);
                session.setAttribute("users", (Object) users);
                session.setAttribute("products", (Object) products);
                response.sendRedirect("admin/admin.jsp");
            }
        } catch (QueryException ex) {
            session.setAttribute("error", "QueryException");
            response.sendRedirect(request.getParameter("from"));
        } catch (GetAllOrdersException ex) {
            session.setAttribute("error", "GetAllOrdersException");
            response.sendRedirect(request.getParameter("from"));
        } catch (GetAllDeliveryException ex) {
            session.setAttribute("error", "GetAllDeliveryException");
            response.sendRedirect(request.getParameter("from"));
        } catch (GetAllInvoicesException ex) {
            session.setAttribute("error", "GetAllInvoicesException");
            response.sendRedirect(request.getParameter("from"));
        } catch (GetAllUsersException ex) {
            session.setAttribute("error", "GetAllUsersException");
            response.sendRedirect(request.getParameter("from"));
        } catch (GetAllProductsException ex) {
            session.setAttribute("error", "GetAllProductsException");
            response.sendRedirect(request.getParameter("from"));
        } catch (ConnectionException r) {
            session.setAttribute("error", "ConnectionException");
            response.sendRedirect(request.getParameter("from"));
        } finally {
            DB.releaseConnection(OrderMapper.getCon());
            DB.releaseConnection(DeliveryMapper.getCon());
            DB.releaseConnection(InvoiceMapper.getCon());
            DB.releaseConnection(UserMapper.getCon());
            DB.releaseConnection(ProductMapper.getCon());
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
