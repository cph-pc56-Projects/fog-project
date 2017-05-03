/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import data.DB;
import data.UserMapper;
import java.io.IOException;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author trez__000
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws java.sql.SQLException
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException, LoginError {
        try {
            response.setContentType("text/html;charset=UTF-8");

            String email = request.getParameter("email");
            String password = request.getParameter("password");

            UserMapper mapper = new UserMapper();
            request.setAttribute("mapper", mapper); // WHY THIS LINE ???
            String emailDB = mapper.getEmail(email);
            String passwordDB = mapper.getPassword(email);
            
            // if the email is non existent or the password is not found or the typed password doesn't match the user`s password
            if (emailDB == null || passwordDB == null || !password.equals(passwordDB)) {
                throw new LoginError();
            }
            User user = mapper.getUser(2); //here give the method the right ID that we need.
            
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            request.getRequestDispatcher("/support.jsp").forward(request, response);
            
        } catch (LoginError x) {
            request.setAttribute("error", "login");
            
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (SQLException x) {
            System.out.println("Sth wrong with user query");
            System.out.println(x);
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
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LoginError ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LoginError ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Login Servlet that takes user input like email and password, connects to DB and checks if the user exist in the DB, if true, opens a session.";
    }// </editor-fold>

    private static class LoginError extends Exception {

        public LoginError() {
        }
    }

}
