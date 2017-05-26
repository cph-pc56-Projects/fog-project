package servlets;

import data.DB;
import data.UserMapper;
import model.User;
import exceptions.ConnectionException;
import exceptions.ConnectionException.LoginError;
import java.io.IOException;
import java.io.PrintWriter;
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

            //Create connection to DB
            UserMapper.setConnection();

            //Check if the email and password match IF NOT throws LoginError();
            UserMapper.validateLoginDetails(email, password);
            
            //Creates a new User obj with the input data from JSP
            User user = UserMapper.getUser(email);

            int role = user.getRole();
            
            //Add to the session our new user object
            session.setAttribute("user", (Object) user);
            
            // Send to customer visible page if customer, send to admin if admin
            if (role == 2) {
                session.setAttribute("admin", "superAdmin");
            }
            // Here will redirect to the Orders Servlet, so everything will be loaded on login (see Orders Servlet for more info! comment#34)
            request.getRequestDispatcher("Orders").forward(request, response);
        } catch (LoginError x) {
            x.printStackTrace();
            session.setAttribute("error", "login");
            response.sendRedirect(request.getParameter("from"));
        } catch (ConnectionException.QueryException ex) {
            ex.printStackTrace();
            session.setAttribute("error", "queryException");
            response.sendRedirect(request.getParameter("from"));
        } catch (ConnectionException ex) {
            ex.printStackTrace();
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
