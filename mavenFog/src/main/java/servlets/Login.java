package servlets;

import data.DB;
import data.UserMapper;
import exceptions.ConnectionException;
import exceptions.ConnectionException.LoginError;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;

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
        UserMapper mapper = null;
        try {
            //Taking the email and password when submit the Log in form       
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            //Create object mapper which opens a connection to DB
            mapper = new UserMapper();

            //Check if we have email and password in the DB and IF NOT throws LoginEroor();
            mapper.getEmail(email);
            mapper.getPassword(email, password);

            int role = mapper.getRole(email);
            //Creates a new User obj with the input data
            User user = new User(email, mapper.getFirstName(email), mapper.getLastName(email), mapper.getAdress(email), mapper.getZipCode(email), mapper.getPhone(email), role, mapper.getAccountID(email));

            //Creates a new session and sends the user object
            HttpSession session = request.getSession();
            session.setAttribute("user", (Object) user);
            // Send to customer visible page if customer, send to admin if admin
            if (role == 2) {
                session.setAttribute("admin", "superAdmin");
            }
            if (request.getParameter("from").equals("/mavenFog/customShed.jsp") || request.getParameter("from").equals("/mavenFog/custompage.jsp") || request.getParameter("from").equals("/mavenFog/customFinalDetails.jsp")) {
                response.sendRedirect("index.jsp");
                return;
            }
            // Here will redirect to the Orders Servlet, so everything will be loaded on login (see Orders Servlet for more info! comment#34)
            request.getRequestDispatcher("Orders").forward(request, response);
        } catch (LoginError x) {
            HttpSession session = request.getSession();
            session.setAttribute("error", "login");
            response.sendRedirect(request.getParameter("from"));
        } catch (ConnectionException.QueryException ex) {
            HttpSession session = request.getSession();
            session.setAttribute("error", "queryException");
            response.sendRedirect(request.getParameter("from"));
        } catch (ConnectionException ex) {
            HttpSession session = request.getSession();
            session.setAttribute("error", "queryException");
            response.sendRedirect("error/DBconnection.jsp");
        } finally {
            //Close the connection to the DB 
            DB.releaseConnection(mapper.getCon());
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
