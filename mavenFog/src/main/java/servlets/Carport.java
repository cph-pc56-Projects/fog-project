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
import java.io.PrintWriter;
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
        String productID;
        Product product;
        try {
            //Get the user from the current session
            User user = (User) session.getAttribute("user");
            
            //Create connection to DB
            ProductMapper.setConnection();
            
            //id of the PRODUCT / PREMADE
            productID = (String)session.getAttribute("productID"); //id of the PRODUCT / PREMADE
            
            //if it is a premade carport Creates an object with all the details of premade carport 
            if (productID.equals("1") || productID.equals("2") || productID.equals("3") || productID.equals("4")) {
                product = ProductMapper.getProduct(productID);
            } else {
                product = (Product) session.getAttribute("product"); // Add it to the session in the JSP!!! THE OBJECT
            }
            //Creates order for the particular product that user wants to buy
            
            //Create connection to DB
            OrderMapper.setConnection();
            
            //Creates order for the particular product that user wants to buy
            OrderMapper.createOrder(user.getAccountID(), product.getProductID());
            
            response.sendRedirect("thankyou.jsp");
            
        } catch (QueryException ex) {
            ex.printStackTrace();
            session.setAttribute("error", "QueryException");
            response.sendRedirect(request.getParameter("from"));
        } catch (ConnectionException ex) {
            ex.printStackTrace();
            session.setAttribute("error", "ConnectionException");
            response.sendRedirect(request.getParameter("from"));
        } catch (CreateOrderException ex) {
            ex.printStackTrace();
            session.setAttribute("error", "CreateOrderException");
            response.sendRedirect(request.getParameter("from"));
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
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
             {
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
    }// </editor-fold
    
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
