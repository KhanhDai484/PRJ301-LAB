package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet (name = "MainController", urlPatterns = {"/MainController"})

public class MainController extends HttpServlet {

    private static final String LOGINSERVLET ="ServletLogin";
    private static final String ADDNEWINVOICE ="addNewInvoice.jsp";
    private static final String ADDNEWINVOICECONTROLLER ="AddnewInvoiceController";
    private static final String DELETEINVOICE ="DeleteInvoice";
    private static final String UPDATEINVOICECONTROLLER ="UpdateInvoice";
    private static final String SEARCHCONTROLLER = "SearchController";
    private static final String LOGOUT ="Logout";
    private static final String CANCELUPDATEINVOICE = "CancelUpdateInvoice";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        String url ="";        
        if ("Login".equals(action)) {
            url = LOGINSERVLET;       
        }else if("Addnew".equals(action)){
            url = ADDNEWINVOICE;
        }else if("Save".equals(action)){
            url = ADDNEWINVOICECONTROLLER;
        }else if("Delete".equals(action)){
            url = DELETEINVOICE;
        }else if("Update".equals(action)){
            url = UPDATEINVOICECONTROLLER;
        }else if("Search".equals(action)){
            url = SEARCHCONTROLLER;
        }else if("Logout".equals(action)){
            url = LOGOUT;
        }else if("Cancel".equals(action)){
            url = CANCELUPDATEINVOICE;
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
