package controller;

import dao.InvoiceDao;
import dto.Invoice;
import dto.InvoiceErr;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
@WebServlet(name = "AddnewInvoiceController", urlPatterns = {"/AddnewInvoiceController"})
public class AddnewInvoiceController extends HttpServlet {

    private static final String SEARCH = "search.jsp";
    private static final String ADDNEWINVOICE = "addNewInvoice.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = SEARCH;
        boolean check = true;
        InvoiceDao objdao = new InvoiceDao();
        ArrayList<Invoice> listinv;
        HttpSession session = request.getSession();
        String invId = request.getParameter("invID").toUpperCase();
        String invDate = request.getParameter("invDate");
        String customer = request.getParameter("customer");
        String userID = (String) session.getAttribute("USERID");
        InvoiceErr invErr = new InvoiceErr();
        if (!invId.matches("I\\d{5}$")) {
            invErr.setInvIdErr("Wrong format (Ixxxxx)");
            check = false;
        } else if (objdao.CheckDuplicate(invId)) {
            invErr.setInvIdErr("Duplicated InvId :" + invId);
            check = false;
        }
        if (!invDate.matches("\\d{4}\\-\\d{2}\\-\\d{2}")) {
            invErr.setInvDate("Wrong formate(yyyy-MM-dd)");
            check = false;
        }
        if (check) {
            Invoice inv = new Invoice(invId, invDate, customer, userID);
            if (objdao.InsertInvoice(inv)) {
                try {
                    listinv = objdao.getInvoiceList(userID);
                    session.setAttribute("LISTINVOICE", listinv);
                    String newDate = invDate;
                    for (Invoice x : listinv) {
                        newDate = formateDate.getDate(x.getInvDate());
                        x.setInvDate(newDate);
                    }
                } catch (SQLException ex) {
                    System.out.println("Error Access DB");
                }
            }
        } else {
            url = ADDNEWINVOICE;
        }
        session.setAttribute("INV_ERROR", invErr);
        request.getRequestDispatcher(url).forward(request, response);

//            if(objdao.InsertInvoice(inv)){
//                try{
//                    listinv = objdao.getInvoiceList(userID);
//                    session.setAttribute("LISTINVOICE", listinv);
//                    request.getRequestDispatcher(url).forward(request, response);
//                } catch(SQLException ex){
//                    System.out.println("Error Access DB");
//                }
//            }else{
//                System.out.println("Add New Fail");
//            }
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
