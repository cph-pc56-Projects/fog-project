package servlets;

import data.*;
import exceptions.ConnectionException;
import exceptions.ConnectionException.*;
import model.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Orders Servlet is used for loading the orders a customer has in the profile page
 * For the admins it creates all the information for the admin page -deliveries, invoices, orders, products and users 
 */
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
        User user;
        ArrayList<Order> orders;
        ArrayList<Delivery> deliveries;
        ArrayList<Invoice> invoices;
        ArrayList<User> users;
        ArrayList<Product> products;
        try {
            //Create connections to DB
            OrderMapper.setConnection();
            DeliveryMapper.setConnection();
            InvoiceMapper.setConnection();
            UserMapper.setConnection();
            ProductMapper.setConnection();

            //Get the user from the current session
            user = (User) session.getAttribute("user");
            
            //Do different for customer and admin
            if (user.getRole() == 0) {
                //create an ArrayList with all the orders a customer has
                orders = OrderMapper.findOrdersByCustomer(user.getAccountID());
                
                //create an ArrayList with all the invoices a customer has
                invoices = InvoiceMapper.getAllInvoiceByCustomer(user.getAccountID());
                
                //Set the objects in the session
                session.setAttribute("orders", (Object) orders);
                session.setAttribute("invoices", (Object) invoices);
                response.sendRedirect(request.getParameter("from"));
            } else {
                //Create ArrayLists with all the information from the DB
                orders = OrderMapper.getAllOrders();
                deliveries = DeliveryMapper.getAllDelivery();
                invoices = InvoiceMapper.getAllInvoice();
                users = UserMapper.getAllUsers();
                products = ProductMapper.getAllProducts();
                
                //Set the ArrayLists into the session
                session.setAttribute("orders", (Object) orders);
                session.setAttribute("deliveries", (Object) deliveries);
                session.setAttribute("invoices", (Object) invoices);
                session.setAttribute("users", (Object) users);
                session.setAttribute("products", (Object) products);
                response.sendRedirect("admin/admin.jsp");
            }
        } catch (GetAllOrdersException ex) {
            ex.printStackTrace();
            session.setAttribute("error", "GetAllOrdersException");
            response.sendRedirect(request.getParameter("from"));
        } catch (GetAllDeliveryException ex) {
            ex.printStackTrace();
            session.setAttribute("error", "GetAllDeliveryException");
            session.removeAttribute("user");
            response.sendRedirect(request.getParameter("from"));
        } catch (GetAllInvoicesException ex) {
            ex.printStackTrace();
            session.setAttribute("error", "GetAllInvoicesException");
            session.removeAttribute("user");
            response.sendRedirect(request.getParameter("from"));
        } catch (GetAllUsersException ex) {
            ex.printStackTrace();
            session.setAttribute("error", "GetAllUsersException");
            session.removeAttribute("user");
            response.sendRedirect(request.getParameter("from"));
        } catch (GetAllProductsException ex) {
            ex.printStackTrace();
            session.setAttribute("error", "GetAllProductsException");
            session.removeAttribute("user");
            response.sendRedirect(request.getParameter("from"));
        } catch (ConnectionException r) {
            r.printStackTrace();
            session.setAttribute("error", "ConnectionException");
            session.removeAttribute("user");
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
        try {
            processRequest(request, response);
        } catch (ServletException | IOException ex) {
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
        } catch (ServletException | IOException ex) {
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
            out.println("<h1>Our servers are down at the moment. We are trying to fix this as soon as possible. Please try again later.");
            out.println("</body>");
            out.println("</html>");
        } catch (IOException ex) {
            
        }
    }
}
