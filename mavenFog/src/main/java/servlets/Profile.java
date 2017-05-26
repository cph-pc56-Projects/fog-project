package servlets;

import data.DB;
import data.UserMapper;
import exceptions.ConnectionException;
import exceptions.ConnectionException.QueryException;
import exceptions.ConnectionException.UpdateUserInfoException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;

@WebServlet(name = "Profile", urlPatterns = {"/Profile"})
public class Profile extends HttpServlet {
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
        User user = null;
        try {
            //Get the user from the current session
            user = (User) session.getAttribute("user");

            // Fetching the paramteres from the edit profile form
            String email, password, address, phone, zipCode;
            email = request.getParameter("email");
            password = request.getParameter("password");
            address = request.getParameter("address");
            phone = request.getParameter("phone");
            zipCode = request.getParameter("zipcode");
            String accountId = user.getAccountID();

            //Create connection to DB
            UserMapper.setConnection();
            
            //Update user info
            if (!email.equals("empty")) {
                UserMapper.updateEmail(email, accountId);
            }
            if (!password.equals("empty")) {
                UserMapper.updatePassword(password, accountId);
            }
            if (!address.equals("empty")) {
                UserMapper.updateAdress(address, accountId);
            }
            if (!phone.equals("empty")) {
                UserMapper.updatePhone(phone, accountId);
            }
            if (!zipCode.equals("empty")) {
                UserMapper.updateZipcode(zipCode, accountId);
            }
            //remove old user's information from session
            session.removeAttribute("user");

            //Updating User Object in session               
            user = new User(email, UserMapper.getFirstName(email), UserMapper.getLastName(email), UserMapper.getAdress(email), UserMapper.getZipCode(email), UserMapper.getPhone(email), UserMapper.getRole(email), UserMapper.getAccountID(email));
            session.setAttribute("user", (Object) user);
            
            
            response.sendRedirect("profile/profile.jsp");
        } catch (UpdateUserInfoException ex) {
            ex.printStackTrace();
            session.setAttribute("error", "connectionException");
            response.sendRedirect("profile/profile.jsp");
        } catch (QueryException ex) {
            ex.printStackTrace();
            session.setAttribute("error", "connectionException");
            response.sendRedirect("profile/profile.jsp");
        } catch (ConnectionException ex) {
            ex.printStackTrace();
            session.setAttribute("error", "connectionException");
            response.sendRedirect("profile/profile.jsp");
        } finally {
            DB.releaseConnection(UserMapper.getCon());
        }
    }//servlet

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
