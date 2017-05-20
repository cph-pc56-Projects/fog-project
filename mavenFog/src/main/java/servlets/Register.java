package servlets;

import exceptions.ConnectionException;
import data.UserMapper;
import exceptions.ConnectionException.CreateCustomerException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Register", urlPatterns = {"/Register"})
public class Register extends HttpServlet {    

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
            UserMapper mapper = new UserMapper();
            try {
                //Get the data from register form
                String email, password, fName, lName, phone, adress, zipCode;
                email = request.getParameter("email");
                password = request.getParameter("password");
                fName = request.getParameter("fName");
                lName = request.getParameter("lName");
                phone = request.getParameter("phone");
                adress = request.getParameter("adress");
                zipCode = request.getParameter("zipCode");

                //Create new user in the database
                mapper.createCustomer(email, password, fName, lName, phone, adress, zipCode);
                
                //If successful go to index, otherwise go to error page
                response.sendRedirect("index.jsp");
                

            } catch (CreateCustomerException cue) {
                //Redirect to error page if we can`t create the profile.
                cue.printStackTrace();
                response.sendRedirect("error/failRegister.jsp");
            } finally {
                //Close the connection to the Database
                mapper.getDb().releaseConnection(mapper.getCon());
            }
        } catch (ConnectionException ce) {
            //JSP ERROR PAGE - Failed to connect to the Database!
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
        } catch (Exception ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
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
