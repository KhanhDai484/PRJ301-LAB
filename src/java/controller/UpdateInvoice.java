/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.InvoiceDao;
import dto.Invoice;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.formateDate;

/**
 *
 * @author HP
 */
@WebServlet(name = "UpdateInvoice", urlPatterns = {"/UpdateInvoice"})
public class UpdateInvoice extends HttpServlet {

    private static final String SEARCH = "search.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            HttpSession session = request.getSession();
            String url = SEARCH;
            String invId = request.getParameter("invID");
            String invdate = request.getParameter("invDate");
            String customer = request.getParameter("customer");
            String userID = (String) session.getAttribute("USERID");
            String newDate = formateDate.getReverseDate(invdate);
            InvoiceDao objdao = new InvoiceDao();
            Invoice inv = new Invoice(invId, newDate, customer, userID);

            if (objdao.UpdateInvoice(inv)) {
                List<Invoice> listinv = objdao.getInvoiceList(userID);
                for (Invoice x : listinv) {
                    newDate = formateDate.getDate(x.getInvDate());
                    x.setInvDate(newDate);
                }
                session.setAttribute("SUCCESS", "Successfully update");
                session.setAttribute("LISTINVOICE", listinv);
                request.getRequestDispatcher(url).forward(request, response);
            }

        } catch (IOException | SQLException | ServletException e) {
            System.out.println("Error Access Database");
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
