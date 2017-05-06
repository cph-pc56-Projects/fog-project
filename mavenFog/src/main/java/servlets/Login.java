/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import data.UserMapper;
import java.io.IOException;
import java.io.PrintWriter;
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
 * @author Patrick
 */
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
            throws SQLException, ServletException, IOException, LoginError {
        response.setContentType("text/html;charset=UTF-8");
        try {
            //Taking the email and password when submit the Log in form       
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            //Create object mapper wich opens a connection to DB
            UserMapper mapper = new UserMapper();
            
            //Check if we have email and password in the DB
            String emailDB = mapper.getEmail(email);
            String passwordDB = mapper.getPassword(email);

            // if the email is non existent or the password is not found or the typed password doesn't match the user`s input
            if (emailDB == null || passwordDB == null || !password.equals(passwordDB)) {
                throw new LoginError();
            } else {
                //Creates a new User obj with the input data
                int role = mapper.getRole(emailDB);
                User user = new User(emailDB, mapper.getFirstName(emailDB), mapper.getLastName(emailDB), mapper.getPhone(emailDB), mapper.getAdress(emailDB), mapper.getZipCode(emailDB), mapper.getRole(emailDB));
                
                //Creates a new session and sends the user object
                HttpSession session = request.getSession();
                session.setAttribute("user", (Object)user);
                
                // Send to index if customer, send to admin if admin
                if (role == 0) {
                    response.sendRedirect("index.jsp");
                } else {
                    response.sendRedirect("admin/admin.jsp");
                }

                
                
                
            }
        } catch (LoginError x) {
            request.setAttribute("error", "login");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (SQLException x) {
            System.out.println("Sth wrong with user query");
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
        return "Short description";
    }// </editor-fold>

    private static class LoginError extends Exception {

        public LoginError() {
        }
    }
    
}
