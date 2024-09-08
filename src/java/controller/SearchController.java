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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.formateDate;

/**
 *
 * @author HP
 */
public class SearchController extends HttpServlet {

    private static final String SEARCH = "search.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        String searchValue = request.getParameter("txtsearch");
        String url = SEARCH;
        List<Invoice> listinv;
        String userID = (String) session.getAttribute("USERID");
        String newDate;
        String sum;
        InvoiceDao objinvoice = new InvoiceDao();
        try {
            
            if (searchValue.isEmpty()) {               
                listinv = objinvoice.getInvoiceList(userID);     
                sum = objinvoice.SumInvoices(userID);
            } else {               
                listinv = objinvoice.getInvoiceSearchList(searchValue, userID);
                sum = objinvoice.SumInvoicesBySearch(userID, searchValue);
                
            }            
                for (Invoice x : listinv) {
                    newDate = formateDate.getDate(x.getInvDate());
                    x.setInvDate(newDate);
                }         
            session.setAttribute("SUM", sum);
            session.setAttribute("LISTINVOICE", listinv);
            session.setAttribute("SEARCHVALUE", searchValue);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        request.getRequestDispatcher(url).forward(request, response);
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
