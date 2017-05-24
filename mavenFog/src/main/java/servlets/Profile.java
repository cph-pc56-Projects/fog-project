/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import data.UserMapper;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
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
@WebServlet(name = "Profile", urlPatterns = {"/Profile"})
public class Profile extends HttpServlet {
User user = null;

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
            throws ServletException, IOException, Exception {
        HttpSession session = request.getSession();
        User user = null;
        user = (User)session.getAttribute("user");
        
        response.setContentType("text/html;charset=UTF-8");
        
        // Fetching the paramteres from the edit profile form
        String email, password, address, phone;
        email = request.getParameter("email");
        password = request.getParameter("password");
        address = request.getParameter("address");
        phone = request.getParameter("phone");
        int zipcode = parseInt(request.getParameter("zipcode"));
        int accountId = user.getAccountID();
        
        UserMapper mapper = new UserMapper();
        mapper.updateEmail(email, accountId);
        mapper.updatePassword(password, accountId);
        mapper.updateAdress(address, accountId);
        mapper.updatePhone(phone, accountId);
        mapper.updateZipcode(zipcode, accountId);
        session.removeAttribute("user"); 
        
        //Updating User Object in session               
        user = new User(email, mapper.getFirstName(email), mapper.getLastName(email), mapper.getAdress(email),  mapper.getZipCode(email), mapper.getPhone(email), mapper.getRole(email), mapper.getAccountID(email));
        session.setAttribute("user", (Object)user);
        response.sendRedirect("profile/profile.jsp");
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
    } catch (Exception ex) {
        Logger.getLogger(Profile.class.getName()).log(Level.SEVERE, null, ex);
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
    } catch (Exception ex) {
        Logger.getLogger(Profile.class.getName()).log(Level.SEVERE, null, ex);
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

}
