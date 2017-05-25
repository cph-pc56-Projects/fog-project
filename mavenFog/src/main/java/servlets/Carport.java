package servlets;

import data.DB;
import data.OrderMapper;
import data.ProductMapper;
import model.Product;
import model.User;
import exceptions.ConnectionException;
import exceptions.ConnectionException.CreateOrderException;
import exceptions.ConnectionException.QueryException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "Carport", urlPatterns = {"/Carport"})
public class Carport extends HttpServlet {

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
        Product product;
        try {
            //Get the user from the current session
            User user = (User) session.getAttribute("user");
            
            //Create connection to DB
            ProductMapper.setConnection();
            
            int productID = 0;
            productID = Integer.parseInt((String)session.getAttribute("productID")); //id of the PRODUCT / PREMADE
            //if it is a premade carport
            if (productID == 1 || productID == 2 || productID == 3 || productID == 4) {
                //Creates an object with all the details of premade carport 
                product = ProductMapper.getProduct(productID);
            } else {
                product = (Product) session.getAttribute("product"); // Add it to the session in the JSP!!! THE OBJECT
            }
            //Creates order for the particular product that user wants to buy
            
            //Create connection to DB
            OrderMapper.setConnection();
            OrderMapper.createOrder(product.getPrice(), user.getAccountID(), product.getProductID());
            
            response.sendRedirect("thankyou.jsp");
            
        } catch (QueryException ex) {
            session.setAttribute("error", "QueryException");
            response.sendRedirect(request.getParameter("from"));
        } catch (ConnectionException ex) {
            session.setAttribute("error", "ConnectionException");
            response.sendRedirect(request.getParameter("from"));
        } catch (CreateOrderException ex) {
            Logger.getLogger(Carport.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DB.releaseConnection(ProductMapper.getCon());
            DB.releaseConnection(OrderMapper.getCon());
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
