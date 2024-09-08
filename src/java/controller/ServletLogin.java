package controller;

import dao.InvoiceDao;
import dao.userDao;
import dto.Invoice;
import dto.User;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.formateDate;

@WebServlet(name = "ServletLogin", urlPatterns = {"/ServletLogin"})
public class ServletLogin extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = "login.jsp";
        String userID = request.getParameter("userID");
        String password = request.getParameter("password");

        HttpSession session = request.getSession();
        User userlogin = null;
        String sum = null;
        ArrayList<Invoice> listinv = null;
        userDao objdao = new userDao();
        InvoiceDao objinvoice = new InvoiceDao();

        try {
            userlogin = objdao.checkLogin(userID, password);
            listinv = objinvoice.getInvoiceList(userID);
            sum = objinvoice.SumInvoices(userID);
        } catch (SQLException ex) {
            System.out.println("Error access database");
        }

        if (userlogin != null) {
            url = "search.jsp";
            session.setAttribute("USERID", userlogin.getUserID());
            session.setAttribute("FULLNAME", userlogin.getFullName());
            session.setAttribute("LISTINVOICE", listinv);
            session.setAttribute("SUM", sum);

            String newDate;
            for (Invoice x : listinv) {
                    newDate = formateDate.getDate(x.getInvDate());
                    x.setInvDate(newDate);
            }
        } else {
            session.setAttribute("mess", "Invalid UserID or Password");
        }

        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
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
