package servlets;

import data.DB;
import data.DeliveryMapper;
import data.InvoiceMapper;
import data.OrderMapper;
import data.ProductMapper;
import data.UserMapper;
import exceptions.ConnectionException;
import exceptions.ConnectionException.QueryException;
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
import model.Order;
import model.Product;
import model.User;


@WebServlet(name = "FinaliseOrder", urlPatterns = {"/FinaliseOrder"})
public class FinaliseOrder extends HttpServlet {

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
        User user;
        Product product;
        String orderID;
        try  {
            UserMapper.setConnection();
            ProductMapper.setConnection();
            OrderMapper.setConnection();
            orderID = request.getParameter("orderID");
            order = OrderMapper.getOrder(orderID);
            user = UserMapper.getUserByID(order.getCustomerID());
            product = ProductMapper.getProduct(order.getProductID());
            session.setAttribute("userOrder", user);
            session.setAttribute("order", order);
            session.setAttribute("product", product);
            session.setAttribute("popupFinalise", "yes");
            response.sendRedirect("../admin/admin.jsp");
        } catch (ConnectionException ex) {
            ex.printStackTrace();
            session.setAttribute("error", "ConnectionException");
            response.sendRedirect(request.getParameter("from"));
        } catch (QueryException ex) {
            ex.printStackTrace();
            session.setAttribute("error", "QueryException");
            response.sendRedirect(request.getParameter("from"));
        } finally {
            DB.releaseConnection(OrderMapper.getCon());
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
