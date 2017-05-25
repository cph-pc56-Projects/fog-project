package servlets;

import data.DB;
import data.UserMapper;
import model.User;
import exceptions.ConnectionException;
import exceptions.ConnectionException.LoginError;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

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
            //Taking the email and password when submit the Log in form       
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            //Create object UserMapper which OPENS a connection to DB
            UserMapper.setConnection();

            //Check if the email and password match IF NOT throws LoginEroor();
            UserMapper.validateLoginDetails(email, password);

            int role = UserMapper.getRole(email);
            //Creates a new User obj with the input data from JSP
            User user = new User(email, UserMapper.getFirstName(email), UserMapper.getLastName(email), UserMapper.getAdress(email), UserMapper.getZipCode(email), UserMapper.getPhone(email), role, UserMapper.getAccountID(email));

            //Add to the session our new user object
            session.setAttribute("user", (Object) user);
            
            // Send to customer visible page if customer, send to admin if admin
            if (role == 2) {
                session.setAttribute("admin", "superAdmin");
            }
            // Here will redirect to the Orders Servlet, so everything will be loaded on login (see Orders Servlet for more info! comment#34)
            request.getRequestDispatcher("Orders").forward(request, response);
        } catch (LoginError x) {
            session.setAttribute("error", "login");
            response.sendRedirect(request.getParameter("from"));
        } catch (ConnectionException.QueryException ex) {
            session.setAttribute("error", "queryException");
            response.sendRedirect(request.getParameter("from"));
        } catch (ConnectionException ex) {
            session.setAttribute("error", "connectionException");
            response.sendRedirect(request.getParameter("from"));
        } finally {
            //Close the connection to the DB 
            DB.releaseConnection(UserMapper.getCon());
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
