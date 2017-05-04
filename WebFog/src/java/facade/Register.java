/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import data.DB;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Register", urlPatterns = {"/Register"})
public class Register extends HttpServlet {

    //define variables comming from user input
    String email, password, fName, lName, phone, adress, zipCode, role, creationDate;
    private Connection conn;

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

        try {
            email = request.getParameter("email");
            password = request.getParameter("password");
            fName = request.getParameter("fName");
            lName = request.getParameter("lName");
            phone = request.getParameter("phone");
            adress = request.getParameter("adress");
            zipCode = request.getParameter("zipCode");
            conn = new DB().getConnection();
            Statement st = conn.createStatement();
            int i = st.executeUpdate("insert into users(email, password, fName, lName, phone, adress, zipCode, role, creationDate) "
                    + "values ('" + email + "','" + password + "','" + fName + "','" + lName + "','" + phone + "','" + adress + "'," + Integer.parseInt(zipCode) + ", 0 , CURDATE())");
            if (i > 0) {
                response.sendRedirect("index.jsp");
            } else {
                response.sendRedirect("error/failRegister.jsp");
            }

        } catch (SQLException e) {
            response.sendRedirect("error/failSQL.jsp");
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
