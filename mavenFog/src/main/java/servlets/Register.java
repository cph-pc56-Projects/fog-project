package servlets;

import data.DB;
import exceptions.ConnectionException;
import data.UserMapper;
import exceptions.ConnectionException.CreateCustomerException;
import exceptions.ConnectionException.CreateSalesRepException;
import exceptions.ConnectionException.UpdateStatusException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        HttpSession session = request.getSession();
        try {
            int userType = Integer.parseInt((String) request.getParameter("userType"));
            
            //Get the data from register form
            String email, password, fName, lName, phone, adress, zipCode;
            email = request.getParameter("email");
            password = request.getParameter("password");
            fName = request.getParameter("fName");
            lName = request.getParameter("lName");
            phone = request.getParameter("phone");
            adress = request.getParameter("adress");
            zipCode = request.getParameter("zipCode");

            //Create connection to DB
            UserMapper.setConnection();
            
            switch (userType) {
                case 0:
                     //Create new user in the database
                    UserMapper.createCustomer(email, password, fName, lName, phone, adress, zipCode);
                    break;
                case 1:
                    //Create new SalesRep in the DB
                    UserMapper.createSalesRep(email, password, fName, lName, phone, adress, zipCode);
                    break;
                case 3:
                    //Deletes a Sales Rep profile form DB
                    String id = (String) request.getParameter("deleteAccountID");
                    UserMapper.updateUserStatus(0,id);
            }
            
           

            //If successful go back
            response.sendRedirect(request.getParameter("from"));

        } catch (CreateCustomerException ex) {
            ex.printStackTrace();
            session.setAttribute("error", "CreateCustomerException"); //We are sorry, we could not create your profile! Probably cause is that you already have an account!
            response.sendRedirect(request.getParameter("from"));
        } catch (ConnectionException ex) {
            ex.printStackTrace();
            session.setAttribute("error", "ConnectionException");
            response.sendRedirect(request.getParameter("from"));
        } catch (CreateSalesRepException ex) {
            ex.printStackTrace();
            session.setAttribute("error", "CreateSalesRepException");
            response.sendRedirect(request.getParameter("from"));
        } catch (UpdateStatusException ex) {
            ex.printStackTrace();
            session.setAttribute("error", "UpdateStatusException");
            response.sendRedirect(request.getParameter("from"));
        } finally {
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
    }// </editor-fold>

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
            out.println("<h1>Our servers are down at the moment. We are trying to fix this as soon as possible. Please try again later.");
            out.println("</body>");
            out.println("</html>");
        } catch (IOException ex) {
            
        }
    }
}
