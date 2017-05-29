package servlets;

import data.DB;
import data.DeliveryMapper;
import data.OrderMapper;
import data.ProductMapper;
import model.Product;
import model.User;
import exceptions.ConnectionException;
import exceptions.ConnectionException.CreateDeliveryException;
import exceptions.ConnectionException.CreateOrderException;
import exceptions.ConnectionException.CreateProductException;
import exceptions.ConnectionException.GetAllOrdersException;
import exceptions.ConnectionException.QueryException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Order;

/**
 * Carport Servlet is used for creating the Custom Carport and registering the
 * order in the DB
 */
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
        double price;
        String productID, moreInfo, orderID;
        Product product;
        User user;
        ArrayList<Order> orders;
        try {
            //Get the user from the current session
            user = (User) session.getAttribute("user");

            //Create connection to DB
            ProductMapper.setConnection();
            OrderMapper.setConnection();
            DeliveryMapper.setConnection();

            //Get the ID of the product if premade
            productID = (String) session.getAttribute("productID");

            //if it is a premade carport Creates an object with all the details of premade carport 
            if (productID.equals("1") || productID.equals("2") || productID.equals("3") || productID.equals("4")) {
                product = ProductMapper.getProduct(productID);
                price = Double.parseDouble((String) session.getAttribute("deliveryPrice"));
            } else {
                //If custom carport, get the object from the session
                product = (Product) session.getAttribute("product");
                //Create a new product in DB
                productID = ProductMapper.createProduct(product);
                //Get the price for the Custom Carport Delivery
                price = Double.parseDouble((String) session.getAttribute("deliveryPrice"));
            }

            //Creates order for the particular product that user wants to buy
            orderID = OrderMapper.createOrder(user.getAccountID(), productID);

            //Resets the customer`s orders
            orders = OrderMapper.findOrdersByCustomer(user.getAccountID());
            session.setAttribute("orders", orders);

            //Creates delivery for this order
            moreInfo = (String) session.getAttribute("moreInfo");
            DeliveryMapper.createDelivery(orderID, moreInfo, price);

            //Remove the productID from the session
            session.removeAttribute(productID);

            //Redirect to thankyou page
            response.sendRedirect("thankyou.jsp");

        } catch (ConnectionException ex) {
            ex.printStackTrace();
            session.setAttribute("error", "ConnectionException");
            response.sendRedirect("index.jsp");
        } catch (CreateOrderException ex) {
            ex.printStackTrace();
            session.setAttribute("error", "CreateOrderException");
            response.sendRedirect("index.jsp");
        } catch (QueryException ex) {
            ex.printStackTrace();
            session.setAttribute("error", "QueryException");
            response.sendRedirect("index.jsp");
        } catch (GetAllOrdersException ex) {
            ex.printStackTrace();
            session.setAttribute("error", "GetAllOrdersException");
            response.sendRedirect("index.jsp");
        } catch (CreateProductException ex) {
            ex.printStackTrace();
            session.setAttribute("error", "CreateProductException");
            response.sendRedirect("index.jsp");
        } catch (CreateDeliveryException ex) {
            ex.printStackTrace();
            session.setAttribute("error", "CreateDeliveryException");
            response.sendRedirect("index.jsp");
        } finally {
            DB.releaseConnection(ProductMapper.getCon());
            DB.releaseConnection(OrderMapper.getCon());
            DB.releaseConnection(DeliveryMapper.getCon());
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
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
