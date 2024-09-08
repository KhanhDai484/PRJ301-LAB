<%-- 
    Document   : AddnewInvoice
    Created on : Jun 12, 2024, 12:13:28 AM
    Author     : HP
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="dto.InvoiceErr"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add new Invoice</title>
        <style>
            .box{
                width: 500px;
                height: 40px;
            }
            .box label{
                width: 120px;
                display: inline-block;
            }
            .lebuton{
                margin-left: 150px;
            }
            .box span{
                color: #ff3333;
            }
        </style>
    </head>
    <body>   
        <form action="MainController">
            <h2>
                Add New Invoice information
            </h2>
            <c:set var="x" value="${sessionScope.INV_ERROR}"/>
            <div class="box">
                <label> Invoice ID</label>
                <input type="text" name="invID" required="true"/>
                <span>${x.getInvIdErr()}</span>
            </div>                      
             <div class="box">
                <label> Invoice Date</label>
                <input type="text" name="invDate" required="true"/>
                 <span>${x.getInvDate()}</span>
            </div>     
             <div class="box">
                <label> Customer</label>
                <input type="text" name="customer" required="true"/>
            </div> 
            
             <div class="box center">
                <input type="submit" name="action" value="Save"/>               
               <button><a href="MainController?action=Cancel" target="_blank"> Cancel</a></button>
            </div>     
        </form>
    </body>
</html>
